package main;

import java.util.Scanner;

public class Ejercicio16 {
    public static void main(String[] args) {

    }

    public void ejercicio16(Scanner sc) {
//        Ejercicio 16
//        Una empresa les paga a sus empleados con base en las horas trabajadas en la semana.
//        Realice un algoritmo para determinar el sueldo semanal de N trabajadores y, además, calcule
//        cuánto pagó la empresa por los N empleados.

        int contCantEmpleados = 1;
        int acumSueldos = 0;
        int sueldo;
        int cantHoras;
        System.out.println("Indique el sueldo base de su empleado nº" + contCantEmpleados + ".");
        sueldo = sc.nextInt();
        sc.nextLine();

        do {
            System.out.println("Ingrese la cantidad de horas que trabajó el empleado nº" + contCantEmpleados + " en la semana:");
            cantHoras = sc.nextInt();
            sc.nextLine();
            System.out.println("El empleado nº" + contCantEmpleados + " cobrará " + sueldo*cantHoras + "€ esta semana.");
            acumSueldos += sueldo*cantHoras;
            contCantEmpleados++;
            System.out.println("Indique el sueldo base de su empleado nº" + contCantEmpleados + ". En caso de no tener más empleados ingrese 0.");
            sueldo = sc.nextInt();
            sc.nextLine();
        }while (sueldo != 0);
        System.out.println("La empresa pagó un total de " + acumSueldos + "€ a los " + (contCantEmpleados - 1)  + " empleados.");

    }
}
