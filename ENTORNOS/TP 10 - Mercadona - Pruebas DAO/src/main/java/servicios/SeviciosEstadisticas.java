package servicios;

import dao.BD;
import dao.DaoEstadisticas;
import modelo.Ingrediente;
import modelo.Producto;
import modelo.Usuario;
import ui.common.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeviciosEstadisticas {

    public List<Map.Entry<String, Double>> listaProductosPorOrdenDeCompra(){
        DaoEstadisticas daoEstadisticas = new DaoEstadisticas(BD.listaUsuarios, BD.listaProductos);
        return daoEstadisticas.listaProductosPorOrdenDeCompra();
    }


    public List<Producto> listaProductosConIngrediente(Ingrediente ingrediente)
    {
        List<Producto> listProductos = new ArrayList<>();
        DaoEstadisticas daoEstadisticas = new DaoEstadisticas(BD.listaUsuarios, BD.listaProductos);
        if (!ingrediente.getNombre().equalsIgnoreCase(Constantes.FIN)){
           listProductos = daoEstadisticas.listaProductosConIngrediente(ingrediente);
    }
        return listProductos;
    }

    public List<Usuario> listaUsuariosPorGastos() {
        DaoEstadisticas daoEstadisticas = new DaoEstadisticas(BD.listaUsuarios, BD.listaProductos);
        return daoEstadisticas.listaClientesPorGasto();
    }
}
