package tateti.data;

import tateti.modelo.Tablero;

public class DaoTablero {

    //BD
    private Tablero tablero;

    private int jugadas;

    public DaoTablero(int x, int y) {

        tablero = new Tablero(x, y);
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

        if (celdaVacia(x,y)){
            tablero.setCelda(x,y,valor);
            jugadas++;
        }
        else{
            celdaVacia = true;
        }

        return celdaVacia;
    }

    public int getJugadas(){
        return jugadas;
    }


    public boolean tresLinea()
    {
        boolean bingo = false;

        if (!tablero.getCelda(0,0).equals("-") &&                                    //1 - -
                tablero.getCelda(0,0).equals(tablero.getCelda(0,1)) &&         //1 - -
                tablero.getCelda(0,2).equals(tablero.getCelda(0,1))){          //1 - -
            bingo = true;
        }

    else if (!tablero.getCelda(1,0).equals("-") &&                              //- 1 -
                tablero.getCelda(1,1).equals(tablero.getCelda(1,0)) &&         //- 1 -
                tablero.getCelda(1,2).equals(tablero.getCelda(1,1))){          //- 1 -
            bingo = true;
        }

        else if (!tablero.getCelda(2,0).equals("-") &&                               //- - 1
                tablero.getCelda(2,1).equals(tablero.getCelda(2,0)) &&         //- - 1
                tablero.getCelda(2,2).equals(tablero.getCelda(2,1))){          //- - 1
            bingo = true;
        }



        else if (!tablero.getCelda(0,0).equals("-") &&                          //1 1 1
                tablero.getCelda(1,0).equals(tablero.getCelda(0,0)) &&         //- - -
                tablero.getCelda(2,0).equals(tablero.getCelda(1,0))){          //- - -
            bingo = true;
        }

        else if (!tablero.getCelda(0,1).equals("-") &&                          //- - -
                tablero.getCelda(1,1).equals(tablero.getCelda(0,1)) &&         //1 1 1
                tablero.getCelda(2,1).equals(tablero.getCelda(1,1))){          //- - -
            bingo = true;
        }

        else if (!tablero.getCelda(0,2).equals("-") &&                          //- - -
                tablero.getCelda(1,2).equals(tablero.getCelda(0,2)) &&         //- - -
                tablero.getCelda(2,2).equals(tablero.getCelda(1,2))){          //1 1 1
            bingo = true;
        }


        else if (!tablero.getCelda(0,0).equals("-") &&                          //1 - -
                tablero.getCelda(1,1).equals(tablero.getCelda(0,0)) &&         //- 1 -
                tablero.getCelda(2,2).equals(tablero.getCelda(1,1))){          //- - 1
            bingo = true;
        }

        else if (!tablero.getCelda(2,0).equals("-") &&                          //- - 1
                tablero.getCelda(1,1).equals(tablero.getCelda(2,0)) &&         //- 1 -
                tablero.getCelda(0,2).equals(tablero.getCelda(1,1))){          //1 - -
            bingo = true;
        }
        return bingo;
    }

    public boolean hayCeldaLibre() {
        boolean hay = true;
        if (jugadas == 9){
            hay = false;
        }
        return hay;

    }

    public Tablero getTablero() {
        return tablero;
    }
}
