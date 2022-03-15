package OOP.utils;

public class ComprobarMatricula {

    /**
     * Comprobamos si la matricula es correcta.
     * @param matricula a comprobar
     * @return Devuelve true si es correcta false si es incorrecta.
     */
    public static boolean isValid(String matricula){
        var regex1 = "[0-9]{4}[a-zA-Z]{3}";
        return matricula.matches(regex1);
    }


}
