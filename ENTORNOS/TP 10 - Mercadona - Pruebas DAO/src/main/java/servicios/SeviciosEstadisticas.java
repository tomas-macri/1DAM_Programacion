package servicios;

import dao.BD;
import dao.DaoEstadisticas;
import dao.DaoProductos;
import dao.DaoUsuarios;
import jakarta.inject.Inject;
import modelo.Ingrediente;
import modelo.Producto;
import modelo.Usuario;
import ui.common.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeviciosEstadisticas {

    private DaoEstadisticas daoEstadisticas;

    @Inject
    public SeviciosEstadisticas(DaoEstadisticas daoEstadisticas) {
        this.daoEstadisticas = daoEstadisticas;
    }

    public List<Map.Entry<String, Double>> listaProductosPorOrdenDeCompra(){
        return daoEstadisticas.listaProductosPorOrdenDeCompra();
    }


    public List<Producto> listaProductosConIngrediente(Ingrediente ingrediente)
    {
        List<Producto> listProductos = new ArrayList<>();
        if (!ingrediente.getNombre().equalsIgnoreCase(Constantes.FIN)){
           listProductos = daoEstadisticas.listaProductosConIngrediente(ingrediente);
    }
        return listProductos;
    }

    public List<Usuario> listaUsuariosPorGastos() {
        return daoEstadisticas.listaClientesPorGasto();
    }
}
