package modelo.Productos;

import modelo.Ingrediente;

import java.util.List;

public class ProductoNormal extends Producto{
    public ProductoNormal(String nombre) {
        super(nombre);
    }

    public ProductoNormal(String nombre, double precio, int stock, List<Ingrediente> listaIngredientes) {
        super(nombre, precio, stock, listaIngredientes);
    }
}
