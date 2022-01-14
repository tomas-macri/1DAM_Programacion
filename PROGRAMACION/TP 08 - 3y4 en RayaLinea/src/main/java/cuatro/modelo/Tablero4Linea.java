package cuatro.modelo;

//POJO
public class Tablero4Linea {



    private final String [][]celdas;

    public Tablero4Linea(int tamX, int tamY) {
        celdas = new String[tamY][tamX];
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                celdas[i][j] = "-";
            }
        }
    }


    public int getYCeldas() {
        return celdas.length;
    }

    public String getCelda(int x, int y){
        if (x < 0 || y < 0 || x >= celdas[0].length || y >= celdas.length){
            return null;
        }
        return celdas[y][x];

    }
    public void setCelda(int x,int y,String valor){
        celdas[y][x] = valor;
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
