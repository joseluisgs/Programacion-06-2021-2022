package es.joseluisgs.dam.controllers;

import es.joseluisgs.dam.comparators.PersonaFechaComparator;
import es.joseluisgs.dam.factories.GenericoFactory;
import es.joseluisgs.dam.factories.SmithFactory;
import es.joseluisgs.dam.models.Generico;
import es.joseluisgs.dam.models.Neo;
import es.joseluisgs.dam.models.Persona;
import es.joseluisgs.dam.models.Smith;
import es.joseluisgs.dam.tdas.cola.Cola;
import es.joseluisgs.dam.tdas.matriz.Matriz;

import java.util.*;

/**
 * Clase controladora de la aplicación The Matrix.
 * Procesa la lógica de la aplicación del enunciado.
 * DoubleBuffer solucion.
 * https://es.wikipedia.org/wiki/Buffer_m%C3%BAltiple
 * No es obligatorio y esto es nivel avanzado de la materia. Pero es bueno conocerlo.
 */
public class TheMatrixController {
    // Constantes
    private static final int ALAMCEN_INIT_SIZE = 200;
    private static TheMatrixController instance; // para singleton
    private final int dimension;
    // Repositorios de objetos...
    // Almacen de personajes
    private final Cola<Generico> almacenRepository;
    // Lista de personajes Usados
    private final List<Persona> usadosRepository;
    // Lista de virus Smith
    private final List<Persona> virusRepository;
    // Lista (conjunto) de eliminados por Neo.
    private final Set<Persona> eliminadosRepository; // permite duplicados
    private final int tiempo_max;
    // Mi estado
    private Matriz<Persona> matrix;
    private Matriz<Persona> tempMatrix;
    private int totalPersonajes;


    /**
     * Constructor privado para singleton
     *
     * @param config configuración de la simulación
     *               - dimension: dimension de la matriz
     *               - time: tiempo máximo de la simulación
     */
    private TheMatrixController(Map<String, Integer> config) {
        dimension = config.get("dimension");
        tiempo_max = config.get("time") * 1000; // En segundos
        matrix = new Matriz<>(dimension, dimension);
        tempMatrix = new Matriz<>(dimension, dimension);
        // Iniciamos los repositorios de almacenamiento de la información
        almacenRepository = new Cola<>();
        usadosRepository = new ArrayList<>();
        virusRepository = new ArrayList<>();
        eliminadosRepository = new TreeSet<>(new PersonaFechaComparator()); // Siempre ordenado por fecha
    }

    /**
     * Instancia única de TheMatrixController por Singleton
     *
     * @param config configuración de la simulación
     *               - dimension: dimension de la matriz
     *               - time: tiempo máximo de la simulación
     * @return instancia única de TheMatrixController
     */
    public static TheMatrixController getInstance(Map<String, Integer> config) {
        if (instance == null) {
            instance = new TheMatrixController(config);
        }
        return instance;
    }

    /**
     * Imprime el estado de la matriz de Matrix
     */
    private void print() {
        System.out.println("The Matrix Status: ");
        System.out.println(matrix);
    }

    /**
     * Inicializa el sistema Matrix
     */
    public void init() {
        // Inicializar almacen
        initAlmacen();
        // printAlmacen(); // Para analizar que hay toda la energia
        // Iniciamos a Neo
        initNeo();
        // Iniciamos a Smith
        initSmith();
        // situamos genericos en la matriz
        initGenericos();
    }

    /**
     * Inicializa a los personajes genéricos dentro de la matriz
     */
    private void initGenericos() {
        // Situamos personajes en huecos libres
        for (int fil = 0; fil < dimension; fil++) {
            for (int col = 0; col < dimension; col++) {
                if (matrix.get(fil, col) == null) {
                    var generico = almacenRepository.desencolar();
                    matrix.set(fil, col, generico);
                    usadosRepository.add(generico);
                    updateLocalicacion(generico, fil, col);
                    // totalPersonajes++; No los pongo porque ya los he puesto en el almacen
                }
            }
        }
    }

    /**
     * Inicializa al primer agente Smith
     */
    private void initSmith() {
        int fil;
        int col;
        do {
            fil = (int) (int) (Math.random() * dimension);
            col = (int) (int) (Math.random() * dimension);
        } while (matrix.get(fil, col) != null);
        var smith = SmithFactory.createRandom();
        matrix.set(fil, col, smith);
        updateLocalicacion(smith, fil, col);
        usadosRepository.add(smith);
        virusRepository.add(smith);
        totalPersonajes++;
    }

    /**
     * Inicializa al Neo
     */
    private void initNeo() {
        var fil = (int) (Math.random() * dimension);
        var col = (int) (Math.random() * dimension);
        Neo neo = new Neo();
        matrix.set(fil, col, neo);
        updateLocalicacion(neo, fil, col);
        usadosRepository.add(neo);
        totalPersonajes++;
    }

