package main;

import java.sql.Array;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {

    }
    public void ejercicio(Scanner sc){
//        Ejercicio 1
//        Escribir por pantalla cada carácter de una cadena introducida por teclado.
        System.out.println("Ingrese una palabra: ");
        String cadena = sc.nextLine();
        for (int i = 0; i < cadena.length(); i++) {
            System.out.println(cadena.charAt(i));
        }
    }
}
