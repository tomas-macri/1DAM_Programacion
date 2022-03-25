package dao;


import modelo.Ingrediente;
import modelo.ProductoComprado;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;

import java.util.*;
import java.util.stream.Collectors;

public class DaoEstadisticas {


    public List<Map.Entry<String, Double>> listaProductosPorOrdenDeCompra() {
        DataBase dataBase = new DataBase();
        LinkedHashMap<String, Usuario> usuarios = dataBase.loadUsuarios();
        if (usuarios != null) {
            Map<String, Double> mapClientes = usuarios.values().stream()
                    .flatMap(cliente -> cliente.getComprasPrevias().stream())
                    .flatMap(Collection::stream)
                    .collect(Collectors.groupingBy(lineaCompra -> lineaCompra.getProducto().getNombre(), Collectors.summingDouble(ProductoComprado::getCantidad)));

            return mapClientes.entrySet().stream()
                    .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                    .collect(Collectors.toUnmodifiableList());
        }
        return new ArrayList<>();
    }

    public List<Producto> listaProductosConIngrediente(Ingrediente ingrediente) {
        DataBase dataBase = new DataBase();
        List<Producto> productos  = dataBase.loadProductos();
        if (productos != null) {
            return productos.stream()
                    .filter(producto ->
                            producto.getListaIngredientes().stream()
                                    .anyMatch(ingrediente1 -> ingrediente1.equals(ingrediente))
                    )
                    .collect(Collectors.toUnmodifiableList());
        }
        return new ArrayList<>();
    }

    public List<Usuario> listaClientesPorGasto() {
        DataBase dataBase = new DataBase();
        LinkedHashMap<String, Usuario> usuarios = dataBase.loadUsuarios();
        if (usuarios != null) {
            return usuarios.values().stream().sorted(
                    (o1, o2) -> Double.compare(o2.getComprasPrevias().stream()
                            .flatMap(Collection::stream).mapToDouble(value -> value.getProducto().getPrecio() * value.getCantidad()).sum(), o1.getComprasPrevias().stream()
                            .flatMap(Collection::stream).mapToDouble(value -> value.getProducto().getPrecio() * value.getCantidad()).sum())
            ).collect(Collectors.toUnmodifiableList());
        }
        return new ArrayList<>();
    }
}