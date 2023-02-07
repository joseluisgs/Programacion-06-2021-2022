package es.joseluisgs.dam.tdas.cola;

/**
 * Interfaz que define los métodos que debe implementar una cola.
 *
 * @param <T> Tipo de datos que almacena la cola.
 */
public interface ICola<T> {
    void encolar(T elem);

    T desencolar();

    boolean esVacia();

    int longitud();

    T primero();
}
