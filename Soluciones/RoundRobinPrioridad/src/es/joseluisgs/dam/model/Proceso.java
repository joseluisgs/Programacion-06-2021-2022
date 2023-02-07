package es.joseluisgs.dam.model;

import java.time.LocalDateTime;

public class Proceso {
    private static int CTOS;
    protected final LocalDateTime createdAt;
    protected String nombre;
    protected int quantum;

    public Proceso() {
        CTOS++;
        quantum = 0;
        nombre = "p" + CTOS;
        createdAt = LocalDateTime.now();
    }

    public Proceso(int quantum) {
        CTOS++;
        nombre = "p" + CTOS;
        this.quantum = quantum;
        createdAt = LocalDateTime.now();
    }

    public Proceso(String nombre, int quantum) {
        this.nombre = nombre + CTOS;
        this.quantum = quantum;
        CTOS++;
        createdAt = LocalDateTime.now();
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
        this.quantum = Math.max(quantum, 0);
    }

    @Override
    public String toString() {
        return "Proceso{" +
                "nombre='" + nombre + '\'' +
                ", quantum=" + quantum +
                ", createdAt=" + createdAt +
                '}';
    }
}
