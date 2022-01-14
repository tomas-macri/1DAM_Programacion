package modelo;

public class Tablero {

    private final Ficha[][] fichas;

    public Tablero(int tamX, int tamY) {
        fichas = new Ficha[tamY][tamX];
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                fichas[i][j] = new Ficha(i, j);
            }
        }
    }

    public Ficha getFicha(int x, int y) {
        return fichas[y][x];
    }

    public int getValorFicha(int x, int y) {
        return fichas[y][x].getValor();
    }

    public void setValorFicha(int x, int y, int valor) {
        fichas[y][x].setValor(valor);
    }

    public boolean getFichaDescubierta(int x, int y) {
        return fichas[y][x].isDescubierta();
    }

    public void setFichaDescubierta(int x, int y, boolean valor) {
        fichas[y][x].setDescubierta(valor);
    }

    public int getY() {
        return fichas.length;
    }

    public int getX() {
        return fichas[0].length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas[i].length; j++) {
                if (fichas[i][j].isDescubierta()) {
                    sb.append(" " + fichas[i][j].getValor() + " ");
                } else {
                    sb.append(" - ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
