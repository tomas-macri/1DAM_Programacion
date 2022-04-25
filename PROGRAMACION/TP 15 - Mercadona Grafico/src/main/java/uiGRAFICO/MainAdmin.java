package uiGRAFICO;

import jakarta.inject.Inject;
import common.Constantes;

import java.util.Scanner;

public class MainAdmin {

    private UIAdminProductos uiAdminProductos;
    private UIAdminUsuarios uiAdminUsuarios;
    private MainEstadisticas mainEstadisticas;

    @Inject
    public MainAdmin(UIAdminProductos uiAdminProductos, UIAdminUsuarios uiAdminUsuarios, MainEstadisticas mainEstadisticas) {
        this.uiAdminProductos = uiAdminProductos;
        this.uiAdminUsuarios = uiAdminUsuarios;
        this.mainEstadisticas = mainEstadisticas;
    }

    public void inicioMenuAdmin() {
        Scanner sc = new Scanner(System.in);
        int opcion;


        System.out.println(Constantes.BIENVENIDO_ADMINISTRADOR);

        do {
            do {
                System.out.println(Constantes.MENU_MAIN_ADMINISTRADOR);
                opcion = sc.nextInt();
            } while (opcion < 1 || opcion > 4);
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1:
                    //ir a la ui de usuarios
                    uiAdminUsuarios.inicioUIUsuarios();
                    break;
                case 2:
                    // modificar prod
                    uiAdminProductos.inicioUIProductos();
                    break;
                case 3:
                    mainEstadisticas.mainEstadisticas();
                    break;
                case 4:
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 4);
    }
}
