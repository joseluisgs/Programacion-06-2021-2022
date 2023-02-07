package es.joseluisgs.dam.repositories;

import java.util.List;

// posteriormente lo cambiaré a Optional....
public interface CRUDRepository<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T save(T entity);

    T update(ID id, T entity);

    T delete(ID id);
}
