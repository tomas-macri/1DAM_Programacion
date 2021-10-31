package modelo;

public class Videogame extends Product {

    private final String fabricante;

    public Videogame(String titulo, int cantidad, String genero, String fabricante) {
        super(titulo, cantidad, genero);
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return
                "Videogame: (" + super.toString() +
        ", fabricante='" + fabricante + ")";
    }
}
