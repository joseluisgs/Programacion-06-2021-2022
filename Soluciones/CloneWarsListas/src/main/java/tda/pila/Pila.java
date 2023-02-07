package tda.pila;

import java.util.ArrayList;

public class Pila<T> extends ArrayList<T> implements IPila<T> {

    @Override
    public void push(T dato) {
        this.add(0, dato);
    }

    @Override
    public T pop() {
        return this.remove(0);
    }

    @Override
    public T peek() {
        return this.get(0);
    }

}
