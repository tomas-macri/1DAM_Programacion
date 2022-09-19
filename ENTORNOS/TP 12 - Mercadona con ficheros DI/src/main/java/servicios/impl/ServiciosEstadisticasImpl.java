package servicios.impl;

import dao.DaoEstadisticas;
import jakarta.inject.Inject;
import modelo.Ingrediente;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;
import servicios.ServiciosEstadisticas;
import ui.common.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiciosEstadisticasImpl implements ServiciosEstadisticas {

    private DaoEstadisticas daoEstadisticasImpl;

    @Inject
    public ServiciosEstadisticasImpl(DaoEstadisticas daoEstadisticasImpl) {
        this.daoEstadisticasImpl = daoEstadisticasImpl;
    }

    @Override public List<Map.Entry<String, Double>> listaProductosPorOrdenDeCompra(){
        return daoEstadisticasImpl.listaProductosPorOrdenDeCompra();
    }


    @Override public List<Producto> listaProductosConIngrediente(Ingrediente ingrediente)
    {
        List<Producto> listProductos = new ArrayList<>();
        if (!ingrediente.getNombre().equalsIgnoreCase(Constantes.FIN)){
           listProductos = daoEstadisticasImpl.listaProductosConIngrediente(ingrediente);
    }
        return listProductos;
    }

    @Override public List<Usuario> listaUsuariosPorGastos() {
        return daoEstadisticasImpl.listaClientesPorGasto();
    }
}
