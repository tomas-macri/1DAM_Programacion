package main;

public class PRUEBAS {

    public static void main(String[] args) {

        // STREAMS CLIENTES
        StreamsClientes streamsClientes = new StreamsClientes();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("STREAMS CLIENTES");
        System.out.println();
        System.out.println();
        System.out.println();

        streamsClientes.clienteConMasCuentas();
        streamsClientes.clienteYNumeroCuentas();
        streamsClientes.clientesAgrupadosPorNumeroCuentas();
        streamsClientes.clientesConMasCuentasQuelaMedia();
        streamsClientes.mediaDineroTodasCuentas();
        streamsClientes.clientesOrdenadosPorSaldoTotal();
        streamsClientes.clientesYSumaSaldoTodasCuentas();
        streamsClientes.cuartoClienteConMasDinero();
        streamsClientes.numeroClientesPorDominioCorreo();

        //STREAMS PEDIDOS
        StreamsPedidos streamsPedidos = new StreamsPedidos();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("STREAMS PEDIDOS (PARA VER QUE FUNCIONEN DEBERIAN CARGARSE)");
        System.out.println();
        System.out.println();
        System.out.println();

        streamsPedidos.lacantidadMediaPedidaDeCadaProductoEnCadaPedidoCompuesto();
        streamsPedidos.pedidoSimpleConMasLineasdePedido();
        streamsPedidos.todoelDineroFacturadoEnTotalentodosLosPedidos();

        // STREAMS PRODUCTOS
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("STREAMS PRODUCTOS");
        System.out.println();
        System.out.println();
        System.out.println();

        StreamsProductos streamsProductos = new StreamsProductos();

        streamsProductos.productoMasCaro();
        streamsProductos.productoMasBarato();
        streamsProductos.mediaPrecioTodosLosProductos();
        streamsProductos.productosAgrupadosPorRangoPrecio10en10();
        streamsProductos.productosConPrecio11a20YStockMayor5();


        // STREAMS VIEDOCLUB


        StreamsVideoClub streamsVideoClub = new StreamsVideoClub();
        MainVideoclubPractica.setupSocioSocios();
        MainVideoclubPractica.setupProductos();
        MainVideoclubPractica.setAlquileres();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("STREAMS VIEDOCLUB");
        System.out.println();
        System.out.println();
        System.out.println();
        streamsVideoClub.mostrarListas();
        streamsVideoClub.numeroSociosSancionados();
        streamsVideoClub.mediaEdadDeSociosSancionados();
        streamsVideoClub.listaDiezProductosMasAlquilados();
        streamsVideoClub.numeroProductosAlquiladosPorTipo();
//        streamsVideoClub.todosLosActoresDistintosDeTodasLasPeliculas();
//        streamsVideoClub.peliculaConMasActores();
//        streamsVideoClub.productoConSuValoracionMediaOrdenadosDeMayoraMenor();
//        streamsVideoClub.las10PeliculasMejorValoradas();
        streamsVideoClub.todosLosFabricantesDistintosDeVideoJuegosEnUnSoloString();
    }
}

