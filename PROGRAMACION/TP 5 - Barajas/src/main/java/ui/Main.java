package ui;

import servicios.ServiciosBaraja;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int cantSubBaraja = 10;
        Scanner sc = new Scanner(System.in);
        char tipoBaraja;
        int baraja[];
        ServiciosBaraja serviciosBaraja = new ServiciosBaraja();
        do {
            System.out.println("Ingrese f para baraja francesa o e para española");
            tipoBaraja = sc.nextLine().toLowerCase().charAt(0);
        }while (tipoBaraja != 'f' && tipoBaraja != 'e');
        if (tipoBaraja == 'e'){
            baraja = serviciosBaraja.inicializarBaraja(40, 10);
        }
        else{
            baraja = serviciosBaraja.inicializarBaraja(52,13);
        }

        int subBaraja1[] = new int[cantSubBaraja];
        for (int i = 0; i < subBaraja1.length; i++) {
            subBaraja1[i] = baraja[i];
        }

        // Hago lo mismo pero agrego la cantidad de cartas repartidas para que no sean iguales
        int subBaraja2[] = new int[cantSubBaraja];
        for (int i = subBaraja1.length; i < subBaraja2.length + subBaraja1.length; i++) {
            subBaraja2[i - subBaraja1.length] = baraja[i];
        }

        for (int i = 0; i < cantSubBaraja; i++) {
            //comparar y sacar puntos
            System.out.println("Carta del jugador 1: " + subBaraja1[i]);
            System.out.println("Carta del jugador 2: " + subBaraja2[i]);
            if (subBaraja1[i] > subBaraja2[i])
            {

            }
            else if (subBaraja2[i] > subBaraja1[i]){ // si son iguales no hay puntos

            }
            //if ()
        }




    }
//hacer proyecto zzzzzzz






}
