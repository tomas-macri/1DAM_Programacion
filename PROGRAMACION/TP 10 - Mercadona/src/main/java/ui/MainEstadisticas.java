package ui;

import servicios.SeviciosEstadisticas;
import ui.common.Constantes;

import java.util.Scanner;

public class MainEstadisticas {

    public void mainEstadisticas() {
        System.out.println(Constantes.BIENVENIDO_AL_MENU_DE_ESTADISTICAS);

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            do {
                System.out.println(Constantes.MENU_ESTADISTICAS);
                opcion = sc.nextInt();
                sc.nextLine();
            } while (opcion < 1 || opcion > 4);
            SeviciosEstadisticas seviciosEstadisticas = new SeviciosEstadisticas();
            switch (opcion) {
                case 1:
                    System.out.println(seviciosEstadisticas.listaProductosPorOrdenDeCompra());
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        } while (opcion != 4);
    }
}
