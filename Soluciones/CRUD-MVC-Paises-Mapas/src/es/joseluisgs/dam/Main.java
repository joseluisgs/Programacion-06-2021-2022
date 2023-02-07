package es.joseluisgs.dam;

import es.joseluisgs.dam.views.PaisView;

/**
 * Ejemplo de Patrón MVC para CRUD de países.
 * Siguiendo los diagramas de Secuencias en Clase
 * Modelo: Pais, gestionador por PaisRepository
 * Vista: La propia consola: comunicacion con el usuario
 * Controlador: PaisController, controla, cómo y de qué manera el modelo, repositorio y la vista interactúan
 * Como vista que soy, soy lo último y gestiono las excepciones con Try/Catch
 */
public class Main {
    public static void main(String[] args) {
        PaisView view = PaisView.getInstance();
        view.menu();

    }
}
