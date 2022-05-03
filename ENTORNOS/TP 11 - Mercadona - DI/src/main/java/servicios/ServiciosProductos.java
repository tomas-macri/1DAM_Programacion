package servicios;

import modelo.Producto;

import java.util.List;

public interface ServiciosProductos {
    boolean agregarProducto(Producto productoNuevo);

    boolean eliminarProducto(String nombProd);

    boolean modificarProducto(Producto prodNuevo, String nombOriginal);

    boolean modificarProductoNombre(String nombOriginal, String nombNuevo);

    boolean modificarProductoPrecio(String nombOriginal, double precioNuevo);

    boolean modificarProductoStock(String nombOriginal, int stockNuevo);

    Producto getProducto(String nombProd);

    List<Producto> getLista();
}
