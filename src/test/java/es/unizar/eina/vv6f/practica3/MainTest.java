package es.unizar.eina.vv6f.practica3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static InputStream entradaSys;
    private static PrintStream salidaSys;
    private File ficheroTempo;

    @BeforeAll  //guardamos la entrada y la salida estándar
    public static void guardar_salida_y_entrada_estandar(){
        entradaSys = System.in;
        salidaSys = System.out;
    }

    @Test
    //Test numero 1 para comprobar que se lanza la excepción NullPointerException al leer de un fichero cuya referencia sea null
    public void comprobar_Fichero_Nulo(){
        ContadorDeLetras contador = new ContadorDeLetras(null);
        assertThrows(NullPointerException.class, contador::frecuencias);
    }



    @AfterEach
    public void eliminar_fichero_temporal() throws  FileNotFoundException{
        //ficheroTempo.delete();
    }

    @Test
    public void comprobar_frecuencias_quijote() throws IOException {

        ByteArrayInputStream byteArrayQuijote = new ByteArrayInputStream("src/main/res/quijote.txt".getBytes());
        System.setIn(byteArrayQuijote);

        ficheroTempo = new File("src/main/res/salida_Artificial.txt");
        PrintStream salidaArtificial = new PrintStream(ficheroTempo);
        System.setOut(salidaArtificial);

        Main.main(null);
        //Array de bytes con la salida estandar de nuestra main
        File s = ajusta_fichero(ficheroTempo);
        byte[] SalidaBytes = Files.readAllBytes(s.toPath());
        //Array de bytes con la supuesta salida estandar del fichero de los profesores
        byte[] EntradaBytes = Files.readAllBytes(Paths.get("src/test/res/salida-quijote.txt"));

        assertArrayEquals(EntradaBytes,SalidaBytes);

    }

   /* @BeforeEach
    public void redireccion_fichero_temporal() throws FileNotFoundException {
        ficheroTempo = new File("src/main/res/salida_Artificial.txt");
        PrintStream salidaArtificial = new PrintStream(ficheroTempo);
        System.setOut(salidaArtificial);
    }*/

    @AfterAll //restauramos entrada y salida estándar
    public static void restaurar_entrada_y_salida_estandar(){
        System.setIn(entradaSys);
        System.setOut(salidaSys);
    }

    private File ajusta_fichero(File f) throws IOException {

        File modified = new File("src/main/res/salidaBuena.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line;
        int i=0;
        while ((line = br.readLine()) != null) {

           if(i==0){
               for(int iter=0; iter< line.length();iter++){
                   if(line.charAt(iter) == 'A'){
                       String s2 = line.substring(iter,line.length());
                       sb.append(line.substring(0,iter-1));
                       sb.append(System.lineSeparator());
                       sb.append(s2);
                       sb.append(System.lineSeparator());
                       break;
                   }
               }
           }else{
               sb.append(line); sb.append(System.lineSeparator());
           }
            i++;
        }
        fr.close();
        modified.delete();
        sb.append(System.lineSeparator());
        OutputStream os = new FileOutputStream(modified,true);
        os.write(String.valueOf(sb).getBytes(), 0, sb.length());
        os.close();
        return modified;

    }

}