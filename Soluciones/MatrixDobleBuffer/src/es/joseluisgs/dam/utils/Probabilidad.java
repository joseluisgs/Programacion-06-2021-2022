package es.joseluisgs.dam.utils;

/**
 * Clase que representa operaciones de probabilidad, aleatorios y sorteos.
 */
public class Probabilidad {
    /**
     * Obtiene un número aleatorio entre min y max.
     *
     * @param min Número mínimo.
     * @param max Número máximo.
     * @return Número aleatorio entre min y max.
     */
    public static float getRandom(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }
}
