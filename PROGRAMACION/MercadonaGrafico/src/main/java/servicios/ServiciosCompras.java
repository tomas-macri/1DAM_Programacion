package servicios;

import modelo.ProductoComprado;
import modelo.Productos.Producto;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;

import java.util.List;

public interface ServiciosCompras {
    boolean hayStock(int stockAComprar, Producto prod);

    Usuario agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado);

    boolean eliminarDeLaCompra(Producto prod, Usuario user);

    List<ProductoComprado> getListaCompra(Usuario userLogueado);

    boolean pagar(Tarjeta tarjeta, Usuario user);

    List<List<ProductoComprado>> getComprasPrevias(Usuario userLogueado);

    List<Producto> getProductosSinAlergia(Usuario userLogueado, List<Producto> lista);

    Double dineroGastadoPorCliente(Usuario userLogueado);
}
