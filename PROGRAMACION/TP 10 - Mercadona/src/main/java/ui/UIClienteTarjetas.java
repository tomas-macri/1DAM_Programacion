package ui;

import modelo.Tarjeta;
import modelo.Usuario;
import servicios.ServiciosTarjetas;
import ui.common.Constantes;

import java.util.Scanner;

public class UIClienteTarjetas {

    public void inicioUITarjetas(Usuario usuarioLogueado){
        System.out.println("BIENVENIDO AL MENU DE LAS TARJETAS");
        Scanner sc = new Scanner(System.in);
        ServiciosTarjetas serviciosTarjetas = new ServiciosTarjetas();
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
            } while (opcion < 1 || opcion > 5);
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar tarjeta
                    agregarTarjeta(sc, usuarioLogueado);
                    break;
                case 2:
                case 3:
                    //eliminar tarjeta
                    //modificar tarjeta
                    estamosTrabajando();
                    break;
                case 4:
                    // ver tarjetas
                    System.out.println(serviciosTarjetas.devolverLista(usuarioLogueado));
                    System.out.println();
                    break;
                case 5:
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }

    private void estamosTrabajando() {
        System.out.println("Estamos trabajando... INTENTE MAS TARDE");
        System.out.println();
    }


    private void agregarTarjeta(Scanner sc, Usuario userLogueado) {
        ServiciosTarjetas servicios = new ServiciosTarjetas();

        System.out.println("AGREGAR TARJETA");
        System.out.println();
        String nombTarjeta;
        int saldoTarjeta;
        int opcion;
        do {
            System.out.println("Ingrese el nombre de la nueva tarjeta: ");
            nombTarjeta= sc.nextLine();
            System.out.println();

            System.out.println("Ingrese el saldo de la nueva tarjeta: ");
            saldoTarjeta = sc.nextInt();
            sc.nextLine();
            System.out.println();
            Tarjeta unaTarjeta = new Tarjeta(nombTarjeta, saldoTarjeta);

            if (servicios.agregarTarjeta(unaTarjeta, userLogueado)) {
                System.out.println(Constantes.SE_AGREGO + unaTarjeta + "a tu lista de tarjetas");
            } else {
                System.out.println("La tarjeta ya se encontraba en tu lista de tarjetas o algunos de los campos ingresados no es válido");
            }
            System.out.println("Ingrese 1 si quiere agregar otra tarjeta o cualquier otro número para salir");
            opcion = sc.nextInt();
            sc.nextLine();

        } while (opcion == 1);
        System.out.println();
        System.out.println("Saliendo de agregar tarjeta...");
        System.out.println();
    }
}
