package modelo;

import java.time.LocalDateTime;

public class ProductoCaducable extends Producto{
    private LocalDateTime caducidad;

    public ProductoCaducable(String nombre,
                             double precio,
                             int stock,
                             LocalDateTime caducidad) {
        super(nombre, precio , stock);

        this.caducidad = caducidad;
    }

    public LocalDateTime getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(LocalDateTime caducidad) {
        this.caducidad = caducidad;
    }

    @Override
    public String toString() {
        return "ProductoCaducable{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", caducidad=" + caducidad +
                '}';
    }
}
