package ui;

import ui.common.Constantes;

import java.util.Scanner;

public class MainAdmin {

    public void inicioMenuAdmin() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        UIAdminUsuarios uiUsuarios = new UIAdminUsuarios();
        UIAdminProductos uiAdminProductos = new UIAdminProductos();


        System.out.println(Constantes.BIENVENIDO_ADMINISTRADOR);

        do {
            do {
                System.out.println(Constantes.MENU_MAIN_ADMINISTRADOR);
                opcion = sc.nextInt();
            } while (opcion < 1 || opcion > 3);
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1:
                    //ir a la ui de usuarios
                    uiUsuarios.inicioUIUsuarios();
                    break;
                case 2:
                    // modificar prod
                    uiAdminProductos.inicioUIProductos();
                    break;
                case 3:
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 3);
    }
}
