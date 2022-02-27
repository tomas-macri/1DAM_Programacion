package dao;


import modelo.ProductoComprado;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaoEstadisticas {


        public List<Map.Entry> listaProductosPorOrdenDeCompra() {
            Map<String, Double> mapClientes = BD.listaUsuarios.values().stream()
                    .flatMap(cliente -> cliente.getComprasPrevias().stream())
                    .flatMap(Collection::stream)
                    .collect(Collectors.groupingBy(lineaCompra -> lineaCompra.getProducto().getNombre(), Collectors.summingDouble(ProductoComprado::getCantidad)));

           return mapClientes.entrySet().stream()
                    .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                   .collect(Collectors.toUnmodifiableList());


        }
}