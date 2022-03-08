package model;

import comparators.DroideModelComparator;
import model.droide.Droide;
import model.droide.DroideSW447;
import model.droide.IDefensa;
import tda.matrix.Matrix;
import tda.pila.Pila;

import java.util.Collections;

public class SistemaApuntado {
    private static SistemaApuntado instance;
    private final Matrix<Droide> espacio;
    private final int dimension;
    private final int numDroides;
    private final int tiempoMax;
    private final int efectoDisparoNormal = 25;
    private final int efectoDisparoCritical = 50;
    private Pila<Droide> droidesEnemigos;
    private int disparos;
    private int aciertos;


    private SistemaApuntado(int dimension, int numDroides, int tiempoMax) {
        this.dimension = dimension;
        this.numDroides = numDroides;
        this.tiempoMax = tiempoMax * 30 * 1000; // tiempo en milisegundos porque me lo dan en minutos
        espacio = new Matrix<Droide>(dimension, dimension);
    }

    public static SistemaApuntado getInstance(int dimension, int numDroides, int tiempoMax) {
        if (instance == null) {
            instance = new SistemaApuntado(dimension, numDroides, tiempoMax);
        }
        return instance;
    }


    public void init() {
        System.out.println("Iniciando cuadricula...");
        initDroidesEnemigos();
        colocarDroidesEnemigos();
        printEspacio();
    }

    private void printEspacio() {
        System.out.println(espacio);
    }

    private void colocarDroidesEnemigos() {
        System.out.println("Colocando droides enemigos...");
        limpiarEspacio();
        for (int i = 0; i < droidesEnemigos.size(); i++) {
            int fil;
            int col;
            // si esta vivo
            if (droidesEnemigos.get(i).isAlive()) {
                do {
                    fil = (int) (Math.random() * dimension);
                    col = (int) (Math.random() * dimension);
                } while (espacio.get(fil, col) != null);
                espacio.set(fil, col, droidesEnemigos.get(i));
            }
        }
    }

    private void limpiarEspacio() {
        espacio.clear();
    }

    private void initDroidesEnemigos() {
        droidesEnemigos = new Pila<>();
        for (int i = 0; i < numDroides; i++) {
            droidesEnemigos.push(Droide.getRandomDroide());
        }
    }

    public void run() {
        System.out.println("Ejecutando cuadricula...");
        int tiempo = 0;
        boolean salida = false;
        do {
            System.out.println("Tiempo: " + (tiempo / 1000) + "s");
            System.out.println("escaneando y apuntando...");
            // Obtengo la posicion a disparar
            int fil = (int) (Math.random() * dimension);
            int col = (int) (Math.random() * dimension);
            System.out.println("Disparando en: [" + fil + "][" + col + "]");
            // Disparo
            disparos++;
            // Acierto si es != null
            if (espacio.get(fil, col) != null) {
                aciertos++;
                int efecto = getEfecto();
                Droide d = espacio.get(fil, col);

                // Si el androide es del tipo SW447, tiene una defensa que lo delimita de los disparos
                if (d instanceof DroideSW447) {
                    System.out.println("El droide SW447 tiene una defensa que lo delimita de los disparos");
                    efecto = efecto - ((DroideSW447) d).getDefensa();
                    efecto = Math.max(efecto, 0);

                    // Si implementa una interfaz de defensa
                } else if (d instanceof IDefensa) {
                    System.out.println("El droide implementa una interfaz de Escudo de Defensa");
                    efecto = ((IDefensa) d).defender(efecto);
                }

                d.setEnergia(d.getEnergia() - efecto);
                System.out.println("Has acertado con un daño de: " + efecto + " a: " + d);

            } else {
                System.out.println("Fallo");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tiempo += 1000;
            // Cambian de posicion los droides
            if (tiempo % 3000 == 0) {
                colocarDroidesEnemigos();
            }
            // Imprimo el espacio
            printEspacio();
            boolean finTiempo = tiempo >= tiempoMax;
            boolean todosMuertos = numDroides == droidesEnemigosMuertos();
            salida = finTiempo || todosMuertos;
        } while (!salida);
    }

    private int getEfecto() {
        int efecto = (int) (Math.random() * 100);
        if (efecto <= 15) {
            return efectoDisparoCritical;
        } else {
            return efectoDisparoNormal;
        }
    }

    public void report() {
        System.out.println("Reporte de cuadricula...");
        System.out.println("Droides enemigos: " + numDroides);
        System.out.println("Droides enemigos muertos: " + droidesEnemigosMuertos());
        System.out.println("Droides enemigos vivos: " + (numDroides - droidesEnemigosMuertos()));
        System.out.println("Disparos realizados: " + disparos);
        System.out.println("Aciertos: " + aciertos);
        System.out.println("Procentaje de aciertos: " + procentajeAciertos() + "%");
        System.out.println("Enemigos ordenados por energia descendente: ");
        ordenarEnemigosPorEnergia();
        imprimirEnemigos();
        System.out.println("Enemigos ordenados por modelo: ");
        ordenarEnemigosPorModelo();
        imprimirEnemigos();
        System.out.println("Existen enemigos: ");
        existenEnemigos();
    }

    private void existenEnemigos() {
        Droide d = droidesEnemigos.get(0);
        System.out.println("Buscando el Droide: " + d);
        if (droidesEnemigos.contains(d)) {
            System.out.println("Existe el droide en la coleccion");
        }
        System.out.println("Y está en la posición: " + droidesEnemigos.indexOf(d));
        int index = Collections.binarySearch(droidesEnemigos, d);
        System.out.println("El primer droide igual a mi en energia (CompareTo): " + droidesEnemigos.get(index));
        // Cuidado con los ordenes que cabia el concepto de max y min
        System.out.println("El droide con Min energía: " + Collections.max(droidesEnemigos));
        System.out.println("El droide con Max energía: " + Collections.min(droidesEnemigos));

    }


    private float procentajeAciertos() {
        float res = 0;
        if (disparos != 0) {
            res = (((float) aciertos) / disparos) * 100;
            return (float) Math.round(res * 100) / 100;
        } else {
            return 0;
        }
    }

    private void ordenarEnemigosPorEnergia() {
        Collections.sort(droidesEnemigos);
        // Collections.reverseOrder(droidesEnemigos);
    }

    private void ordenarEnemigosPorModelo() {
        droidesEnemigos.sort(new DroideModelComparator());
    }

    private void imprimirEnemigos() {
        for (int i = 0; i < droidesEnemigos.size(); i++) {
            System.out.println("[" + i + "] " + droidesEnemigos.get(i));
        }
    }

    private int droidesEnemigosMuertos() {
        int muertos = 0;
        for (int i = 0; i < droidesEnemigos.size(); i++) {
            if (!droidesEnemigos.get(i).isAlive()) {
                muertos++;
            }
        }
        return muertos;
    }
}
