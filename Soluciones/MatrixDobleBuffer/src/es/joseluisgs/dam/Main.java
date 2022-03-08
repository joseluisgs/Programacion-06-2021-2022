package es.joseluisgs.dam;

import es.joseluisgs.dam.controllers.TheMatrixController;
import es.joseluisgs.dam.utils.Inputs;

import java.util.Map;

/**
 * Enfoque del problema de Matrix usando Modelo-Vista-Controlador.
 * Modelo: Clases de objetos que representan el estado del problema.
 * Vista: Clases de objetos que representan la interfaz de usuario e interactúan con el usuario.
 * Controlador: Clases de objetos que interactúan con el modelo y la vista. Lógica de control.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenid@ a Matrix!\n");
        // Leemos la dimension de la matriz

        var config = initConfig();
        // var dimension = 5;
        System.out.println();
        System.out.println("Iniciando The Matrix con dimension: " + config.get("dimension")
                + "x" + config.get("dimension") + " y tiempo " + config.get("time") + "s");
        // Controlador de la lógica del programa
        var theMatrix = TheMatrixController.getInstance(config);
        // iniciamos los elementos del sistema
        theMatrix.init();
        // imprimimos la matriz...
        // theMatrix.print();
        // iniciamos la simulacion
        theMatrix.simulate();
        // informe de resultados
        theMatrix.report();
    }

    /**
     * Inicializamos la configuración del programa.
     *
     * @return dimension de la matriz
     */
    private static Map<String, Integer> initConfig() {
        return Map.of("dimension", readDimension(), "time", readTime());
    }

    // Leemos los valores de la matriz interactuando con el usuario
    // Opciones de filtro con valores posibles. Expresiones regulares.
    // https://www.vogella.com/tutorials/JavaRegularExpressions/article.html

    private static int readDimension() {
        String input;
        // Expresion regular para validar el formato de la entrada
        // caracteres que representa un digito entre 5 y 9, y dos caracteres que son 1 y 0, es decir 10.
        var regex = "[5-9]|10";
        do {
            input = Inputs.getString("Introduce la dimension de la matriz [5-10]: ");
            if (!input.matches(regex)) {
                System.out.println("Error: La dimension debe estar entre 5 y 10\n");
            }
        } while (!input.matches(regex));
        return Integer.parseInt(input);
    }

    private static int readTime() {
        String input;
        // Expresion regular para validar el formato de la entrada
        // carcter que va desde 2 hasta 9, siguiente caracter que es un digito entre 0 y 9,
        // o tres caracteres que son 1 0 0, es decir 100.
        var regex = "[2-9][0-9]|100";
        do {
            input = Inputs.getString("Introduce el tiempo de ejecución en segundos [20-100]: ");
            if (!input.matches(regex)) {
                System.out.println("Error: El tiempo de ejecución debe estar entre 20 y 100\n");
            }
        } while (!input.matches(regex));
        return Integer.parseInt(input);
    }
}
