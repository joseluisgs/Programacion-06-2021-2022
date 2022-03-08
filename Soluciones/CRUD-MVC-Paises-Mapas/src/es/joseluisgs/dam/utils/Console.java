package es.joseluisgs.dam.utils;

import java.util.Scanner;

/**
 * Clase que permite leer datos desde la consola.
 */
public class Console {
    public static String getString(String message) {
        System.out.println(message);
        return new Scanner(System.in).nextLine().trim();
    }
}
