package main;

import java.util.Scanner;

public class Ejercicio12 {
//        12. Leer por teclado una tabla de 10 elementos numéricos enteros y leer una posición (entre
//                0 y 9). Eliminar el elemento situado en la posición dada sin dejar huecos.

    public int[] ejercicio(Scanner sc) {
        final int arrayLength = 10;
        Main claseMain = new Main();
        int[] arrayNumeros = claseMain.llenarArray(arrayLength);
        //tengo numeros

        int posicionEliminar;
        do {
            System.out.println("Ingrese una posicion entre 0 y 9: ");
            posicionEliminar = sc.nextInt();
        }while (posicionEliminar<0 || posicionEliminar > 9);
        boolean cambiado = false;
        for (int i = 0; i < arrayNumeros.length && !cambiado; i++) {
            if (i==posicionEliminar){
                for (int j = i; j < arrayNumeros.length-1 ; j++) {
                    //lo reemplazo por la siguiente posicion del array para sobreescribir los numeros
                    arrayNumeros[i] = arrayNumeros[i+1];
                }
                // cambio la ultima posicion por 0 para que la posi 8 y la 9 no sean iguales.
                arrayNumeros[(arrayNumeros.length-1)] = 0;
                cambiado = true;
            }
        }
        return arrayNumeros;
    }
}
