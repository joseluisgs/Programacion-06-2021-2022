package tda.matrix;

public interface IMatrix<T> {
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
