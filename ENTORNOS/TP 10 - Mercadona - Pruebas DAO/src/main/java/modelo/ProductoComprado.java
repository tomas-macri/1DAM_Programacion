package modelo;

import java.util.Objects;

public class ProductoComprado implements Clonable<ProductoComprado> {

    private Producto producto;
    private int cantidad;

    public ProductoComprado(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ProductoComprado(Producto producto) {
        this.producto = producto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoComprado that = (ProductoComprado) o;
        return producto.equals(that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto);
    }

    @Override
    public String toString() {
        return "ProductoComprado{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public ProductoComprado clonar() {
        return new ProductoComprado(getProducto(), getCantidad());
    }
}
