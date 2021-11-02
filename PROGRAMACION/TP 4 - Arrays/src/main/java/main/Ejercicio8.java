package main;

import java.util.Scanner;

public class
Ejercicio8 {
    public int[] ejercicio(Scanner sc) {
//        8. Diseñar una aplicación que declare una tabla de 10 elementos enteros. Leer mediante el
//        teclado 8 números. Después se debe pedir un número y una posición, insertarlo en la
//        posición indicada, desplazando los que estén detrás.

        final int arrayLength = 10;
        int[] arrayNumeros = new int[arrayLength];

        for (int i = 0; i < 8; i++) {
            System.out.println("Ingrese un numero para la posicion " + (i+1));
            arrayNumeros[i] = sc.nextInt();
        }

        System.out.println("Ingrese el numero a insertar");
        int numeroInsertar = sc.nextInt();

        int posicionInsertar = -1;
        do {
            System.out.println("Ingrese la posicion del numero a insertar");
            posicionInsertar = sc.nextInt();
        }while (posicionInsertar < 0 || posicionInsertar > 9);

        int[] arrayNumerosConInsertado = new int[arrayLength];

        for (int i = 0; i < arrayNumeros.length; i++) {
            if (i < posicionInsertar){
                arrayNumerosConInsertado[i] = arrayNumeros[i];
            }
            else if (i==posicionInsertar){
                arrayNumerosConInsertado[i] = numeroInsertar;
            }
            else{
                arrayNumerosConInsertado[i] = arrayNumeros[i-1];
            }
        }
        return arrayNumerosConInsertado;
    }
}
