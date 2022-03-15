package OOP.views;

import OOP.CocheController.CocheController;
import OOP.comparators.CocheMatriculaComparator;
import OOP.exceptions.CocheException;
import OOP.models.Coche;
import OOP.utils.ComprobarMatricula;
import OOP.utils.Input;

import java.util.List;

public class CocheViews {
    public static final CocheController cocheController = CocheController.getInstance();

    public CocheViews() throws CocheException {
        menu();
    }

    public void menu() throws CocheException {
        System.out.println("Gestión de Coches");
        System.out.println("🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗");

        do {
            System.out.println("1. Crear coche \n" +
                                "2. Eliminar coche \n" +
                                "3. Actualizar coche \n" +
                                "4. Mostrar la lista de coche \n" +
                                "5. Salir \n");

            String opcion = Input.getString("Selecciona la la opcion de tu preferencia [1-5]");
            var regex = "[1-5]";
            if(opcion.matches(regex)){
                switch(opcion){
                    case "1":
                        crearCoche();
                        break;
                    case "2":
                        eliminarCoche();
                        break;
                    case "3":
                        actualizarCoche();
                        break;
                    case "4":
                        mostrarCoches();
                        break;
                    case "5":
                        salir();
                        break;
                }
            }

        }while (true);



    }

    /**
     * Crear un coche.
     * @throws CocheException Comprobamos que la matrícula sea correcta.
     */
    private static void crearCoche()throws CocheException {

        System.out.println("Crear un coche.");
        System.out.println("Comprobamos si la matricula es correcta.");
        String matricula = Input.getString("Matricula del coche: ");

        if (ComprobarMatricula.isValid(matricula)){
            System.out.println("Los datos del coche no pueden estar vacíos..");
            String marca = Input.getString("Marca del coche: ");
            String modelo = Input.getString("Modelo del coche: ");
            String color = Input.getString("Color del coche: ");

            Coche coche = new Coche(marca, modelo, matricula, color);

            try {
                var res = cocheController.crearCoche(coche);
                System.out.println("Coche creado correctamente.");
                System.out.println(res);
            }catch (Exception e){
                System.out.println("Error al crear el coche: "+ e.getMessage());
            }
        }else {
            throw new CocheException("La matricula "+matricula+ " es incorrecta");
        }
    }


    /**
     * Eliminar coche
     */
    private static void eliminarCoche(){
        System.out.println("Eliminar coche");
        String matricula = Input.getString("Matricula del coche");
        if (ComprobarMatricula.isValid(matricula)){
            try {
                var res = cocheController.deleteByMatricula(matricula);
                System.out.println("Coche eliminado correctamente.");
                System.out.println(res);
            }catch (Exception e){
                System.out.println("Error al eliminar: "+ e.getMessage());
            }

        }

    }


    /**
     * Actualizar coche por matricula
     * @throws CocheException si la matricula es incorrecta.
     */
    private static void actualizarCoche() throws CocheException {
        System.out.println("Actualizar coche");
        String matricula = Input.getString("Matricula del coche");
        if (ComprobarMatricula.isValid(matricula)){
            System.out.println("Introduce los nuevos datos de la matricula");
            try {
                var existe =cocheController.getCocheByMatricula(matricula);

                String nuevaMatricula = Input.getString("Introduce la nueva matricula: ");
                if (ComprobarMatricula.isValid(matricula)){
                    String nuevaMarca = Input.getString("Nueva marca del coche: ");
                    nuevaMarca = (nuevaMarca.isEmpty()) ? existe.getMarca() : nuevaMarca;
                    String nuevoModelo =Input.getString("Modelos del nuevo coche: ");
                    nuevoModelo = (nuevaMarca.isEmpty()) ? existe.getModelos() : nuevoModelo;
                    String nuevoColor = Input.getString("Introduce el nuevo color: ");
                    nuevoColor = (nuevoColor.isEmpty()) ? existe.getColor() : nuevoColor;

                    var update = existe.clone(nuevaMarca, nuevoModelo, nuevaMatricula, nuevoColor);

                    var res = cocheController.updateCoche(existe.getMatricula(), update);
                    System.out.println("Coche actualizado");
                    System.out.println(res);
                }else {
                    throw new CocheException("La matricula "+nuevaMatricula+ " es incorrecta");
                }


            }catch (Exception e){
                System.out.println("Error al actualizar el coche " + e.getMessage());
            }
        }else {
            throw new CocheException("La matricula del coche que deseas actualizar es incorrecta");
        }
    }


    /**
     * Mostrar la lista de coches
     */
    private static void mostrarCoches() {
        System.out.println("Mostrar lista de coches");
        List<Coche> coches = cocheController.getAllCoches();

        for (var coche: coches) {
            System.out.println(coche);
        }
    }


    /**
     * Salir
     */
    private static void salir() {
        System.out.println("Adios...");
        System.exit(0);
    }


}
