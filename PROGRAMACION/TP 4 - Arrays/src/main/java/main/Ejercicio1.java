package main;

import java.util.Scanner;

public class Ejercicio1 {
    public int[] ejercicio(Scanner sc) {
        final int lengthArray = 5;
        int[] arrayNumeros = new int[lengthArray];
        for (int i = 0; i < arrayNumeros.length; i++) {
            System.out.println("Ingrese el numero de la posicion " + (i+1) + " del array: ");
            arrayNumeros[i] = sc.nextInt();
        }
        return arrayNumeros;
    }
}
