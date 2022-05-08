package servicios.impl;

import dao.DaoCompras;
import jakarta.inject.Inject;
import modelo.*;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioEspecial;
import servicios.ServiciosCompras;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiciosComprasImpl implements ServiciosCompras {

    private DaoCompras daoComprasImpl;

    @Inject
    public ServiciosComprasImpl( DaoCompras daoComprasImpl) {
//        user.setCarrito(new ArrayList<>());
        this.daoComprasImpl = daoComprasImpl;

    }

    @Override public boolean hayStock(int stockAComprar, Producto prod) {
        if (stockAComprar > 0 && prod.getStock() - stockAComprar >= 0) {
            daoComprasImpl.quitarStock(stockAComprar, prod);
            return true;
        }
        return false;
    }

    @Override public Usuario agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado) {

        if (prodComp != null && userLogueado != null && prodComp.getCantidad() > 0) {
            return daoComprasImpl.agregarALaCompra(prodComp, userLogueado);
        }
        return userLogueado;
    }

    @Override public boolean eliminarDeLaCompra(Producto prod, Usuario user) {
        if (prod != null && user != null) {
            return daoComprasImpl.eliminarDeLaCompra(prod, user);
        } else {
            return false;
        }
    }

    @Override public List<ProductoComprado> getListaCompra(Usuario userLogueado) {
        return daoComprasImpl.devolverLista(userLogueado);
    }

    @Override public boolean pagar(Tarjeta tarjeta, Usuario user) {
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

                daoComprasImpl.pagar(tarjeta, valorFinalCompra.get(), user, porcentajeACobrar);
                pudoPagar = true;
            } else {
                pudoPagar = false;
            }
        } else {
            pudoPagar = false;
        }
        return pudoPagar;
    }

    @Override public List<List<ProductoComprado>> getComprasPrevias(Usuario userLogueado) {
        return daoComprasImpl.devolverComprasPrevias(userLogueado);
    }

    @Override public List<Producto> getProductosSinAlergia(Usuario userLogueado, List<Producto> lista) {
        return daoComprasImpl.getProductosSinAlergias(userLogueado, lista);
    }

    @Override public Double dineroGastadoPorCliente(Usuario userLogueado){
        return daoComprasImpl.dineroGastadoDeCliente(userLogueado);
    }
}
