package ui;


import dao.BD;
import dao.DaoUsuarios;
import modelo.Producto;
import modelo.Usuario;
import servicios.ServiciosUsuarios;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        ServiciosUsuarios serviciosUsuarios = new ServiciosUsuarios();
        Scanner sc = new Scanner(System.in);

        String dniIngresado;
        do {
            System.out.println(serviciosUsuarios.getLista().toString());
            System.out.println("Bienvenido al Mercadona");
            System.out.println("Ingrese su DNI o fin para salir: ");
            dniIngresado = sc.nextLine();
            Usuario userConEseDni = serviciosUsuarios.getUsuario(dniIngresado);
            if (userConEseDni != null) {
                if (userConEseDni.isAdmin()) {
                    MainAdmin mainAdmin = new MainAdmin();
                    mainAdmin.inicioMenuAdmin();
                } else {
                    MainClientes mainClientes = new MainClientes();
                    mainClientes.inicioMenuClientes();
                }
            } else {
                System.out.println("Usuario INEXISTENTE");

            }

        }while (!dniIngresado.equalsIgnoreCase("fin"));
    }
}
