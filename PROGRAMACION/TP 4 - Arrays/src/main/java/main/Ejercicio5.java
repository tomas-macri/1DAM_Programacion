package main;

import java.util.Scanner;

public class Ejercicio5 {
    public int[] ejercicio(Scanner sc) {
        //5. Leer por teclado dos tablas de 10 números enteros y mezclarlas en una tercera de la
        //forma: el 1º de A, el 1º de B, el 2º de A, el 2º de B, etc.
        int[] tabla1 = new int[10];
        for (int i = 0; i < tabla1.length; i++) {
            System.out.println("Ingrese el numero de la posicion " + (i+1) + " de la tabla 1: ");
            tabla1[i] = sc.nextInt();
        }

        int[] tabla2 = new int[10];
        for (int i = 0; i < tabla2.length; i++) {
            System.out.println("Ingrese el numero de la posicion " + (i+1) + " de la tabla 2: ");
            tabla2[i] = sc.nextInt();
        }

        int[] tablaCombinada = new int[20];
        int contTabla1 = 0;
        int contTabla2 = 0;
        for (int i = 0; i < tablaCombinada.length; i++) {
            if (i%2==0){
                tablaCombinada[i] = tabla1[contTabla1];
                contTabla1++;
            }
            else {
                tablaCombinada[i] = tabla2[contTabla2];
                contTabla2++;
            }
        }

        return tablaCombinada;
    }
}
