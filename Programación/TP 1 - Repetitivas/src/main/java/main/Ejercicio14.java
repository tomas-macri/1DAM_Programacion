package main;

import java.util.Scanner;

public class Ejercicio14 {
    public static void main(String[] args) {

    }
    public void ejercicio14(Scanner sc) {
//        Ejercicio 14
//        Una persona se encuentra en el kilómetro 70 de una carretera, otra se encuentra en el km
//        150, los coches tienen sentido opuesto y tienen la misma velocidad. Realizar un programa
//        para determinar en qué kilómetro de esa carretera se encontrarán.

        int dist1 = 70;
        int dist2 = 150;
        System.out.println("Ingrese la velocidad de los coches (en km/h)");
        int velocidad = sc.nextInt();
        int horasEncuentro = 0;
        sc.nextLine();
        while (dist1 < dist2){
            horasEncuentro++;
            dist1 += velocidad;
            dist2 -= velocidad;
        }
        int diferencia = (dist1 + dist2) / 2;
        System.out.println("Se encontrarán después de " + horasEncuentro + " horas. En el kilómetro " + diferencia);
    }


}
