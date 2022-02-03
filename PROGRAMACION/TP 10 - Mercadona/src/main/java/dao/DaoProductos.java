package dao;

import modelo.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoProductos {

    private static final ArrayList<Producto> listaProductos = new ArrayList<>();


    public boolean agregarProducto(Producto productoNuevo) {
        return listaProductos.add(productoNuevo);
    }

    public boolean eliminarProducto(String nombProd) {
        return listaProductos.remove(new Producto(nombProd));
    }

    public boolean elProductoExiste(Producto prod) {
        return listaProductos.contains(prod);
    }

    public void modificarProducto(int indexProdViejo, Producto prodNuevo) {
        listaProductos.set(indexProdViejo, prodNuevo);
    }

    public void modificarProductoNombre(int indexProdViejo, String nombNuevo) {
        listaProductos.get(indexProdViejo).setNombre(nombNuevo);
    }

    public void modificarProductoPrecio(int indexProdViejo, double precioNuevo) {
        listaProductos.get(indexProdViejo).setPrecio(precioNuevo);
    }

    public void modificarProductoStock(int indexProdViejo, int stockNuevo) {
        listaProductos.get(indexProdViejo).setStock(stockNuevo);
    }

    public String getProducto(int indexProd) {
        return listaProductos.get(indexProd).toString();
    }

    public int obtenerIndexProducto(Producto prod) {
        return listaProductos.indexOf(prod);
    }


    public List<Producto> devolverLista() {
        return listaProductos.stream()
                .map(producto -> new Producto(producto.getNombre(), producto.getPrecio(), producto.getStock()))
                .collect(Collectors.toUnmodifiableList());
    }
}
