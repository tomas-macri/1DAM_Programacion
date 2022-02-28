package modelo;

import java.time.LocalDateTime;
import java.util.List;

public class ProductoCaducable extends Producto implements Clonable<Producto> {
    private LocalDateTime caducidad;

    public ProductoCaducable(String nombre,
                             double precio,
                             int stock,
                             List<Ingrediente> ingredienteArrayList,
                             LocalDateTime caducidad) {
        super(nombre, precio , stock, ingredienteArrayList);

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
                ", listaIngredientes=" + listaIngredientes +
                ", caducidad=" + caducidad +
                '}';
    }

    @Override
    public Producto clonar() {
        return new ProductoCaducable(this.getNombre(), this.getPrecio(), this.getStock(), this.getListaIngredientes(), this.caducidad);
    }
}
