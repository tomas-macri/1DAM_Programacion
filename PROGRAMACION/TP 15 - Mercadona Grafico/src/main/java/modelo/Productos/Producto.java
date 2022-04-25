package modelo.Productos;

import modelo.Clonable;
import modelo.Ingrediente;

import java.util.List;
import java.util.Objects;

public abstract class Producto implements Clonable<Producto> {

    public String type;
    private String nombre;
    private double precio;
    private int stock;
    private List<Ingrediente> listaIngredientes;

    protected Producto(){

    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public Producto(String nombre, double precio, int stock, List<Ingrediente> listaIngredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.listaIngredientes =  listaIngredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() && o.getClass()!= ProductoCaducable.class) return false;
        Producto producto = (Producto) o;
        return Objects.equals(nombre.toLowerCase(), producto.nombre.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", listaIngredientes=" + listaIngredientes +
                "} \n";
    }

    @Override
    public Producto clonar() {
        return new ProductoNormal(this.nombre, this.precio, this.stock, this.listaIngredientes);
    }
}
