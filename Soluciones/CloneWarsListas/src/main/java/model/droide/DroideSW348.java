package model.droide;

import java.util.UUID;

public class DroideSW348 extends Droide implements IDefensa {
    String numSerie = UUID.randomUUID().toString().substring(0, 5);

    public DroideSW348(TipoDroide model, int energia) {
        super();
        this.setModel(model);
        this.setEnergia(energia);
    }

    @Override
    public String toString() {
        return "M: " + getModel() +
                ", E: " + getEnergia() +
                ", NS: " + numSerie;
    }

    @Override
    public int defender(int ataque) {
        int defensa = (int) Math.round(Math.random() * (12 - 3) + 3);
        return Math.max(0, ataque - defensa);
    }
}
