package main;

import java.util.Scanner;

public class Ejercicio10 {
    public int[] ejercicio(Scanner sc) {
//        10. Ídem, desplazar N posiciones (N es introducido por el usuario).
        final int arrayLength = 10;
        int[] arrayNumeros = new int[arrayLength];
        for (int i = 0; i < arrayNumeros.length; i++) {
            System.out.println("Ingrese el numero " + (i+1) + " del array: ");
            arrayNumeros[i] = sc.nextInt();
        }
        System.out.println("Ingrese cuantas posiciones quiere desplazar el array");
        int cantPosiciones = sc.nextInt();
        int[] arrayNumerosDesplazados = new int[arrayLength];
        for (int i = 0; i < arrayNumeros.length; i++) {
            // agrego una vuelta por si el usuario quiere restar posiciones
            arrayNumerosDesplazados[(((i+cantPosiciones) + arrayNumerosDesplazados.length)%arrayNumerosDesplazados.length)] = arrayNumeros[i];
        }
        return arrayNumerosDesplazados;
    }
}
