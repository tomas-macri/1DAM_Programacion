package dao;

import modelo.Producto;

import java.util.List;

public interface DaoProductos {
    boolean agregarProducto(Producto productoNuevo);

    boolean eliminarProducto(String nombProd);

    boolean elProductoExiste(Producto prod);

    boolean modificarProducto(int indexProdViejo, Producto prodNuevo);

    boolean modificarProductoNombre(int indexProdViejo, String nombNuevo);

    boolean modificarProductoPrecio(int indexProdViejo, double precioNuevo);

    boolean modificarProductoStock(int indexProdViejo, int stockNuevo);

    Producto getProducto(int indexProd);

    int obtenerIndexProducto(Producto prod);

    List<Producto> devolverLista();
}
