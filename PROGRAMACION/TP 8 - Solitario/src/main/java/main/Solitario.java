package main;

import dao.DaoCartas;
import modelo.Cartas;
import modelo.Suits;

public class Solitario {
    public static void main(String[] args) {

        DaoCartas daoCartas = new DaoCartas();
        Cartas[] miBaraja = daoCartas.inicializarBaraja();

        System.out.println("COMIENZA EL SOLITARIO");
        boolean sePudoPonerCarta = false;
        boolean seColocaCarta = false;
        do {
            sePudoPonerCarta = false;
            do {
                //saco 2 cartas
                System.out.println("NUEVA VUELTAAAAAA");
                for (int i = 0; i < 2 && daoCartas.quedanCartasEnBaraja(); i++) {
                    System.out.println();
                    System.out.println("Carta nueva " + (i + 1) + ": " + daoCartas.sacarUnaCarta());
                }
                do{
                    if (daoCartas.giveLaQueEstaEncimaCard() != null){
                        if (daoCartas.getLimiteCarta(daoCartas.giveLaQueEstaEncimaCard())) {
                            System.out.println();
                            System.out.println("Se ha puesto el " + daoCartas.ponerCarta(daoCartas.giveLaQueEstaEncimaCard()));
                            System.out.println();
                            seColocaCarta = true;
                            sePudoPonerCarta = true;
                        }
                        else {
                            seColocaCarta = false;
                        }
                    }
                    else {
                        seColocaCarta = false;
                    }
                }while(seColocaCarta);
            }while (daoCartas.quedanCartasEnBaraja());
            System.out.println("DOY LA VUELTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            System.out.println(daoCartas.getLimites().toString());



            daoCartas.darVueltaBaraja(); // AL DAR VUELTA QUEDA UN ERROR CON LA ULTIMA CARTA DE LA BARAJA REVISARRRRRRRR
        }while (sePudoPonerCarta);

        if (!sePudoPonerCarta){
            System.out.println("perdiste");
        }
        else {
            System.out.println("ganaste");
        }


    }
}
