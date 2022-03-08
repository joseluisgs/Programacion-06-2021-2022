package es.joseluisgs.dam.repositories;

import es.joseluisgs.dam.models.Pais;

import java.util.List;

public interface ICRUDRepository<T, ID> {
    List<T> findAll();

    T insert(T entity);

    T update(T entity);

    T findById(ID id);

    T deleteById(ID id);

    Pais delete(Pais pais);
}
