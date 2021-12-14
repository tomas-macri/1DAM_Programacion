package tateti.modelo;

import java.util.Arrays;

//POJO
public class Tablero {

    private final String [][]celdas;

    public Tablero(int tamX,int tamY) {
        celdas = new String[tamX][tamY];
        for (int i = 0; i < tamX; i++) {
            for (int j = 0; j < tamY; j++) {
                celdas[i][j] = "- ";
            }
        }
    }

    public String getCelda(int x, int y){
        if (celdas[x][y] == null)
            return "-";
        return celdas[x][y];

    }
    public void setCelda(int x,int y,String valor){
        celdas[x][y] = valor;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                sb.append(" "+celdas[i][j]+" ");
            }
            sb.append("\n");
        }


        return sb.toString();
    }
}
