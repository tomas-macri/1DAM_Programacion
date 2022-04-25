package modelo.Productos;

import modelo.Ingrediente;

import java.util.List;

public class ProductoNormal extends Producto{

  /*  public ProductoNormal(){
        super();
        type="ProductoNormal";
    }*/
    public ProductoNormal(String nombre) {
        super(nombre);
        type = "ProductoNormal";
    }

    public ProductoNormal(String nombre, double precio, int stock, List<Ingrediente> listaIngredientes) {
        super(nombre, precio, stock, listaIngredientes);
        type = "ProductoNormal";
    }
}
