package servicios;

import dao.BD;
import dao.DaoCompras;
import dao.DaoProductos;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;
import modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiciosCompras {
    
    SeContainerInitializer initializer = SeContainerInitializer.newInstance();
    final SeContainer container = initializer.initialize();

    private DaoCompras daoCompras;

    @Inject
    public ServiciosCompras(Usuario user, DaoCompras daoCompras) {
        user.setCarrito(new ArrayList<>());
        this.daoCompras = daoCompras;
        
    }

    public boolean hayStock(int stockAComprar, Producto prod) {
        if (stockAComprar > 0 && prod.getStock() - stockAComprar >= 0) {
            daoCompras.quitarStock(stockAComprar, prod);
            return true;
        }
        return false;
    }

    public boolean agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado) {
        if (prodComp != null && userLogueado != null) {
            daoCompras.agregarALaCompra(prodComp, userLogueado);
            return true;
        }
        return false;
    }

    public boolean eliminarDeLaCompra(Producto prod, Usuario user) {
        if (prod != null) {
            return daoCompras.eliminarDeLaCompra(prod, user);
        } else {
            return false;
        }
    }

    public List<ProductoComprado> getListaCompra(Usuario userLogueado) {
        return daoCompras.devolverLista(userLogueado);
    }

    public boolean pagar(Tarjeta tarjeta, Usuario user) {
        boolean pudoPagar;
        if (!tarjeta.getNombre().equalsIgnoreCase("error") && user != null && !user.getCarrito().isEmpty()) {
            double saldoDisponible = tarjeta.getSaldo();
            AtomicInteger valorFinalCompra = new AtomicInteger();
            user.getCarrito().forEach(productoComprado -> valorFinalCompra.addAndGet((int) (productoComprado.getCantidad() * productoComprado.getProducto().getPrecio()))

            );

            if (saldoDisponible - valorFinalCompra.get() > 0) {
                int porcentajeACobrar = 100;
                if (user.getClass() == UsuarioEspecial.class){
                    porcentajeACobrar -= ((UsuarioEspecial) user).getPorcentajeDescuento();
                }
                daoCompras.pagar(tarjeta, valorFinalCompra.get(), user, porcentajeACobrar);
                return true;
            } else {
                pudoPagar = false;
            }
        } else {
            pudoPagar = false;
        }
        return pudoPagar;
    }

    public List<List<ProductoComprado>> getComprasPrevias(Usuario userLogueado) {
        return daoCompras.devolverComprasPrevias(userLogueado);
    }

    public List<Producto> getProductosSinAlergia(Usuario userLogueado, List<Producto> lista) {
        return daoCompras.getProductosSinAlergias(userLogueado, lista);
    }

    public Double dineroGastadoPorCliente(Usuario userLogueado){
        return daoCompras.dineroGastadoDeCliente(userLogueado);
    }
}
