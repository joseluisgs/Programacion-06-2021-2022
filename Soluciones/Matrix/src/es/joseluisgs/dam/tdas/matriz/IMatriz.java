package es.joseluisgs.dam.tdas.matriz;

/**
 * Interfaz que define los métodos que debe implementar una matriz.
 *
 * @param <T> Tipo de datos de la matriz.
 */
public interface IMatriz<T> {
    T get(int row, int col);

    void set(int row, int col, T value);

    int getRows();

    int getCols();

    void clear();

    void addRows(int numberOfRows);

    void addCols(int numberOfCols);

    void removeRows(int numberOfRows);

    void removeCols(int numberOfCols);

    String toString();

}
