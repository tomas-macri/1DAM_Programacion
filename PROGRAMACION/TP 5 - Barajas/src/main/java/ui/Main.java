package ui;

import servicios.ServiciosBaraja;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        ServiciosBaraja serviciosBaraja = new ServiciosBaraja();
        Main claseMain = new Main();

        int[] baraja;
        baraja = serviciosBaraja.inicializarBaraja(40, 10);
        int opcion;
        do{
            System.out.println("Ingrese a que quiere jugar: ");
            System.out.println("17 para el ejercicio");
            System.out.println("7 para las 7 1/2");
            System.out.println();
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion){
                case 17:
                    claseMain.ejercicio17(baraja);
                    break;
                case 7:
                    claseMain.sieteYMedia(sc, serviciosBaraja, baraja);
                    break;
                default:
                    System.out.println("Juego inexistente");
                    break;
            }
        }while (opcion != 0);
    }

    private void sieteYMedia(Scanner sc, ServiciosBaraja serviciosBaraja, int[] baraja) throws InterruptedException {
        int contTotalBaraja = 0;
        double acumValorBanca = 0;
        double maximoPuntajeJuego = 7.5;
        int cantCartasPorPalo = baraja.length/4;
        double[] valoresSieteYMedia = serviciosBaraja.inicializarValoresCartasPorJuego(cantCartasPorPalo);
        int cantJugadores;
        do {
            System.out.println("Ingrese la cantidad de jugadores: ");
            cantJugadores = sc.nextInt();
        }while (cantJugadores < 1);
        sc.nextLine();
        int[] jugadores = new int[cantJugadores];
        double[] acumPuntosJugadores = new double[cantJugadores];

        // primera carta a cada jugador
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println("Primera carta del jugador " + (i+1) + ": " + baraja[contTotalBaraja]);
            acumPuntosJugadores[i] += valoresSieteYMedia[(baraja[contTotalBaraja])-1];
            contTotalBaraja++;
            System.out.println();
        }

        // primera carta a la banca
        System.out.println("Primera carta de la banca: " + baraja[contTotalBaraja]);
        acumValorBanca+= valoresSieteYMedia[(baraja[contTotalBaraja])-1];
        contTotalBaraja++;
        System.out.println();

        boolean plantarse;
        //va jugando cada jugador
        for (int i = 0; i < cantJugadores; i++) {
            plantarse = false; // cuando se planta el primera jugador vuelve a valer false
            System.out.println("JUGADOR " + (i+1));
            char plantOPedir;
            do{
                System.out.println("Llevas acumulado un total de " + acumPuntosJugadores[i] + " puntos.");
                plantOPedir = validacionPlantOPedir(sc);
                if (plantOPedir == 'd'){
                    System.out.println("Nueva carta para el jugador " + (i+1) + ": " + baraja[contTotalBaraja]);
                    acumPuntosJugadores[i] += valoresSieteYMedia[(baraja[contTotalBaraja])-1]; // -1 porque los valores de baraja van de 1 a 10
                    contTotalBaraja++;
                }
                else {
                    plantarse = true;
                }
            }while (acumPuntosJugadores[i] <= 7.5 && !plantarse);
            System.out.println("El jugador " + (i+1) + " sumó un total de " + acumPuntosJugadores[i]);
            System.out.println();
        }
        double maximoPuntajeJugador = serviciosBaraja.calcularMaximoPuntaje(acumPuntosJugadores, maximoPuntajeJuego);

        plantarse = true; // por defecto la banca se plantaría
        System.out.println("Ahora es el turno de la banca: ");
        Thread.sleep(3000);
        if (maximoPuntajeJugador <= 7.5 || acumValorBanca >=  maximoPuntajeJugador) {
            plantarse = false;
        }
        while (acumValorBanca <= 6 && !plantarse) {
            System.out.println("Nueva carta para la banca: " + baraja[contTotalBaraja]);
            acumValorBanca += valoresSieteYMedia[(baraja[contTotalBaraja]) - 1];
            System.out.println("La banca lleva un total de " + acumValorBanca + " puntos.");
            contTotalBaraja++;
            if (acumValorBanca >= maximoPuntajeJugador){
                plantarse = true;
            }
            Thread.sleep(3000);
        }
        System.out.println("La banca sumó un total de " + acumValorBanca);

        for (int i = 0; i < jugadores.length; i++) {
            System.out.println("RESULTADOS DEL JUGADOR " + (i+1));
            if (acumPuntosJugadores[i] >7.5){
                System.out.println("LA BANCA TE GANÓ!");
            }
            else if(acumValorBanca>7.5 || acumPuntosJugadores[i] > acumValorBanca){
                System.out.println("HAS GANADO!");
            }
            else {
                System.out.println("LA BANCA TE GANÓ!");
            }
            System.out.println();
        }
        System.out.println();
    }

    private char validacionPlantOPedir(Scanner sc) {
        char plantOPedir;
        do {
            System.out.println("Ingresa p para plantarse o d para que te de una carta");
            plantOPedir = sc.nextLine().charAt(0);
        }while (plantOPedir != 'p' && plantOPedir != 'd');
        return plantOPedir;
    }

    private void ejercicio17(int[] baraja) throws InterruptedException {
        final int cantSubBaraja = 10;
        int[] subBaraja1 = new int[cantSubBaraja];
        for (int i = 0; i < subBaraja1.length; i++) {
            subBaraja1[i] = baraja[i];
        }

        // Hago lo mismo pero agrego la cantidad de cartas repartidas para que no sean iguales
        int[] subBaraja2 = new int[cantSubBaraja];
        for (int i = subBaraja1.length; i < subBaraja2.length + subBaraja1.length; i++) {
            subBaraja2[i - subBaraja1.length] = baraja[i];
        }

        int puntosJug1 = 0;
        int puntosJug2 = 0;
        for (int i = 0; i < cantSubBaraja; i++) {

            if (subBaraja1[i] > subBaraja2[i])
            {
                puntosJug1++;
            }
            else if (subBaraja2[i] > subBaraja1[i]){ // si son iguales no hay puntos
                puntosJug2++;
            }
            System.out.println("RONDA " + (i+1));
            System.out.println();
            System.out.println("Carta del jugador 1 (" + puntosJug1 + ") : " + subBaraja1[i]);
            System.out.println("Carta del jugador 2 (" + puntosJug2 + "): " + subBaraja2[i]);
            System.out.println();
            Thread.sleep(5000);
        }
        String mensajeFinal = "Es un empate a " + puntosJug1 + " entre los jugadores";
        if (puntosJug1 > puntosJug2)
        {
            mensajeFinal = "El jugador 1 gana por " + puntosJug1 + " a " + puntosJug2;
        }
        else if (puntosJug2 > puntosJug1){ // si son iguales no hay puntos
            mensajeFinal = "El jugador 2 gana por " + puntosJug2 + " a " + puntosJug1;
        }
        System.out.println(mensajeFinal);
    }
}
