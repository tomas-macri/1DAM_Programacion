package main;


import pedidos.dao.modelo.*;
import pedidos.servicios.ServiciosPedido;

import java.util.List;
import java.util.stream.Collectors;


public class StreamsPedidos {
    ServiciosPedido sp = new ServiciosPedido();
    List<PedidoCompuesto> pedidos = sp.getTodosPedidos();

    // un map con nombre de producto y cantidad de veces pedido
    public void productosAgrupadosPorCantidadDeVecesPedidos() {

        Producto p=null;




        pedidos.stream()
                .flatMap(pc -> pc.getPedidosSimples().stream())
                .flatMap(ps -> ps.getLineasPedido().stream())
                .filter(ps -> ps.getProducto().equals(p))
                .mapToInt(ps -> ps.getCantidad())
                .sum();


    }

    public void clienteQueMasDineroSehaGastado() {
//
//        pedidos.stream()
//                .filter(pedidoCompuesto -> pedidoCompuesto.getCliente() == X)
//                .mapToDouble(value -> value.getTotalFactura()).sum()
        Cliente c = sp.getTodosClientes().stream().sorted((c1, c2) -> (int)(pedidos.stream()
                .filter(pedidoCompuesto -> pedidoCompuesto.getCliente().equals(c1))
                .mapToDouble(value -> value.getTotalFactura()).sum() -
                pedidos.stream()
                        .filter(pedidoCompuesto -> pedidoCompuesto.getCliente().equals(c2))
                        .mapToDouble(value -> value.getTotalFactura()).sum())).findFirst().get();

    }

    // La cantidad media de producto por pedido simple, sumando todas las lineas de pedido de cada simple
    public void lacantidadMediaPedidaDeCadaProductoEnCadaPedidoCompuesto() {

    }


    public void pedidoSimpleConMasLineasdePedido() {

    }


    public void todoelDineroFacturadoEnTotalentodosLosPedidos() {

    }

    public void setFacturaTotal() {

    }
}
