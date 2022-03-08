package es.joseluisgs.dam.factories;

import es.joseluisgs.dam.models.Localizacion;
import es.joseluisgs.dam.models.Smith;
import es.joseluisgs.dam.utils.Probabilidad;

import java.util.List;

/**
 * Factoría de Smiths.
 */
public class SmithFactory {

    /**
     * Crea un Smith aleatorio.
     *
     * @return Smith aleatorio.
     */
    public static Smith createRandom() {
        var ciudad = List.of("Madrid", "New York", "Pekin", "Leganés", "Londres", "Paris");
        var localizacion = new Localizacion(
                (int) Probabilidad.getRandom(0, 100),
                (int) Probabilidad.getRandom(0, 100),
                ciudad.get((int) (Math.random() * ciudad.size()))
        );
        return new Smith(
                localizacion,
                // Solo infecta en las 8 direcciones: N, NE, E, SE, S, SW, W, NW
                (int) Probabilidad.getRandom(0, 8)
        );
    }
}
