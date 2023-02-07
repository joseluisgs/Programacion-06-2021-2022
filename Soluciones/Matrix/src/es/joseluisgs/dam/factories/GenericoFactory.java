package es.joseluisgs.dam.factories;

import es.joseluisgs.dam.models.Generico;
import es.joseluisgs.dam.models.Localizacion;
import es.joseluisgs.dam.utils.Probabilidad;

import java.util.List;

/**
 * Patrón Factoría para crear objetos de tipo Generico.
 */
public class GenericoFactory {

    /**
     * Crea un objeto de tipo Generico con datos aleatorios.
     *
     * @return Objeto de tipo Generico.
     */
    public static Generico createRandom() {
        var nombre = List.of("Pepe", "Juan", "Maria", "Jose", "Ana", "Sonia");
        var ciudad = List.of("Madrid", "New York", "Pekin", "Leganés", "Londres", "Paris");
        var localizacion = new Localizacion(
                (int) Probabilidad.getRandom(0, 100),
                (int) Probabilidad.getRandom(0, 100),
                ciudad.get((int) (Math.random() * ciudad.size()))
        );
        return new Generico(
                nombre.get((int) (Math.random() * nombre.size())),
                localizacion
        );
    }
}
