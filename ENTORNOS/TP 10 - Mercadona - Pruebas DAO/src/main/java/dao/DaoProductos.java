package dao;

import modelo.Producto;

import java.util.List;
import java.util.stream.Collectors;

public class DaoProductos {

    public boolean agregarProducto(Producto productoNuevo) {
        return BD.listaProductos.add(productoNuevo);
    }

    public boolean eliminarProducto(String nombProd) {
        return BD.listaProductos.remove(new Producto(nombProd));
    }

    public boolean elProductoExiste(Producto prod) {
        return BD.listaProductos.contains(prod);
    }

    public void modificarProducto(int indexProdViejo, Producto prodNuevo) {
        BD.listaProductos.set(indexProdViejo, prodNuevo);
    }

    public void modificarProductoNombre(int indexProdViejo, String nombNuevo) {
        BD.listaProductos.get(indexProdViejo).setNombre(nombNuevo);
    }

    public void modificarProductoPrecio(int indexProdViejo, double precioNuevo) {
        BD.listaProductos.get(indexProdViejo).setPrecio(precioNuevo);
    }

    public void modificarProductoStock(int indexProdViejo, int stockNuevo) {
        BD.listaProductos.get(indexProdViejo).setStock(stockNuevo);
    }

    public Producto getProducto(int indexProd) {
        return BD.listaProductos.get(indexProd);
    }

    public int obtenerIndexProducto(Producto prod) {
        return BD.listaProductos.indexOf(prod);
    }

    public List<Producto> devolverLista() {
        return BD.listaProductos.stream()
                .map(Producto::clonar)
                .collect(Collectors.toUnmodifiableList());
    }
}
