package main;

import java.util.Scanner;

public class Ejercicio6 {
    public int[] ejercicio(Scanner sc) {
//        6. Leer los datos correspondiente a dos tablas de 12 elementos numéricos, y mezclarlos en
//        una tercera de la forma: 3 de la tabla A, 3 de la B, otros 3 de A, otros 3 de la B, etc.
        final int cantDatosPorArray = 3;
        final int lengthArray = 12;
        int[] primerArray = new int[lengthArray];
        int[] segundoArray = new int[lengthArray];
        for (int i = 0; i < primerArray.length; i++) {
            System.out.println("Ingrese un numero para la posicion " + (i+1) + " del array 1: ");
            primerArray[i] = sc.nextInt();
        }

        for (int i = 0; i < segundoArray.length; i++) {
            System.out.println("Ingrese un numero para la posicion " + (i+1) + " del array 2: ");
            segundoArray[i] = sc.nextInt();
        }
        int contPrimerArray = 0;
        int contSegundoArray = 0;
        int[] arrayResultado = new int[lengthArray];
        for (int i = 0; i < primerArray.length/cantDatosPorArray; i+=3) {
            for (int j = 0; j < cantDatosPorArray; j++) {
                arrayResultado[i+j] = primerArray[contPrimerArray];
                //System.out.println(primerArray[contPrimerArray]);
                contPrimerArray++;
            }
            for (int j = 0; j < cantDatosPorArray; j++) {
                arrayResultado[(i+j+3)] = segundoArray[contSegundoArray];
                //System.out.println(segundoArray[contSegundoArray]);
                contSegundoArray++;
            }
        }
        return arrayResultado;
    }
}
