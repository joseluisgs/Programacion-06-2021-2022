package es.joseluisgs.dam.model;

public class ProcesoPrioritario extends Proceso implements Comparable<ProcesoPrioritario> {
    private int prioridad;

    public ProcesoPrioritario(int quatum, int prioridad) {
        // Super esta ya inicializado con el constructor de Proceso
        this.prioridad = prioridad;
        this.quantum = quatum;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "ProcesoProritario{" +
                "nombre='" + nombre + '\'' +
                ", quantum=" + quantum +
                ", prioridad=" + prioridad +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public int compareTo(ProcesoPrioritario o) {
        // Descedente....
        return o.prioridad - this.prioridad;
    }
}
