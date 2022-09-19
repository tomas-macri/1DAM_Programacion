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
        System.out.println("PRODUCTO MAS CARO CON REDUCE: " +
                productos.stream()
                        .reduce((producto, producto2) -> producto.getPrecio() > producto2.getPrecio() ? producto : producto2).orElse(new Producto("No hay prods", 0, 0))
        );
        System.out.println("PRODUCTO MAS CARO CON SORTED: " +
                productos.stream().sorted((o1, o2) -> Integer.compare(o2.getPrecio(), o1.getPrecio()))
                        .collect(Collectors.toList())
                        .get(0)

        );
        System.out.println();
    }

    //con reduce y con sorted
    public void productoMasBarato() {
        System.out.println("PRODUCTO MAS BARATO CON REDUCE: " +
                productos.stream().reduce((producto, producto2) -> producto.getPrecio() > producto2.getPrecio() ? producto2 : producto).orElse(new Producto("No hay prods", 0, 0))
        );
        System.out.println("PRODUCTO MAS BARATO CON SORTED: " +
                productos.stream().sorted(Comparator.comparingInt(Producto::getPrecio))
                        .collect(Collectors.toList())
                        .get(0)

        );
        System.out.println();
    }


    public void mediaPrecioTodosLosProductos() {
        System.out.println( "MEDIA DE PRECIO DE TODOS LOS PRODUCTOS: " +
                productos.stream()
                        .mapToDouble(Producto::getPrecio).average().orElse(0.0)
        );
        System.out.println();
    }


    // un grupo de producto por cada franja de 10, de 0 a 10, 11 a 20, etc...
    public void productosAgrupadosPorRangoPrecio10en10() {
        System.out.println("PRODUCTOS AGRUPADOS POR DECENAS: " +
                productos.stream()
                        .collect(Collectors.groupingBy(producto -> producto.getPrecio() / 11))
        );
        System.out.println();
    }

    // de los productos que tenga precio de 11 a 20, indicar cuales tienen stock mayor que 5
    public void productosConPrecio11a20YStockMayor5() {
        System.out.println("PRODUCTOS CON PRECIO ENTRE 11 Y 20 Y CON STOCK MAYOR QUE 5: " +
                 productos.stream()
                        .filter(producto -> producto.getPrecio() > 10 && producto.getPrecio() < 21)
                        .filter(producto -> producto.getStock() > 5)
                        .collect(Collectors.toList())
        );
        System.out.println();
    }
}
