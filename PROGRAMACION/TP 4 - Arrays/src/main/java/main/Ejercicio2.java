package main;

import java.util.Scanner;

public class Ejercicio2 {
    public int[] ejercicio(Scanner sc) {
        final int lengthArray = 5;
        int[] arrayNumeros = new int[lengthArray];
        for (int i = arrayNumeros.length-1; i >= 0; i--) {
            System.out.println("Ingrese el numero de la posicion " + (i+1) + " del array: ");
            arrayNumeros[i] = sc.nextInt();
        }
        return arrayNumeros;
    }
}
