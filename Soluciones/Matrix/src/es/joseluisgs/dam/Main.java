package es.joseluisgs.dam;

import es.joseluisgs.dam.controllers.TheMatrixController;
import es.joseluisgs.dam.utils.Inputs;

import java.util.List;

/**
 * Enfoque del problema de Matrix usando Modelo-Vista-Controlador.
 * Modelo: Clases de objetos que representan el estado del problema.
 * Vista: Clases de objetos que representan la interfaz de usuario e interactúan con el usuario.
 * Controlador: Clases de objetos que interactúan con el modelo y la vista. Lógica de control.
 */
public class Main {

    public static void main(String[] args) {
        System.out.printf("Bienvenid@ a Matrix!\n");
        // Leemos la dimension de la matriz

        var dimension = initConfig();
        // var dimension = 5;
        System.out.printf("Iniciando la matriz de dimension %d\n", dimension);
        // Controlador de la lógica del programa
        var theMatrix = TheMatrixController.getInstance(dimension);
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
     * Este método no es correcto, porque implica que si hay que detectar muchos valores 
     * de entrada...
     *
     * @return dimension de la matriz
     */
    private static int initConfig() {
        // Leemos los valores de la matriz interactuando con el usuario
        // Opciones de filtro con valores posibles. Así me ahorro bastante código
        var options = List.of("5", "6", "7", "8", "9", "10");
        String dimension;
        do {
            dimension = Inputs.getString("Introduce la dimension de la matriz [5-10]: ");
            if (!options.contains(dimension)) {
                System.out.println("Error: La dimension debe estar entre 5 y 10\n");
            }
        } while (dimension.isEmpty() || !options.contains(dimension));
        return Integer.parseInt(dimension);
    }
}
