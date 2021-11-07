package main;

import java.util.Scanner;

public class Ejercicio14 {
     //14. Leer dos series de 10 enteros, que estarán ordenados crecientemente. Copiar (fusionar)
     //las dos tablas en una tercera, de forma que sigan ordenados.

    public int[] ejercicio(Scanner sc) {
        final int arrayLength = 10;
        int[] arrayNumeros = new int[arrayLength];
        pedirNumerosValidosCrecientes(sc, arrayNumeros);
        int[] arrayNumeros2 = new int[arrayLength];
        pedirNumerosValidosCrecientes(sc, arrayNumeros2);
        // tengo numeros validos
        int[] arrayNumerosTotal = new int[arrayLength*2];
        int contArrayNums1 = 0;
        int contArrayNums2 = 0;
        for (int i = 0; i < arrayNumerosTotal.length; i++) {
            // valido que ningun contador se pase del index maximo antes de comparar valores
            if (contArrayNums1 < arrayNumeros.length && contArrayNums2 < arrayNumeros2.length){
                //comparo valores
                if (arrayNumeros[contArrayNums1] > arrayNumeros2[contArrayNums2])
                {
                    arrayNumerosTotal[i] = arrayNumeros2[contArrayNums2];
                    contArrayNums2++;
                }
                else
                {
                    arrayNumerosTotal[i] = arrayNumeros[contArrayNums1];
                    contArrayNums1++;
                }
            }
            // Me fijo que contador se paso y guardo los valores del otro solamente.
            // Los dos nunca se van a pasar porque la longitud del array es la suma de ambos.
            else if (contArrayNums1 >= arrayNumeros.length)
            {
                arrayNumerosTotal[i] = arrayNumeros2[contArrayNums2];
                contArrayNums2++;
            }
            else{
                arrayNumerosTotal[i] = arrayNumeros[contArrayNums1];
                contArrayNums1++;
            }
        }
        return arrayNumerosTotal;
    }

    private void pedirNumerosValidosCrecientes(Scanner sc, int[] arrayNumeros) {
        int numIngresadoArray;
        for (int i = 0; i < arrayNumeros.length; i++) {
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
