package main;

import java.util.Scanner;

public class Ejercicio10 {
    public static void main(String[] args) {

    }

    public String ejercicio(Scanner sc) {
        System.out.println("Ingrese una palabra");
        String cadena = sc.nextLine();
        StringBuilder cadenaInvertida = new StringBuilder();
        for (int i = cadena.length() - 1; i >= 0; i--) {
            cadenaInvertida.append(cadena.charAt(i));
        }
        String solucion = "La palabra no es un palíndromo";
        if (cadena.equals(cadenaInvertida.toString())) {
            solucion = "La palabra es un palíndromo";
        }
        return solucion;

    }
}

