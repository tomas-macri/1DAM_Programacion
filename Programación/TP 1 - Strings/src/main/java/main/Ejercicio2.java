package main;

import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {

    }

    public String ejercicio(Scanner sc) {
//        Ejercicio 2
//        Realizar un programa que comprueba si una cadena leída por teclado comienza por una
//        subcadena introducida por teclado.
        System.out.println("Ingrese una palabra: ");
        String cadena = sc.nextLine();
        System.out.println("Ingrese la subcadena: ");
        String subcadena = sc.nextLine();
        int caracter = cadena.indexOf(subcadena);
        String solucion = "La palabra no comienza con " + subcadena;
        if (caracter == 0) {
            solucion = "La palabra comienza con " + subcadena;
        }
        return solucion;
    }
}
