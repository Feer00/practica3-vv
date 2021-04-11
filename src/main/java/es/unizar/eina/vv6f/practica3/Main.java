package es.unizar.eina.vv6f.practica3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Programa Java que, al iniciar su ejecución, solicita al usuario el nombre de un fichero de texto.
 * A continuación, si el fichero existe y se puede leer, muestra en la salida estándar una lista de
 * las letras del alfabeto español y el número de veces que dicha letra aparece en el fichero
 * introducido. En caso contrario, escribe en la salida estándar un mensaje de error de la forma
 * «El fichero 'f' no existe.», donde 'f' es el nombre de fichero introducido por el usuario.
 * 
 * No se distingue entre mayúsculas y minúsculas. La letra Ñ es una letra en español. El resto de
 * apariciones de letras voladas y caracteres con diacríticos (acentos agudos, graves, diéresis y
 * cedillas), se consideran como ocurrencias de la letra correspondiente sin diacríticos.
 */
public class Main {

    private static final String FORMATO_SALIDA_FRECUENCIAS = "%c: %7d%n";

    /**
     * Método que, al iniciar su ejecución, solicita al usuario el nombre de un fichero de texto.
     * A continuación, si el fichero existe y se puede leer, muestra en la salida estándar una lista
     * de las letras del alfabeto español y el número de veces que dicha letra aparece en el fichero
     * introducido. En caso contrario, escribe en la salida estándar un mensaje de error de la forma
     * «El fichero 'f' no existe.», donde 'f' es el nombre de fichero introducido por el usuario.
     *
     * No se distingue entre mayúsculas y minúsculas. La letra Ñ es una letra en español. El resto
     * de apariciones de letras voladas y caracteres con diacríticos (acentos agudos, graves,
     * diéresis y cedillas), se consideran como ocurrencias de la letra correspondiente sin
     * diacríticos.
     *
     * @param args
     *            no utilizado.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre de un fichero de texto: ");
        String nomFich = scanner.next();
        ContadorDeLetras contador = new ContadorDeLetras(new File(nomFich));
        int[] vecFrecuencias = new int[27];
        vecFrecuencias = contador.frecuencias();
        for (int i = 0; i< 14; i++){
            System.out.format(FORMATO_SALIDA_FRECUENCIAS,'A'+ i,vecFrecuencias[i]);
        }
        System.out.format(FORMATO_SALIDA_FRECUENCIAS,'Ñ',vecFrecuencias[26]);
        for (int i = 14; i< vecFrecuencias.length-1; i++){
            System.out.format(FORMATO_SALIDA_FRECUENCIAS,'A'+ i,vecFrecuencias[i]);
        }
        scanner.close();
    }
}
