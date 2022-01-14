package main;

import dao.DaoCartas;
import modelo.Cartas;
import modelo.Suits;

import javax.imageio.metadata.IIOMetadataFormatImpl;

public class Solitario {
    public static void main(String[] args) {

        DaoCartas daoCartas = new DaoCartas();
        Cartas[] miBaraja = daoCartas.inicializarBaraja();

        System.out.println("COMIENZA EL SOLITARIO");
        boolean seQuitoCartaDeBaraja = false;
        boolean seColocaCarta = false;
        boolean seColocaCartaEnPila = false;
        do {
            seQuitoCartaDeBaraja = false;
            while (daoCartas.sePuedenDarVueltaCartas()){
                System.out.println("DOY VUELTAAAAAA 2 CARTAAAAAAAAAS");
                for (int i = 0; i < 2; i++) {
                    System.out.println();
                        System.out.println("Carta nueva " + (i + 1) + ": " + daoCartas.sacarUnaCarta()); //Si hay una cantidad impar en la baraja, la última carta se mostrará dos veces pero esto no afecta el funcionmiento del programa.
                }

                do {
                    seColocaCartaEnPila = false;
                    Cartas cartaActual = daoCartas.giveLaQueEstaEncimaCard();
                    if (cartaActual != null){
                        if (daoCartas.getLimiteCarta(cartaActual)){
                            System.out.println();
                            System.out.println("Se ha puesto el " + daoCartas.ponerCarta(cartaActual));
//                            daoCartas.ponerCarta(cartaActual);
                            seColocaCartaEnPila = true;
                            seQuitoCartaDeBaraja = true;
                        }
                    }
                }while(seColocaCartaEnPila);
            }
            System.out.println("Asi van las distintas pilas: ");
            for (int i = 0; i < Suits.values().length; i++) {
                System.out.println(Suits.values()[i] + ": " + daoCartas.getLimite(Suits.values()[i]));

            }

            System.out.println("---------------------------------------------------------------------- SE DA VUELTA LA BARAJA ----------------------------------------------------------------------");
            daoCartas.darVueltaBaraja();
        }while (seQuitoCartaDeBaraja && !daoCartas.limitesCompletos());

        if (daoCartas.limitesCompletos()){
            System.out.println("GANASTEEEEEEE");
        }
        else{
            System.out.println("Perdiste :(");
        }
    }
}


//            do {
//                //saco 2 cartas
//                System.out.println("NUEVA VUELTAAAAAA");
//                for (int i = 0; i < 2 && daoCartas.quedanCartasEnBaraja(); i++) {
//                    System.out.println();
//                    System.out.println("Carta nueva " + (i + 1) + ": " + daoCartas.sacarUnaCarta());
//                }
//                do{
//                    if (daoCartas.giveLaQueEstaEncimaCard() != null){
//                        if (daoCartas.getLimiteCarta(daoCartas.giveLaQueEstaEncimaCard())) {
//                            System.out.println();
//                            System.out.println("Se ha puesto el " + daoCartas.ponerCarta(daoCartas.giveLaQueEstaEncimaCard()));
//                            System.out.println();
//                            seColocaCarta = true;
//                            seQuitoCartaDeBaraja = true;
//                        }
//                        else {
//                            seColocaCarta = false;
//                        }
//                    }
//                    else {
//                        seColocaCarta = false;
//                    }
//                }while(seColocaCarta);
//            }while (daoCartas.quedanCartasEnBaraja());
//            System.out.println("DOY LA VUELTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//            System.out.println(daoCartas.getLimites().toString());



  //          daoCartas.darVueltaBaraja(); // AL DAR VUELTA QUEDA UN ERROR CON LA ULTIMA CARTA DE LA BARAJA REVISARRRRRRRR

