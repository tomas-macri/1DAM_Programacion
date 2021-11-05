package main;

import java.util.Scanner;

public class Ejercicio18 {
    public static void main(String[] args) {

    }

    public void ejercicio18(Scanner sc) {
//        Ejercicio 18
//        Hacer un programa que muestre un cronometro, indicando las horas, minutos y segundos.
        int horas = 0;
        int minutos = 0;
        int segundos = 0;

        while (true){
            System.out.println(horas + ":" + minutos + ":" + segundos);
            segundos++;
            if (segundos > 59){
                segundos = 0;
                minutos++;
                if (minutos > 59){
                    minutos = 0;
                    horas++;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
