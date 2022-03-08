package es.joseluisgs.dam.repositories;

import es.joseluisgs.dam.models.Pais;
import es.joseluisgs.dam.tdas.ICola;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository para los paises siguiendo el TDA Cola
 */
public class PaisRepository extends ArrayList<Pais> implements ICola<Pais>, ICRUDRepository<Pais, Integer> {

    // Métodos para que el Array sea cola

    @Override
    public void encolar(Pais elem) {
        this.add(elem);
    }

    @Override
    public Pais desencolar() {
        return this.poll();
    }

    @Override
    public Pais primero() {
        return this.peek();
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
    public void vaciar() {
        this.clear();
    }

    @Override
    public boolean offer(Pais pais) {
        return this.add(pais);
    }

    @Override
    public Pais remove() {
        return this.remove(0);
    }

    @Override
    public Pais poll() {
        return this.remove(0);
    }

    @Override
    public Pais element() {
        return this.get(0);
    }

    @Override
    public Pais peek() {
        return this.get(0);
    }

    /**
     * Busca un pais por su nombre
     *
     * @param nombre nombre del pais
     * @return el pais encontrado o null si no lo encuentra
     */
    public Pais findByNombre(String nombre) {
        for (Pais pais : this) {
            if (pais.getNombre().equalsIgnoreCase(nombre)) {
                return pais;
            }
        }
        return null;
    }

    /**
     * Busca un pais por su id
     *
     * @param id id del pais
     * @return el pais encontrado o null si no lo encuentra
     */
    @Override
    public Pais findById(Integer id) {
        for (Pais pais : this) {
            if (pais.getId() == id) {
                return pais;
            }
        }
        return null;
    }

    /**
     * Devuelve una lista con los nombres de los paises
     *
     * @return lista con los nombres de los paises
     */
    @Override
    public List<Pais> findAll() {
        return this;
    }

    /**
     * Actualiza un pais
     *
     * @param pais pais a actualizar con sus datos nuevos
     * @return el pais actualizado, null si no es posible
     */
    @Override
    public Pais update(Pais pais) {
        var index = this.indexById(pais.getId());
        if (index >= 0) {
            this.set(index, pais);
            return this.get(index);
        }
        return null;
    }

    /**
     * Elimina un pais
     *
     * @param id id del pais a eliminar
     * @return el pais eliminado
     */
    public Pais deleteById(Integer id) {
        // También lo podríamos haber hecho pasando el país a eliminar y no su id, como update
        // dos maneras alternativas por id, o cogiendo el id del objeto
        var pais = this.findById(id);
        if (pais != null) {
            this.remove(pais);
            return pais;
        }
        return null;
    }

    /**
     * Realiza la búsqueda en base un id y devuleve su índice
     *
     * @param id id del objeto a buscar
     * @return indice en la colección
     */
    private int indexById(int id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    public Pais insert(Pais pais) {
        this.encolar(pais);
        return pais;
    }
}
