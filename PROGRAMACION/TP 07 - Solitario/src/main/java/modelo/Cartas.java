package modelo;

public class Cartas {
    int numero;
    Suits palo;

    public Cartas(int numero, Suits palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    public Suits getPalo() {
        return palo;
    }

    @Override
    public String toString() {
        return "Cartas{" +
                "numero=" + numero +
                ", palo=" + palo +
                '}';
    }


}
