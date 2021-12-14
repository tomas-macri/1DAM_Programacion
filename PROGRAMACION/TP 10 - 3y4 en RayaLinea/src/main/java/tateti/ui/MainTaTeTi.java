package tateti.ui;

import tateti.data.DaoTablero;
import tateti.modelo.ValoresCelda;

import java.util.Scanner;

public class MainTaTeTi {
    private static final int EJE_X = 3;
    private static final int EJE_Y = 3;

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        DaoTablero dao = new DaoTablero(EJE_X, EJE_Y);
        MainTaTeTi main = new MainTaTeTi();
        int xElegido;
        int yElegido;

        System.out.println("COMIENZA TA TE TI");
        main.printJuego(dao);


        do {
            do {
                do {
                    System.out.println("Elige el x de la celda (1 a 3, de izquierda a derecha): ");
                    xElegido = sc.nextInt();
                    System.out.println("Elige el y de la celda (1 a 3 de arriba a abajo): ");
                    yElegido = sc.nextInt();
                    sc.nextLine();
                } while ((xElegido - 1) < 0 || (xElegido - 1) > 2 || (yElegido - 1) < 0 || (yElegido - 1) > 2);
            } while (dao.setCelda(xElegido - 1, yElegido - 1, ValoresCelda.values()[dao.getJugadas()%2].toString()));

            main.printJuego(dao);

        } while (dao.hayCeldaLibre() && !dao.tresLinea());

        String mensajeFinal = "Empate, que aburrido";
        if (dao.tresLinea()){
            mensajeFinal = "GANO EL JUGADOR QUE USA " + ValoresCelda.values()[(dao.getJugadas()-1)%2].toString(); //-1 porque gana el anterior
        }

        System.out.println(mensajeFinal);

    }

    private void printJuego(DaoTablero dao) {
        for (int i = 0; i < EJE_Y; i++) {
            for (int j = 0; j < EJE_X; j++) {
                System.out.print(dao.getCelda(j, i));
            }
            System.out.println();
        }
    }
}