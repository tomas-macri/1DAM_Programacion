package main;

import java.util.Scanner;

public class Ejercicio4 {
    public void ejercicio(Scanner sc) {
//        4. Leer 10 números enteros. Debemos mostrarlos en el siguiente orden: el primero, el
//        último, el segundo, el penúltimo, el tercero, etc.

        final int lengthArray = 10;
        int[] arrayNumeros = new int[lengthArray];

        for (int i = 0; i < arrayNumeros.length; i++) {
            System.out.println("Ingrese un numero");
            arrayNumeros[i] = sc.nextInt();
        }


        for (int i = 0, j = arrayNumeros.length-1; i < j; i++, j--) {
            System.out.println(arrayNumeros[i]);
            System.out.println(arrayNumeros[j]);
        }
    }
}
