package dao.impl;

import dao.DaoCompras;
import jakarta.inject.Inject;
import modelo.Productos.Producto;
import modelo.ProductoComprado;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DaoComprasImpl implements DaoCompras {

    private DataBase dataBase;

    @Inject
    public DaoComprasImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override public void quitarStock(int cant, Producto prod) {
        prod.setStock(prod.getStock() - cant);
    }

    @Override public Usuario agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado) {
        LinkedHashMap<String, Usuario> usuarios = dataBase.loadUsuarios();
        if (usuarios != null) {
            Usuario userLogueadoBD = usuarios.get(userLogueado.getDni());
            userLogueadoBD.getCarrito().add(prodComp);
            dataBase.saveUsuarios(usuarios);
            return userLogueadoBD;
        }
        return userLogueado;
    }

    // REEMPLAZAR TOD0 COMO ARRIBA

    @Override public boolean eliminarDeLaCompra(Producto prod, Usuario user) {
        LinkedHashMap<String, Usuario> usuarios = dataBase.loadUsuarios();
        if (usuarios != null) {
            Usuario userLogueadoBD = usuarios.get(user.getDni());
            boolean seElimino = userLogueadoBD.getCarrito().remove(new ProductoComprado(prod));
            dataBase.saveUsuarios(usuarios);
            return seElimino;
        }
        return false;
    }

    @Override public void pagar(Tarjeta tarjeta, int valorCompra, Usuario user, int porcentajeACobrar) {
        LinkedHashMap<String, Usuario> usuarios = dataBase.loadUsuarios();
        if (usuarios != null) {
            Usuario userLogueadoBD = usuarios.get(user.getDni());
            Tarjeta tarjetaBD = userLogueadoBD.getListaTarjetas().stream().filter(tarjeta1 -> tarjeta1.getNombre().equalsIgnoreCase(tarjeta.getNombre())).findFirst().get();
            tarjetaBD.setSaldo(tarjetaBD.getSaldo() - (valorCompra * ((double) porcentajeACobrar / 100)));
            userLogueadoBD.getComprasPrevias().add(user.getCarrito());
            userLogueadoBD.setCarrito(new ArrayList<>());
            dataBase.saveUsuarios(usuarios);
        }
    }

    @Override public List<ProductoComprado> devolverLista(Usuario userLogueado) {
        return dataBase.loadUsuarios().get(userLogueado.getDni()).getCarrito().stream()
                .map(ProductoComprado::clonar)
                .collect(Collectors.toUnmodifiableList());
    }


    @Override public List<List<ProductoComprado>> devolverComprasPrevias(Usuario userLogueado) {
        return dataBase.loadUsuarios().get(userLogueado.getDni()).getComprasPrevias().stream()
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
