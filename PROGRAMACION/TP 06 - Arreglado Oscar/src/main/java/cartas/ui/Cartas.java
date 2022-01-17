package cartas.ui;

import cartas.servicios.ServiciosCartas;

public class Cartas {


    public static void main(String[] args) {
        ServiciosCartas serviciosCartas = new ServiciosCartas();

        int []cartas = serviciosCartas.generarBaraja(40,10);
        int []baraja2 = serviciosCartas.generarBaraja(40,10);

        int []cartasFrancesas = serviciosCartas.generarBaraja(52,13);


        int numeroJugadores = 4;
        int indiceBaraja = 0;
        int indiceJugadorActual = 0;

        int [][]cartasJugadores = new int[numeroJugadores][10];
        int []sumaJugadores = new int[numeroJugadores];




        for (int i = 0; i < numeroJugadores; i++) {

            cartasJugadores[i][indiceJugadorActual] = cartas[indiceBaraja];
            indiceBaraja++;

        }

        boolean carta = true;
        for (int i = 0; i < numeroJugadores; i++) {
            indiceJugadorActual = 1;
            //quieres carta
            while ( carta) {
                cartasJugadores[i][indiceJugadorActual] = cartas[indiceBaraja];
                indiceBaraja++;

                indiceJugadorActual++;
            }

        }



    }

}
