package dao;

import modelo.Cartas;
import modelo.Suits;

import java.util.Arrays;
import java.util.Random;

public class DaoCartas {

    private final static int LIMITE_BARAJA = 40;
    private final static int LIMITE_PALO = 10;


    private final Cartas[] barajaBocaArriba;
    private final Cartas[] barajaBocaAbajo;
    private final int[] arrayLimites = new int[Suits.values().length];
    private int indiceBaraja;
    private int indiceBarajaArriba;


    public DaoCartas(){
        barajaBocaAbajo = new Cartas[LIMITE_BARAJA];
        barajaBocaArriba = new Cartas[LIMITE_BARAJA];
        indiceBaraja = 0;
        indiceBarajaArriba = 0;
    }

    public Cartas[] inicializarBaraja(){
        int contador = 0;

        for (int i = 0; i < Suits.values().length; i++) {
            for (int j = 0; j < LIMITE_PALO; j++) {
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
        if (indiceBaraja < LIMITE_BARAJA){
            barajaBocaArriba[indiceBarajaArriba] = barajaBocaAbajo[indiceBaraja];
            indiceBaraja++;
            indiceBarajaArriba++;
        }
        return barajaBocaArriba[indiceBarajaArriba-1];
    }

    public Cartas giveLaQueEstaEncimaCard() {
        Cartas c = null;
        if (indiceBarajaArriba >0){
            c = barajaBocaArriba[indiceBarajaArriba-1];
        }
        return c;
    }






    public int giveActualDePaloEnMesa(Suits suit) {
            return arrayLimites[suit.ordinal()];
    }


    public boolean quedanCartasEnBaraja() {
        return (barajaBocaArriba.length > indiceBaraja);
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
        //eliminar carta de la baraja?????????
    }

    public void darVueltaBaraja(){
        for (int i = indiceBarajaArriba-1; i > 0 ; i--) {
            barajaBocaAbajo[i] = barajaBocaArriba[i];
        }
        indiceBaraja = 0;
        indiceBarajaArriba = 0;
    }


    public StringBuilder getLimites() {
        StringBuilder mensaje = new StringBuilder();
        for (int i = 0; i < arrayLimites.length; i++) {
            mensaje.append("Limite para ").append(Suits.values()[i]).append(": ").append(arrayLimites[i]);
        }
        return mensaje;
    }
}
