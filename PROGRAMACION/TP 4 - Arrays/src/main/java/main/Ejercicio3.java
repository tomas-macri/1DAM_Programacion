package main;

import java.util.Scanner;

public class Ejercicio3 {
    public void ejercicio(Scanner sc) {
//        3. Leer 5 números por teclado y a continuación realizar la media de los números positivos,
//                la media de los negativos y contar el número de ceros.
        final int lengthArray = 5;
        int[] arrayNumeros = new int[lengthArray];
        int contPositivos = 0;
        int contNegativos = 0;
        int acumPositivos = 0;
        int acumNegativos = 0;
        int contCeros = 0;
        for (int i = 0; i < arrayNumeros.length; i++) {
            System.out.println("Ingrese el numero de la posicion " + (i + 1));
            arrayNumeros[i] = sc.nextInt();
            if (arrayNumeros[i] > 0) {
                //positivo
                contPositivos++;
                acumPositivos += arrayNumeros[i];
            } else if (arrayNumeros[i] < 0) {
                //negativo
                contNegativos++;
                acumNegativos += arrayNumeros[i];
            } else {
                contCeros++;
            }
        }
        int mediaPositivos;
        int mediaNegativos;
        String mensajePositivos = "No se ingresaron numeros positivos";
        String mensajeNegativos = "No se ingresaron numeros negativos";
        if (contPositivos != 0) {
            mediaPositivos = acumPositivos / contPositivos;
            mensajePositivos = "La media de los " + contPositivos + " numeros positivos ingresados es de: " + mediaPositivos;
        }
        if (contNegativos != 0) {
            mediaNegativos = acumNegativos / contNegativos;
            mensajeNegativos = "La media de los " + contNegativos + " numeros negativos ingresados es de: " + mediaNegativos;
        }


        System.out.println(mensajePositivos);
        System.out.println(mensajeNegativos);
        System.out.println("Se han ingresado " + contCeros + " numero/s 0 en el array");
    }
}
