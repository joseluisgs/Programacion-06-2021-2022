package es.joseluisgs.dam;

import es.joseluisgs.dam.comparators.ProcesoQuantumComparator;
import es.joseluisgs.dam.model.Proceso;
import es.joseluisgs.dam.model.ProcesoPrioritario;
import es.joseluisgs.dam.tda.ColaPrioritaria;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        ColaPrioritaria<ProcesoPrioritario> RR = new ColaPrioritaria<ProcesoPrioritario>();
        int t = 0;

        while (t <= 60) {
            System.out.println(RR);
            if (t % 2 == 0) {
                System.out.println("Nuevo proceso ha entrado en RR");
                // Prioridad entre 1 y 9. Tiempo de procesasor 2 y 12
                var newProceso = new ProcesoPrioritario(
                        (int) (Math.random() * 10) + 2,
                        (int) (Math.random() * 9) + 1
                );
                System.out.println("✅ Proceso Prioritario " + newProceso.getNombre() + " creado con prioridad " + newProceso.getPrioridad());
                RR.encolar(newProceso);
            }
            if (t % 4 == 0) {
                ProcesoPrioritario p_aux = RR.desencolar();
                System.out.println("--> Tratando el proceso " + p_aux.getNombre() + ", " +
                        p_aux.getQuantum() + " ," + p_aux.getPrioridad());
                p_aux.setQuantum(p_aux.getQuantum() - 2);
                if (p_aux.getQuantum() > 0) {
                    RR.encolar(p_aux);
                } else {
                    System.out.println("\uD83C\uDFC1 Proceso " + p_aux.getNombre() + ", " +
                            p_aux.getQuantum() + " ," + p_aux.getPrioridad() + " terminado");
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t++;
        }
        System.out.println("\uD83D\uDEA8 Fin de la simulacion");
        System.out.println("Cola en represantación toString, no tiene porque estar ordenada!!");
        System.out.println(RR);
        System.out.println();

        System.out.println("Cola por orden de quantum");
        LinkedList<Proceso> ordenados = new LinkedList<>(RR);
        ordenados.sort(new ProcesoQuantumComparator());
        for (Proceso p : ordenados) {
            System.out.println(p);
        }
        System.out.println();

        System.out.println("Mostrando y vaciando cola en su estado de finalización. Esta ordenada por Prioridad");
        // Aquí el encolar y el desencolar sí funcionan con prioridad
        while (!RR.esVacia()) {
            System.out.println(RR.primero().toString()); // Podemos llamarlo con desencolar
            RR.desencolar();
        }
    }
}
