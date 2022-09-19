package dao;

import modelo.Producto;
import modelo.ProductoComprado;
import modelo.Tarjeta;
import modelo.Usuario;

import java.util.List;

public interface DaoCompras {
    void quitarStock(int cant, Producto prod);

    boolean agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado);

    boolean eliminarDeLaCompra(Producto prod, Usuario user);

    void pagar(Tarjeta tarjeta, int valorCompra, Usuario user, int porcentajeACobrar);

    List<ProductoComprado> devolverLista(Usuario userLogueado);

    List<List<ProductoComprado>> devolverComprasPrevias(Usuario userLogueado);

    List<Producto> getProductosSinAlergias(Usuario userLogueado, List<Producto> lista);

    Double dineroGastadoDeCliente(Usuario userLogueado);
}
