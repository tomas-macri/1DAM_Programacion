package servicios;

import dao.DaoEstadisticas;
import modelo.Ingrediente;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;
import ui.common.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeviciosEstadisticas {

    public List<Map.Entry<String, Double>> listaProductosPorOrdenDeCompra(){
        DaoEstadisticas daoEstadisticas = new DaoEstadisticas();
        return daoEstadisticas.listaProductosPorOrdenDeCompra();
    }


    public List<Producto> listaProductosConIngrediente(Ingrediente ingrediente)
    {
        List<Producto> listProductos = new ArrayList<>();
        DaoEstadisticas daoEstadisticas = new DaoEstadisticas();
        if (!ingrediente.getNombre().equalsIgnoreCase(Constantes.FIN)){
           listProductos = daoEstadisticas.listaProductosConIngrediente(ingrediente);
    }
        return listProductos;
    }

    public List<Usuario> listaUsuariosPorGastos() {
        DaoEstadisticas daoEstadisticas = new DaoEstadisticas();
        return daoEstadisticas.listaClientesPorGasto();
    }
}