    /**
     * Actualiza la localización de un personaje en la matriz
     *
     * @param persona personaje a actualizar
     * @param fila    fila donde se encuentra es la latitud
     * @param columna columna donde se encuentra es la longitud
     */
    private void updateLocalicacion(Persona persona, int fila, int columna) {
        persona.getLocalizacion().setLatitud(fila);
        persona.getLocalizacion().setLongitud(columna);
    }

    /**
     * Obtenemos la posición de Neo dentro de la matriz de Matrix
     *
     * @return posición de Neo como Mapa de coordenadas fila y columna
     */
    private Map<String, Integer> getNeoPosition() {
        for (int fil = 0; fil < dimension; fil++) {
            for (int col = 0; col < dimension; col++) {
                if (matrix.get(fil, col) instanceof Neo) {
                    var pos = new HashMap<String, Integer>();
                    pos.put("fila", fil + 1);
                    pos.put("columna", col + 1);
                    return pos;
                }
            }
        }
        return null;
    }

    /**
     * Inicializa al almacen de personajes genéricos
     */
    private void initAlmacen() {
        for (int i = 0; i < ALAMCEN_INIT_SIZE; i++) {
            almacenRepository.encolar(GenericoFactory.createRandom());
        }
        // Para llevar el total de personajes
        totalPersonajes += ALAMCEN_INIT_SIZE;
    }

    /**
     * Imprime el almacen de personajes genéricos
     *
     * @deprecated No se usa y eliminar
     */
    private void printAlmacen() {
        System.out.println("Almacen: ");
        System.out.println(almacenRepository);
        var list = almacenRepository.toArray();
        for (int i = 0; i < almacenRepository.size(); i++) {
            System.out.println(((Generico) list[i]).mostrar());
        }
    }

    /**
     * Imprime el almacen de personajes genéricos
     */
    public void report() {
        System.out.println();
        System.out.println("Informe de simulación de The Matrix");
        System.out.println("The Matrix Final Status: ");
        System.out.println(matrix);
        System.out.println();

        System.out.println("Total Personajes: " + totalPersonajes);
        System.out.println("Total Personajes Usados: " + usadosRepository.size());
        System.out.println("Total Personajes en almacen: " + almacenRepository.size());
        System.out.println("Total de Smith generados por virus: " + virusRepository.size());
        System.out.println("Total de Smith eliminados por Neo: " + eliminadosRepository.size());
        System.out.println();

        /*var neoPos = getNeoPosition();
        System.out.println("Posición de Neo \uD83D\uDE0E: [" + neoPos.get("fila") + "," + neoPos.get("columna") + "]");
        System.out.println();*/
        var neo = searchNeo();
        System.out.println("Posición de Neo \uD83D\uDE0E: [" + (neo.getLocalizacion().getLatitud() + 1) + "," +
                (neo.getLocalizacion().getLongitud() + 1) + "]");
        System.out.println();


        System.out.println("Lista de personajes usados ordenados por id: ");
        Collections.sort(usadosRepository);
        for (Persona usado : usadosRepository) {
            System.out.println("id: " + usado.getId() + " - nombre: " + usado.getNombre() + " - creado: " + usado.getCreatedAt());
        }
        System.out.println();

        System.out.println("Lista de virus Smith ordenados por Fecha descedente: ");
        virusRepository.sort(new PersonaFechaComparator().reversed());
        for (Persona smith : virusRepository) {
            System.out.println("id: " + smith.getId() + " - nombre: " + smith.getNombre() + " - creado: " + smith.getCreatedAt());
        }
        System.out.println();

        System.out.println("Lista de virus Smith eliminados por Neo: ");
        for (Persona eliminado : eliminadosRepository) {
            System.out.println("id: " + eliminado.getId() + " - nombre: " + eliminado.getNombre() + " - creado: " + eliminado.getCreatedAt());
        }
    }

    /**
     * Busca al Neo en la matriz
     *
     * @return Neo
     */
    private Persona searchNeo() {
        for (int fil = 0; fil < dimension; fil++) {
            for (int col = 0; col < dimension; col++) {
                if (matrix.get(fil, col) instanceof Neo) {
                    return matrix.get(fil, col);
                }
            }
        }
        return null;
    }

