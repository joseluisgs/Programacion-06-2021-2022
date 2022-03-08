package model.droide;

public class DroideSW4421 extends Droide {
    int velocidad = 100;

    public DroideSW4421(TipoDroide model, int energia) {
        super();
        this.setModel(model);
        this.setEnergia(energia);
    }

    @Override
    public String toString() {
        return "M: " + getModel() +
                ", E: " + getEnergia() +
                ", V: " + velocidad;
    }

}
