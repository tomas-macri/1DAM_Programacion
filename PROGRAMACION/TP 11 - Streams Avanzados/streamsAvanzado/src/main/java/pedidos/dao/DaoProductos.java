package pedidos.dao;

import pedidos.dao.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class DaoProductos {
    private static List<Producto> productos = new ArrayList<>();

    static {
        productos.add(new Producto("prod1",  6,15));
        productos.add(new Producto("prod2", 4, 10));
        productos.add(new Producto("prod3", 3, 20));
        productos.add(new Producto("prod4", 2, 15));

    }

    public List<Producto> getProductos() {
        return productos;
    }

    public boolean addProducto(Producto producto){
            return productos.add(producto);
    }
}
