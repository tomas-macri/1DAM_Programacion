package dao.impl;

import dao.BD;
import dao.DaoCompras;
import jakarta.inject.Inject;
import modelo.Producto;
import modelo.ProductoComprado;
import modelo.Tarjeta;
import modelo.Usuario;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DaoComprasImpl implements DaoCompras {

    private LinkedHashMap<String, Usuario> bdUsuarios;
    @Inject
    public DaoComprasImpl(BD bd){
        this.bdUsuarios = bd.listaUsuarios;
    }

    @Override public void quitarStock(int cant, Producto prod) {
        prod.setStock(prod.getStock() - cant);
    }

    @Override public boolean agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado) {
        return userLogueado.getCarrito().add(prodComp);
    }

    @Override public boolean eliminarDeLaCompra(Producto prod, Usuario user) {
        return bdUsuarios.get(user.getDni())
                .getCarrito()
                .remove(new ProductoComprado(prod));
    }

    @Override public void pagar(Tarjeta tarjeta, int valorCompra, Usuario user, int porcentajeACobrar) {
        tarjeta.setSaldo(tarjeta.getSaldo() - (valorCompra*((double)porcentajeACobrar/100)));
        user.getComprasPrevias().add(user.getCarrito());
    }

    @Override public List<ProductoComprado> devolverLista(Usuario userLogueado) {
        return bdUsuarios.get(userLogueado.getDni()).getCarrito().stream()
                .map(ProductoComprado::clonar)
                .collect(Collectors.toUnmodifiableList());
    }


    @Override public List<List<ProductoComprado>> devolverComprasPrevias(Usuario userLogueado) {
        return bdUsuarios.get(userLogueado.getDni()).getComprasPrevias().stream()
                .collect(Collectors.toUnmodifiableList());


    }

    @Override public List<Producto> getProductosSinAlergias(Usuario userLogueado,List<Producto> lista) {
        return lista.stream()
                .filter(producto ->
                        producto.getListaIngredientes().stream()
                                .noneMatch(ingrediente -> userLogueado.getIngredienteList().contains(ingrediente))
                )
                .collect(Collectors.toUnmodifiableList());
    }

    @Override public Double dineroGastadoDeCliente(Usuario userLogueado) {

        return userLogueado.getComprasPrevias().stream()
                .flatMap(Collection::stream).mapToDouble(value -> value.getProducto().getPrecio() * value.getCantidad()).sum();
    }


}
