package main;

public class PRUEBAS {

    public static void main(String[] args) {

        // STREAMS CLIENTES --> no se por que la clase sale en rojo pero compila
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
        // STREAMS VIEDOCLUB


        StreamsVideoClub streamsVideoClub = new StreamsVideoClub();
        MainVideoclubPractica.setupSocioSocios();
        MainVideoclubPractica.setupProductos();
        MainVideoclubPractica.setAlquileres();
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("STREAMS VIEDOCLUB");
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        streamsVideoClub.mostrarListas();
//        streamsVideoClub.numeroSociosSancionados();
//        streamsVideoClub.mediaEdadDeSociosSancionados();
//        streamsVideoClub.listaDiezProductosMasAlquilados();
//        streamsVideoClub.numeroProductosAlquiladosPorTipo();
    }
}

