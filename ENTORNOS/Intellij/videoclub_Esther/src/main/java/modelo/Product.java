package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Product {

    private String titulo;
    private final List<Poll> polls;
    private int cantidad;
    private int cantidadAlquilada;
    private String genero;
    private double valoracionMedia;

    public Product(String titulo, int cantidad, String genero) {
        this();
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.genero = genero;
    }

    public Product() {
        polls = new ArrayList<>();
        this.cantidadAlquilada = 0;
        this.valoracionMedia = 0;
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", cantidad=" + cantidad +
                ", cantidadAlquilada=" + cantidadAlquilada +
                ", cantidadDisponible=" + (cantidad - cantidadAlquilada) +
                ", genero='" + genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Poll> getEncuestas() {
        return polls;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {

        this.cantidad = cantidad;
    }

    public int getCantidadAlquilada() {
        return cantidadAlquilada;
    }

    public void setCantidadAlquilada(int cantidadAlquilada) {
        this.cantidadAlquilada = cantidadAlquilada;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getValoracionMedia() {
        return valoracionMedia;
    }

    public void setValoracionMedia(double valoracionMedia) {
        this.valoracionMedia = valoracionMedia;
    }
}
