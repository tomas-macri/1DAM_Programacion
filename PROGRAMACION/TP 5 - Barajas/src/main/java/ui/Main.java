package ui;

import servicios.ServiciosBaraja;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        ServiciosBaraja serviciosBaraja = new ServiciosBaraja();
        Main claseMain = new Main();
        char tipoBaraja;
        do {
            System.out.println("Ingrese f para baraja francesa o e para española");
            tipoBaraja = sc.nextLine().toLowerCase().charAt(0);
        }while (tipoBaraja != 'f' && tipoBaraja != 'e');

        int[] baraja;
        if (tipoBaraja == 'e'){
            baraja = serviciosBaraja.inicializarBaraja(40, 10);
        }
        else{
            baraja = serviciosBaraja.inicializarBaraja(52,13);
        }

        System.out.println("Ingrese a que quiere jugar: ");
        System.out.println("17 para el ejercicio");
        System.out.println();
        int opcion = sc.nextInt();
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
    }

    private void sieteYMedia(Scanner sc, ServiciosBaraja serviciosBaraja, int[] baraja) throws InterruptedException {
        int contTotalBaraja = 0;
        double acumValorJug1 = 0;
        double acumValorBanca = 0;
        int cantCartasPorPalo = baraja.length/4;
        double[] valoresSieteYMedia = serviciosBaraja.inicializarValoresCartasPorJuego(cantCartasPorPalo);

        System.out.println("Primera carta del jugador 1: " + baraja[contTotalBaraja]);
        acumValorJug1+= valoresSieteYMedia[(baraja[contTotalBaraja])-1];
        contTotalBaraja++;

        System.out.println("Primera carta de la banca: " + baraja[contTotalBaraja]);
        acumValorBanca+= valoresSieteYMedia[(baraja[contTotalBaraja])-1];
        contTotalBaraja++;

        boolean plantarse = false;
        char plantOPedir;
        do{
            do {
                System.out.println("Ingresa p para plantarse o d para que te de una carta");
                plantOPedir = sc.nextLine().toLowerCase().charAt(0);
            }while (plantOPedir != 'p' && plantOPedir != 'd');
            if (plantOPedir == 'd'){
                System.out.println("Nueva carta para el jugador 1: " + baraja[contTotalBaraja]);
                acumValorJug1+= valoresSieteYMedia[(baraja[contTotalBaraja])-1];
                contTotalBaraja++;
            }
            else {
                plantarse = true;
            }
        }while (acumValorJug1 <= 7.5 && !plantarse);
        System.out.println("El jugador sumó un total de " + acumValorJug1);
        System.out.println();
        System.out.println("Ahora es el turno de la banca: ");


        if (acumValorJug1 <= 7.5 || acumValorBanca >= acumValorJug1) {
            plantarse = false;
        }
        while (acumValorBanca <= 6 && !plantarse) {
            System.out.println("Nueva carta para la banca: " + baraja[contTotalBaraja]);
            acumValorBanca += valoresSieteYMedia[(baraja[contTotalBaraja]) - 1];
            contTotalBaraja++;
            if (acumValorBanca >= acumValorJug1){
                plantarse = true;
            }
            Thread.sleep(3000);
        }
        System.out.println("La banca sumó un total de " + acumValorBanca);

        if (acumValorJug1>7.5){
            System.out.println("LA BANCA GANA!");
        }
        else if(acumValorBanca>7.5 || acumValorJug1 > acumValorBanca){
            System.out.println("EL JUGADOR 1 GANA!");
        }
        else {
            System.out.println("LA BANCA GANA!");
        }
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
