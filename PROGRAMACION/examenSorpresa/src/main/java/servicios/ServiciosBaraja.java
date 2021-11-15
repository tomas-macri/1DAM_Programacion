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
}
