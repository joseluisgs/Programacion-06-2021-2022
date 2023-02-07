package es.joseluisgs.dam.models;

/**
 * Clase que representa un personaje de tipo Smith.
 */
public final class Smith extends Persona implements IPersonaje {
    private final int probInfectar;

    public Smith(Localizacion localizacion, int probInfectar) {
        super("Smith", localizacion);
        this.probInfectar = probInfectar;
        setNombre(nombre + "-" + id);
    }

    public int getProbInfectar() {
        return probInfectar;
    }

    @Override
    public String toString() {
        return "\uD83D\uDE08";
    }


    @Override
    public void pedir() {
    }

    @Override
    public void generar() {
    }

    @Override
    public String mostrar() {
        return "Smith{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localizacion=" + localizacion +
                ", createdAt=" + createdAt +
                ", probInfectar=" + probInfectar +
                '}';
    }
}