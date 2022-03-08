package es.joseluisgs.dam.comparators;

import es.joseluisgs.dam.models.Pais;

import java.util.Comparator;

/**
 * Comparador de países por nombre.
 */
public class PaisNombreComparator implements Comparator<Pais> {
    @Override
    public int compare(Pais p1, Pais p2) {
        return p1.getNombre().compareTo(p2.getNombre());
    }
}
