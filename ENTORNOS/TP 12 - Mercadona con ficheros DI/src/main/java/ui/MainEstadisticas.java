package ui;

import jakarta.inject.Inject;
import modelo.Ingrediente;
import servicios.ServiciosEstadisticas;
import ui.common.Constantes;

import java.util.Scanner;

public class MainEstadisticas {

    private ServiciosEstadisticas serviciosEstadisticasImpl;
    private Scanner sc;


    @Inject
    public MainEstadisticas(ServiciosEstadisticas serviciosEstadisticasImpl, Scanner sc){
        this.serviciosEstadisticasImpl = serviciosEstadisticasImpl;
        this.sc = sc;

    }


    public void mainEstadisticas() {
        System.out.println(Constantes.BIENVENIDO_AL_MENU_DE_ESTADISTICAS);

        int opcion;
        do {
            do {
                System.out.println(Constantes.MENU_ESTADISTICAS);
                opcion = sc.nextInt();
                sc.nextLine();
            } while (opcion < 1 || opcion > 4);
            switch (opcion) {
                case 1:
                    System.out.println(serviciosEstadisticasImpl.listaProductosPorOrdenDeCompra());
                    break;
                case 2:
                    String ingrediente;
                    do {
                        System.out.println(Constantes.INGRESE_EL_NOMBRE_DEL_INGREDIENTE_QUE_QUIERE_BUSCAR_EN_LOS_PRODUCTOS_O_FIN_PARA_SALIR);
                        ingrediente = sc.nextLine();
                        System.out.println(serviciosEstadisticasImpl.listaProductosConIngrediente(new Ingrediente(ingrediente)));
                    }while (!ingrediente.equalsIgnoreCase(Constantes.FIN));
                    break;
                case 3:
                    System.out.println(serviciosEstadisticasImpl.listaUsuariosPorGastos());
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (opcion != 4);
    }
}
