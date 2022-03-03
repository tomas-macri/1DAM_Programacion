package main;

import java.util.Random;

public class MainArrays {
    public static void main(String[] args) {
//        1) (3.5Pto) Arrays, tienes 10000 oportunidades para hacer un array con 20 numeros del 0 al 100, que
//        cumpla ciertas condiciones.
//        (1Pto) La media de los valores en posiciones divisbles entre 5, es menor que la suma de los valores
//        pares.
//        (1Pto) Hay un máximo de 7 números primos.
//        (1.5Pto) Hay un máximo de 15 números mayores a la media de la suma de todos los números.

        MainArrays ma = new MainArrays();
        int[] arrayNumeros = new int[20];
        boolean cumpleLasTres = false;


        // variables primera condicion
        int acumDivisiblesCinco = 0;
        int contDivisibleCinco = 0;
        double mediaDivisiblesCinco = 0;
        int acumValoresPares = 0;

        // variables segunda condicion
        int contNumerosPrimos = 0;

        // variables tercera condicion
        int contNumerosMayores = 0;
        int acumTodosNumeros = 0;

        for (int i = 0; i < 10000 && !cumpleLasTres; i++) {
            arrayNumeros = ma.generoArray();
            // tengo el array con los 20 numeros
            for (int j = 0; j < arrayNumeros.length; j++) {
                // primera condicion
                if (arrayNumeros[j] % 5 == 0) {
                    acumDivisiblesCinco += arrayNumeros[j];
                    contDivisibleCinco++;
                }
                if (arrayNumeros[j] % 2 == 0) {
                    acumValoresPares += arrayNumeros[j];
                }

                //segunda condicion
                if (ma.esPrimo(arrayNumeros[j])) {
                    contNumerosPrimos++;
                }

                // inicio tercera condicion
                acumTodosNumeros += arrayNumeros[j];
            }

            mediaDivisiblesCinco = (double) acumDivisiblesCinco / contDivisibleCinco;

            if (mediaDivisiblesCinco < acumValoresPares && contNumerosPrimos <= 7){
                // por ahora se cumplen las primeras dos condiciones, vuelvo a recorrerlo para ver si se cumple la tercera
                for (int j = 0; j < arrayNumeros.length; j++) {
                    if (arrayNumeros[j] > acumTodosNumeros/20){
                        contNumerosMayores++;
                    }
                }
                if (contNumerosMayores <= 15){
                    // la tercera condicion se cumple, me salgo del for
                    cumpleLasTres = true;
                }

            }
        }

        if (cumpleLasTres){
            System.out.println("SE ENCONTRO UN ARRAY QUE CUMPLE LAS CONDICIONES, ES EL SIGUIENTE: ");
            for (int i = 0; i < arrayNumeros.length; i++) {
                System.out.print(arrayNumeros[i]);
                if (i != arrayNumeros.length-1){
                    System.out.print(" - ");
                }
            }
            System.out.println();
            System.out.println("La media de los divisibles por 5 es " + mediaDivisiblesCinco + ", mientras que la suma de los valores pares es " + acumValoresPares);
            System.out.println("Tiene solamente " + contNumerosPrimos + " numeros primos");
            System.out.println("Solo " + contNumerosMayores + " numeros son mayores que la media de los 20 numeros que es " + (double) acumTodosNumeros/20);
        }

        else {
            System.out.println("No se encontro un array que cumpla con las tres condiciones :(");
        }

    }

    private int[] generoArray() {
        int[] arrayNumeros = new int[20];
        Random numRandom = new Random();
        for (int j = 0; j < arrayNumeros.length; j++) {
            arrayNumeros[j] = numRandom.nextInt(101);
        }
        return arrayNumeros;
    }

    private boolean esPrimo(int numero){
        boolean esPrimo = true;
        for (int i = 2; i <= Math.sqrt(numero) && esPrimo; i++){
            if (numero % i == 0){
                esPrimo = false;
            }
        }
        return esPrimo;
    }

}


