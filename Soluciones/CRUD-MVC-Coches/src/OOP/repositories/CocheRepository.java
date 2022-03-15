package OOP.repositories;

import OOP.models.Coche;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CocheRepository extends TreeMap<String, Coche> implements ICRUDRepositoru<Coche, String> {


    /**
     * Lista de coches.
     * @return Devuelve la lista de coches que hayamos creado.
     */
    public List<Coche> findAll(){
        return new ArrayList<>(this.values());
    }

    /**
     * Guardar un nuevo coche
     * @param coche que deseamos guardar.
     * @return Devuelve el coche que hemos guardado.
     */
    @Override
    public Coche insert(Coche coche) {
        this.put(coche.getMatricula(), coche);
        return coche;
    }


    /**
     * Actualizar un coche.
     *
     * @param coche a actualizar con sus nuevos datos.
     * @return coche actualizado o nul si es posible.
     */
    @Override
    public Coche update(Coche coche) {
        var res = this.findByMatricula(coche.getMatricula());
        if (res != null){
            this.remove(coche.getMatricula());
            this.put(res.getMatricula(), res);
        }
        return null;
    }


    /**
     * Busca un coche por la matricula
     * @param matricula del coche a buscar
     * @return Devuelve el coche encontrado o null si no existe.
     */
    @Override
    public Coche findByMatricula(String matricula) {
        for (var coche:this.values()) {
            if (coche.getMatricula().equals(matricula)){
                return coche;
            }
        }
        return null;
    }


    /**
     * Elimina un coche.
     *
     * @param coche que se desea eliminar.
     * @return el coche eliminado o nul si no es posible.
     */
    @Override
    public Coche delete(Coche coche) {
        var res = this.findByMatricula(coche.getMatricula());
        if (res != null){
            this.remove(coche.getMatricula());
        }
        return null;
    }




}
