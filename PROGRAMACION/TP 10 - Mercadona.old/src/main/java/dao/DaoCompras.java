package dao;

import modelo.Producto;
import modelo.ProductoComprado;
import modelo.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class DaoCompras {

    public void quitarStock(int cant, Producto prod){
        prod.setStock(prod.getStock()-cant);
    }

    public boolean agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado)
    {
        return userLogueado.getCarrito().add(prodComp);
    }

    public boolean eliminarDeLaCompra(Producto prod, Usuario user){
        return BD.listaUsuarios.get(user.getDni()).getCarrito().remove(new ProductoComprado(prod));
    }


    public List<ProductoComprado> devolverLista(Usuario userLogueado) {
        return BD.listaUsuarios.get(userLogueado.getDni()).getCarrito().stream()
                .map(producto -> new ProductoComprado(producto.getProducto(), producto.getCantidad()))
                .collect(Collectors.toUnmodifiableList());
    }
}
