package main;

import java.util.Scanner;

public class Ejercicio11 {
    public static void main(String[] args) {

    }

    public String ejercicio(Scanner sc){
        StringBuilder resultado = new StringBuilder();
        System.out.println("Ingrese una palabra: ");
        String cadena = sc.nextLine();
        System.out.println("Ingrese el caracter que quiere modificar: ");
        char caracter1 = sc.nextLine().charAt(0);
        System.out.println("Ingrese el caracter por el que quiere modificar las " + caracter1 + ": ");
        char caracter2 = sc.nextLine().charAt(0);
        char letra;
        for (int i = 0; i < cadena.length(); i++) {
            letra = cadena.charAt(i);
            if (letra == caracter1){
                letra = caracter2;
            }
            else if (letra == caracter2){
                letra = caracter1;
            }
            resultado.append(letra);
        }




        return resultado.toString();
    }
}
