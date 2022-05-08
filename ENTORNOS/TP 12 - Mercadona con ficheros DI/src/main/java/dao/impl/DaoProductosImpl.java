package dao.impl;

import dao.DaoProductos;
import jakarta.inject.Inject;
import modelo.Productos.Producto;
import modelo.Productos.ProductoNormal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoProductosImpl implements DaoProductos {

    private DataBase dataBase;

    @Inject
    public DaoProductosImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean agregarProducto(Producto productoNuevo) {
        List<Producto> listaProductos = dataBase.loadProductos();
        if (listaProductos != null) {
            boolean seAgrego = listaProductos.add(productoNuevo);
            dataBase.saveProductos(listaProductos);
            return seAgrego;
        }
        return false;
    }

    @Override
    public boolean eliminarProducto(String nombProd) {
        List<Producto> listaProductos = dataBase.loadProductos();
        if (listaProductos != null) {
            boolean seElimino = listaProductos.remove(new ProductoNormal(nombProd));
            dataBase.saveProductos(listaProductos);
            return seElimino;
        }
        return false;
    }

    @Override
    public boolean elProductoExiste(Producto prod) {
        List<Producto> listaProductos = dataBase.loadProductos();
        if (listaProductos != null) {
            return listaProductos.contains(prod);
        }
        return false;
    }

    @Override
    public void modificarProducto(int indexProdViejo, Producto prodNuevo) {
        List<Producto> listaProductos = dataBase.loadProductos();
        if (listaProductos != null) {
            listaProductos.set(indexProdViejo, prodNuevo);
            dataBase.saveProductos(listaProductos);
        }
    }

    @Override
    public void modificarProductoNombre(int indexProdViejo, String nombNuevo) {
        List<Producto> listaProductos = dataBase.loadProductos();
        if (listaProductos != null) {
            listaProductos.get(indexProdViejo).setNombre(nombNuevo);
            dataBase.saveProductos(listaProductos);
        }
    }

    @Override
    public void modificarProductoPrecio(int indexProdViejo, double precioNuevo) {
        List<Producto> listaProductos = dataBase.loadProductos();
        if (listaProductos != null) {
            listaProductos.get(indexProdViejo).setPrecio(precioNuevo);
            dataBase.saveProductos(listaProductos);
        }
    }

    @Override
    public void modificarProductoStock(int indexProdViejo, int stockNuevo) {
        List<Producto> listaProductos = dataBase.loadProductos();
        if (listaProductos != null) {
            listaProductos.get(indexProdViejo).setStock(stockNuevo);
            dataBase.saveProductos(listaProductos);
        }
    }

    @Override
    public Producto getProducto(int indexProd) {
        List<Producto> listaProductos = dataBase.loadProductos();
        if (listaProductos != null && indexProd != -1) {
            return listaProductos.get(indexProd);
        }
        return null;
    }


    @Override
    public int obtenerIndexProducto(Producto prod) {
        List<Producto> listaProductos = dataBase.loadProductos();
        if (listaProductos != null) {
            return listaProductos.indexOf(prod);
        }
        return -1;
    }

    @Override
    public List<Producto> devolverLista() {
        List<Producto> listaProductos = dataBase.loadProductos();
        if (listaProductos != null) {
            return listaProductos.stream()
                    .map(Producto::clonar)
                    .collect(Collectors.toUnmodifiableList());

        }
        return new ArrayList<>();
    }
}
