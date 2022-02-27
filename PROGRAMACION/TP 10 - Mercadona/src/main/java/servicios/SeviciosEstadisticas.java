package servicios;

import dao.DaoEstadisticas;

import java.util.List;
import java.util.Map;

public class SeviciosEstadisticas {

    public List<Map.Entry> listaProductosPorOrdenDeCompra(){
        DaoEstadisticas daoEstadisticas = new DaoEstadisticas();
        return daoEstadisticas.listaProductosPorOrdenDeCompra();
    }



}
