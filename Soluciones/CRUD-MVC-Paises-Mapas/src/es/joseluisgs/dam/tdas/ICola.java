package es.joseluisgs.dam.tdas;

import java.util.Queue;

/**
 * Interfaz que define el comportamiento de una cola.
 *
 * @param <T> Tipo de datos que almacena la cola.
 */
public interface ICola<T> extends Queue<T> {

    void encolar(T elem);

    T desencolar();

    T primero();

    boolean esVacia();

    int longitud();

    void vaciar();

    String toString();
}
