package ui;

import servicios.ServiciosCalculadora;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        System.out.println("CALCULADORA");

        int num1;
        int num2;
        System.out.println("Ingrese un numero");
        num1 = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese otro numero");
        num2 = sc.nextInt();
        sc.nextLine();

        char operacion;
        System.out.println("Ingrese la operacion a realizar (+ - * /): ");
        operacion = sc.nextLine().charAt(0);
        System.out.println("El resultado es " + serviciosCalculadora.hacerOperacion(num1, num2, operacion));
    }
}
