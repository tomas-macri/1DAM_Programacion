package servicios;

import java.util.Random;

public class ServiciosBaraja {

    public int[] inicializarBaraja(int cantCartas, int cartasPorPalo){
        int[] baraja = new int[cantCartas];
        for (int i = 0; i < cantCartas; i++) {
            baraja[i] = (i%cartasPorPalo)+1;
        }
        // tengo baraja ordenada
        //barajar
        barajar(baraja);
        return baraja;
    }

    private void barajar(int[] baraja){
        Random posi = new Random();
        int posicionArray1;
        int posicionArray2;
        int cartaPosi1;
        for (int i = 0; i < 1000; i++) {
            posicionArray1 = posi.nextInt(baraja.length-1);
            posicionArray2 = posi.nextInt(baraja.length-1);
            cartaPosi1 = baraja[posicionArray1];
            baraja[posicionArray1] = baraja[posicionArray2];
            baraja[posicionArray2] = cartaPosi1;
        }
    }

    public double[] inicializarValoresCartasPorJuego(int cantCartasPorPalo) {
        double[] valoresArray = new double[cantCartasPorPalo];
        if (cantCartasPorPalo == 10){
            //baraja española
            for (int i = 0; i < cantCartasPorPalo; i++) {
                if ((i+1) < 8){
                    valoresArray[i] = i+1;
                }
                else {
                    valoresArray[i] = 0.5;
                }
            }
        }
        return valoresArray;
    }

    public double calcularMaximoPuntaje(double[] acumPuntosJugadores, double maximoPuntajeJuego) {
        double valorMax = 0;
        for (int i = 0; i < acumPuntosJugadores.length; i++) {
            if (acumPuntosJugadores[i] > valorMax && acumPuntosJugadores[i] <= maximoPuntajeJuego){
                valorMax = acumPuntosJugadores[i];
            }
        }
        return valorMax;

    }
}
