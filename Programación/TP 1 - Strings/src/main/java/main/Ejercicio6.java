package main;

import java.util.Scanner;

public class Ejercicio6 {
    public static void main(String[] args) {

    }
    public String ejercicio(Scanner sc){
//        Ejercicio 6
//        Realizar un programa que dada una cadena de caracteres por caracteres, genere otra
//        cadena resultado de invertir la primera.
        System.out.println("Ingrese una frase");
        String cadena = sc.nextLine();
        StringBuilder cadenaInvertida = new StringBuilder();
        for (int i = cadena.length()-1; i >= 0; i--) {
            cadenaInvertida.append(cadena.charAt(i));
        }
        return cadena + " invertida sería: " + cadenaInvertida ;
    }
}
