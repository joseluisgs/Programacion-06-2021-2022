package OOP.comparators;

import OOP.models.Coche;

import java.util.Comparator;


/**
 * Comparador los coches por matrícula
 */
public class CocheMatriculaComparator implements Comparator<Coche> {
    @Override
    public int compare(Coche c1, Coche c2) {
        return c1.getMatricula().compareTo(c2.getMatricula());
    }

    @Override
    public Comparator<Coche> reversed() {
        return Comparator.super.reversed();
    }
}
