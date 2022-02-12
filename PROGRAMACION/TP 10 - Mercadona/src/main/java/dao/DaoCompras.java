package dao;

import modelo.Producto;
import modelo.ProductoComprado;
import modelo.Usuario;

public class DaoCompras {

    public void quitarStock(int cant, Producto prod){
        prod.setStock(prod.getStock()-cant);
    }

    public boolean agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado)
    {
        return userLogueado.getCarrito().add(prodComp);
    }

}
