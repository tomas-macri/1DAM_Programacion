package main;

import java.util.Scanner;

public class Ejercicio12 {
    public static void main(String[] args) {

    }
    public void ejercicio12(Scanner sc) {
//        Ejercicio 12
//        Realizar un algoritmo para determinar cuánto ahorrará una persona en un año, si al final de
//        cada mes deposita cantidades variables de dinero; además, se quiere saber cuánto lleva
//        ahorrado cada mes.

        int acumuladorAhorro = 0;

        for (int i = 1; i<=12; i++){
            System.out.println("Por ahora tienes ahorrado " + acumuladorAhorro + "€");
            System.out.print("Cuánto ahorraste el mes " + i + "? ");
            acumuladorAhorro += sc.nextInt();
            sc.nextLine();

        }
        System.out.println("Este año has ahorrado un total de: " + acumuladorAhorro + "€");
    }
}
