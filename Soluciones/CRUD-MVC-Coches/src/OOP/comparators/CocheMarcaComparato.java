package OOP.comparators;

import OOP.models.Coche;

import java.util.Comparator;


/**
 * Compara los coches por la marca.
 */
public class CocheMarcaComparato implements Comparator<Coche> {
    @Override
    public int compare(Coche c1, Coche c2) {
        return c1.getMarca().compareTo(c2.getMarca());
    }

    @Override
    public Comparator<Coche> reversed() {
        return Comparator.super.reversed();
    }
}
