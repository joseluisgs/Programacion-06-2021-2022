package es.joseluisgs.dam.tda;

import java.util.ArrayDeque;

public class Cola<T> extends ArrayDeque<T> implements ICola<T> {

    @Override
    public void encolar(T elem) {
        this.addLast(elem);
    }

    @Override
    public T desencolar() {
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