    /**
     * Método que se encarga de la lógica de simulación de The Matrix
     */
    public void simulate() {
        var time = 0;
        boolean exit = false;
        do {
            this.print();
            // Copiamos todos los cambios de la matriz original a la matriz temporal
            // leemos de la matriz original, y actuamos en la temporal.
            // y las acciones las escribiremos aquí.
            tempMatrix = matrix.clone();

            // incrementamos el tiempo y suspendemos el sistema para que no vaya tan rápido
            try {
                Thread.sleep(1000);
                time += 1000;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Tiempo: " + (time / 1000) + "s");
            if (time % 1000 == 0) {
                System.out.println("\uD83E\uDD28 Evaluando personajes");
                accionGenericos();
            }
            if (time % 2000 == 0) {
                System.out.println("\uD83D\uDE08 Agente Smith entra en acción");
                accionSmith();
            }
            if (time % 4000 == 0) {
                System.out.println("\uD83D\uDE0E Neo entra en acción");
                accionNeo();
            }
            if (time % 10000 == 0) {
                System.out.println("Nuevos personajes aparecen en The Matrix");
                accionNuevosGenericos();
            }

            // Cogemos todos lso cambios de la matriz temporal y se lo aplicamos a la original
            matrix = tempMatrix.clone();

            // Condiciones de salida
            exit = time > tiempo_max || almacenRepository.esVacia();
        } while (!exit);
    }

    /**
     * Acción correspondiente a añadir nuevos genéricos a huecos libres de The Matrix
     * Siempre que haya hueco o como máximo 5, si tenemos en la cola.
     * Posición aleatoria de los nuevos genéricos
     */
    private void accionNuevosGenericos() {
        var hayHueco = getFreeSpace();
        System.out.println("Espacios libres en The Matrix: " + hayHueco);
        System.out.println("Personajes en almacen dismponibles: " + almacenRepository.size());
        var max = Math.min(almacenRepository.size(), Math.min(hayHueco, 5));
        for (int i = 0; i < max; i++) {
            var personaje = almacenRepository.desencolar();
            usadosRepository.add(personaje);
            //totalPersonajes++;
            // vamos a ver si lo ponemos en una posición aleatoria
            int fil, col;
            var asigando = false;
            do {
                fil = (int) (Math.random() * dimension);
                col = (int) (Math.random() * dimension);
                if (matrix.get(fil, col) == null) {
                    //matrix.set(fil, col, personaje);
                    tempMatrix.set(fil, col, personaje);
                    asigando = true;
                    System.out.println("\uD83E\uDD28 Nuevo personaje en [" + (fil + 1) + "," + (col + 1) + "]");
                    updateLocalicacion(personaje, fil, col);
                }
            } while (!asigando);
        }
    }

    /**
     * Obtiene el número de huecos libres en The Matrix
     *
     * @return número de huecos libres
     */
    private int getFreeSpace() {
        var huecos = 0;
        for (int fil = 0; fil < dimension; fil++) {
            for (int col = 0; col < dimension; col++) {
                if (matrix.get(fil, col) == null) {
                    huecos++;
                }
            }
        }
        return huecos;
    }

    /**
     * Acción correspondiente a la lógica de Neo
     */
    private void accionNeo() {
        for (int fil = 0; fil < dimension; fil++) {
            for (int col = 0; col < dimension; col++) {
                // Si es Neo
                if (matrix.get(fil, col) instanceof Neo) {
                    Neo neo = (Neo) matrix.get(fil, col);
                    System.out.println("\uD83D\uDE0E Neo aparece desde la posicion [" + (fil + 1) + "," + (col + 1) + "]");
                    System.out.println("\uD83D\uDE0E Neo Decide si actua o no");
                    if (neo.isElegido()) {
                        System.out.println("\uD83D\uDE0E Neo decide actuar y con ello elimina a los agentes Smith a su alrededor");
                        for (int i = -1; i <= 1; i++) {
                            for (int j = -1; j <= 1; j++) {
                                if (fil + i >= 0 && fil + i < dimension && col + j >= 0 && col + j < dimension) {
                                    // Si es un agente smith
                                    if (matrix.get((fil + i), (col + j)) instanceof Smith) {
                                        Smith smith = (Smith) matrix.get((fil + i), (col + j));
                                        System.out.println("\uD83D\uDE0E Neo elimina a Smith " + smith.getNombre() + " de la posicion [" + (fil + i + 1) + "," + (col + j + 1) + "]");
                                        // Añadimos un personaje...
                                        eliminadosRepository.add(smith);
                                        // ...y lo eliminamos de la matriz
                                        // matrix.set((fil + i), (col + j), null);
                                        tempMatrix.set((fil + i), (col + j), null);
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("\uD83D\uDE0E Neo decide no actuar");
                    }
                    // Debenos cambiar de posicion
                    System.out.println("\uD83D\uDE0E Neo cambia de posicion");
                    int newFil = (int) (Math.random() * dimension);
                    int newCol = (int) (Math.random() * dimension);
                    System.out.println("\uD83D\uDE0E Neo se mueve a la posicion [" + (newFil + 1) + "," + (newCol + 1) + "]");
                    // matrix.set(fil, col, null);
                    tempMatrix.set(fil, col, null);
                    // si esta ocupado por Smith --> muerte
                    if (matrix.get(newFil, newCol) instanceof Smith) {
                        Smith smith = (Smith) matrix.get((newFil), (newCol));
                        System.out.println("\uD83D\uDE0E Neo elimina a Smith " + smith.getNombre() +
                                " de la posicion [" + (newFil + 1) + "," + (newCol + 1) + "]");
                        eliminadosRepository.add(smith);
                        // matrix.set(fil, col, null);
                        tempMatrix.set(newFil, newCol, null);
                    } else if (matrix.get(newFil, newCol) instanceof Generico) {
                        Generico generico = (Generico) matrix.get((newFil), (newCol));
                        System.out.println("\uD83D\uDE0E Neo intercambia posicion con: " + generico.getNombre() +
                                " de la posicion [" + (newFil + 1) + "," + (newCol + 1) + "]");
                        // matrix.set(fil, col, generico);
                        tempMatrix.set(newFil, newCol, generico);
                        updateLocalicacion(generico, fil, col);
                    }
                    // matrix.set(newFil, newCol, neo);
                    tempMatrix.set(newFil, newCol, neo);
                    updateLocalicacion(neo, newFil, newCol);
                }
            }
        }
    }

    /**
     * Acción correspondiente a la lógica de Smith
     */
    private void accionSmith() {
        for (int fil = 0; fil < dimension; fil++) {
            for (int col = 0; col < dimension; col++) {
                // Si es instancia de Smith
                if (matrix.get(fil, col) instanceof Smith) {
                    // Vamos a por los de alrededor en las 8 direcciones
                    // pero solo podemos actuar las veces que nos quedan...
                    var smith = (Smith) matrix.get(fil, col);
                    var intentos = smith.getProbInfectar(); // solos los intebntos que podemos hacer
                    System.out.println("\uD83D\uDE08 Agente Smith: " + smith.getNombre() + " - Intentos: " + intentos + " - Posición: [" + (fil + 1) + "," + (col + 1) + "]");
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (fil + i >= 0 && fil + i < dimension && col + j >= 0 && col + j < dimension && intentos > 0) {
                                if (matrix.get((fil + i), (col + j)) instanceof Generico) {
                                    intentos--;
                                    System.out.println("\uD83D\uDE08 Agente Smith: " + smith.getNombre() +
                                            " infecta: [" + (fil + i + 1) + "," + (col + j + 1) + "] le quedan: " +
                                            intentos + " intentos");
                                    var newSmith = SmithFactory.createRandom();
                                    // matrix.set((fil + i), (col + j), newSmith);
                                    tempMatrix.set((fil + i), (col + j), newSmith);
                                    usadosRepository.add(newSmith);
                                    virusRepository.add(newSmith);
                                    updateLocalicacion(newSmith, fil, col);
                                    totalPersonajes++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Acción correspondiente a la lógica de Genérico
     */
    private void accionGenericos() {
        // Recorremos la matriz en busca de genéricos
        for (int fil = 0; fil < dimension; fil++) {
            for (int col = 0; col < dimension; col++) {
                // Si es instancia de Generico
                if (matrix.get(fil, col) instanceof Generico)
                //if (matrix[f][c].getClass().getSimpleName().equals("Generico"))
                {
                    var generico = (Generico) matrix.get(fil, col);
                    if (generico.getProbMorir() < 30) {
                        System.out.println("☠️ Muere generico: " + generico.getId() +
                                " en posición: [" + (fil + 1) + "," + (col + 1) + "] su " +
                                "probabilida de muerte era: " +
                                String.format("%.2f", generico.getProbMorir()));
                        // sacamos un nuevo personaje, solo si tenemos...
                        if (almacenRepository.size() > 0) {
                            var newGenerico = almacenRepository.desencolar();
                            // Lo ponemos en su lugar
                            // matrix.set(fil, col, newGenerico);
                            tempMatrix.set(fil, col, newGenerico);
                            // Lo añadimos a la lista de usados
                            usadosRepository.add(newGenerico);
                            // Actualizamos su localizacion
                            updateLocalicacion(newGenerico, fil, col);
                            // totalPersonajes++;
                            System.out.println("\uD83E\uDD28 Aparece un nuevo Generico: " + newGenerico.getId() + " en posición: [" + (fil + 1) + "," + (col + 1) + "]");
                        } else {
                            System.out.println("\uD83D\uDEA9 No hay personajes en el almacen");
                            tempMatrix.set(fil, col, null);
                        }
                    } else {
                        // Disminuimos la probabilidad de muerte
                        generico.setProbMorir(generico.getProbMorir() - 10);
                    }
                }
            }
        }
    }
}
