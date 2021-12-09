package CardGame;

import java.util.Arrays;
import java.util.Scanner;

public class SieteMedio {

    public static int PLAYERS_AMOUNT;
    public static int PLAYER_TURN;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DaoCards d = new DaoCards();
        System.out.println(Arrays.toString(d.showDeck()));

        while (nohaycolocadas) {
            while (sincartasmano) {
                Card c1 = d.giveLaQueEstaEncimaCard();
                Card c2 = d.giveLaQueEstaEncimaCard();

                if (c1.getNumber() == d.giveActualDePaloEnMesa(c1.getSuits()) + 1) {
                    d.colocarCarta(c1);
                }
                buscaCarta();
            }
            d.darBueltaBaraja();
        }





    }


    public static void buscaCarta()
    {
        DaoCards d = new DaoCards();

    }
}
