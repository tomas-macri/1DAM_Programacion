package ui;

import modelo.Ingrediente;
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
                    String ingrediente;
                    do {
                        System.out.println(Constantes.INGRESE_EL_NOMBRE_DEL_INGREDIENTE_QUE_QUIERE_BUSCAR_EN_LOS_PRODUCTOS_O_FIN_PARA_SALIR);
                        ingrediente = sc.nextLine();
                        System.out.println(seviciosEstadisticas.listaProductosConIngrediente(new Ingrediente(ingrediente)));
                    }while (!ingrediente.equalsIgnoreCase(Constantes.FIN));
                    break;
                case 3:
                    System.out.println(seviciosEstadisticas.listaUsuariosPorGastos());
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (opcion != 4);
    }
}
