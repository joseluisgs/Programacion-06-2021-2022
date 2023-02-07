package es.joseluisgs.dam.models;

/**
 * Clase que representa una localización de procedencia de un personaje.
 */
public class Localizacion {
    private int longitud;
    private int latitud;
    private String ciudad;

    public Localizacion() {
        this.longitud = 0;
        this.latitud = 0;
        this.ciudad = "";
    }

    public Localizacion(int longitud, int latitud, String ciudad) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.ciudad = ciudad;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Localizacion{" +
                "longitud=" + longitud +
                ", latitud=" + latitud +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
