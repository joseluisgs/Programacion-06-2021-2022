package es.joseluisgs.dam.tdas.cola;

import java.util.ArrayDeque;

/**
 * Implementación de la cola con una cola interna de tipo ArrayDeque.
 *
 * @param <T> Tipo de los elementos de la cola.
 */
public class Cola<T> extends ArrayDeque<T> implements ICola<T> {

    @Override
    public void encolar(T elem) {
        this.addLast(elem);
    }

    @Override
    public T desencolar() {
        // cuidado con removeFirst y pollFirst
        // El primero lanza una expeción si la cola es vacía, el segundo te devuleve nulo si no hay nada
        return this.pollFirst();

    }

    @Override
    public boolean esVacia() {
        return this.isEmpty();
    }

    @Override
    public int longitud() {
        return this.size();
    }

    @Override
    public T primero() {
        return this.peekFirst();
    }

    @Override
    public String toString() {
        return "Cola{ " + super.toString() + " }";
    }
}
