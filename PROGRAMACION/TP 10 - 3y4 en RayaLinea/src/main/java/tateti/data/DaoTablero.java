package tateti.data;

import tateti.modelo.Celda;
import tateti.modelo.Tablero;

public class DaoTablero {

    //BD
    private Tablero tablero;

    private int jugadas;

    public DaoTablero() {

        tablero = new Tablero(3,3);
    }


    public String getCelda(int x, int y)
    {
        return tablero.getCelda(x,y);
    }


    private boolean celdaVacia(int x,int y)
    {
        return tablero.getCelda(x,y).equals("-");
    }

    public boolean setCelda(int x, int y, String valor){
        boolean celdaVacia = false;

        if (celdaVacia(x,y))
            tablero.setCelda(x,y,valor);
        else
            celdaVacia = true;

        return celdaVacia;
    }


    public boolean tresLinea()
    {
        boolean bingo = false;

        if (tablero.getCelda(0,0)!= null &&
                tablero.getCelda(0,0) == tablero.getCelda(0,1) &&
                tablero.getCelda(0,2) == tablero.getCelda(0,1))
            bingo = true;



        return bingo;
    }

    public boolean hayCeldaLibre() {
        boolean hay = false;
        if (jugadas == 9){
            hay = true;
        }
        return hay;

    }

    public Tablero getTablero() {
        return tablero;
    }
}
