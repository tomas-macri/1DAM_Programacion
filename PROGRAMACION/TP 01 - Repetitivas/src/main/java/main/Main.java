package main;

import java.security.cert.TrustAnchor;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;


        do {
            System.out.println("Ejercicios para elegir: Del 10 al 20");
            System.out.println("Seleccione 0 para salir.");
            opcion = sc.nextInt();

            switch (opcion){
                case 10:
                    Ejercicio10 ej10 = new Ejercicio10();
                    ej10.ejercicio10();
                    break;
                case 11:
                    Ejercicio11 ej11 = new Ejercicio11();
                    ej11.ejercicio11(sc);
                    break;
                case 12:
                    Ejercicio12 ej12 = new Ejercicio12();
                    ej12.ejercicio12(sc);
                    break;
                case 13:
                    Ejercicio13 ej13 = new Ejercicio13();
                    ej13.ejercicio13(sc);
                    break;
                case 14:
                    Ejercicio14 ej14 = new Ejercicio14();
                    ej14.ejercicio14(sc);
                    break;
                case 15:
                    Ejercicio15 ej15 = new Ejercicio15();
                    ej15.ejercicio15();
                    break;
                case 16:
                    Ejercicio16 ej16 = new Ejercicio16();
                    ej16.ejercicio16(sc);
                    break;
                case 17:
                    Ejercicio17 ej17 = new Ejercicio17();
                    ej17.ejercicio17(sc);
                    break;
                case 18:
                    Ejercicio18 ej18 = new Ejercicio18();
                    ej18.ejercicio18(sc);
                    break;
                case 19:
                    Ejercicio19 ej19 = new Ejercicio19();
                    ej19.ejercicio19();
                    break;
                case 20:
                    Ejercicio20 ej20 = new Ejercicio20();
                    ej20.ejercicio20(sc);
                    break;
                default:
                    if (opcion != 0){
                        System.out.println("Ejercicio inexistente");
                    }
            }
        }while (opcion != 0);
    }
}



