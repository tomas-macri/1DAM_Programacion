package main;

import pedidos.dao.modelo.Producto;
import pedidos.servicios.ServiciosPedido;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsProductos {
    ServiciosPedido sp = new ServiciosPedido();
    List<Producto> productos = sp.todosProductos();

    // con reduce y con sorted
    public void productoMasCaro() {

    }

    //con reduce y con sorted
    public void productoMasBarato() {

    }


    public void mediaPrecioTodosLosProductos() {

    }


    // un grupo de producto por cada franja de 10, de 0 a 10, 11 a 20, etc...
    public void productosAgrupadosPorRangoPrecio10en10() {

    }

    // de los productos que tenga precio de 11 a 20, indicar cuales tienen stock mayor que 5
    public void productosConPrecio11a20YStockMayor5() {

    }
}
