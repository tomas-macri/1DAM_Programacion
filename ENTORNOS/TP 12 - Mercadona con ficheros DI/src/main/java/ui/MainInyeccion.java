package ui;


import jakarta.inject.Inject;
import ui.common.Constantes;

import java.util.Scanner;

public class MainInyeccion {

    private MainRegistrarse mainRegistrarse;
    private MainLogin mainLogin;
    private Scanner sc;

    @Inject
    public MainInyeccion(MainRegistrarse mainRegistrarse, MainLogin mainLogin, Scanner sc) {
        this.mainRegistrarse = mainRegistrarse;
        this.mainLogin = mainLogin;
        this.sc = sc;
    }

    public void mainMain(){
        int opcion;

        System.out.println("BIENVENIDO AL MERCADONA");


        do {
            System.out.println(Constantes.MENU_MAIN);
            opcion = sc.nextInt();
            sc.nextLine();


        switch (opcion){
            case 1:
                mainLogin.inicioLogin();
                break;
            case 2:
                mainRegistrarse.inicioRegistrarse();
                break;
            default:
                break;
        }

        } while (opcion!=3);
    }


}
