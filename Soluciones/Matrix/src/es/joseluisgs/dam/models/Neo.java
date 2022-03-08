package es.joseluisgs.dam.models;

import es.joseluisgs.dam.utils.Probabilidad;

/**
 * Clase que representa al personaje Neo.
 */
public final class Neo extends Persona implements IPersonaje {

    public Neo() {
        super("Neo", new Localizacion(0, 0, "Luis Vives"));
    }

    /**
     * Método que devuelve una probabilidad de que el personaje indique si es el Elegido.
     *
     * @return
     */
    public boolean isElegido() {
        return Probabilidad.getRandom(0, 100) < 50;
    }

    @Override
    public String toString() {
        return "\uD83D\uDE0E";
    }

    @Override
    public String mostrar() {
        return "Neo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localizacion=" + localizacion +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public void pedir() {
    }

    @Override
    public void generar() {
    }
}