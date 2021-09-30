package main;

import java.util.Scanner;

public class Ejercicio17 {
    public static void main(String[] args) {

    }

    public void ejercicio17(Scanner sc) {
//        Ejercicio 17
//        Una empresa les paga a sus empleados con base en las horas trabajadas en la semana.
//        Para esto, se registran los días que trabajó y las horas de cada día. Realice un algoritmo
//        para determinar el sueldo semanal de N trabajadores y además calcule cuánto pagó la
//        empresa por los N empleados.
        int contCantEmpleados = 1;
        int acumSueldos = 0;
        int sueldo;
        int cantHorasSemanales = 0;
        int cantDias;
        System.out.println("Indique el sueldo base de su empleado nº" + contCantEmpleados + ".");
        sueldo = sc.nextInt();
        sc.nextLine();

        do {
            do {
                System.out.println("Cuántos días de la semana trabajó el empleado nº" + contCantEmpleados + "?");
                cantDias = sc.nextInt();
            }while (cantDias > 7 || cantDias < 0);
            for (int i = 0; i <= cantDias-1; i++){
                System.out.println("Cuantas horas trabajó el empleado el día " + (i + 1) + "?");
                cantHorasSemanales += sc.nextInt();
            }
            int sueldoFinal = cantHorasSemanales * sueldo;
            System.out.println("El sueldo final del empleado nº" + contCantEmpleados + " esta semana será de " + sueldoFinal + "€ ya que trabajó " + cantHorasSemanales + " horas.");
            acumSueldos += sueldoFinal;
            contCantEmpleados++;
            System.out.println("Indique el sueldo base de su empleado nº" + contCantEmpleados + ". En caso de no tener más empleados ingrese 0.");
            sueldo = sc.nextInt();
            sc.nextLine();
            cantHorasSemanales = 0;
        }while (sueldo != 0);
        System.out.println("La empresa pagó un total de " + acumSueldos + "€ a los " + (contCantEmpleados - 1)+ " empleados.");
    }
}
