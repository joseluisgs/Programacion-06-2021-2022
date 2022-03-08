package es.joseluisgs.dam.model;

public class Proceso {
    private String nombre;
    private int quantum;
    private static int CTOS;

    public Proceso() {
        CTOS++;
        quantum=0;
        nombre = "p" + CTOS;
    }

    public Proceso(int quantum) {
        CTOS++;
        nombre = "p" + CTOS;
        this.quantum = quantum;
    }

    public Proceso(String nombre, int quantum) {
        this.nombre = nombre + CTOS;
        this.quantum = quantum;
        CTOS++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public String toString() {
        return "Proceso{" +
                "nombre='" + nombre + '\'' +
                ", quantum=" + quantum +
                '}';
    }
}
