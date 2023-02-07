package es.joseluisgs.dam.repositories;

import es.joseluisgs.dam.models.Pais;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Repository para los paises siguiendo el TDA Mapa
 * Voy a usar un mapa y meteré la clave que es el ID
 * Con esto vemos lo sencillo que es usar mapas para este tipo de aplicaciones CRUD
 * Si lo hacemos bien, solo debo tocar el repositorio, porque tengo un buen diseño de capas
 */
public class PaisRepository implements CRUDRepository<Pais, Integer> {
    private final TreeMap<Integer, Pais> paises = new TreeMap<>();

    /**
     * Busca un pais por su nombre
     *
     * @param nombre nombre del pais
     * @return el pais encontrado o null si no lo encuentra
     */
    public Pais findByNombre(String nombre) {
        for (Pais pais : this.paises.values()) {
            if (pais.getNombre().equals(nombre))
                return pais;
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
        return this.paises.get(id);
    }

    /**
     * Devuelve todos los paises
     *
     * @return lista de paises
     */
    @Override
    public List<Pais> findAll() {
        return new ArrayList<>(this.paises.values());
    }

    /**
     * Añade un pais
     *
     * @param pais pais a añadir
     */
    @Override
    public Pais save(Pais pais) {
        this.paises.put(pais.getId(), pais);
        return pais;
    }

    /**
     * Actualiza un pais
     *
     * @param id   id del pais a actualizar
     * @param pais pais con los nuevos datos
     * @return el pais actualizado
     */
    @Override
    public Pais update(Integer id, Pais pais) {
        this.paises.put(id, pais);
        return pais;
    }

    /**
     * Elimina un pais dado el id
     *
     * @param id id del pais a eliminar
     * @return el pais eliminado o null si no lo encuentra
     */
    @Override
    public Pais delete(Integer id) {
        return this.paises.remove(id);
    }
}
