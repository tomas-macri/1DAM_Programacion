package cuatro.data;

import cuatro.modelo.Tablero4Linea;


public class DaoTablero4Linea {

    //BD
    private Tablero4Linea tablero;
    private int ultimaCeldaX;
    private int ultimaCeldaY;
    private int jugadas;

    public DaoTablero4Linea(int x, int y) {
        ultimaCeldaX = -8;
        ultimaCeldaY = -8;
        tablero = new Tablero4Linea(x, y);
    }


    public String getCelda(int x, int y)
    {
        return tablero.getCelda(x,y);
    }

    public boolean setCelda(int x, String valor){

        boolean seColoca = false;
        int altura = 0;

        while (altura < tablero.getYCeldas() && !seColoca){
            if (!tablero.getCelda(x, altura).equals("-")){
                seColoca = true;
            }
            else{
                altura++;
            }
        }

        if ((seColoca || altura-1 == tablero.getYCeldas()-1) && altura != 0){ // altura puede ser 0 si la fila ya esta llena hasta arriba
            seColoca = true;
            tablero.setCelda(x,altura-1,valor);
            ultimaCeldaX = x;
            ultimaCeldaY = altura-1;
            jugadas++;
        }
        else {
            seColoca = false; // este else es por si altura es 0 y entra en el if del while a la primera, no colocaria ficha
        }
        return seColoca;
    }

    public int getJugadas(){
        return jugadas;
    }


    public boolean hayCeldaLibre(int x, int y) {
        boolean hay = true;
        if (jugadas == x*y){
            hay = false;
        }
        return hay;

    }

    public Tablero4Linea getTablero() {
        return tablero;
    }

    public boolean cuatroLinea(String valor)
    {
        boolean hayCuatro = false;
            int auxX = ultimaCeldaX;
            int auxY = ultimaCeldaY;
            int contPosi = 0;

            // valido HORIZONTAL

            // valido izquierda
            while (valor.equals(tablero.getCelda(auxX, auxY))){
                auxX--;
                contPosi++;
            }

            // valido derecha
            auxX = ultimaCeldaX;
            while (valor.equals(tablero.getCelda(auxX, auxY))){
                auxX++;
                contPosi++;
            }
            // suman 4?
            if (contPosi - 1 >= 4){ //se resta uno porque se suma cada vez que valida para un lado
                hayCuatro = true;
            }

            if (!hayCuatro){
                // no hay 4 en linea horizontal
                // reinicio valores
                contPosi = 0;
                auxX = ultimaCeldaX;

                //validacion vertical
                //validacion hacia arriba es inutil porque va contra las reglas
                // valido abajo
                auxY = ultimaCeldaY;
                while (valor.equals(tablero.getCelda(auxX, auxY))) {
                    auxY++;
                    contPosi++;
                }
                // suman 4?
                if (contPosi >= 4) {
                    hayCuatro = true; // en este caso no resto uno porque solo valido hacia abajo
                }
                if (!hayCuatro){
                    // no hay 4 en linea vertical
                    // reinicio valores
                    contPosi = 0;
                    auxY = ultimaCeldaY;

                    // valido diagonal de abajo a la izquierda hacia arriba a la derecha y viceversa
                    // valido hacia arriba derecha
                    while (valor.equals(tablero.getCelda(auxX, auxY))) {
                        auxY--;
                        auxX++;
                        contPosi++;
                    }

                    // valido hacia abajo izquierda
                    auxX = ultimaCeldaX;
                    auxY = ultimaCeldaY;
                    while (valor.equals(tablero.getCelda(auxX, auxY))){
                        auxY++;
                        auxX--;
                        contPosi++;
                    }
                    //suman 4?
                    if (contPosi-1 >= 4){
                       hayCuatro = true; //se resta uno porque se suma cada vez que valida para un lado
                    }
                    if (!hayCuatro){
                        // no hay 4 en linea diagonal /, pruebo la otra diagonal (\)
                        // reinicio valores
                        contPosi = 0;
                        auxY = ultimaCeldaY;
                        auxX = ultimaCeldaX;

                        // valido diagonal de abajo a la derecha hacia arriba a la izquierda y viceversa
                        // valido hacia arriba izquierda
                        while (valor.equals(tablero.getCelda(auxX, auxY))) {
                            auxY--;
                            auxX--;
                            contPosi++;
                        }

                        // valido hacia abajo a la derecha
                        auxX = ultimaCeldaX;
                        auxY = ultimaCeldaY;
                        while (valor.equals(tablero.getCelda(auxX, auxY))){
                            auxY++;
                            auxX++;
                            contPosi++;
                        }
                        //suman 4?
                        if (contPosi-1 >= 4){
                            hayCuatro = true; //se resta uno porque se suma cada vez que valida para un lado
                        }
                    }
                }
            }

        return  hayCuatro;
    }

}
