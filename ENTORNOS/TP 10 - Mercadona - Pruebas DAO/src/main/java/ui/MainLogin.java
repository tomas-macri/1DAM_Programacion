package ui;



import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;
import modelo.Usuario;
import servicios.ServiciosUsuarios;
import ui.common.Constantes;

import java.util.Scanner;

public class MainLogin
{

    private ServiciosUsuarios serviciosUsuarios;

    @Inject
    public MainLogin(ServiciosUsuarios serviciosUsuarios){
        this.serviciosUsuarios = serviciosUsuarios;
    }

    public void inicioLogin() {
        Scanner sc = new Scanner(System.in);

        String dniIngresado;
        do {
            System.out.println(Constantes.BIENVENIDO_AL_MERCADONA);
            System.out.println(Constantes.INGRESE_SU_DNI_O_FIN_PARA_SALIR);
            dniIngresado = sc.nextLine();
            Usuario userConEseDni = serviciosUsuarios.getUsuario(dniIngresado);
            if (userConEseDni != null) {
                SeContainerInitializer initializer = SeContainerInitializer.newInstance();
                final SeContainer container = initializer.initialize();
                if (userConEseDni.isAdmin()) {
                    MainAdmin mainAdmin = container.select(MainAdmin.class).get();
                    mainAdmin.inicioMenuAdmin();
                } else {
                    MainClientes mainClientes = container.select(MainClientes.class).get();
                    mainClientes.inicioMenuClientes(userConEseDni);
                }
            } else {
                System.out.println(Constantes.USUARIO_INEXISTENTE);

            }

        }while (!dniIngresado.equalsIgnoreCase(Constantes.FIN));
    }
}
