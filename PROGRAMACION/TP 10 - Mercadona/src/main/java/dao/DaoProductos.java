package dao;

import modelo.Producto;

import java.util.ArrayList;

public class DaoProductos {

    private final ArrayList<Producto> listaProductos;

    public DaoProductos() {
        listaProductos = new ArrayList<>();
    }

    public boolean agregarProducto(Producto productoNuevo) {
        if (!elProductoExiste(productoNuevo) && (!(productoNuevo.getNombre().equals("") || productoNuevo.getPrecio() < 0 || productoNuevo.getStock() < 0))) {
            return listaProductos.add(productoNuevo);
        }
        return false;
    }

    public boolean eliminarProducto(String nombProd) {
        return listaProductos.remove(new Producto(nombProd));
    }

    public boolean elProductoExiste(Producto prod) {
        return listaProductos.contains(prod);
    }

    public boolean modificarProducto(Producto prodNuevo, String nombOriginal) {
        boolean exito = false;
        if (!(prodNuevo.getNombre().equals("") || prodNuevo.getPrecio() <= 0 || prodNuevo.getStock() < 0)) {
            int indexProdViejo = listaProductos.indexOf(new Producto(nombOriginal));
            if (indexProdViejo >= 0) {
                listaProductos.set(indexProdViejo, prodNuevo);
                exito = true;
            }
        }
        return exito;
    }

    public boolean modificarProductoNombre(String nombOriginal, String nombNuevo) {
        boolean exito = false;
        int indexProdViejo = listaProductos.indexOf(new Producto(nombOriginal));
        if (indexProdViejo >= 0 && !nombNuevo.equals("")) {
            listaProductos.get(indexProdViejo).setNombre(nombNuevo);
            exito = true;
        }
        return exito;
    }

    public boolean modificarProductoPrecio(String nombOriginal, double precioNuevo) {
        boolean exito = false;
        int indexProdViejo = listaProductos.indexOf(new Producto(nombOriginal));
        if (indexProdViejo >= 0 && precioNuevo >= 0) {
            listaProductos.get(indexProdViejo).setPrecio(precioNuevo);
            exito = true;
        }
        return exito;
    }

    public boolean modificarProductoStock(String nombOriginal, int stockNuevo) {
        boolean exito = false;
        int indexProdViejo = listaProductos.indexOf(new Producto(nombOriginal));
        if (indexProdViejo >= 0 && stockNuevo >= 0) {
            listaProductos.get(indexProdViejo).setStock(stockNuevo);
            exito = true;
        }
        return exito;
    }

    public String getProducto(String nombProd) {
        String producto = "error";
        int indexProdViejo = listaProductos.indexOf(new Producto(nombProd));
        if (indexProdViejo >= 0) {
            producto = listaProductos.get(indexProdViejo).toString();
        }
        return producto;
    }

    @Override
    public String toString() {
        return "Lista de productos = " + listaProductos;
    }
}
