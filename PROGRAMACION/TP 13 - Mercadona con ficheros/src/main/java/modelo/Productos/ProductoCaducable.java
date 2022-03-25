package modelo.Productos;

import modelo.Clonable;
import modelo.Ingrediente;

import java.time.LocalDateTime;
import java.util.List;

public class ProductoCaducable extends Producto implements Clonable<Producto> {
    private LocalDateTime caducidad;

    /*public ProductoCaducable(){
        super();
        type="ProductoCaducable";
    }*/

    public ProductoCaducable(String nombre,
                             double precio,
                             int stock,
                             List<Ingrediente> ingredienteArrayList,
                             LocalDateTime caducidad) {
        super(nombre, precio , stock, ingredienteArrayList);
        type = "ProductoCaducable";

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
                "nombre='" + getNombre() + '\'' +
                "tipo='" + type + '\'' +
                ", precio=" + getPrecio() +
                ", stock=" + getStock() +
                ", listaIngredientes=" + getListaIngredientes() +
                ", caducidad=" + caducidad +
                '}';
    }

    @Override
    public Producto clonar() {
        return new ProductoCaducable(this.getNombre(), this.getPrecio(), this.getStock(), this.getListaIngredientes(), this.caducidad);
    }
}
