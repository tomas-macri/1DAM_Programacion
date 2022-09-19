package ui.pantallas.compras;

import lombok.Data;
import modelo.Ingrediente;
import modelo.ProductoComprado;
import modelo.Productos.Producto;
import servicios.impl.ServiciosProductosImpl;

import java.util.List;

@Data
public class ComprasState {

    List<Producto> productosList;
    List<ProductoComprado> productosComprados;
    String error;

    public ComprasState(List<Producto> productosList, List<ProductoComprado> productosComprados, String error) {
        this.productosList = productosList;
        this.productosComprados = productosComprados;
        this.error = error;
    }
}
