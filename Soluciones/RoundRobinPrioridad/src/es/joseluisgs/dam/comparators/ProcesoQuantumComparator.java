package es.joseluisgs.dam.comparators;

import es.joseluisgs.dam.model.Proceso;

import java.util.Comparator;

public class ProcesoQuantumComparator implements Comparator<Proceso> {
    @Override
    public int compare(Proceso o1, Proceso o2) {
        return o1.getQuantum() - o2.getQuantum();
    }

    @Override
    public Comparator<Proceso> reversed() {
        return Comparator.super.reversed();
    }
}
