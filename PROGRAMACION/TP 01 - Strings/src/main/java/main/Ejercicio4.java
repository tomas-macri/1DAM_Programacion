package main;

import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {

    }
    public String ejercicio(Scanner sc){
//        Ejercicio 4
//        Suponiendo que hemos introducido una cadena por teclado que representa una frase
//        (palabras separadas por espacios), realiza un programa que cuente cuantas palabras tiene.
        System.out.println("Ingrese una frase");
        String cadena = sc.nextLine();
        String[] palabras = cadena.split(" ");
        return "La frase tiene " + palabras.length + " palabras";
    }
}
