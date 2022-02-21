package servicios;

import dao.BD;
import dao.DaoCompras;
import dao.DaoUsuarios;
import modelo.Producto;
import modelo.ProductoComprado;
import modelo.Tarjeta;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiciosCompras {

    public ServiciosCompras(Usuario user){
        //vaciarCarrito(user.getCarrito());
        user.setCarrito(new ArrayList<>());
    }

    private void vaciarCarrito(List<ProductoComprado> carrito) {
        carrito.forEach(
                productoComprado -> {
                    Producto productoDejado = BD.listaProductos.get(BD.listaProductos.indexOf(productoComprado.getProducto()));
                    productoDejado.setStock(productoDejado.getStock() + productoComprado.getCantidad());
                }
        );
    }

    public boolean hayStock(int stockAComprar, Producto prod){
        DaoCompras daoCompras = new DaoCompras();
        if (stockAComprar > 0 && prod.getStock() - stockAComprar > 0){
            daoCompras.quitarStock(stockAComprar, prod);
            return true;
        }
        return false;
    }

    public boolean agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado){
        DaoCompras daoCompras = new DaoCompras();

        if (prodComp != null && userLogueado != null){
            daoCompras.agregarALaCompra(prodComp, userLogueado);
            return true;
        }
        return false;
    }

    public boolean eliminarDeLaCompra(Producto prod, Usuario user){
        DaoCompras daoCompras = new DaoCompras();
        if (prod != null){
            return daoCompras.eliminarDeLaCompra(prod, user);
        }
        else{
            return false;
        }
    }

    public List<ProductoComprado> getListaCompra(Usuario userLogueado) {
        DaoCompras daoCompras = new DaoCompras();
        return daoCompras.devolverLista(userLogueado);
    }

    public boolean pagar(Tarjeta tarjeta, Usuario user) {
        boolean pudoPagar;
        if (!tarjeta.getNombre().equalsIgnoreCase("error") && user != null && !user.getCarrito().isEmpty()){
            double saldoDisponible = tarjeta.getSaldo();
            AtomicInteger valorFinalCompra = new AtomicInteger();
            user.getCarrito().stream().forEach( productoComprado -> {
                    valorFinalCompra.addAndGet((int)(productoComprado.getCantidad() * productoComprado.getProducto().getPrecio()));
                }
            );

            if (saldoDisponible - valorFinalCompra.get() > 0){
                DaoCompras daoCompras = new DaoCompras();
                daoCompras.pagar(tarjeta, valorFinalCompra.get(), user);
                return true;
            }
            else {
                pudoPagar = false;
            }
        }
        else {
           pudoPagar = false;
        }
        return pudoPagar;
    }

    public List<List<ProductoComprado>> getComprasPrevias(Usuario userLogueado){
        DaoCompras daoCompras = new DaoCompras();
        return daoCompras.devolverComprasPrevias(userLogueado);
    }
}
