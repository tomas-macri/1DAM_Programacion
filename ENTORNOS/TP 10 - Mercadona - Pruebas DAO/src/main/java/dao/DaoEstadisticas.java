package dao;

import modelo.Ingrediente;
import modelo.Producto;
import modelo.Usuario;

import java.util.List;
import java.util.Map;

public interface DaoEstadisticas {
    List<Map.Entry<String, Double>> listaProductosPorOrdenDeCompra();

    List<Producto> listaProductosConIngrediente(Ingrediente ingrediente);

    List<Usuario> listaClientesPorGasto();
}
