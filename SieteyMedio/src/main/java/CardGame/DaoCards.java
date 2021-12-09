package CardGame;


import java.util.Arrays;
import java.util.Random;

public class DaoCards {


    // BASE DE DATOS
    private Card baraja[];
    private int nextCardPos;
    private int pila[];
    private Card barajaBocaArriba[];
    private int espadas;
    private int copas;



    private static final int CARDS_AMOUNT = 40;
    private static final int SUIT_CARD_LIMIT = 10;


    public DaoCards(){
        pila = new int[Palos.values().length];
        baraja =new Card[CARDS_AMOUNT];
        nextCardPos=0;
        Card carta = baraja[10];


        Card c = new Card(1, Palos.ESPADAS);

        int valorActual = pila[c.getSuits().ordinal()];

        createDeck();
        shuffle();
    }
    private void createDeck(){
        int counter=0;

            for (int i = 0; i < SUIT_CARD_LIMIT; i++) {
                baraja[counter]=new Card(i+1, Palos.ESPADAS);
                counter++;
                baraja[counter]=new Card(i+1, Palos.COPAS);
                counter++;
                baraja[counter]=new Card(i+1, Palos.BASTOS);
                counter++;
                baraja[counter]=new Card(i+1, Palos.OROS);
                counter++;
            }

        for (int i = 0; i < Palos.values().length; i++) {
            for (int j = 0; j < SUIT_CARD_LIMIT; j++) {
                        baraja[counter]=new Card(j+1, Palos.values()[i]);
                        counter++;
            }
        }

        Card c = new Card(1,Palos.ESPADAS);

        for (int i = 0; i < 10; i++) {

            baraja[i] = c;
            c.setNumber(i+1);

        }


    }

    public void shuffle() {

        int randomPos = 0;
        Card c;

        Random r = new Random();

        int indice1= 0;
        int indice2 = 0;
        Card swap;

        for (int i = 0; i < 1000; i++) {
            indice1 = r.nextInt(39);
            indice2 = r.nextInt(39);
            swap = baraja[indice1];
            baraja[indice1] = baraja[indice2];
            baraja[indice2] = swap;
        }

    }

    public void colocarCarta(Card c)
    {
        pila[c.getSuits().ordinal()]++;
    }

    public int giveActualDePaloEnMesa(Palos suit)
    {
        switch (suit)
        {
            case COPAS: return copas;

            case ESPADAS: return espadas;
        }

        return pila[suit.ordinal()];
    }

    public Card giveLaQueEstaEncimaCard() {
        Card c = null;

        if (nextCardPos<CARDS_AMOUNT){
            c = baraja[nextCardPos];
            nextCardPos++;

        }
        barajaBocaArriba[nextCardPosArriba]= c;
        return c;
    }

    public void darBueltaBaraja(){

        for (int i = 0; i < nextCardPosArriba; i++) {
            baraja[i] = barajaBocaArriba[i];
        }

    }

    public int availableCards(){
        return CARDS_AMOUNT-nextCardPos;
    }

    public Card[] showDeck(){
        return Arrays.copyOf(baraja,CARDS_AMOUNT);
    }


}
