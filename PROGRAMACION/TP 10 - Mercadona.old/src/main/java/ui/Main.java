package ui;



import modelo.Usuario;
import servicios.ServiciosUsuarios;
import ui.common.Constantes;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args) {
        ServiciosUsuarios serviciosUsuarios = new ServiciosUsuarios();
        Scanner sc = new Scanner(System.in);

        String dniIngresado;
        do {
            System.out.println(Constantes.BIENVENIDO_AL_MERCADONA);
            System.out.println(Constantes.INGRESE_SU_DNI_O_FIN_PARA_SALIR);
            dniIngresado = sc.nextLine();
            Usuario userConEseDni = serviciosUsuarios.getUsuario(dniIngresado);
            if (userConEseDni != null) {
                if (userConEseDni.isAdmin()) {
                    MainAdmin mainAdmin = new MainAdmin();
                    mainAdmin.inicioMenuAdmin();
                } else {
                    MainClientes mainClientes = new MainClientes();
                    mainClientes.inicioMenuClientes(userConEseDni);
                }
            } else {
                System.out.println(Constantes.USUARIO_INEXISTENTE);

            }

        }while (!dniIngresado.equalsIgnoreCase(Constantes.FIN));
    }
}
