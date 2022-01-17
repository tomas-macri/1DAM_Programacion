package dao;

import modelo.Ficha;
import modelo.Tablero;

import java.util.Random;

public class DaoTablero {

    //BD
    private Tablero tablero;

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

    Random r = new Random(); // el Random esta inicializado fuera por una sugerencia del SonarLint

    private void mezclarFichas() {

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

    public boolean fichaDescubierta(int x, int y) {
        return tablero.getFichaDescubierta(x, y);
    }

    public Ficha descubrirFicha(int x, int y) {
        tablero.setFichaDescubierta(x, y, true);
        return tablero.getFicha(x, y);
    }

    public boolean compararFichas(Ficha ficha1, Ficha ficha2) {
        boolean iguales = true;
        if (ficha1.getValor() != ficha2.getValor()) {
            ficha1.setDescubierta(false);
            ficha2.setDescubierta(false);
            iguales = false;
        }
        return iguales;
    }

    public String getTablero() {
        return tablero.toString();
    }
}
