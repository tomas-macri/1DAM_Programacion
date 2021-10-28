package main;

import java.util.Scanner;

public class Ejercicio20 {

    public static void main(String[] args) {

    }
    public void ejercicio20(Scanner sc) {
//        Ejercicio 20
//        Mostrar en pantalla los N primero número primos. Se pide por teclado la cantidad de
//        números primos que queremos mostrar
        System.out.println("Ingrese la cantidad de números primos que desea ver:");
        int cantPrimos = sc.nextInt();
        sc.nextLine();
        int contadorNumeros = 2;
        boolean esPrimo = true;
        // FORMULA PRIMOS
        for (int i = 0; i < cantPrimos;){

            for (int j = 2; j <= Math.sqrt(contadorNumeros); j++) {
                if (contadorNumeros % j == 0) {
                    esPrimo = false;
                    break;
                }
            }
            if (esPrimo) {
                System.out.print(contadorNumeros + " ");
                i++;
            }
            esPrimo = true;
            contadorNumeros++;

        }
        System.out.println();
    }

}
