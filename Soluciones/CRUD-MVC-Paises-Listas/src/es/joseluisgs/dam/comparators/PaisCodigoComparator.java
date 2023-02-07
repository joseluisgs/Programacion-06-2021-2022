package es.joseluisgs.dam.comparators;

import es.joseluisgs.dam.models.Pais;

import java.util.Comparator;

/**
 * Comparador de países por código.
 */
public class PaisCodigoComparator implements Comparator<Pais> {
    @Override
    public int compare(Pais p1, Pais p2) {
        return p1.getCodigo().compareTo(p2.getCodigo());
    }

    @Override
    public Comparator<Pais> reversed() {
        return Comparator.super.reversed();
    }
}
