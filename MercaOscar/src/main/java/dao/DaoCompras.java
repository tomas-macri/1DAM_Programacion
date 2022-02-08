package dao;

import modelo.Cliente;
import modelo.LineaCompra;
import modelo.Monedero;
import modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class DaoCompras {


    public void addProductoCompra(Cliente c, LineaCompra linea) {
        BD.clientes.get(c.getDni())
                .getCarrito()
                .add(linea);
    }

    public void compraHecha(Cliente c){

        c.getComprasAntiguas().add(c.getCarrito());
        c.setCarrito(new ArrayList<>());



    }
}
