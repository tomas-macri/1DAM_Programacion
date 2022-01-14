package main;

import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {

    }
    public String ejercicio(Scanner sc){
//        Ejercicio 3
//        Pide una cadena y un carácter por teclado (valida que sea un carácter) y muestra cuantas
//        veces aparece el carácter en la cadena.
        System.out.println("Ingrese una palabra: ");
        String cadena = sc.nextLine();
        System.out.println("Ingrese el caracter a buscar: ");
        String caracter = sc.nextLine();
        int posicionCaracter;
        int contadorCaracter = 0;
        for (int i = 0; i < cadena.length() ; i++) {
            posicionCaracter = cadena.indexOf(caracter, i);
            if (posicionCaracter != -1){
                i = posicionCaracter;
                contadorCaracter++;
            }
            else {
                i = cadena.length();
            }
        }
        return "El caracter '" + caracter + "', se encuentra " + contadorCaracter + " veces en " + cadena;
    }
}
