package main;

import java.util.Scanner;

public class Ejercicio13 {
    public static void main(String[] args) {

    }
    public void ejercicio13(Scanner sc) {
//        Ejercicio 13
//        Una empresa tiene el registro de las horas que trabaja diariamente un empleado durante la
//        semana (seis días) y requiere determinar el total de éstas, así como el sueldo que recibirá
//        por las horas trabajadas.

        int acumHoras = 0;
        System.out.println("Ingrese el sueldo por hora del empleado: ");
        int sueldoPorHora = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= 6; i++){
            System.out.println("Cuantas horas trabajó el empleado el día " + i + "?");
            acumHoras += sc.nextInt();
            sc.nextLine();
        }
        int sueldoFinal = acumHoras * sueldoPorHora;
        System.out.println("El sueldo final del empleado esta semana será de " + sueldoFinal + "€ ya que trabajó " + acumHoras + " horas.");

    }
}
