package main;

import java.util.Scanner;

public class Ejercicio7 {
    public static void main(String[] args) {

    }
    public String ejercicio(Scanner sc){
//        Ejercicio 7
//        Pide una cadena y dos caracteres por teclado (valida que sea un carácter), sustituye la
//        aparición del primer carácter en la cadena por el segundo carácter
        System.out.println("Ingrese una frase");
        String cadena = sc.nextLine();
        System.out.println("Ingrese el caracter que quiere quitar");
        String caracterQuitar = sc.nextLine();
        System.out.println("Ingrese el caracter por el cual quiere reemplazar los caracter '" + caracterQuitar +"': ");
        String caracterReemplazar = sc.nextLine();
        cadena = cadena.replace(caracterQuitar,caracterReemplazar);

        return "Su nueva frase sería: " + cadena;
    }
}
