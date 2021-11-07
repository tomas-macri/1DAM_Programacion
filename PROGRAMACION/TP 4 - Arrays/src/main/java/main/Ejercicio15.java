package main;

import java.util.Scanner;

public class Ejercicio15 {
//        15. Leer 10 enteros ordenados crecientemente. Leer N y buscarlo en la tabla. Se debe
//        mostrar la posición en que se encuentra. Si no está, indicarlo con un mensaje.

    public String ejercicio(Scanner sc) {
        final int arrayLength = 10;
        int[] arrayNumeros = new int[arrayLength];
        pedirNumerosValidosCrecientes(sc, arrayNumeros);
        System.out.println("De que numero desea saber la posicion?");
        int numeroABuscar = sc.nextInt();
        String mensajeResultado = "El numero " + numeroABuscar + " no se encuentra en el array";
        boolean numEncontrado = false;
        for (int i = 0; i < arrayNumeros.length && !numEncontrado; i++) {
           if (arrayNumeros[i] == numeroABuscar){
               mensajeResultado = "El numero " + numeroABuscar + " se encuentra en la posicion " + i + " del array";
               numEncontrado = true;
           }
        }
        return mensajeResultado;
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
