package dao;

import modelo.Productos.Producto;

import java.util.List;

public interface DaoProductos {
    boolean agregarProducto(Producto productoNuevo);

    boolean eliminarProducto(String nombProd);

    boolean elProductoExiste(Producto prod);

    void modificarProducto(int indexProdViejo, Producto prodNuevo);

    void modificarProductoNombre(int indexProdViejo, String nombNuevo);

    void modificarProductoPrecio(int indexProdViejo, double precioNuevo);

    void modificarProductoStock(int indexProdViejo, int stockNuevo);

    Producto getProducto(int indexProd);

    int obtenerIndexProducto(Producto prod);

    List<Producto> devolverLista();
}
