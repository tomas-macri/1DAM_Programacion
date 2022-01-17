package modelo;

public class Ficha {
    private int x;
    private int y;
    private int valor;
    private boolean descubierta;


    public Ficha(int x, int y) {
        this.x = x;
        this.y = y;
        //por defecto el valor será 0 y descubierta sera false
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public void setDescubierta(boolean descubierta) {
        this.descubierta = descubierta;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}

