package servicios;

import dao.DaoProducto;
import modelo.Producto;

import java.util.List;

public class ServiciosProductos {


    public boolean addProducto(Producto p) {
        DaoProducto dao = new DaoProducto();
        if (p.getPrecio() < 0 || p.getStock() < 0)
            return false;
        return dao.addProducto(p);
    }


    public List<Producto> getProductos() {
        DaoProducto dao = new DaoProducto();
        return dao.verProductos();
    }
}
