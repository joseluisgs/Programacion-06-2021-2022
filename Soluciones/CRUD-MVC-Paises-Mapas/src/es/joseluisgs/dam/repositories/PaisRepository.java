package es.joseluisgs.dam.repositories;

import es.joseluisgs.dam.models.Pais;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Repository para los paises siguiendo el TDA Mapa
 * Voy a usar un mapa y meteré de clave el nbomvre, pero lo lógico es que fuera
 * el codigo del pais o el id, pero así no trastoco mucho
 * Con esto vemos lo sencillo que es usar mapas para este tipo de aplicaciones CRUD
 * Si lo hacemos bien, solo debeo tocar el repositorio, porque tengo un buen diseño de capas
 */
public class PaisRepository extends TreeMap<String, Pais> implements ICRUDRepository<Pais, Integer> {
    /**
     * Busca un pais por su nombre
     *
     * @param nombre nombre del pais
     * @return el pais encontrado o null si no lo encuentra
     */
    public Pais findByNombre(String nombre) {
        return this.get(nombre);
    }

    /**
     * Busca un pais por su id, este deja de tener sentido!!!
     *
     * @param id id del pais
     * @return el pais encontrado o null si no lo encuentra
     */
    @Override
    public Pais findById(Integer id) {
        for (Pais pais : this.values()) {
            if (pais.getId() == id)
                return pais;
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
        return new ArrayList<>(this.values());
    }

    /**
     * Actualiza un pais
     *
     * @param pais pais a actualizar con sus datos nuevos
     * @return el pais actualizado, null si no es posible
     */
    @Override
    public Pais update(Pais pais) {
        // como el nombre es la clave, tenemos un problema, por eso es tan mala "clave"
        // si actualizamos el nombre del pais, no podemos actualizar el nombre de la clave
        // return this.put(pais.getNombre(), pais);
        // es por eso que debemos borrar a insertar
        var res = this.findById(pais.getId());
        if (res != null) {
            this.remove(res.getNombre());
            return this.put(pais.getNombre(), pais);
        }
        return null;
    }

    /**
     * Elimina un pais
     *
     * @param pais Pais del pais a eliminar
     * @return el pais eliminado
     */
    @Override
    public Pais delete(Pais pais) {
        // También lo podríamos haber hecho pasando el país a eliminar y no su id, como update
        // pasamos del id, y buscamos pro la clave
        // var paisEncontrado = this.get(pais.getNombre());
        return this.remove(pais.getNombre());
    }

    /**
     * Borra un pais por su id
     *
     * @param id id del pais a eliminar
     * @return el pais eliminado o null si no lo encuentra
     */

    @Override
    public Pais deleteById(Integer id) {
        var pais = this.findById(id);
        if (pais != null) {
            this.remove(pais.getNombre());
        }
        return null;
    }

    @Override
    public Pais insert(Pais pais) {
        this.put(pais.getNombre(), pais);
        return pais;
    }

}
