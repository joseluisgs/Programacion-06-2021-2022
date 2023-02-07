package es.joseluisgs.dam.utils;

import java.util.Scanner;

/**
 * Clase que permite leer datos de entrada.
 */
public class Inputs {
    /**
     * Lee una cadena de caracteres.
     *
     * @param message Mensaje a mostrar en el terminal.
     * @return Cadena de caracteres leída desde el terminal
     */
    public static String getString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }
}
