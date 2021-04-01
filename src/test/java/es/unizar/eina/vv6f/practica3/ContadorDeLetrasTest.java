package es.unizar.eina.vv6f.practica3;

import org.junit.jupiter.api.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ContadorDeLetrasTest {

    private final  int POS_ENYE = 14;

    @Test //Test numero 1 para comprobar que se lanza la excepción NullPointerException al leer de un fichero cuya referencia sea null
    public void comprobar_Fichero_Nulo(){
        ContadorDeLetras contador = new ContadorDeLetras(null);
        assertThrows(NullPointerException.class, contador::frecuencias);
    }

    @Test //Test numero 2 para comprobar que se lanza la excepción FileNotFoundExcepction al leer de un fichero que no existe
    public void comprobar_Fichero_Noleible_o_Inexistente(){
        ContadorDeLetras contador = new ContadorDeLetras(new File("notexistingfile.txt"));
        assertThrows(FileNotFoundException.class,contador::frecuencias);
    }

    @Test //Test numero 3 para la correcta lectura de los carácteres del fichero Hamlet
    public void comprobar_Fichero_Hamlet() throws FileNotFoundException {
        ContadorDeLetras contador = new ContadorDeLetras(new File("src/main/res/hamlet.txt"));
        int[] vectoraComparar = leerFichero(new File("src/test/res/salida-hamlet.txt"));
        assertArrayEquals(contador.frecuencias(),vectoraComparar);

    }

    @Test   //Test numero 4 para comprobar la correcta lectura de los carácteres del fichero Quijote
    public void comprobar_Fichero_Quijote() throws  FileNotFoundException{
        ContadorDeLetras contador = new ContadorDeLetras(new File("src/main/res/quijote.txt"));
        int[] vectoraComparar = leerFichero(new File("src/test/res/salida-quijote.txt"));
        assertArrayEquals(contador.frecuencias(),vectoraComparar);
    }

    @Test   //Test numero 5 para comprobar la correcta lectura de los carácteres del fichero Regenta
    public void comprobar_Fichero_Regenta() throws  FileNotFoundException{
        ContadorDeLetras contador = new ContadorDeLetras(new File("src/main/res/regenta.txt"));
        int[] vectoraComparar = leerFichero(new File("src/test/res/salida-regenta.txt"));
        assertArrayEquals(contador.frecuencias(),vectoraComparar);
    }

    @Test  //Test numero 6 para comprobar la correcta lectura de las letras voladas ª y º
    public void comprobar_letras_voladas() throws  FileNotFoundException{
        ContadorDeLetras contador = new ContadorDeLetras(new File("src/main/res/letrasVoladas.txt"));
        int[] vectoraComparar = new int[27];
        vectoraComparar['o' - 'a'] = 1;
        vectoraComparar[0] = 1;
        assertArrayEquals(contador.frecuencias(),vectoraComparar);
    }

    /**
     *
     * @param f fichero a leer
     * @return vector que ha contado las ocurrencias de cada caracter que aparecen en los ficheros de salida dados por los profesores y
     * que además los ha guardado en la componente correspondiente del vector.
     * @throws FileNotFoundException
     */
    private int[] leerFichero(File f) throws FileNotFoundException{
        Scanner scanner = new Scanner(f);
        int[] aparacionesFichero = new int[27];
        //nos saltamos la primera linea
        scanner.nextLine();
        for(int i = 0; i < 26; i++) {
            scanner.next();
            if(i == POS_ENYE) {
                aparacionesFichero[26] = scanner.nextInt();
                scanner.next();
            }
            aparacionesFichero[i] = scanner.nextInt();
        }
        return  aparacionesFichero;
    }
}