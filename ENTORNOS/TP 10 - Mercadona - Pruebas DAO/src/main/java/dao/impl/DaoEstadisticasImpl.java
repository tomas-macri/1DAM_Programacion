package dao.impl;


import dao.BD;
import dao.DaoEstadisticas;
import jakarta.inject.Inject;
import modelo.Ingrediente;
import modelo.Producto;
import modelo.ProductoComprado;
import modelo.Usuario;

import java.util.*;
import java.util.stream.Collectors;

public class DaoEstadisticasImpl implements DaoEstadisticas {

    private LinkedHashMap<String, Usuario> bdUsuarios;
    private List<Producto> bdProductos;

    @Inject
    public DaoEstadisticasImpl(BD bd){
        this.bdUsuarios = bd.listaUsuarios;
        this.bdProductos = bd.listaProductos;
    }

    @Override public List<Map.Entry<String, Double>> listaProductosPorOrdenDeCompra() {
        Map<String, Double> mapClientes = bdUsuarios.values().stream()
                .flatMap(cliente -> cliente.getComprasPrevias().stream())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(lineaCompra -> lineaCompra.getProducto().getNombre(), Collectors.summingDouble(ProductoComprado::getCantidad)));

        return mapClientes.entrySet().stream()
                .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                .collect(Collectors.toUnmodifiableList());


    }

    @Override public List<Producto> listaProductosConIngrediente(Ingrediente ingrediente) {
        return bdProductos.stream()
                .filter(producto ->
                        producto.getListaIngredientes().stream()
                                .anyMatch(ingrediente1 -> ingrediente1.equals(ingrediente))
                )
                .collect(Collectors.toUnmodifiableList());
    }

    @Override public List<Usuario> listaClientesPorGasto() {
        return bdUsuarios.values().stream().sorted(
                (o1, o2) -> Double.compare(o2.getComprasPrevias().stream()
                        .flatMap(Collection::stream).mapToDouble(value -> value.getProducto().getPrecio() * value.getCantidad()).sum(), o1.getComprasPrevias().stream()
                        .flatMap(Collection::stream).mapToDouble(value -> value.getProducto().getPrecio() * value.getCantidad()).sum())
        ).collect(Collectors.toUnmodifiableList());
    }
}