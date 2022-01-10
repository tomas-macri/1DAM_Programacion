package ui;

import dao.DaoTablero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        DaoTablero dao;
        Main main = new Main();
        int dificultad;

        System.out.println("COMIENZA MEMORYassss");
        System.out.println("Selecciona un nivel de dificultad (1 es facil y 3 es dificil)");
        do {
            dificultad = sc.nextInt();
        } while (dificultad < 1 || dificultad > 3);

        sc.nextLine();
        switch (dificultad) {
            case 1:
                dao = new DaoTablero(4, 4);
                break;
            case 2:
                dao = new DaoTablero(10, 10);
                break;
            case 3:
                dao = new DaoTablero(20, 20);
                break;
        }





//        do {
//            do {
//                do {
//                    System.out.println("Elige el x de la celda (1 a 3, de izquierda a derecha): ");
//                    xElegido = sc.nextInt();
//                    System.out.println("Elige el y de la celda (1 a 3 de arriba a abajo): ");
//                    yElegido = sc.nextInt();
//                    sc.nextLine();
//                } while ((xElegido - 1) < 0 || (xElegido - 1) > 2 || (yElegido - 1) < 0 || (yElegido - 1) > 2);
//            } while (dao.setCelda(xElegido - 1, yElegido - 1, ValoresCelda.values()[dao.getJugadas()%2].toString()));
//
//            main.printJuego(dao);
//
//        } while (dao.hayCeldaLibre() && !dao.tresLinea());
//
//        String mensajeFinal = "Empate, que aburrido";
//        if (dao.tresLinea()){
//         ///  mensajeFinal = "GANO EL JUGADOR QUE USA " + ValoresCelda.values()[(dao.getJugadas()-1)%2].toString(); //-1 porque gana el anterior
//        }
//
//        System.out.println(mensajeFinal);
//
//    }
//
//    private void printJuego(DaoTablero dao) {
//        for (int i = 0; i < EJE_Y; i++) {
//            for (int j = 0; j < EJE_X; j++) {
//                System.out.print(dao.getCelda(j, i));
//            }
//            System.out.println();
//        }
//    }
    }
}
