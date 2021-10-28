package main;

import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {
      /*  Scanner sc = new Scanner(System.in);
        Ejercicio5 ej = new Ejercicio5();
        ej.ejConIndex(sc);
    */
    }

    public String ejercicio(Scanner sc) {
//        Ejercicio 5
//        Si tenemos una cadena con un nombre y apellidos, realizar un programa que muestre las
//        iniciales en mayúsculas.
        System.out.println("Ingrese su nombre y apellidos");
        String cadena = sc.nextLine();
        String[] palabras = cadena.split(" ");
        StringBuilder iniciales = new StringBuilder();
        for (int i = 0; i < palabras.length; i++) {
            iniciales.append(palabras[i].toUpperCase().charAt(0)).append(" ");
        }
        return "Sus iniciales son: " + iniciales;
    }
  /* public String ejConIndex(Scanner sc){
        char caracter = ' ';
        String resultado = "";
        System.out.println("Ingrese su nombre y apellidos");
        String cadena = sc.nextLine();
        System.out.println(cadena.trim().charAt(0));
        boolean noHayCaracteres = false;
        for (int i = 0; i < cadena.length() && !noHayCaracteres; i++) {
            i = cadena.indexOf(caracter,i);
            if (i != -1) {
                resultado += cadena.charAt(i + 1);
            }
            else{
                noHayCaracteres = true;
            }
        }
        return resultado;

   */
}






