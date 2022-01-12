package ui;

import dao.DaoTablero;
import modelo.Ficha;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        DaoTablero dao;
        Main main = new Main();
        int dificultad;
        int ejeY = -2;
        int ejeX = -2;
        System.out.println("COMIENZA MEMORY");
        System.out.println("Selecciona un nivel de dificultad (1 es facil y 3 es dificil)");
        dificultad = sc.nextInt();
        while (dificultad < 1 || dificultad > 3){
            System.out.println("Selecciona un nivel de dificultad (1 es facil y 3 es dificil)");
            dificultad = sc.nextInt();
        }

        sc.nextLine();
        switch (dificultad) {
            case 1:
                ejeX = 4;
                ejeY = 4;
                break;
            case 2:
                ejeX = 8;
                ejeY = 8;
                break;
            case 3:
                ejeX = 12;
                ejeY = 12;
                break;
            default:
                break;
        }
        dao = new DaoTablero(ejeX, ejeY);
        System.out.println(dao.getTablero().toString());
        dao.rellenarTablero();
        System.out.println(dao.getTablero().toString());

        do {
            int posiX;
            int posiY;
            System.out.println("nueva ronda:");
            Ficha ficha1 = main.elegirFicha(sc, dao, ejeY, ejeX);
            System.out.println(dao.getTablero().toString());
            Ficha ficha2 = main.elegirFicha(sc,dao,ejeY,ejeX);
            System.out.println(dao.getTablero().toString());
            dao.compararFichas(ficha1, ficha2);


        }while (!dao.hayFichasCubiertas());
        // tengo tablero sin valores



    }

    private Ficha elegirFicha(Scanner sc, DaoTablero dao, int ejeY, int ejeX) {
        int posiX = -1;
        int posiY = -1;
        while (posiX > ejeX || posiX < 0 || posiY > ejeY || posiY < 0) {
            System.out.println("seleccione la posicion del eje x de la ficha que quiere dar vuelta: ");
            posiX = sc.nextInt();
            System.out.println("seleccione la posicion del eje y de la ficha que quiere dar vuelta: ");
            posiY = sc.nextInt();
            sc.nextLine();
        }
        return dao.descubrirFicha(posiX,posiY);
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
}
