package OOP.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Coche {
    private static int contador = 0;
    private final int id;
    private String marca;
    private String modelos;
    private String matricula;
    private String color;
    private LocalDateTime anioDeFabricacion;


    public Coche() {
        this.id = ++contador;
    }

    public Coche(String marca, String modelos, String matricula, String color) {
        this.id = ++contador;
        this.marca = marca;
        this.modelos = modelos;
        this.matricula = matricula;
        this.color = color;
        this.anioDeFabricacion = LocalDateTime.now();
    }


    public static void setContador(int contador) {
        Coche.contador = contador;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelos() {
        return modelos;
    }

    public void setModelos(String modelos) {
        this.modelos = modelos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getAnioDeFabricacion() {
        return anioDeFabricacion;
    }

    public void setAnioDeFabricacion(LocalDateTime anioDeFabricacion) {
        this.anioDeFabricacion = anioDeFabricacion;
    }


    @Override
    public String toString() {
        return "Coche{" +
                "Id: " + id +
                " | Marca: " + marca +
                " | Modelos: " + modelos +
                " | Matricula: " + matricula +
                " | Color: " + color +
                " | Año de fabricación: " + anioDeFabricacion +
                '}';
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coche coche = (Coche) o;
        return id == coche.id && Objects.equals(marca, coche.marca) && Objects.equals(modelos, coche.modelos) && Objects.equals(matricula, coche.matricula) && color == coche.color && Objects.equals(anioDeFabricacion, coche.anioDeFabricacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, modelos, matricula, color, anioDeFabricacion);
    }


    public Coche clone(String marca, String modelos, String matricula, String color) {
        return new Coche(this.marca, this.modelos, this.matricula, this.color);
    }
}
