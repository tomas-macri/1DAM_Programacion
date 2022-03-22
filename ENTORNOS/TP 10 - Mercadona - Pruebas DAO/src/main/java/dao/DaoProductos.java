package dao;

import modelo.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoProductos {

    private ArrayList<Producto> bdProductos;

    public DaoProductos(ArrayList<Producto> bdProductos) {
        this.bdProductos = bdProductos;
    }

    public boolean agregarProducto(Producto productoNuevo) {
        if (!elProductoExiste(productoNuevo)) {
            return bdProductos.add(productoNuevo);
        }
        return false;
    }

    public boolean eliminarProducto(String nombProd) {
        return bdProductos.remove(new Producto(nombProd));
    }

    public boolean elProductoExiste(Producto prod) {
        return bdProductos.contains(prod);
    }

    public boolean modificarProducto(int indexProdViejo, Producto prodNuevo) {
        if (!(indexProdViejo < 0 || indexProdViejo > bdProductos.size() - 1)) {
            bdProductos.set(indexProdViejo, prodNuevo);
            return true;
        }
        return false;
    }

    public boolean modificarProductoNombre(int indexProdViejo, String nombNuevo) {
        if (!(indexProdViejo < 0 || indexProdViejo > bdProductos.size() - 1)) {
            bdProductos.get(indexProdViejo).setNombre(nombNuevo);
            return true;
        }
        return false;
    }

    public boolean modificarProductoPrecio(int indexProdViejo, double precioNuevo) {
        if (!(indexProdViejo < 0 || indexProdViejo > bdProductos.size() - 1)) {
            bdProductos.get(indexProdViejo).setPrecio(precioNuevo);
            return true;
        }
        return false;
    }

    public boolean modificarProductoStock(int indexProdViejo, int stockNuevo) {
        if (!(indexProdViejo < 0 || indexProdViejo > bdProductos.size() - 1)) {
            bdProductos.get(indexProdViejo).setStock(stockNuevo);
            return true;
        }
        return false;
    }

    public Producto getProducto(int indexProd) {
        if (!(indexProd < 0 || indexProd > bdProductos.size() - 1)) {
            return bdProductos.get(indexProd);
        }
        return null;
    }

    public int obtenerIndexProducto(Producto prod) {
        return bdProductos.indexOf(prod);
    }

    public List<Producto> devolverLista() {
        return bdProductos.stream()
                .map(Producto::clonar)
                .collect(Collectors.toUnmodifiableList());
    }
}
