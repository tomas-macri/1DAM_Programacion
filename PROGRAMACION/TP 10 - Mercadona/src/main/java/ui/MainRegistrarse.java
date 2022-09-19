package ui;

import modelo.Ingrediente;
import modelo.Usuario;
import servicios.ServiciosUsuarios;
import ui.common.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainRegistrarse {

    public void inicioRegistrarse() {
        ServiciosUsuarios servicios = new ServiciosUsuarios();
        Scanner sc = new Scanner(System.in);
        System.out.println("CREAR UN USUARIO");
        System.out.println();
        String nomCliente;
        String dniCliente;
        do {
            System.out.println("Ingrese su DNI o -1 para volver: ");
            dniCliente = sc.nextLine();
            System.out.println();

            System.out.println("Ingrese su nombre: ");
            nomCliente = sc.nextLine();
            System.out.println();

            List<Ingrediente>  ingredienteArrayList = cargarListIngredientes(sc);
            Usuario unUser = new Usuario(dniCliente, nomCliente, ingredienteArrayList);
            if (!dniCliente.equalsIgnoreCase("-1") && servicios.agregarusuario(unUser)) {
                    MainClientes mainClientes = new MainClientes();
                    mainClientes.inicioMenuClientes(unUser);
            }
        } while (!dniCliente.equalsIgnoreCase("-1"));
    }


    private List<Ingrediente> cargarListIngredientes(Scanner sc) {
        String ingrediente;
        List<Ingrediente> ingredienteList = new ArrayList<>();
        do {
            System.out.println("Ingrese un ingrediente al que es alergico o fin para finalizar");
            ingrediente = sc.nextLine();
            if (!ingrediente.equalsIgnoreCase(Constantes.FIN)) {
                ingredienteList.add(new Ingrediente(ingrediente));
            }
        } while (!ingrediente.equalsIgnoreCase(Constantes.FIN));
        return ingredienteList;
    }
}
