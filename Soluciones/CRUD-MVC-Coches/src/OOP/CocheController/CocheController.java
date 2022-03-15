package OOP.CocheController;

import OOP.exceptions.CocheException;
import OOP.factories.CocheFactory;
import OOP.models.Coche;
import OOP.repositories.CocheRepository;

import java.util.List;

public class CocheController {
    private static CocheController instance;
    private final CocheRepository cocheRepository;


    public CocheController(CocheRepository cocheRepository) {
        this.cocheRepository = cocheRepository;
        initRepository();
    }


    /**
     * Singleton
     * @return Devuelve el mismo objeto.
     */
    public static CocheController getInstance(){
        if (instance == null){
            instance = new CocheController(new CocheRepository());
        }
        return instance;
    }



    private void initRepository() {
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(CocheFactory.createCocherandom());
        this.cocheRepository.insert(new Coche("Mazda", "mazda3", "0616CND", "Gris"));
        this.cocheRepository.insert(new Coche("Mazda", "CX", "1606CND", "Blanco"));
    }

    /**
     * Crear un coche.
     * @param coche que vamos a insertar
     * @return Devuelve un coche o null sino es existe.
     * @throws CocheException
     */
    public Coche crearCoche(Coche coche) throws CocheException{
        //Comprobamos que los campos del coche sean correctos;
        chekCocheData(coche);
        var existe = cocheRepository.findByMatricula(coche.getMatricula());
        if (existe != null){
            cocheRepository.insert(coche);
            return coche;
        }
        return null;
    }


    /**
     * Comprobación de los datos del coche
     * @param coche a comprobar
     * @throws CocheException si los datos son incorrectos
     */
    private void chekCocheData(Coche coche)throws CocheException {
        if (coche.getMarca() == null || coche.getMarca().isEmpty()){
            throw new CocheException("La marca del coche esta vacía");
        }
        if (coche.getModelos() == null || coche.getModelos().isEmpty()){
            throw new CocheException("El nombre del coche no puede estar vacío");
        }
        if (coche.getMatricula() == null || coche.getMatricula().isEmpty()){
            throw new CocheException("La matricula del coche no es correcta");
        }
        if (coche.getColor() == null || coche.getColor().isEmpty()){
            throw new CocheException("El color del coche no puede estar vacío");
        }

    }

    /**
     * Devuelve un coches con la matricula indicada.
     * @param matricula del coche a buscar
     * @return Devuelve un coche o null sino existe.
     * @throws CocheException
     */
    public Coche getCocheByMatricula(String matricula) throws CocheException{
        var coche = cocheRepository.findByMatricula(matricula);
        if (coche != null){
            return coche;
        }
        return null;
    }

    /**
     * Devuelve todos los coches;
     * @return Lista de coches.
     */
    public List<Coche> getAllCoches(){
        return cocheRepository.findAll();
    }

    /**
     * Actualiza los datos del coche.
     * @param coche a actualizar.
     * @return Devuelve el coche actualizado.
     * @throws CocheException
     */
    public Coche updateCoche(String matriculaKey, Coche coche)throws CocheException{
        //Comprobamos que los datos del coche que queremos actualizar son correcto.
        chekCocheData(coche);
        var res = cocheRepository.findByMatricula(coche.getMatricula());
        //comprueba que no sea null
        if (res == null || res.getMatricula().equals(coche.getMatricula())){
            cocheRepository.update(coche);
            return coche;
        }else {
            throw new CocheException("El coche con matricula "+coche.getMatricula()+" ya existe.");
        }
    }


    /**
     * Elimina por matricula
     * @param matricula del coche a eliminar
     * @return devuelve el coche eliminado
     * @throws CocheException
     */
    public Coche deleteByMatricula(String  matricula)throws CocheException{
        var coche =cocheRepository.findByMatricula(matricula);
         if (coche != null){
             cocheRepository.remove(coche.getMatricula());
             return coche;
         }else {
             throw new CocheException("El coche con matricula "+matricula);
         }
    }




}
