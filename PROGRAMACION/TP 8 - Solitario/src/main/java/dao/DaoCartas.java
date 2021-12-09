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


    public DaoCartas(){
        barajaBocaAbajo = new Cartas[LIMITE_BARAJA];
        barajaBocaArriba = new Cartas[LIMITE_BARAJA];
        indiceBaraja = 0;
    }

    public Cartas[] inicializarBaraja(){
        int contador = 0;

        for (int i = 0; i < Suits.values().length; i++) {
            for (int j = 0; j < LIMITE_PALO; j++) {
                barajaBocaAbajo[contador] = new Cartas(j+1, Suits.values()[i]);
                contador++;
            }
        }
        barajar(barajaBocaArriba);
        return Arrays.copyOf(barajaBocaArriba, barajaBocaArriba.length);
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

    public void sacarUnaCarta(){
        if (indiceBaraja < LIMITE_BARAJA){
            barajaBocaAbajo[indiceBaraja] = barajaBocaArriba[indiceBaraja];
            indiceBaraja++;
        }
    }

    public Cartas giveLaQueEstaEncimaCard() {
        Cartas c = null;

        if (indiceBaraja<LIMITE_BARAJA){
            c = barajaBocaArriba[indiceBaraja];
            indiceBaraja++;
        }
        barajaBocaArriba[indiceBaraja] = c;
        return c;
    }





    public void colocarCarta(Cartas c)
    {
        arrayLimites[c.getPalo().ordinal()]++;
    }

    public int giveActualDePaloEnMesa(Suits suit) {
            return arrayLimites[suit.ordinal()];
    }





        public Cartas sacarCarta(){
        Cartas unaCarta = barajaBocaArriba[indiceBaraja];
        indiceBaraja++;
        return unaCarta;
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

    public void ponerCarta(Cartas cartaAPoner){
        arrayLimites[cartaAPoner.getPalo().ordinal()]++;
        //eliminar carta de la baraja?????????
    }



}
