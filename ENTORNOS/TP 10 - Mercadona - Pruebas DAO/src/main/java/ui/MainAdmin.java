package ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import ui.common.Constantes;

import java.util.Scanner;

public class MainAdmin {
    SeContainerInitializer initializer = SeContainerInitializer.newInstance();
    final SeContainer container = initializer.initialize();

    public void inicioMenuAdmin() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        UIAdminUsuarios uiUsuarios = container.select(UIAdminUsuarios.class).get();
        UIAdminProductos uiAdminProductos = container.select(UIAdminProductos.class).get();
        MainEstadisticas mainEstadisticas = container.select(MainEstadisticas.class).get();

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
                    uiUsuarios.inicioUIUsuarios();
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
