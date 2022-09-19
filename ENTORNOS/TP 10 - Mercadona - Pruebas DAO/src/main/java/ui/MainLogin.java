package ui;



import jakarta.inject.Inject;
import modelo.Usuario;
import servicios.ServiciosUsuarios;
import ui.common.Constantes;

import java.util.Scanner;

public class MainLogin
{

    private ServiciosUsuarios serviciosUsuariosImpl;
    private MainAdmin mainAdmin;
    private MainClientes mainClientes;

    @Inject
    public MainLogin(ServiciosUsuarios serviciosUsuariosImpl, MainAdmin mainAdmin, MainClientes mainClientes){
        this.serviciosUsuariosImpl = serviciosUsuariosImpl;
        this.mainAdmin = mainAdmin;
        this.mainClientes = mainClientes;
    }

    public void inicioLogin() {
        Scanner sc = new Scanner(System.in);

        String dniIngresado;
        do {
            System.out.println(Constantes.BIENVENIDO_AL_MERCADONA);
            System.out.println(Constantes.INGRESE_SU_DNI_O_FIN_PARA_SALIR);
            dniIngresado = sc.nextLine();
            Usuario userConEseDni = serviciosUsuariosImpl.getUsuario(dniIngresado);
            if (userConEseDni != null) {
                if (userConEseDni.isAdmin()) {
                    mainAdmin.inicioMenuAdmin();
                } else {
                    mainClientes.inicioMenuClientes(userConEseDni);
                }
            } else {
                System.out.println(Constantes.USUARIO_INEXISTENTE);

            }

        }while (!dniIngresado.equalsIgnoreCase(Constantes.FIN));
    }
}
