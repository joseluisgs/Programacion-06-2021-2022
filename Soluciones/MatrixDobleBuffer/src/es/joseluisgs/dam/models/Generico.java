package es.joseluisgs.dam.models;

import es.joseluisgs.dam.utils.Probabilidad;

/**
 * Clase que representa un personaje generico.
 */
public class Generico extends Persona implements IPersonaje {
    private float probMorir;


    public Generico(String nombre, Localizacion localizacion) {
        super(nombre, localizacion);
        this.probMorir = Probabilidad.getRandom(0, 100);
    }


    public float getProbMorir() {
        return probMorir;
    }

    public void setProbMorir(float probMorir) {
        this.probMorir = probMorir;
        if (probMorir < 0) {
            this.probMorir = 0;
        }
    }

    @Override
    public String toString() {
        return "\uD83E\uDD28";
    }

    @Override
    public String mostrar() {
        // Usando DecimalFormat
        //
        // DecimalFormat df = new DecimalFormat("#.00");
        // System.out.println(df.format(number));
        // /* Salida : 1.42*/
        //Usando String.Format
        //
        //System.out.println(String.format("%.2f", number));
        ///* Salida : 1.42*/
        return "Generico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localizacion=" + localizacion +
                ", createdAt=" + createdAt +
                ", probMorir=" + String.format("%.2f", probMorir) +
                '}';
    }

    @Override
    public void pedir() {
    }

    @Override
    public void generar() {
    }
}
