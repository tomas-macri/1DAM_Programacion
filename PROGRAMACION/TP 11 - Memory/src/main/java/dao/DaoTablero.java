package dao;

import modelo.Ficha;
import modelo.Tablero;

import java.util.Random;

public class DaoTablero {

    //BD
    private Tablero tablero;

    private int jugadas;

    public DaoTablero(int x, int y) {

        tablero = new Tablero(x, y);
    }

    public Tablero rellenarTablero() {
        int valor = 1;
        for (int i = 0; i < tablero.getX(); i++) {
            for (int j = 1; j < tablero.getY(); j += 2) {
                tablero.setValorFicha(i, j - 1, valor);
                tablero.setValorFicha(i, j, valor);
                valor++;
            }
        }
        mezclarFichas();
        return tablero;
    }

    private void mezclarFichas() {
        Random r = new Random();

        int indiceX1;
        int indiceY1;

        int indiceX2;
        int indiceY2;
        int auxNumber;


        for (int i = 0; i < 1000; i++) {
            indiceX1 = r.nextInt(tablero.getX());
            indiceY1 = r.nextInt(tablero.getY());
            indiceX2 = r.nextInt(tablero.getX());
            indiceY2 = r.nextInt(tablero.getY());

            auxNumber = tablero.getValorFicha(indiceX1, indiceY1);
            tablero.setValorFicha(indiceX1, indiceY1, tablero.getValorFicha(indiceX2, indiceY2));
            tablero.setValorFicha(indiceX2, indiceY2, auxNumber);
        }

    }

    public int getCelda(int x, int y) {
        return tablero.getValorFicha(x, y);
    }


    private boolean celdaVacia(int x, int y) {
        return tablero.getValorFicha(x, y) == 0;
    }

    public boolean setCelda(int x, int y, int valor) {
        boolean celdaVacia = false;

        if (celdaVacia(x, y)) {
            tablero.setValorFicha(x, y, valor);
            jugadas++;
        } else {
            celdaVacia = true;
        }

        return celdaVacia;
    }

    public int getJugadas() {
        return jugadas;
    }


    public boolean hayFichasCubiertas() {
        boolean todasDescubiertas = true;
        for (int i = 0; i < tablero.getX() && todasDescubiertas; i++) {
            for (int j = 0; j < tablero.getY() && todasDescubiertas; j++) {
                if (!tablero.getFichaDescubierta(i, j)) {
                    todasDescubiertas = false;
                }
            }
        }
        return todasDescubiertas;

    }

    public Ficha descubrirFicha(int x, int y) {
        tablero.setFichaDescubierta(x, y, true);
        return tablero.getFicha(x,y);
    }

    public void compararFichas(Ficha ficha1, Ficha ficha2){
        //main ghasta aca llegue
    }

    public Tablero getTablero() {
        return tablero;
    }
}
