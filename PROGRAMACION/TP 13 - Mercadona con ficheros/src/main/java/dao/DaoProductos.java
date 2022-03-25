package dao;

import modelo.Productos.Producto;
import modelo.Productos.ProductoNormal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoProductos {

    private DataBase productosDataBase;

    public DaoProductos() {
        this.productosDataBase = new DataBase();
    }

    public boolean agregarProducto(Producto productoNuevo) {
        List<Producto> listaProductos = productosDataBase.loadProductos();
        if (listaProductos != null) {
            boolean seAgrego = listaProductos.add(productoNuevo);
            productosDataBase.saveProductos(listaProductos);
            return seAgrego;
        }
        return false;
    }

    public boolean eliminarProducto(String nombProd) {
        List<Producto> listaProductos = productosDataBase.loadProductos();
        if (listaProductos != null) {
            boolean seElimino = listaProductos.remove(new ProductoNormal(nombProd));
            productosDataBase.saveProductos(listaProductos);
            return seElimino;
        }
        return false;
    }

    public boolean elProductoExiste(Producto prod) {
        List<Producto> listaProductos = productosDataBase.loadProductos();
        if (listaProductos != null) {
            return listaProductos.contains(prod);
        }
        return false;
    }

    public void modificarProducto(int indexProdViejo, Producto prodNuevo) {
        List<Producto> listaProductos = productosDataBase.loadProductos();
        if (listaProductos != null) {
            listaProductos.set(indexProdViejo, prodNuevo);
            productosDataBase.saveProductos(listaProductos);
        }
    }

    public void modificarProductoNombre(int indexProdViejo, String nombNuevo) {
        List<Producto> listaProductos = productosDataBase.loadProductos();
        if (listaProductos != null) {
            listaProductos.get(indexProdViejo).setNombre(nombNuevo);
        }
    }

    public void modificarProductoPrecio(int indexProdViejo, double precioNuevo) {
        List<Producto> listaProductos = productosDataBase.loadProductos();
        if (listaProductos != null) {
            listaProductos.get(indexProdViejo).setPrecio(precioNuevo);
        }
    }

    public void modificarProductoStock(int indexProdViejo, int stockNuevo) {
        List<Producto> listaProductos = productosDataBase.loadProductos();
        if (listaProductos != null) {
            listaProductos.get(indexProdViejo).setStock(stockNuevo);
        }
    }

    public Producto getProducto(int indexProd) {
        List<Producto> listaProductos = productosDataBase.loadProductos();
        if (listaProductos != null) {
            return listaProductos.get(indexProd);
        }
        return null;
    }


    public int obtenerIndexProducto(Producto prod) {
        List<Producto> listaProductos = productosDataBase.loadProductos();
        if (listaProductos != null) {
            return listaProductos.indexOf(prod);

        }
        return -1;
    }

    public List<Producto> devolverLista() {
        List<Producto> listaProductos = productosDataBase.loadProductos();
        if (listaProductos != null) {
            return listaProductos.stream()
                    .map(Producto::clonar)
                    .collect(Collectors.toUnmodifiableList());

        }
        return new ArrayList<>();
    }
}
