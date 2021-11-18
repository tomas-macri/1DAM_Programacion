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
                for (int j = 0; j < cantJugadores; i++) {
                    if (cartaJugadores[(j+1)%cantJugadores] != 10) {

                        System.out.println("JUGADOR " + ((i + j)%cantJugadores + 1));
                        System.out.println("Tu carta actual es un " + cartaJugadores[j]);
                        System.out.println("Si quieres cambiar la carta con la del siguiennte jugador introduce 'yes'");
                        System.out.println("Si no quieres más cartas y quieres plantarte introduce 'no'");
                        String cambiarCartas = sc.nextLine();
                        if (cambiarCartas.equals("no")) {
                            System.out.println("El JUGADOR " + ((i + j + 1)%cantJugadores) + " no cambiara la carta");
                            System.out.println(" ");
                        } else if (cambiarCartas.equals("yes")) {
                            // cambio cartas
                            int aux = cartaJugadores[j];
                            cartaJugadores[j] = cartaJugadores[(j + 1)%cantJugadores];
                            cartaJugadores[(j+1)%cantJugadores] = aux;
                            System.out.println("El JUGADOR " + ((i + j)%cantJugadores+1) + " se cambio la carta y obtuvo un " + cartaJugadores[j]);
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
