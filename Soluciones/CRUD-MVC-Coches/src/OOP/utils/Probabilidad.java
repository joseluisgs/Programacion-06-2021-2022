package OOP.utils;

import java.nio.channels.FileLock;
import java.util.Random;

public class Probabilidad {

    /**
     * Generar una vamos aleatorio,
     * @param min valor minimio del rango de probabilidad.
     * @param max valor maximo del rando de probabilidad.
     * @return Devuelve una valor aleatorio de entre el minimo y el maximo.
     */
    public static int getRandom(int min, int max){
        return (int) (Math.random() * (max - min) + min);
    }
}
