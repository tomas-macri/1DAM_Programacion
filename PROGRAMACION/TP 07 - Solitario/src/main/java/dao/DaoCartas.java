package dao;

import modelo.Cartas;
import modelo.Suits;

import java.util.Arrays;
import java.util.Random;

public class DaoCartas {

    private static final int LIMITE_BARAJA = 40;
    private static final int LIMITE_PALO = 10;


    private final Cartas[] barajaBocaArriba;
    private final Cartas[] barajaBocaAbajo;
    private final int[] arrayLimites;
    private int indiceBaraja;
    private int indiceBarajaArriba;
    private int cantidadCartasRestantes = LIMITE_BARAJA;


    public DaoCartas(){
        arrayLimites = new int[Suits.values().length];
        barajaBocaAbajo = new Cartas[LIMITE_BARAJA];
        barajaBocaArriba = new Cartas[LIMITE_BARAJA];
        indiceBaraja = 0;
        indiceBarajaArriba = 0;
    }

    public Cartas[] inicializarBaraja(){
        int contador = 0;

        for (int i = 0; i < Suits.values().length; i++) {
            for (int j = LIMITE_PALO-1; j >= 0; j--) {
                barajaBocaAbajo[contador] = new Cartas(j+1, Suits.values()[i]);
                contador++;
            }
        }
        barajar(barajaBocaAbajo);
        return Arrays.copyOf(barajaBocaAbajo, barajaBocaAbajo.length);
    }

    private void barajar(Cartas[] baraja){
        Random posi = new Random();
        int posicionArray1;
        int posicionArray2;
        Cartas cartaPosi1;
        for (int i = 0; i < 1000; i++) {
            posicionArray1 = posi.nextInt(baraja.length-1);
            posicionArray2 = posi.nextInt(baraja.length-1);
            cartaPosi1 = baraja[posicionArray1];
            baraja[posicionArray1] = baraja[posicionArray2];
            baraja[posicionArray2] = cartaPosi1;
        }
    }

    public Cartas sacarUnaCarta(){
        if (indiceBaraja < cantidadCartasRestantes){
            barajaBocaArriba[indiceBarajaArriba] = barajaBocaAbajo[indiceBaraja];
            indiceBaraja++;
            indiceBarajaArriba++;
        }
        return barajaBocaArriba[indiceBarajaArriba-1];
    }

    public Cartas giveLaQueEstaEncimaCard() {
        Cartas c = null;
        if (indiceBarajaArriba > 0){
            c = barajaBocaArriba[indiceBarajaArriba-1];
        }
        return c;
    }

    public boolean getLimiteCarta (Cartas carta){
        boolean cartaValida = false;
        if ((arrayLimites[carta.getPalo().ordinal()] + 1) == carta.getNumero()){
            cartaValida = true;
        }
        return cartaValida;
    }

    public Cartas ponerCarta(Cartas cartaAPoner){
        arrayLimites[cartaAPoner.getPalo().ordinal()]++;
        Cartas cartaColocada = cartaAPoner;
        indiceBarajaArriba--;
        return cartaColocada;
    }

    public void darVueltaBaraja(){
        for (int i = 0; i < indiceBarajaArriba ; i++) {
            barajaBocaAbajo[i] = barajaBocaArriba[i];
        }
        cantidadCartasRestantes = indiceBarajaArriba;
        indiceBaraja = 0;
        indiceBarajaArriba = 0;
    }

    public boolean sePuedenDarVueltaCartas(){
        return ((cantidadCartasRestantes - indiceBaraja) > 0);
    }

    public int getLimite(Suits suit) {
        return arrayLimites[suit.ordinal()];
    }

    public boolean limitesCompletos(){
        boolean completos = true;
        for (int i = 0; i < Suits.values().length; i++) {
            if (arrayLimites[i] != 10) {
                completos = false;
            }
        }
        return completos;
    }
}
