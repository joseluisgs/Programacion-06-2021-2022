package es.joseluisgs.dam.controllers;

import es.joseluisgs.dam.exceptions.PaisException;
import es.joseluisgs.dam.models.Pais;
import es.joseluisgs.dam.repositories.PaisRepository;

import java.util.List;

/**
 * Controlador de Gestion de Paises
 * Dependencias: PaisRepository
 * Es importante que lanzo las excepciones de PaisException
 * Que ya las recogerá la vista
 */
public class PaisController {
    private static PaisController instance;
    private final PaisRepository paisRepository;

    private PaisController(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
        initRepositoryData();
    }

    /**
     * Singleton pattern implementation
     *
     * @return instance of PaisController
     */
    public static PaisController getInstance() {
        if (instance == null) {
            instance = new PaisController(new PaisRepository());
        }
        return instance;
    }

    /**
     * Inicializa el repositorio con datos de prueba
     */
    private void initRepositoryData() {

        this.paisRepository.insert(new Pais("España", "ES", "Castellano", "Euro", "Madrid"));
        this.paisRepository.insert(new Pais("Francia", "FR", "Francés", "Euro", "Paris"));
        this.paisRepository.insert(new Pais("Italia", "IT", "Italiano", "Euro", "Roma"));
        this.paisRepository.insert(new Pais("Alemania", "DE", "Alemán", "Euro", "Berlín"));
        this.paisRepository.insert(new Pais("Reino Unido", "UK", "Inglés", "Libra", "Londres"));
        this.paisRepository.insert(new Pais("Japón", "JP", "Japonés", "Yen", "Tokio"));
        this.paisRepository.insert(new Pais("China", "CN", "Chino", "Yuan", "Pekín"));
        this.paisRepository.insert(new Pais("Australia", "AU", "Inglés", "Dólar", "Canberra"));
        this.paisRepository.insert(new Pais("Argentina", "AR", "Español", "Peso", "Buenos Aires"));
        this.paisRepository.insert(new Pais("Brasil", "BR", "Portugués", "Real", "Brasilia"));
        this.paisRepository.insert(new Pais("Estados Unidos", "US", "Inglés", "Dólar", "Washington"));
    }


    public Pais crearPais(Pais pais) throws PaisException {
        // Comprobamos que no haya datos incorrectos, por si ha fallado la interfaz
        checkPaisData(pais);
        // comprobamos si existe
        var existe = paisRepository.findByNombre(pais.getNombre());
        if (existe == null) {
            paisRepository.insert(pais);
            return pais;
        }
        throw new PaisException("Ya existe un pais con el nombre " + pais.getNombre());
    }

    /**
     * Comprueba que los datos del pais son correctos. No lo hacemos en el modelo para no contaminarlo.
     *
     * @param pais Pais a comprobar
     * @throws PaisException Si los datos son incorrectos
     */
    private void checkPaisData(Pais pais) throws PaisException {
        if (pais.getNombre() == null || pais.getNombre().trim().isEmpty()) {
            throw new PaisException("El nombre del pais no puede estar vacio");
        }
        if (pais.getCodigo() == null || pais.getCodigo().trim().isEmpty()) {
            throw new PaisException("El codigo del pais no puede estar vacio");
        }
        if (pais.getIdioma() == null || pais.getIdioma().trim().isEmpty()) {
            throw new PaisException("El idioma del pais no puede estar vacio");
        }
        if (pais.getMoneda() == null || pais.getMoneda().trim().isEmpty()) {
            throw new PaisException("La moneda del pais no puede estar vacia");
        }
        if (pais.getCapital() == null || pais.getCapital().trim().isEmpty()) {
            throw new PaisException("La capital del pais no puede estar vacia");
        }
    }

    /**
     * Devuelve el pais con el nombre indicado
     *
     * @param nombre nombre del pais
     * @return Pais con el nombre indicado
     * @throws PaisException si no existe el pais
     */
    public Pais getPaisByNombre(String nombre) throws PaisException {
        var pais = paisRepository.findByNombre(nombre);
        if (pais != null) {
            return pais;
        }
        throw new PaisException("No existe el pais con nombre " + nombre);
    }

    /**
     * Devuelve el pais con el id indicado
     *
     * @param id id del pais
     * @return Pais con el id indicado
     * @throws PaisException si no existe el pais
     */
    public Pais getPaisById(int id) throws PaisException {
        var pais = paisRepository.findById(id);
        if (pais != null) {
            return pais;
        }
        throw new PaisException("No existe el pais con id " + id);
    }

    /**
     * Devuelve todos los paises
     *
     * @return Lista de paises
     */
    public List<Pais> getAllPaises() {
        return paisRepository.findAll();
    }


    public Pais updatePais(String nombreKey, Pais pais) throws PaisException {
        // Comprobamos los datos
        checkPaisData(pais);
        // Vemos si con los datos nuevos existe un pais que se llame igual
        var otro = paisRepository.findByNombre(pais.getNombre());
        // si no existe otro pais con el mismo nombre, actualizamos
        // o si simplemente los id son iguales, actualizamos, pues somos el mismo objeto, por eso hay "otro" ya
        // si no lo entiendes dale la vuelta al if
        if ((otro == null) || (otro.getId() == pais.getId())) {
            // si no existe otro pais con el mismo nombre, lo actualizamos o somos nosotros por id
            paisRepository.update(pais);
            return pais;
        } else {
            throw new PaisException("Ya existe un pais con el nombre " + pais.getNombre());
        }
    }

    /**
     * Elimina el pais con el nombre indicado
     *
     * @param nombre nombre del pais
     * @return Pais eliminado
     * @throws PaisException si no existe el pais
     */
    public Pais deletePais(String nombre) throws PaisException {
        var pais = paisRepository.findByNombre(nombre);
        if (pais != null) {
            // paisRepository.deleteById(pais.getId());
            paisRepository.delete(pais);
            return pais;
        } else {
            throw new PaisException("No existe el pais con nombre " + nombre);
        }
    }
}
