package ui;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        System.out.println("BIENVENIDO AL MERCADONA");


        do {
            System.out.println("SELECCIONE UNA OPCION: \n" +
                    "1 - LOGIN \n" +
                    "2 - REGISTRARME \n" +
                    "3 - SALIR");
            opcion = sc.nextInt();
            sc.nextLine();


        switch (opcion){
            case 1:
                MainLogin mainLogin = new MainLogin();
                mainLogin.inicioLogin();
                break;
            case 2:
                MainRegistrarse mainRegistrarse = new MainRegistrarse();
                mainRegistrarse.inicioRegistrarse();
                break;
            default:
                break;
        }

        } while (opcion!=3);
    }


}
