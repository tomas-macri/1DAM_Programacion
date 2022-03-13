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

        Producto p = null;


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
        Cliente c = sp.getTodosClientes().stream().sorted((c1, c2) -> (int) (pedidos.stream()
                .filter(pedidoCompuesto -> pedidoCompuesto.getCliente().equals(c1))
                .mapToDouble(value -> value.getTotalFactura()).sum() -
                pedidos.stream()
                        .filter(pedidoCompuesto -> pedidoCompuesto.getCliente().equals(c2))
                        .mapToDouble(value -> value.getTotalFactura()).sum())).findFirst().get();

    }

    // La cantidad media de producto por pedido simple, sumando todas las lineas de pedido de cada simple
    public void lacantidadMediaPedidaDeCadaProductoEnCadaPedidoCompuesto() {
        System.out.println();
        System.out.println( "La cantidad media de producto por pedido simple, sumando todas las lineas de pedido de cada simple: " +
                pedidos.stream().flatMap(pedidoCompuesto -> pedidoCompuesto.getPedidosSimples().stream())
                        .flatMap(pedidoSimple -> pedidoSimple.getLineasPedido().stream())
                        .mapToInt(lineaPedido -> lineaPedido.getCantidad()).average().orElse(0.0)
        );
    }


    public void pedidoSimpleConMasLineasdePedido() {
        System.out.println();
        System.out.println( "Pedido simple con mas lineas de pedido: " +
                pedidos.stream().flatMap(pedidoCompuesto -> pedidoCompuesto.getPedidosSimples().stream())
                        .max((o1, o2) -> Integer.compare(o2.getLineasPedido().size(), o1.getLineasPedido().size()))
                        .orElse(new PedidoSimple(new Cuenta("no hay pedidos simples")))
        );
    }


    public void todoelDineroFacturadoEnTotalentodosLosPedidos() {
        System.out.println();
        System.out.println(
                "Todo el dinero facturado en todos los pedidos: " +
                pedidos.stream().flatMap(pedidoCompuesto -> pedidoCompuesto.getPedidosSimples().stream())
                        .flatMap(pedidoSimple -> pedidoSimple.getLineasPedido().stream())
                        .mapToInt(lineaPedido -> lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad()).sum()
        );
    }

}
