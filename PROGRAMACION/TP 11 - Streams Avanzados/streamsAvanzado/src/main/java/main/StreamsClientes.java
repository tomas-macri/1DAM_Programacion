package main;

import pedidos.dao.modelo.Cliente;
import pedidos.servicios.ServiciosPedido;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



public class StreamsClientes {


  ServiciosPedido sp = new ServiciosPedido();

  List<Cliente> clientes = sp.getTodosClientes();

  // Cliente con mas cuentas
  public void clienteConMasCuentas() {

  }

  // Cliente + Numero de cuentas de cada cliente.
  public void clienteYNumeroCuentas() {

  }

  // Clientes agrupados por el numero de cuentas
  public void clientesAgrupadosPorNumeroCuentas() {

  }

  // Clientes que tienen mas cuentas o iguales a la media.
  public void clientesConMasCuentasQuelaMedia() {

  }

  // media de dinero de todas las cuentas
  public void mediaDineroTodasCuentas() {

  }


  // Clientes ordenados por el saldo total.
  public void clientesOrdenadosPorSaldoTotal() {

  }

  // Cliente con la suma del saldo de todas sus cuentas.
  public void clientesYSumaSaldoTodasCuentas() {

  }


  // el cuarto cliente con más dinero
  public void cuartoClienteConMasDinero() {

  }


  // numero de clientes agrupados por dominio del correo ya@gmail.com
  public void numeroClientesPorDominioCorreo() {

  }
}
