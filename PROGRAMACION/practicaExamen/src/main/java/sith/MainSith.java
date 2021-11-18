package sith;

import servicios.ServiciosBaraja;

import java.util.Scanner;

public class MainSith {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ServiciosBaraja sb = new ServiciosBaraja();

        int baraja[] = sb.inicializarBaraja(40, 10);
        int contadorBaraja = 0;
        System.out.println("Ingrese cantidad de jugadores: ");
        int cantJugadores = sc.nextInt();
        sc.nextLine();
        int numeroDeJugadoresPlantados;
        for (int i = 0; i < cantJugadores; i++) {
            // CADA JUGADOR BARAJEA
            numeroDeJugadoresPlantados = 0;
            int[] cartaJugadores = new int[cantJugadores];
            for (int j = 0; j < cantJugadores; j++) {
                cartaJugadores[j] = baraja[contadorBaraja];
                contadorBaraja++;
                System.out.println("La carta del jugador " + (j+1) + " es: " + cartaJugadores[j]);
            }

            // RONDAS
                for (int j = 0; j < cantJugadores; j++) {
                    if (cartaJugadores[((j+i+1)%cantJugadores)] != 10) {
                        int valorCartaSiguiente = (j + i + 1)%cantJugadores;
                        int valorCartaJugadorActual = (j+i)%cantJugadores;
                        int jugadorActual = (i + j)%cantJugadores + 1;
                        System.out.println("JUGADOR " + (jugadorActual));
                        System.out.println("Tu carta actual es un " + cartaJugadores[valorCartaJugadorActual]);
                        System.out.println("Si quieres cambiar la carta con la del siguiennte jugador introduce 'yes'");
                        System.out.println("Si no quieres más cartas y quieres plantarte introduce 'no'");
                        String cambiarCartas = sc.nextLine();
                        if (cambiarCartas.equals("no")) {
                            System.out.println("El JUGADOR " + ((i + j + 1)%cantJugadores) + " no cambiara la carta");
                            System.out.println(" ");
                        } else if (cambiarCartas.equals("yes")) {
                            // cambio cartas
                            int aux = cartaJugadores[valorCartaJugadorActual];
                            cartaJugadores[valorCartaJugadorActual] = cartaJugadores[valorCartaSiguiente];
                            cartaJugadores[valorCartaSiguiente] = aux;
                            System.out.println("El JUGADOR " + (jugadorActual) + " se cambio la carta y obtuvo un " + cartaJugadores[valorCartaJugadorActual]);
                        }
                    }
                    else {
                        System.out.println("El siguiente jugador tiene un 10, por lo que no puedes cambiar la carta");
                    }
                    System.out.println(" ");
                }
            baraja = sb.inicializarBaraja(40,10);
        }
    }
}
