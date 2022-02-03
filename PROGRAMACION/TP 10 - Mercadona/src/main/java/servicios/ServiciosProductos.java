package servicios;

import dao.DaoProductos;
import modelo.Producto;

import java.util.List;
import java.util.stream.Collectors;

public class ServiciosProductos {
    DaoProductos daoProductos = new DaoProductos();
    public boolean agregarProducto(Producto productoNuevo) {
        if (!daoProductos.elProductoExiste(productoNuevo) && (!(productoNuevo.getNombre().equals("") || productoNuevo.getPrecio() < 0 || productoNuevo.getStock() < 0))) {
            return daoProductos.agregarProducto(productoNuevo);
        }
        return false;
    }

    public boolean eliminarProducto(String nombProd) {
        return daoProductos.eliminarProducto(nombProd);
    }



    public boolean modificarProducto(Producto prodNuevo, String nombOriginal) {
        boolean exito = false;
        if (!(prodNuevo.getNombre().equals("") || prodNuevo.getPrecio() <= 0 || prodNuevo.getStock() < 0)) {
            int indexProdViejo = daoProductos.obtenerIndexProducto(new Producto(nombOriginal));
            if (indexProdViejo >= 0) {
                daoProductos.modificarProducto(indexProdViejo, prodNuevo);
                exito = true;
            }
        }
        return exito;
    }

    public boolean modificarProductoNombre(String nombOriginal, String nombNuevo) {
        boolean exito = false;
        int indexProdViejo = daoProductos.obtenerIndexProducto(new Producto(nombOriginal));
        if (indexProdViejo >= 0 && !nombNuevo.equals("")) {
            daoProductos.modificarProductoNombre(indexProdViejo, nombNuevo);
            exito = true;
        }
        return exito;
    }

    public boolean modificarProductoPrecio(String nombOriginal, double precioNuevo) {
        boolean exito = false;
        int indexProdViejo = daoProductos.obtenerIndexProducto(new Producto(nombOriginal));
        if (indexProdViejo >= 0 && precioNuevo >= 0) {
            daoProductos.modificarProductoPrecio(indexProdViejo, precioNuevo);
            exito = true;
        }
        return exito;
    }

    public boolean modificarProductoStock(String nombOriginal, int stockNuevo) {
        boolean exito = false;
        int indexProdViejo = daoProductos.obtenerIndexProducto(new Producto(nombOriginal));
        if (indexProdViejo >= 0 && stockNuevo >= 0) {
            daoProductos.modificarProductoStock(indexProdViejo, stockNuevo);
            exito = true;
        }
        return exito;
    }

    public String getProducto(String nombProd) {
        String producto = "error";
        int indexProdViejo = daoProductos.obtenerIndexProducto(new Producto(nombProd));
        if (indexProdViejo >= 0) {
            producto = daoProductos.getProducto(indexProdViejo);
        }
        return producto;
    }

    public List<Producto> getLista() {
        return daoProductos.devolverLista();
    }
}
