package es.joseluisgs.dam.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase que representa una persona en Matrix.
 */
public abstract class Persona implements Comparable<Persona> {
    private static int contador = 0;
    protected int id;
    protected String nombre;
    protected Localizacion localizacion;
    protected LocalDateTime createdAt;

    public Persona(String nombre, Localizacion localizacion) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.createdAt = LocalDateTime.now();
        this.id = ++contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public int getId() {
        return id;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localizacion=" + localizacion +
                ", createdAt=" + createdAt +
                '}';
    }

    // La ordenación por defecto es por id
    @Override
    public int compareTo(Persona o) {
        return this.id - o.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return id == persona.id && Objects.equals(nombre, persona.nombre) && Objects.equals(localizacion, persona.localizacion) && Objects.equals(createdAt, persona.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, localizacion, createdAt);
    }
}

