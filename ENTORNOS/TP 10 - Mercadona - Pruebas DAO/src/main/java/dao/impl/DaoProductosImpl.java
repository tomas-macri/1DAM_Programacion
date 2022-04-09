package dao.impl;

import dao.BD;
import dao.DaoProductos;
import jakarta.inject.Inject;
import modelo.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoProductosImpl implements DaoProductos {

    private ArrayList<Producto> bdProductos;

    @Inject
    public DaoProductosImpl(BD bd) {
        this.bdProductos = bd.listaProductos;
    }



    @Override public boolean agregarProducto(Producto productoNuevo) {
        if (!elProductoExiste(productoNuevo)) {
            return bdProductos.add(productoNuevo);
        }
        return false;
    }

    @Override public boolean eliminarProducto(String nombProd) {
        return bdProductos.remove(new Producto(nombProd));
    }

    @Override public boolean elProductoExiste(Producto prod) {
        return bdProductos.contains(prod);
    }

    @Override public boolean modificarProducto(int indexProdViejo, Producto prodNuevo) {
        if (!(indexProdViejo < 0 || indexProdViejo > bdProductos.size() - 1)) {
            bdProductos.set(indexProdViejo, prodNuevo);
            return true;
        }
        return false;
    }

    @Override public boolean modificarProductoNombre(int indexProdViejo, String nombNuevo) {
        if (!(indexProdViejo < 0 || indexProdViejo > bdProductos.size() - 1)) {
            bdProductos.get(indexProdViejo).setNombre(nombNuevo);
            return true;
        }
        return false;
    }

    @Override public boolean modificarProductoPrecio(int indexProdViejo, double precioNuevo) {
        if (!(indexProdViejo < 0 || indexProdViejo > bdProductos.size() - 1)) {
            bdProductos.get(indexProdViejo).setPrecio(precioNuevo);
            return true;
        }
        return false;
    }

    @Override public boolean modificarProductoStock(int indexProdViejo, int stockNuevo) {
        if (!(indexProdViejo < 0 || indexProdViejo > bdProductos.size() - 1)) {
            bdProductos.get(indexProdViejo).setStock(stockNuevo);
            return true;
        }
        return false;
    }

    @Override public Producto getProducto(int indexProd) {
        if (!(indexProd < 0 || indexProd > bdProductos.size() - 1)) {
            return bdProductos.get(indexProd);
        }
        return null;
    }

    @Override public int obtenerIndexProducto(Producto prod) {
        return bdProductos.indexOf(prod);
    }

    @Override public List<Producto> devolverLista() {
        return bdProductos.stream()
                .map(Producto::clonar)
                .collect(Collectors.toUnmodifiableList());
    }
}
