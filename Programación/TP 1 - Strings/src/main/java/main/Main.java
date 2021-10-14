package main;

import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        String resultadoFinal;
        do{
            System.out.println("Seleccione una opción del 1 al 10 o 0 para terminar: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    Ejercicio1 ej1 = new Ejercicio1();
                    ej1.ejercicio(sc);
                    break;
                case 2:
                    Ejercicio2 ej2 = new Ejercicio2();
                    resultadoFinal = ej2.ejercicio(sc);
                    System.out.println(resultadoFinal);
                    break;
                case 3:
                    Ejercicio3 ej3 = new Ejercicio3();
                    resultadoFinal = ej3.ejercicio(sc);
                    System.out.println(resultadoFinal);
                    break;
                case 4:
                    Ejercicio4 ej4 = new Ejercicio4();
                    resultadoFinal = ej4.ejercicio(sc);
                    System.out.println(resultadoFinal);
                    break;
                case 5:
                    Ejercicio5 ej5 = new Ejercicio5();
                    resultadoFinal = ej5.ejercicio(sc);
                    System.out.println(resultadoFinal);
                    break;
                case 6:
                    Ejercicio6 ej6 = new Ejercicio6();
                    resultadoFinal = ej6.ejercicio(sc);
                    System.out.println(resultadoFinal);
                    break;
                case 7:
                    Ejercicio7 ej7 = new Ejercicio7();
                    resultadoFinal = ej7.ejercicio(sc);
                    System.out.println(resultadoFinal);
                    break;
                case 8:
                    Ejercicio8 ej8 = new Ejercicio8();
                    resultadoFinal = ej8.ejercicio(sc);
                    System.out.println(resultadoFinal);
                    break;
                case 9:
                    Ejercicio9 ej9 = new Ejercicio9();
                    resultadoFinal = ej9.ejercicio(sc);
                    System.out.println(resultadoFinal);
                    break;
                case 10:
                    Ejercicio10 ej10 = new Ejercicio10();
                    resultadoFinal = ej10.ejercicio(sc);
                    System.out.println(resultadoFinal);
                    break;
                default:
                    System.out.println("Ejercicio inexistente");
            }
        }while(opcion != 0);
    }
}
