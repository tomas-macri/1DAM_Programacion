package ui;


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

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
            System.out.println("SELECCIONE UNA OPCION: \n" +
                    "1 - LOGIN \n" +
                    "2 - REGISTRARME \n" +
                    "3 - SALIR");
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
