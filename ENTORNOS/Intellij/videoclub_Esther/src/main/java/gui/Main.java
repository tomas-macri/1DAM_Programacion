package gui;

import com.github.javafaker.Faker;
import modelo.*;
import servicios.ServiciosVideoclub;

import java.util.Scanner;

public class Main {

    public static final String STRING_MENU = "¿Qué desea hacer? \n" +
            "1. Registrar un socio \n" +
            "2. Borrar un socio \n" +
            "3. Añadir un producto nuevo \n" +
            "4. Actualizar el Stock de un producto existente \n" +
            "5. Realizar un nuevo alquiler \n" +
            "6. Devolver un producto alquilado \n" +
            "7. Pagar una multa\n" +
            "8. Salir";
    public static final String ERROR_MENU = "Por favor, dime una de las opciones del menu. Vuelvo a mostrartelo.";
    public static final String GRACIAS_POR_SU_VISITA = "\n GRACIAS POR SU VISITA";


    public static void main(String[] args) {
        Faker f = new Faker();
        Scanner sc = new Scanner(System.in);
        ServiciosVideoclub sv = new ServiciosVideoclub();
        //menu
        int opcion = 0;
        boolean seguir = true;
        MainMethods mm = new MainMethods();
        do {
            do {
                System.out.println(STRING_MENU);
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion < 1 || opcion > 8) {
                    System.out.println(ERROR_MENU);
                }
            } while (opcion < 1 || opcion > 8);
            switch (opcion) {
                case 1:
                    //1. addSocio
                    mm.addSocio(f, sv);
                    Member member;
                    String dni;
                    break;
                case 2:
                    //2. borrarSocio
                    mm.borrarSocio(sc, sv);
                    break;
                case 3:
                    //3. addProducto
                    mm.addProducto(f, sc, sv);
                    break;
                case 4:
                    //4. AddStockProducto
                    mm.addStockProducto(sc, sv);
                    break;
                case 5:
                    //5. Alquilar
                    //encontrar producto,
                    mm.alquilar(sc, sv);
                    break;
                case 6:
                    //6. Devolver
                    mm.devolver(sc, sv);
                    break;
                case 7:
                    //7. pagarMulta
                    mm.pagarMulta(sc, sv);
                    break;
                case 8:
                    seguir = mm.salir();
                    break;
            }
        } while (seguir);
    }











}
