package tateti.ui;

import tateti.data.DaoTablero;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int ejeX = 3;
    private static final int ejeY = 3;

    public static void main(String[] args) {


        Random r = new Random();
        r.nextInt(10);
        StringBuilder posiciones = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        DaoTablero dao = new DaoTablero();
        for (int i = 0; i < ejeY; i++) {
            for (int j = 0; j < ejeX; j++) {
                System.out.print(dao.getCelda(i, j));
            }
            System.out.println();
        }


        do {
            System.out.println("Elige una celda XY todo junto): ");
            String msj = sc.nextLine();
            posiciones.append(msj.charAt(0)).append(msj.charAt(1));
        } while (!dao.setCelda((posiciones.charAt(0), posiciones.charAt(1), ValoresCeldaST.X));
        dao.getCelda(posiciones.charAt(0), posiciones.charAt(1));

            for (int i = 0; i < ejeY; i++) {
                for (int j = 0; j < ejeX; j++) {
                    System.out.print(dao.getCelda(i, j));
                }
            }


        //} while (!dao.tresLinea() && dao.hayCeldaLibre()) ;


    }
}