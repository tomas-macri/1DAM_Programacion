package main;

import java.util.Scanner;

public class Ejercicio9 {
    public static void main(String[] args) {

    }

    public String ejercicio(Scanner sc) {
        System.out.println("Ingrese una frase: ");
        String cadena = sc.nextLine();
        System.out.println("Ingrese la subcadena: ");
        String subcadena = sc.nextLine();
        int caracter = cadena.indexOf(subcadena);
        String solucion = "La palabra no contiene la subcadena " + subcadena;
        if (caracter != -1) {
            solucion = "La frase contiene la subcadena " + subcadena;
        }
        return solucion;
    }
}

