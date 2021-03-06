package model.droide;

import java.util.Objects;

public abstract class Droide implements Comparable<Droide> {
    private int energia;
    private TipoDroide model;

    // Principio de responsabilidad única violado.
    // Si soy asbtrracto no debería ejecutarme, solo sirvo de patrón o base de herencia
    public static Droide getRandomDroide() {
        int random = (int) (Math.random() * 10);
        if (random < 3) {
            return new DroideSW447(TipoDroide.SW447, 50);
        } else if (random < 8) {
            return new DroideSW348(TipoDroide.SW348, 100);
        } else {
            int energia = (int) (Math.random() * (150 - 100)) + 100;
            return new DroideSW4421(TipoDroide.SW4421, energia);
        }
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
        if (this.energia < 0) {
            this.energia = 0;
        }
    }

    public boolean isAlive() {
        return this.energia != 0;
    }

    public TipoDroide getModel() {
        return model;
    }

    public void setModel(TipoDroide model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Droide{" +
                "M: " + model +
                ", E: " + energia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Droide droide = (Droide) o;
        return energia == droide.energia && model == droide.model;
    }

    @Override
    public int hashCode() {
        return Objects.hash(energia, model);
    }

    @Override
    public int compareTo(Droide o) {
        // Orden descendente
        return o.energia - this.energia;
    }
}
