package es.joseluisgs.dam.tda;

import java.util.PriorityQueue;

// Ahora si somos una cola de prioridad
// Podemos mapearla o usar la que existe en Java
// OJO, si la imprimes no la verás ordenada, solo si la recorres sacando o metiendo!!!
public class ColaPrioritaria<T> extends PriorityQueue<T> implements ICola<T> {

    @Override
    public void encolar(T elem) {
        this.add(elem);
    }

    @Override
    public T desencolar() {
        return this.poll();
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
        return this.peek();
    }

    @Override
    public String toString() {
        return "Cola{ " + super.toString() + " }";
    }
}
