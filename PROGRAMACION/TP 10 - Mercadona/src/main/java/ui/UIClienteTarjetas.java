package ui;

import ui.common.Constantes;

import java.util.Scanner;

public class UIClienteTarjetas {

    public void inicioUITarjetas(){
        System.out.println("BIENVENIDO AL MENU DE LAS TARJETAS");
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            do {
                System.out.println("Elige una opción: \n " +
                        "1 - Agregar tarjeta \n " +
                        "2 - Modificar tarjeta \n " +
                        "3 - Eliminar tarjeta \n " +
                        "4 - Ver mis tarjetas \n " +
                        "5 - Salir");
                opcion = sc.nextInt();
            } while (opcion < 1 || opcion > 4);
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar tarjeta
                    break;
                case 2:
                    //modificar tarjeta
                    break;
                case 3:
                    //eliminar tarjeta
                case 4:
                    // ver tarjetas
                    break;
                case 5:
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 4);
    }
}
