package main;

import java.util.Scanner;

public class Ejercicio11 {
    public static void main(String[] args) {
        // 11. Leer 5 elementos numéricos que se introducirán ordenados de forma creciente. Éstos
        //los guardaremos en una tabla de tamaño 10. Leer un número N, e insertarlo en el lugar
        //adecuado para que la tabla continúe ordenada.
    }

    public int[] ejercicio(Scanner sc) {
        final int cantNumeroAPedir = 5;
        final int arrayLength = 10;
        int[] arrayNumeros = new int[arrayLength];

        pedirNumerosValidos(sc, cantNumeroAPedir, arrayNumeros);
        // ya tengo numeros
        int numeroAInsertar;
        System.out.println("Ingrese el numero que quiere insertar");
        numeroAInsertar = sc.nextInt();

        int[] arrayNumerosConInsertado = new int[arrayLength];

        for (int i = 0; i < arrayNumeros.length; i++) {

            if (numeroAInsertar <= arrayNumeros[i] || arrayNumeros[i] == 0){
                // aqui debe guardarse el numero
                if (i==0){
                    arrayNumerosConInsertado[i] = numeroAInsertar;
                }
                else if (arrayNumeros[i-1] < numeroAInsertar){
                    if (arrayNumeros[i-1] !=0){
                        // INSERTA SIEMPRE ACA UNA VEZ QUE QUEDAN LOS 0
                        arrayNumerosConInsertado[i] = numeroAInsertar;
                    }
                }

                else {
                    // ya se inserto
                    arrayNumerosConInsertado[i] = arrayNumeros[i-1];
                }


            }
            else {
                //todavia son menores los numeros del array, no tiene insertado el numero
                arrayNumerosConInsertado[i] = arrayNumeros[i];
            }
        }
        return arrayNumerosConInsertado;
    }

    private void pedirNumerosValidos(Scanner sc, int cantNumeroAPedir, int[] arrayNumeros) {
        int numIngresadoArray;
        for (int i = 0; i < cantNumeroAPedir; i++) {
            if (i == 0){
                System.out.println("Ingrese un numero para la posicion " + (i+1));
                arrayNumeros[i] = sc.nextInt();
            }
            else {
                do {
                    System.out.println("Ingrese un numero para la posicion " + (i+1) + ". No puede ser 0.");
                    numIngresadoArray = sc.nextInt();
                }while (arrayNumeros[i - 1] >= numIngresadoArray || numIngresadoArray == 0);
                arrayNumeros[i] = numIngresadoArray;
            }
        }
    }
}
