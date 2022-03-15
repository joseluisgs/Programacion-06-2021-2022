package OOP.factories;

import OOP.models.Coche;
import OOP.utils.Probabilidad;

import java.util.List;

public class CocheFactory {

    public static Coche createCocherandom(){
        var pos = 0;
        var marcaRnadom = "";
        var modeloRandom = "";
        var matriculasRandom = "";
        var coloresRandom = "";

        //Lista de marcas.
        var marca = List.of("Honda", "Toyota", "Mazda", "Seat", "Fiat", "Lexus");

        //Lista de modelos de cada una de las marcas.
        var ModelosHonda = List.of("Accord", "CR-V", "Prelude", "Legend", "Civic", "Brio");
        var ModelosToyota = List.of("Auris", "Corolla", "Camry", "Yaris", "Rav4", "Aygo");
        var ModelosMazda = List.of("Mazda 3", "Mazda 6", "Mazda CX-3", "Mazda CX-30", "Mazda MX-5", "Mazda 2");
        var ModelosSeat = List.of("Leon", "Ibiza", "Cordoba", "Toledo");
        var ModelosFiat = List.of("500", "Ducato", "Panda", "Talento", "Doblo");
        var ModelosLexus = List.of("GS", "LFA", "LX", "RC", "RX", "RCF");

        pos = Probabilidad.getRandom(0, marca.size());
        marcaRnadom = marca.get(pos);

        if (marcaRnadom.equals("Honda")){
            pos = Probabilidad.getRandom(0, ModelosHonda.size());
            modeloRandom = ModelosHonda.get(pos);
        }
        if (marcaRnadom.equals("Toyota")){
            pos = Probabilidad.getRandom(0, ModelosToyota.size());
            modeloRandom = ModelosToyota.get(pos);
        }
        if (marcaRnadom.equals("Mazda")){
            pos = Probabilidad.getRandom(0, ModelosMazda.size());
            modeloRandom = ModelosMazda.get(pos);
        }
        if (marcaRnadom.equals("Seat")){
            pos = Probabilidad.getRandom(0, ModelosSeat.size());
            modeloRandom = ModelosSeat.get(pos);
        }
        if (marcaRnadom.equals("Fiat")){
            pos = Probabilidad.getRandom(0, ModelosFiat.size());
            modeloRandom = ModelosFiat.get(pos);
        }
        if (marcaRnadom.equals("Lexus")){
            pos = Probabilidad.getRandom(0, ModelosLexus.size());
            modeloRandom = ModelosLexus.get(pos);
        }


        var matriculas = List.of("1234BHC", "4567MKD", "1345YUI", "3579RDE", "0842ADR", "6091LER");
        pos = Probabilidad.getRandom(0, matriculas.size());
        matriculasRandom = matriculas.get(pos);

        var colores = List.of("Rojo", "Azul", "Negro", "Amarillo", "Gris", "Rosa");
        pos = Probabilidad.getRandom(0, colores.size());
        coloresRandom = matriculas.get(pos);

        return new Coche(marcaRnadom, modeloRandom, matriculasRandom, coloresRandom);

    }
}
