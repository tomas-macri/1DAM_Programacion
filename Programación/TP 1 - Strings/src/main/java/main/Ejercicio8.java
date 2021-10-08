package main;

import java.util.Scanner;

public class Ejercicio8 {
    public static void main(String[] args) {

    }

    public String ejercicio(Scanner sc) {
//        Ejercicio 8
//        Realizar un programa que lea una cadena por teclado y convierta las mayúsculas a
//        minúsculas y viceversa.
        System.out.println("Ingrese una palabra: ");
        String cadena = sc.nextLine();
        StringBuilder resultado;
        // REEMPLAZA TODOS LOS CARACTERES DE LA CADENA, REVISAR;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) >= 'A' && cadena.charAt(i) <= 'Z') {
                cadena = cadena.replace(cadena.charAt(i), (char) (cadena.charAt(i) + 32));
            }
            else if (cadena.charAt(i) >= 'a' && cadena.charAt(i) <= 'z') {
                cadena = cadena.replace(cadena.charAt(i), (char) (cadena.charAt(i) - 32));
            }
            else if (cadena.charAt(i) == 'ñ') {
                cadena = cadena.replace(cadena.charAt(i), (char) (cadena.charAt(i) + 1));
            }
            else {
                cadena = cadena.replace(cadena.charAt(i), (char) (cadena.charAt(i) - 1));
            }
        }
        return cadena;
    }
}
