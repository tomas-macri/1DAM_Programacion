package main;

import dao.DaoCartas;
import modelo.Cartas;
import modelo.Suits;

public class Solitario {
    public static void main(String[] args) {

        DaoCartas daoCartas = new DaoCartas();
        Cartas[] miBaraja = daoCartas.inicializarBaraja();
        for (int i = 0; i < miBaraja.length; i++) {
            System.out.println("carta " + (i+1) + ": " + miBaraja[i].toString());
        }

        System.out.println("COMIENZA EL SOLITARIO");
        boolean sePudoPonerCarta = false;
        do {
            do {
                sePudoPonerCarta = false;
                Cartas[] arrayCartasMesa = new Cartas[miBaraja.length];
                int contArrayMesa = 0;
                int cartasLevantadas = 0; // variable que indicara cuantas cartas se pusieron sobre las pilas de los cuatro palos

                //saco 2 cartas
                for (int i = 0; i < 2; i++) {
                    Cartas cartaDeLaBaraja = daoCartas.sacarCarta();
                    System.out.println("Carta nueva " + (i + 1) + ": " + cartaDeLaBaraja);
                    arrayCartasMesa[contArrayMesa] = cartaDeLaBaraja;
                    contArrayMesa++;
                }

                //empiezo a poner mientras se pueda (validar con if antes de mandar index de abajo)
                while (daoCartas.getLimiteCarta(arrayCartasMesa[contArrayMesa - cartasLevantadas])){
                    daoCartas.ponerCarta(arrayCartasMesa[contArrayMesa - cartasLevantadas]);
                    sePudoPonerCarta = true;
                }

                //mallllllll

            }while (daoCartas.quedanCartasEnBaraja());
        }while (sePudoPonerCarta);


    }
}
