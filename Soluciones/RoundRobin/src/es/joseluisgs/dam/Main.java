package es.joseluisgs.dam;

import es.joseluisgs.dam.comparators.ProcesoQuantumComparator;
import es.joseluisgs.dam.model.Proceso;
import es.joseluisgs.dam.tda.Cola;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Cola<Proceso> RR = new Cola<Proceso>();
        int t = 0;

        while (t <= 60) {
            System.out.println(RR);
            if (t % 2 == 0) {
                System.out.println("Nuevo proceso ha entrado en RR");
                var newProceso = new Proceso((int) (Math.random() * 10) + 2);
                System.out.println("✅ Proceso " + newProceso.getNombre() + " creado");
                RR.encolar(newProceso);
            }
            if (t % 4 == 0) {
                Proceso p_aux = RR.desencolar();
                System.out.println("--> Tratando el proceso " + p_aux.getNombre() + ", " + p_aux.getQuantum());
                p_aux.setQuantum(p_aux.getQuantum() - 2);
                if (p_aux.getQuantum() > 0) {
                    RR.encolar(p_aux);
                } else {
                    System.out.println("\uD83C\uDFC1 Proceso " + p_aux.getNombre() + " terminado");
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t++;
        }

        System.out.println(RR);

        /*RR.encolar(new Proceso(4));
        RR.encolar(new Proceso(3));
        RR.encolar(new Proceso(2));*/

        System.out.println("Cola por orden de quantum");
        LinkedList<Proceso> ordenados = new LinkedList<>(RR);
        ordenados.sort(new ProcesoQuantumComparator());
        for (Proceso p : ordenados) {
            System.out.println(p);
        }

        System.out.println("Mostrando y vaciando cola en su estado de finalización");
        while (!RR.esVacia()) {
            System.out.println(RR.primero().toString()); // Podemos llamarlo con desencolar
            RR.desencolar();
        }
    }
}
