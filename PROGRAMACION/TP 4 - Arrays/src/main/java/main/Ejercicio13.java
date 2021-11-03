package main;

import java.util.Scanner;

public class Ejercicio13 {
    public static void main(String[] args) {
//        13. Leer 10 enteros. Guardar en otra tabla los elementos pares de la primera, y a
//        continuación los elementos impares. Realizar dos versiones: una trabajando con los valores
//        y otra trabajando con los índices.
    }

    public void ejercicio(Scanner sc) {
        final int arrayLength = 10;
        int[] arrayNumeros = new int[arrayLength];
        for (int i = 0; i < arrayNumeros.length; i++) {
            System.out.println("Ingrese un numero para la posicion " + (i+1));
            arrayNumeros[i] = sc.nextInt();
        }
        // tengo numeros
        int[] arrayNumerosPares = new int[arrayLength];
        int[] arrayNumerosImpares = new int[arrayLength];
        int[] arrayIndexPares = new int[arrayLength/2];
        int[] arrayIndexImpares = new int[arrayLength/2];
        int contIndexPares = 0;
        int contIndexImpares = 0;
        int contNumerosPares = 0;
        int contNumerosImpares = 0;

        for (int i = 0; i < arrayNumeros.length; i++) {
            if (i%2 == 0){
                arrayIndexPares[contIndexPares] = arrayNumeros[i];
                contIndexPares++;
            }
            else{
                arrayIndexImpares[contIndexImpares] = arrayNumeros[i];
                contIndexImpares++;
            }
            if (arrayNumeros[i]%2 == 0){
                arrayNumerosPares[contNumerosPares] = arrayNumeros[i];
                contNumerosPares++;
            }
            else{
                arrayNumerosImpares[contNumerosImpares] = arrayNumeros[i];
                contNumerosImpares++;
            }
        }

        System.out.println("Numeros con index par: ");
        soutArrayIndex(arrayLength, arrayIndexPares);

        System.out.println("Numeros con index impar: ");
        soutArrayIndex(arrayLength, arrayIndexImpares);

        System.out.println("Numeros con valor par: ");
        soutArraysNumeros(arrayNumerosPares);

        System.out.println("Numeros con valor impar: ");
        soutArraysNumeros(arrayNumerosImpares);
    }

    private void soutArrayIndex(int arrayLength, int[] arrayIndex) {
        for (int i = 0; i < arrayLength/2; i++) {
            if(i == arrayIndex.length-1){
                System.out.print(arrayIndex[i]);
                System.out.println();
            }
            else{
                System.out.print(arrayIndex[i] + " - ");
            }
        }
    }

    private void soutArraysNumeros(int[] arrayNumeros) {
        for (int i = 0; i < arrayNumeros.length; i++) {
            if(i == arrayNumeros.length-1){
                System.out.print(arrayNumeros[i]);
                System.out.println();
            }
            else{
                System.out.print(arrayNumeros[i] + " - ");
            }
        }

    }
}
