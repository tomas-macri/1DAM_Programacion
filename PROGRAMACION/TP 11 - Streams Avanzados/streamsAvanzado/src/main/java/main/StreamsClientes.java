package main;

import pedidos.dao.modelo.Cliente;
import pedidos.dao.modelo.Cuenta;
import pedidos.servicios.ServiciosPedido;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class StreamsClientes {


    ServiciosPedido sp = new ServiciosPedido();

    List<Cliente> clientes = sp.getTodosClientes();

    // Cliente con mas cuentas
    public void clienteConMasCuentas() {
        System.out.println("CLIENTE CON MAS CUENTAS: " +
                clientes.stream().reduce((cliente, cliente2) -> cliente.getCuentas().size() >= cliente2.getCuentas().size()
                        ? cliente : cliente2)
        );
        System.out.println();
    }

    // Cliente + Numero de cuentas de cada cliente.
    public void clienteYNumeroCuentas() {
        System.out.println();
        System.out.println("CLIENTE / NUM CUENTAS : \n" +
                clientes.stream()
                        .collect(Collectors.toMap(cliente -> cliente.getNombre(), cliente -> cliente.getCuentas().size()))
        );
        System.out.println();
    }

    // Clientes agrupados por el numero de cuentas
    public void clientesAgrupadosPorNumeroCuentas() {
        System.out.println();
        System.out.println("CLIENTES AGRUPADOS POR EL NUMERO DE CUENTAS:" + clientes.stream()
                .collect(Collectors.groupingBy(cliente -> cliente.getCuentas().size()))
        );
    }

    // Clientes que tienen mas cuentas o iguales a la media.
    public void clientesConMasCuentasQuelaMedia() {
        System.out.println();
        System.out.println("CLIENTES CON MAS CUENTAS QUE LA MEDIA: " +
                clientes.stream()
                        .filter(cliente -> cliente.getCuentas().size() >= (clientes.stream().mapToInt(cliente1 -> cliente1.getCuentas().size()).average().orElse(0.0)))
                        .collect(Collectors.toList())
        );
    }

    // media de dinero de todas las cuentas
    public void mediaDineroTodasCuentas() {
        int sumaTotal = clientes.stream().flatMap(cliente -> cliente.getCuentas().stream())
                .mapToInt(value -> value.getSaldo()).sum();
        int cuentasTotal = clientes.stream().mapToInt(cliente -> cliente.getCuentas().size()).sum();

        System.out.println();
        System.out.println("SUMA: " + sumaTotal);
        System.out.println("COUNT_ " + cuentasTotal);
        System.out.println("MEDIA DE DINERO DE TODAS LAS CUENTAS: " + sumaTotal / cuentasTotal);
    }


    // Clientes ordenados por el saldo total.
    public void clientesOrdenadosPorSaldoTotal() {
        System.out.println();
        System.out.println("CLIENTES ORDENADOS POR SALDO TOTAL" +
                clientes.stream().sorted((o1, o2) -> Integer.compare(o2.getCuentas().stream().mapToInt(cuenta -> cuenta.getSaldo()).sum(), o1.getCuentas().stream().mapToInt(cuenta -> cuenta.getSaldo()).sum()))
                        .collect(Collectors.toList())
        );
    }

    // Cliente con la suma del saldo de todas sus cuentas.
    public void clientesYSumaSaldoTodasCuentas() {
        System.out.println();
        System.out.println("CLIENTE / SALDO TOTAL: " + clientes.stream()
                .collect(Collectors.toMap(cliente -> cliente.getNombre(), cliente -> cliente.getCuentas().stream().mapToInt(cuenta -> cuenta.getSaldo()).sum()))
        );
    }


    // el cuarto cliente con más dinero
    public void cuartoClienteConMasDinero() {
        System.out.println();
        System.out.println(
                "CUARTO CLIENTE CON MAS SALDO EN SUS CUENTAS: " +
                        clientes.stream().sorted((o1, o2) -> Integer.compare(o2.getCuentas().stream().mapToInt(cuenta -> cuenta.getSaldo()).sum(), o1.getCuentas().stream().mapToInt(cuenta -> cuenta.getSaldo()).sum()))
                                .collect(Collectors.toList()).get(3)
        );
    }


    // numero de clientes agrupados por dominio del correo ya@gmail.com
    public void numeroClientesPorDominioCorreo() {
        System.out.println(

                clientes.stream()
                        .collect(Collectors.groupingBy(cliente -> cliente.getEmail().substring(cliente.getEmail().indexOf('@')), Collectors.counting()))
        );
    }
}
