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
        System.out.println(dao.getTablero());
        dao.rellenarTablero();
        System.out.println(dao.getTablero());
        boolean jugador1 = true; //variable que dice el turno de quien es
        int acumPuntos1 = 0;
        int acumPuntos2 = 0;
        // tengo tablero sin valores
        int quienLeToca;
        do {
            if (jugador1){
                quienLeToca = 1;
            }
            else {
                quienLeToca = 2;
            }
            System.out.println(dao.getTablero());
            System.out.println("Turno del jugador " + quienLeToca + ":");
            Ficha ficha1 = main.elegirFicha(sc, dao, ejeY, ejeX);
            System.out.println(dao.getTablero());
            Ficha ficha2 = main.elegirFicha(sc,dao,ejeY,ejeX);
            System.out.println(dao.getTablero());
            if (dao.compararFichas(ficha1, ficha2)){
                System.out.println("Es una pareja!");
                if (jugador1){
                    System.out.println("El jugador 1 suma dos puntos");
                    acumPuntos1+=2;
                }
                else {
                    System.out.println("El jugador 2 suma dos puntos");
                    acumPuntos2+=2;
                }
            }
            else {
                jugador1 = !jugador1;
            }
            System.out.println("Puntos del jugador 1: " + acumPuntos1);
            System.out.println("Puntos del jugador 2: " + acumPuntos2);
        }while (!dao.hayFichasCubiertas());
        System.out.println("Puntos finales del jugador 1: " + acumPuntos1);
        System.out.println("Puntos finales del jugador 2: " + acumPuntos2);
        String mensajeFinal = "empate, que aburridos";
        if (acumPuntos1 > acumPuntos2){
            mensajeFinal = "El jugador 1 gana!";
        }
        else if (acumPuntos2 > acumPuntos1){
            mensajeFinal = "El jugador 2 gana!";
        }
        System.out.println(mensajeFinal);

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
        }while (posiX >= ejeX || posiX < 0 || posiY >= ejeY || posiY < 0 || dao.fichaDescubierta(posiX, posiY));
        return dao.descubrirFicha(posiX,posiY);
    }

}
