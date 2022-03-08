package es.joseluisgs.dam;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        // joinExample();
        // splitEcample();
        regexExample();
    }

    private static void regexExample() {
        var texto = "Hola, que tal estas, por aquí, en clase de DAM 12345678v  12345678A caca de la vaca 12345678B";
        var regex = "\\d{8}[A-Z]";

        var existe = texto.matches(regex);
        System.out.println(existe);
        // Sale false, porque matches busca que la cadena completa sea esa expresión regular y reemplazarla por otra cosa
        var sal = texto.replaceAll(regex, "****");
        System.out.println(sal);

        // Para operar con ellas necesitamos un motor de expresiones regulares inicializado al patron
        Pattern pattern = Pattern.compile(regex); // Inicia el objeto en base al patron
        Matcher matcher = pattern.matcher(texto); // Nos devuelve los resultados de operar con el patrḉon en el texto

        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group());
        }

        matcher = pattern.matcher(texto);
        var listaEncontrados = new ArrayList<String>();
        while (matcher.find()) {
            listaEncontrados.add(matcher.group());
        }

        System.out.println(listaEncontrados);
        System.out.println(listaEncontrados.size());


        // Para filtrar datos de entrada
        var dniBueno = "12345678A";
        var dniMalo = "1234678v";
        regex = "\\d{8}[A-Z]";
        existe = dniBueno.matches(regex);
        System.out.println(existe);
        existe = dniMalo.matches(regex);
        System.out.println(existe);

        System.out.println();
        regex = "([a-dA-D,f-gF-G])[1-8]";
        var butacaBuena = "A1";
        var butacaMala1 = "E9";
        var butacaMala2 = "F10";
        var butacaMala3 = "e2";
        var butacaBuena2 = "d8";
        System.out.println(butacaBuena.matches(regex));
        System.out.println(butacaMala1.matches(regex));
        System.out.println(butacaMala2.matches(regex));
        System.out.println(butacaBuena2.matches(regex));
        System.out.println(butacaMala3.matches(regex));

        var butacas = "A1,A2,A3,A4,A5,A6,A7,A8,B1,B2,B3,B4,B5,B6,B7,B8,C1,C2,C3,C4,C5,C6,C7,C8,D1,D2,D3,D4,D5,D6,D7,D8,E1,E2,E3,E4,E5,E6,E7,E8,F1,F2,F3,F4,F5,F6,F7,F8,G1,G2,G3,G4,G5,G6,G7,G8";
        butacas += "A9, B9, C9, D9, E9, F9, G9, H9, I9, J9, K9, L9, M9, N9, O9, P9, Q9, R9, S9, T9, U9, V9, W9, X9, Y9, Z9";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(butacas);
        var listaButacas = new ArrayList<String>();
        while (matcher.find()) {
            listaButacas.add(matcher.group());
        }
        System.out.println(listaButacas);
        System.out.println(listaButacas.size());


    }

    private static void splitEcample() {
        var cad = "Hola, que tal estas, por aquí, en clase de DAM";
        var lista = cad.split(",");
        for (var s : lista) {
            System.out.println(s.trim());
        }
        System.out.println();

        lista = cad.split(" ");
        for (var s : lista) {
            System.out.println(s.trim());
        }
        System.out.println();

        lista = cad.split("e");
        for (var s : lista) {
            System.out.println(s.trim());
        }
    }

    private static void joinExample() {
        var lista = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        var sal = String.join("", lista);
        System.out.println(sal);
        sal = String.join("-", lista);
        System.out.println(sal);
        sal = String.join(";", lista);
        System.out.println(sal);
        sal = "$"+String.join("->", lista)+"$";
        System.out.println(sal);
    }
}
