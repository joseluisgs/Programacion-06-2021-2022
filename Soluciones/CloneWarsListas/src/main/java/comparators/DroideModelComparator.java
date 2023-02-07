package comparators;

import model.droide.Droide;

import java.util.Comparator;

public class DroideModelComparator implements Comparator<Droide> {
    @Override
    public int compare(Droide o1, Droide o2) {
        return o1.getModel().compareTo(o2.getModel());
    }

    @Override
    public Comparator<Droide> reversed() {
        return Comparator.super.reversed();
    }
}
