package dao;

import modelo.Cartas;
import modelo.Suits;

import java.util.Arrays;
import java.util.Random;

public class DaoCartas {

    private final Cartas[] baraja = new Cartas[40];
    private final int[] arrayLimites = new int[Suits.values().length];
    private int indiceBaraja = 0;

    public Cartas[] inicializarBaraja(int cartasPorPalo){
        int contador = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < cartasPorPalo; j++) {
                baraja[contador] = new Cartas(j,Suits.values()[i]);
                contador++;
            }


            /*if (i== 0){
                 baraja[i] = new Cartas((i%cartasPorPalo) + 1, Suits.PICAS);
             }
             else if (i%4 == 1){
                 baraja[i] = new Cartas((i%cartasPorPalo) + 1, Suits.DIAMANTES);
             }
             else if (i%4 == 2){
                 baraja[i] = new Cartas((i%cartasPorPalo) + 1 , Suits.CORAZONES);
             }
             else{
                 baraja[i] = new Cartas((i%cartasPorPalo) + 1, Suits.TREBOL);
             }*/
        }
        // tengo baraja ordenada
        //barajar
        barajar(baraja);
        return Arrays.copyOf(baraja, baraja.length);
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

    public Cartas sacarCarta(){
        Cartas unaCarta = baraja[indiceBaraja];
        indiceBaraja++;
        return unaCarta;
    }

    public boolean quedanCartasEnBaraja() {
        return (baraja.length > indiceBaraja);
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
