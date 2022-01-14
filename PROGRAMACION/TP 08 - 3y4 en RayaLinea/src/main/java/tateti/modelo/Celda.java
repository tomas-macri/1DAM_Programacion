package tateti.modelo;

public class Celda {

    private int x;
    private int y;
    private String valor;


    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getValor() {
        if (valor == null)
            return " ";
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
