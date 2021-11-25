package main;

import java.util.Scanner;

public class Ejercicio11 {
    public static void main(String[] args) {

    }

    public void ejercicio11(Scanner sc) {
//        Ejercicio 11
//        Escribe un programa que diga si un número introducido por teclado es o no primo. Un
//        número primo es aquel que sólo es divisible entre él mismo y la unidad. Nota: Es suficiente
//        probar hasta la raíz cuadrada del número para ver si es divisible por algún otro número.

        boolean esPrimo = true;
        int numeroIngresado;
        System.out.println("Ingrese un número: ");
        numeroIngresado = sc.nextInt();
        sc.nextLine();
        for (int i = 2; i <= Math.sqrt(numeroIngresado) && esPrimo; i++){
            if (numeroIngresado % i == 0){
                esPrimo = false;
            }
        }
        if (esPrimo){
            System.out.println("El número ingresado es un número primo.");
        }
        else{
            System.out.println("El número ingresado no es un número primo.");
        }
    }
}
