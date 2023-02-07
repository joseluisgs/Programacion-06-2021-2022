package es.joseluisgs.dam.tdas.matriz;

import java.util.ArrayList;

/**
 * Clase que representa una matriz de objetos de tipo T.
 *
 * @param <T> Tipo de los objetos que se almacenan en la matriz.
 */
public class Matriz<T> extends ArrayList<ArrayList<T>> implements IMatriz<T> {
    private int rows;
    private int columns;

    /**
     * Constructor por defecto. Inicia todo a a null.
     *
     * @param rows    Número de filas de la matriz.
     * @param columns Número de columnas de la matriz.
     */
    public Matriz(int rows, int columns) {
        super(rows);
        this.rows = rows;
        this.columns = columns;
        for (int i = 0; i < rows; i++) {
            // Creamos por cada fila, la columnas a null
            ArrayList<T> fila = new ArrayList<T>();
            for (int j = 0; j < columns; j++) {
                fila.add(null);
            }
            this.add(fila);
        }
    }

    /**
     * Devuelve el valor de la posición indicada.
     *
     * @param row Fila de la posición.
     * @param col Columna de la posición.
     */
    @Override
    public T get(int row, int col) {
        return this.get(row).get(col);
    }

    /**
     * Establece el valor de la posición indicada.
     *
     * @param row   Fila de la posición.
     * @param col   Columna de la posición.
     * @param value Valor a establecer.
     */
    @Override
    public void set(int row, int col, T value) {
        this.get(row).set(col, value);
    }

    /**
     * Devuelve el número de filas de la matriz.
     *
     * @return Número de filas de la matriz.
     */
    @Override
    public int getRows() {
        return this.rows;
    }

    /**
     * Devuelve el número de columnas de la matriz.
     *
     * @return Número de columnas de la matriz.
     */
    @Override
    public int getCols() {
        return this.columns;
    }

    /**
     * Limpia la matriz, poniendo todo a null.
     */
    @Override
    public void clear() {
        // La inicializamos a null creando los arrayList
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.set(i, j, null);
            }
        }
    }

    /**
     * Añade filas al final de la matriz.
     *
     * @param numberOfRows Número de filas a añadir.
     */
    @Override
    public void addRows(int numberOfRows) {
        for (int i = 0; i < numberOfRows; i++) {
            // Creamos por cada fila, la columnas a null
            ArrayList<T> fila = new ArrayList<T>();
            for (int j = 0; j < columns; j++) {
                fila.add(null);
            }
            this.add(fila);
        }
        this.rows += numberOfRows;
    }

    /**
     * Añade columnas al final de la matriz.
     *
     * @param numberOfCols Número de columnas a añadir.
     */
    @Override
    public void addCols(int numberOfCols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                this.get(i).add(null);
            }
        }
        this.columns += numberOfCols;
    }

    /**
     * Elimina las filas indicadas.
     *
     * @param numberOfRows Número de filas a eliminar desde el final
     */
    @Override
    public void removeRows(int numberOfRows) {
        for (int i = 0; i < numberOfRows; i++) {
            this.remove(this.size() - 1);
            //this.remove(0);
        }
        this.rows -= numberOfRows;

    }

    /**
     * Elimina las columnas indicadas al final.
     *
     * @param numberOfCols Número de columnas a eliminar.
     */
    @Override
    public void removeCols(int numberOfCols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                this.get(i).remove(this.get(i).size() - 1);
                // this.get(i).remove(0);
            }
        }
        this.columns -= numberOfCols;
    }

    /**
     * Devuelve la representación en String de la matriz.
     *
     * @return Representación en String de la matriz.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (this.get(i, j) != null) {
                    sb.append("[").append(this.get(i, j)).append("]");
                } else {
                    sb.append("[ ]");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Devuelve una copia clone de la matriz.
     *
     * @return Copia clone de la matriz.
     */
    @Override
    public Matriz<T> clone() {
        Matriz<T> clone = new Matriz<T>(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                clone.set(i, j, this.get(i, j));
            }
        }
        return clone;
    }
}
