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
        while (dificultad < 1 || dificultad > 3) {
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
        int cantidadJugadores;
        do {
            System.out.println("Cuantos jugadores seran?: ");
            cantidadJugadores = sc.nextInt();
        } while (cantidadJugadores <= 1);
        sc.nextLine();

        dao = new DaoTablero(ejeX, ejeY);
        dao.rellenarTablero();
        int[] arrayJugadores = new int[cantidadJugadores];
        int turnoJugador = 0;
        do {
            System.out.println(dao.getTablero());
            System.out.println("Turno del jugador " + ((turnoJugador % arrayJugadores.length) + 1) + ":");
            Ficha ficha1 = main.elegirFicha(sc, dao, ejeY, ejeX);
            System.out.println(dao.getTablero());
            Ficha ficha2 = main.elegirFicha(sc, dao, ejeY, ejeX);
            System.out.println(dao.getTablero());
            if (dao.compararFichas(ficha1, ficha2)) {
                System.out.println("Es una pareja!");
                arrayJugadores[turnoJugador % arrayJugadores.length] += 2;
            } else {
                System.out.println("Eso no es una pareja :(");
                System.out.println();
                turnoJugador++;
            }
            for (int i = 0; i < arrayJugadores.length; i++) {
                System.out.println("Puntos del jugador " + (i + 1) + ": " + arrayJugadores[i]);
            }
        } while (!dao.hayFichasCubiertas());

        int ganadorPuntos = -1;
        int posiGanador = -1;
        int cantEmpatados = 0;
        for (int i = 0; i < arrayJugadores.length; i++) {
            if (arrayJugadores[i] > ganadorPuntos) {
                posiGanador = i;
                ganadorPuntos = arrayJugadores[i];
                cantEmpatados = 1;
            } else if (arrayJugadores[i] == ganadorPuntos) {
                cantEmpatados++;
            }
        }
        if (cantEmpatados > 1) {
            System.out.println("Hubo un empate a " + ganadorPuntos + " entre " + cantEmpatados + " jugadores");
        } else {
            System.out.println("El ganador es el jugador " + (posiGanador + 1) + " con " + ganadorPuntos + " puntos");
        }

    }

    private Ficha elegirFicha(Scanner sc, DaoTablero dao, int ejeY, int ejeX) {
        int posiX;
        int posiY;
        do {
            System.out.println("seleccione la posicion del eje x de la ficha que quiere dar vuelta: ");
            posiX = sc.nextInt();
            System.out.println("seleccione la posicion del eje y de la ficha que quiere dar vuelta: ");
            posiY = sc.nextInt();
            sc.nextLine();
        } while (posiX >= ejeX || posiX < 0 || posiY >= ejeY || posiY < 0 || dao.fichaDescubierta(posiX, posiY));
        return dao.descubrirFicha(posiX, posiY);
    }

}
