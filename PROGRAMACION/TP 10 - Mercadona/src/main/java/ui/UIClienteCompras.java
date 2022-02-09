package ui;

import servicios.ServiciosProductos;
import ui.common.Constantes;

import java.util.Scanner;

public class UIClienteCompras {

    public void inicioUICompras() {
        Scanner sc = new Scanner(System.in);
        UIClienteCompras uiClienteCompras = new UIClienteCompras();
        int opcion;
        System.out.println("BIENVENIDO AL MENU DE COMPRAS");
        System.out.println();

        do {
            opcion = uiClienteCompras.mostrarMenu(sc);
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar productos a la compra
                    break;
                case 2:
                    // eliminar prod de la compra
                    break;
                case 3:
                    // mostrar productos
                    // System.out.println(serviciosProductos.getLista());

                    // System.out.println();
                    break;
                case 4:
                    //pagar
                case 5:
                    // salir
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }


    private int mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("Elija una opcion: \n" +
                    "1 - Comprar Productos \n" +
                    "2 - Eliminar Productos de la Compra \n" +
                    "3 - Ver todos los Productos \n" +
                    "4 - Pagar \n" +
                    "5 - Salir ");

            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }
}
