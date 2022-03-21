package dao;

import modelo.Productos.Producto;
import modelo.ProductoComprado;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;

import java.util.Collection;
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

    public void pagar(Tarjeta tarjeta, int valorCompra, Usuario user, int porcentajeACobrar) {
        tarjeta.setSaldo(tarjeta.getSaldo() - (valorCompra*((double)porcentajeACobrar/100)));
        user.getComprasPrevias().add(user.getCarrito());
    }

    public List<ProductoComprado> devolverLista(Usuario userLogueado) {
        return BD.listaUsuarios.get(userLogueado.getDni()).getCarrito().stream()
                .map(ProductoComprado::clonar)
                .collect(Collectors.toUnmodifiableList());
    }


    public List<List<ProductoComprado>> devolverComprasPrevias(Usuario userLogueado) {
        return BD.listaUsuarios.get(userLogueado.getDni()).getComprasPrevias().stream()
                .collect(Collectors.toUnmodifiableList());


    }

    public List<Producto> getProductosSinAlergias(Usuario userLogueado,List<Producto> lista) {
        return lista.stream()
                .filter(producto ->
                        producto.getListaIngredientes().stream()
                                .noneMatch(ingrediente -> userLogueado.getIngredienteList().contains(ingrediente))
                )
                .collect(Collectors.toUnmodifiableList());
    }

    public Double dineroGastadoDeCliente(Usuario userLogueado) {

        return userLogueado.getComprasPrevias().stream()
                .flatMap(Collection::stream).mapToDouble(value -> value.getProducto().getPrecio() * value.getCantidad()).sum();
    }


}
