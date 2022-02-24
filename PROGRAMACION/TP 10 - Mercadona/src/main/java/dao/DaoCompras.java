package dao;

import modelo.Producto;
import modelo.ProductoComprado;
import modelo.Tarjeta;
import modelo.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class DaoCompras {

    public void quitarStock(int cant, Producto prod) {
        prod.setStock(prod.getStock() - cant);
    }

    public boolean agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado) {
        return userLogueado.getCarrito().add(prodComp);
    }

    public boolean eliminarDeLaCompra(Producto prod, Usuario user) {
        return BD.listaUsuarios.get(user.getDni()).getCarrito().remove(new ProductoComprado(prod));
    }

    public void pagar(Tarjeta tarjeta, int valorCompra, Usuario user) {
        tarjeta.setSaldo(tarjeta.getSaldo() - valorCompra);
        user.getComprasPrevias().add(user.getCarrito());
    }

    public List<ProductoComprado> devolverLista(Usuario userLogueado) {
        return BD.listaUsuarios.get(userLogueado.getDni()).getCarrito().stream()
                .map(producto -> new ProductoComprado(producto.getProducto(), producto.getCantidad()))
                .collect(Collectors.toUnmodifiableList());
    }


    public List<List<ProductoComprado>> devolverComprasPrevias(Usuario userLogueado) {
        return BD.listaUsuarios.get(userLogueado.getDni()).getComprasPrevias().stream()
                .collect(Collectors.toUnmodifiableList());


    }
}
