package servicios;

import dao.DaoProductos;
import modelo.Productos.Producto;
import modelo.Productos.ProductoCaducable;
import modelo.Productos.ProductoNormal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class ServiciosProductos {
    public boolean agregarProducto(Producto productoNuevo) {
        DaoProductos daoProductos = new DaoProductos();
        if (!daoProductos.elProductoExiste(productoNuevo) && (!(productoNuevo.getNombre().equals("") || productoNuevo.getPrecio() < 0 || productoNuevo.getStock() < 0))) {
            return daoProductos.agregarProducto(productoNuevo);
        }
        return false;
    }

    public boolean eliminarProducto(String nombProd) {
        DaoProductos daoProductos = new DaoProductos();
        return daoProductos.eliminarProducto(nombProd);
    }


    public boolean modificarProducto(Producto prodNuevo, String nombOriginal) {
        DaoProductos daoProductos = new DaoProductos();
        boolean exito = false;
        if (!(prodNuevo.getNombre().equals("") || prodNuevo.getPrecio() <= 0 || prodNuevo.getStock() < 0)) {
            int indexProdViejo = daoProductos.obtenerIndexProducto(new ProductoNormal(nombOriginal));
            if (indexProdViejo >= 0) {
                daoProductos.modificarProducto(indexProdViejo, prodNuevo);
                exito = true;
            }
        }
        return exito;
    }

    public boolean modificarProductoNombre(String nombOriginal, String nombNuevo) {
        DaoProductos daoProductos = new DaoProductos();
        boolean exito = false;
        int indexProdViejo = daoProductos.obtenerIndexProducto(new ProductoNormal(nombOriginal));
        if (indexProdViejo >= 0 && !nombNuevo.equals("")) {
            daoProductos.modificarProductoNombre(indexProdViejo, nombNuevo);
            exito = true;
        }
        return exito;
    }

    public boolean modificarProductoPrecio(String nombOriginal, double precioNuevo) {
        DaoProductos daoProductos = new DaoProductos();
        boolean exito = false;
        int indexProdViejo = daoProductos.obtenerIndexProducto(new ProductoNormal(nombOriginal));
        if (indexProdViejo >= 0 && precioNuevo >= 0) {
            daoProductos.modificarProductoPrecio(indexProdViejo, precioNuevo);
            exito = true;
        }
        return exito;
    }

    public boolean modificarProductoStock(String nombOriginal, int stockNuevo) {
        DaoProductos daoProductos = new DaoProductos();
        boolean exito = false;
        int indexProdViejo = daoProductos.obtenerIndexProducto(new ProductoNormal(nombOriginal));
        if (indexProdViejo >= 0 && stockNuevo >= 0) {
            daoProductos.modificarProductoStock(indexProdViejo, stockNuevo);
            exito = true;
        }
        return exito;
    }

    public Producto getProducto(String nombProd) {
        DaoProductos daoProductos = new DaoProductos();
        Producto producto = null;
        int indexProd = daoProductos.obtenerIndexProducto(new ProductoNormal(nombProd));
        if (indexProd >= 0) {
            producto = daoProductos.getProducto(indexProd);
        }
        return producto;
    }


    public List<Producto> getLista() {
        DaoProductos daoProductos = new DaoProductos();
        return daoProductos.devolverLista().stream()
                .filter(producto -> !((producto instanceof ProductoCaducable) && ((ProductoCaducable) producto).getCaducidad().isBefore(LocalDateTime.now())))
                .collect(Collectors.toUnmodifiableList());
    }
}
