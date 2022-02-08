package modelo;

import java.util.Objects;

public class Producto {

    private double precio;
    private String nombre;
    private int stock;


    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public Producto(double precio, String nombre, int stock) {
        this.precio = precio;
        this.nombre = nombre;
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public void aumentarStock(int cantidad)
    {
        this.stock += cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return nombre.equalsIgnoreCase(producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }

    @Override
    public String toString() {
        return "Producto{" +
                "precio=" + precio + " "+
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                '}';
    }
}
