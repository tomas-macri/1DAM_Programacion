package dao;

import modelo.Producto;
import java.util.ArrayList;

public class DaoProducto {

    ArrayList<Producto> listaProductos;

    public DaoProducto() {
        listaProductos = new ArrayList<>();
    }

    public boolean agregarProducto(Producto productoNuevo){
        if (!elProductoExiste(productoNuevo)){
            return listaProductos.add(productoNuevo);
        }
        return false;
    }

    public boolean eliminarProducto(String nombProd){
        return listaProductos.remove(new Producto(nombProd));
    }

    public boolean elProductoExiste(Producto prod){
        return listaProductos.contains(prod);
    }

    public void modificarProducto(Producto prodNuevo, String nombOriginal){
        int indexProdViejo = listaProductos.indexOf(new Producto(nombOriginal));
        listaProductos.set(indexProdViejo, prodNuevo);
    }

    public void modificarProductoNombre(String nombOriginal, String nombNuevo){
        int indexProdViejo = listaProductos.indexOf(new Producto(nombOriginal));
        listaProductos.get(indexProdViejo).setNombre(nombNuevo);
    }

    public void modificarProductoPrecio(String nombOriginal, double precioNuevo){
        int indexProdViejo = listaProductos.indexOf(new Producto(nombOriginal));
        listaProductos.get(indexProdViejo).setPrecio(precioNuevo);
    }

    public void modificarProductoStock(String nombOriginal, int stockNuevo){
        int indexProdViejo = listaProductos.indexOf(new Producto(nombOriginal));
        listaProductos.get(indexProdViejo).setStock(stockNuevo);
    }



    public Producto getProducto(String nombre){
        return listaProductos.get(listaProductos.indexOf(nombre));
    }

    @Override
    public String toString() {
        return "Lista de productos = " + listaProductos;
    }
}
