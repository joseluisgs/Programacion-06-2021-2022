package es.joseluisgs.dam.comparators;

import es.joseluisgs.dam.models.Persona;

import java.util.Comparator;

/**
 * Compraor de personas por fecha de creación en el sistema
 */
public class PersonaFechaComparator implements Comparator<Persona> {
    @Override
    public int compare(Persona o1, Persona o2) {
        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
    }

    @Override
    public Comparator<Persona> reversed() {
        return Comparator.super.reversed();
    }
}
