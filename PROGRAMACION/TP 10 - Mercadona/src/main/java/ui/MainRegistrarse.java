package ui;

import modelo.Usuario;
import servicios.ServiciosUsuarios;

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
            Usuario unUser = new Usuario(dniCliente, nomCliente);
            if (!dniCliente.equalsIgnoreCase("-1")) {
                if (servicios.agregarusuario(unUser)) {
                    MainClientes mainClientes = new MainClientes();
                    mainClientes.inicioMenuClientes(unUser);
                }
            }
        } while (!dniCliente.equalsIgnoreCase("-1"));
    }
}
