package tda.pila;

public interface IPila<T> {
    void push(T dato);

    T pop();

    T peek();

    boolean isEmpty();

    int size();
}
