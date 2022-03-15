package OOP.repositories;

import java.util.List;

public interface ICRUDRepositoru<T, ID> {
    List<T> findAll();

    T insert(T item);

    T update(T item);

    T findByMatricula(ID id);

    T delete(T item);




}
