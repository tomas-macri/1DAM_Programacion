package main;

import java.util.Scanner;

public class Ejercicio7 {
    public String ejercicio(Scanner sc) {
//        7. Leer por teclado una serie de 10 números enteros. La aplicación debe indicarnos si los
//        números están ordenados de forma creciente, decreciente, o si están desordenados.
        final int arrayLength = 10;
        int[] arrayNumeros = new int[arrayLength];
        for (int i = 0; i < arrayNumeros.length; i++) {
            System.out.println("Ingrese el numero de la posicion " + (i+1) + " del array: ");
            arrayNumeros[i] = sc.nextInt();
        }
        boolean ordAscendentes = true;
        boolean ordDescendentes = true;

        // i = 1 porque el primer numero no va a ser mayor, menor o igual que el anterior
        for (int i = 1; i < arrayNumeros.length && (ordAscendentes || ordDescendentes); i++) {
            if (arrayNumeros[i] > arrayNumeros[i-1]){
                // es ascendente respecto al anterior ergo no es descendente
                ordDescendentes = false;
            }
            else  if (arrayNumeros[i] < arrayNumeros[i-1]){
                // es descendente respecto al anterior ergo no es ascendente
                ordAscendentes = false;
            }
            else {
                // si son iguales no es ni ascendente ni descendente
                ordAscendentes = false;
                ordDescendentes = false;
            }
        }
        String mensajeResultado;
        if ((!ordAscendentes && !ordDescendentes)){
            mensajeResultado = "Los numeros ingresados estan desordenados";
        }
        else if (ordAscendentes){
            mensajeResultado = "Los numeros estan ordenados en orden ascendente";
        }
        else {
            mensajeResultado = "Los numeros estan ordenados en orden descendente";
        }

        return mensajeResultado;
    }
}
