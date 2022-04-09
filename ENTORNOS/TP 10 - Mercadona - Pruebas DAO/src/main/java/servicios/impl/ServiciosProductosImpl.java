package servicios.impl;

import dao.DaoProductos;
import jakarta.inject.Inject;
import modelo.Producto;
import modelo.ProductoCaducable;
import servicios.ServiciosProductos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class ServiciosProductosImpl implements ServiciosProductos {
    
    private DaoProductos daoProductosImpl;
    
    @Inject
    public ServiciosProductosImpl (DaoProductos daoProductosImpl){
        this.daoProductosImpl = daoProductosImpl;
    }
    
    
    @Override public boolean agregarProducto(Producto productoNuevo) {
       
        if (!daoProductosImpl.elProductoExiste(productoNuevo) && (!(productoNuevo.getNombre().equals("") || productoNuevo.getPrecio() < 0 || productoNuevo.getStock() < 0))) {
            return daoProductosImpl.agregarProducto(productoNuevo);
        }
        return false;
    }

    @Override public boolean eliminarProducto(String nombProd) {
       
        return daoProductosImpl.eliminarProducto(nombProd);
    }


    @Override public boolean modificarProducto(Producto prodNuevo, String nombOriginal) {
       
        boolean exito = false;
        if (!(prodNuevo.getNombre().equals("") || prodNuevo.getPrecio() <= 0 || prodNuevo.getStock() < 0)) {
            int indexProdViejo = daoProductosImpl.obtenerIndexProducto(new Producto(nombOriginal));
            if (indexProdViejo >= 0) {
                daoProductosImpl.modificarProducto(indexProdViejo, prodNuevo);
                exito = true;
            }
        }
        return exito;
    }

    @Override public boolean modificarProductoNombre(String nombOriginal, String nombNuevo) {
       
        boolean exito = false;
        int indexProdViejo = daoProductosImpl.obtenerIndexProducto(new Producto(nombOriginal));
        if (indexProdViejo >= 0 && !nombNuevo.equals("")) {
            daoProductosImpl.modificarProductoNombre(indexProdViejo, nombNuevo);
            exito = true;
        }
        return exito;
    }

    @Override public boolean modificarProductoPrecio(String nombOriginal, double precioNuevo) {
       
        boolean exito = false;
        int indexProdViejo = daoProductosImpl.obtenerIndexProducto(new Producto(nombOriginal));
        if (indexProdViejo >= 0 && precioNuevo >= 0) {
            daoProductosImpl.modificarProductoPrecio(indexProdViejo, precioNuevo);
            exito = true;
        }
        return exito;
    }

    @Override public boolean modificarProductoStock(String nombOriginal, int stockNuevo) {
       
        boolean exito = false;
        int indexProdViejo = daoProductosImpl.obtenerIndexProducto(new Producto(nombOriginal));
        if (indexProdViejo >= 0 && stockNuevo >= 0) {
            daoProductosImpl.modificarProductoStock(indexProdViejo, stockNuevo);
            exito = true;
        }
        return exito;
    }

    @Override public Producto getProducto(String nombProd) {
       
        Producto producto = null;
        int indexProd = daoProductosImpl.obtenerIndexProducto(new Producto(nombProd));
        if (indexProd >= 0) {
            producto = daoProductosImpl.getProducto(indexProd);
        }
        return producto;
    }


    @Override public List<Producto> getLista() {
       
        return daoProductosImpl.devolverLista().stream()
                .filter(producto -> !((producto instanceof ProductoCaducable) && ((ProductoCaducable) producto).getCaducidad().isBefore(LocalDateTime.now())))
                .collect(Collectors.toUnmodifiableList());
    }
}
