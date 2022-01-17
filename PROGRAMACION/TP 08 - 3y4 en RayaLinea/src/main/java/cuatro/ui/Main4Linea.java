package cuatro.ui;

import cuatro.data.DaoTablero4Linea;
import cuatro.modelo.ValoresCelda;

import java.util.Scanner;

public class Main4Linea {
    private static final int EJE_X = 7;
    private static final int EJE_Y = 6;

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        DaoTablero4Linea dao = new DaoTablero4Linea(EJE_X, EJE_Y);
        Main4Linea main = new Main4Linea();
        int xElegido;
        int yElegido;

        System.out.println("COMIENZA 4 EN LINEA");
        main.printJuego(dao);


        do {
            do {
                do {
                    System.out.println("Elige el x de la celda (1 a 7, de izquierda a derecha): ");
                    xElegido = sc.nextInt();
                    sc.nextLine();
                } while ((xElegido - 1) < 0 || (xElegido - 1) > EJE_X);
            } while (!dao.setCelda(xElegido - 1, ValoresCelda.values()[dao.getJugadas()%2].toString()));

            main.printJuego(dao);

        } while (dao.hayCeldaLibre(EJE_X, EJE_Y) && !dao.cuatroLinea(ValoresCelda.values()[(dao.getJugadas()-1)%2].toString())); //&& !dao.tresLinea()

        String mensajeFinal = "Empate, que aburrido";
        if (dao.cuatroLinea(ValoresCelda.values()[(dao.getJugadas()-1)%2].toString())){
            mensajeFinal = "GANO EL JUGADOR QUE USA " + ValoresCelda.values()[(dao.getJugadas()-1)%2].toString(); //-1 porque gana el anterior
        }

        System.out.println(mensajeFinal);

    }

    private void printJuego(DaoTablero4Linea dao) {
        for (int i = 0; i < EJE_Y; i++) {
            for (int j = 0; j < EJE_X; j++) {
                System.out.print(dao.getCelda(j, i));
            }
            System.out.println();
        }
    }
}