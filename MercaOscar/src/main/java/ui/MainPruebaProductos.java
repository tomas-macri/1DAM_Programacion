package ui;

import dao.DaoProducto;
import modelo.Producto;
import servicios.ServiciosProductos;

import java.util.List;

public class MainPruebaProductos {


    public static void main(String[] args) {

        ServiciosProductos sp = new ServiciosProductos();



//        dao.verProductos().forEach(System.out::println);

        sp.addProducto(new Producto(10,"00",0));
        sp.addProducto(new Producto(10,"01",0));
//        dao.addProducto(new Producto(10,"02",0));
//        dao.addProducto(new Producto(10,"03",0));
//
//        List<Producto> productos = dao.verProductos();
//
        sp.getProductos().forEach(System.out::println);
//
//        productos.get(0).setNombre("no se vale");
//
//
//        dao.verProductos().forEach(System.out::println);
    }
}
