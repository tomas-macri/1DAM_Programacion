package ui;

import servicios.ServiciosBaraja;

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
            tipoBaraja = sc.nextLine().charAt(0);
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
            //if ()
        }




    }
//hacer proyecto zzzzzzz






}
