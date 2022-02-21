package ui;

import modelo.Usuario;
import servicios.ServiciosCompras;
import servicios.ServiciosUsuarios;
import ui.common.Constantes;

import java.util.Scanner;

public class MainClientes {

    public void inicioMenuClientes(Usuario userLogueado){
        System.out.println(Constantes.BIENVENIDO_AL_MENU_DE_LOS_CLIENTES);
        Scanner sc = new Scanner(System.in);
        int opcion;
        ServiciosCompras serviciosCompras = new ServiciosCompras(userLogueado);
        UIClienteCompras uiClienteCompras = new UIClienteCompras();
        UIClienteTarjetas uiClienteTarjetas = new UIClienteTarjetas();


        System.out.println(Constantes.BIENVENIDO_CLIENTE);

        do {
            do {
                System.out.println(Constantes.ELIGE_UNA_OPCIÓN +
                        Constantes.COMPRAR +
                        Constantes.VER_MIS_COMPRAS_PREVIAS +
                        Constantes.ADMINISTRAR_MIS_TARJETAS +
                        Constantes.SALIR);
                opcion = sc.nextInt();
            } while (opcion < 1 || opcion > 4);
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1:
                    //ir a la ui de compras
                    uiClienteCompras.inicioUICompras(userLogueado);
                    break;
                case 2:
                    // ver las comprar previas
                    System.out.println(serviciosCompras.getComprasPrevias(userLogueado));
                    break;
                case 3:
                    //ir a la ui de tarjetas
                    uiClienteTarjetas.inicioUITarjetas(userLogueado);
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
