package main;

import com.github.javafaker.Faker;
import pedidos.dao.modelo.*;
import pedidos.servicios.ServiciosPedido;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class MainPedidosPractica {

    public static void main(String[] args) {
        setupClienteClientes();
        setupProductos();
        setupPedidos();
        ServiciosPedido sp = new ServiciosPedido();

//    System.out.println(sp.getTodosClientes());
//    System.out.println(sp.todosProductos());
//    System.out.println(sp.getTodosPedidos());


        sp.getTodosPedidos().stream()
                .flatMap(pedidoCompuesto -> pedidoCompuesto.getPedidosSimples().stream())

                .forEach(System.out::println);


        sp.getTodosPedidos().stream()
                .flatMapToInt(pedidoCompuesto ->
                        pedidoCompuesto.getPedidosSimples().stream()
                                .flatMap(pedidoSimple -> pedidoSimple.getLineasPedido().stream())
                                .mapToInt(LineaPedido::getCantidad)).average();

        Map<PedidoSimple,Integer> l = sp.getTodosPedidos().stream()
                .flatMap(pedidoCompuesto -> pedidoCompuesto.getPedidosSimples().stream())
                .collect(Collectors
                        .toMap(pedidoSimple -> pedidoSimple,
                                o -> o.getLineasPedido().stream()
                                        .mapToInt(value -> value.getCantidad()).sum()));

        StreamsPedidos spr = new StreamsPedidos();

        spr.productosAgrupadosPorCantidadDeVecesPedidos();


    }


    private static void setupClienteClientes() {
        Faker f = new Faker();
        ServiciosPedido sp = new ServiciosPedido();

        for (int i = 0; i < 100; i++) {
            String nombre = f.gameOfThrones().character();
            String direccion = f.gameOfThrones().city();
            String tel = f.phoneNumber().toString();
            String email = f.internet().emailAddress();
            Cliente cliente = new Cliente(nombre, direccion, tel, email);
            sp.addCliente(cliente);
            Random r = new Random();
            int numeroCuentas = r.nextInt(100) + 1;
            for (int j = 0; j < numeroCuentas; j++) {
                sp.addCuentaACliente(email, f.idNumber().valid()).setSaldo(r.nextInt(100) + 100);
            }
        }
    }

    private static void setupProductos() {
        Faker f = new Faker();
        ServiciosPedido sp = new ServiciosPedido();
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            String nombre = f.animal().name();
            int stock = f.number().numberBetween(200, 300);
            int precio = r.nextInt(100);
            Producto producto = new Producto(nombre, stock, precio);
            sp.addProducto(producto);
        }
    }

    private static void setupPedidos() {
        ServiciosPedido sp = new ServiciosPedido();
        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            Cliente cliente = sp.getTodosClientes().get(r.nextInt(sp.getTodosClientes().size()));
            PedidoCompuesto pedidoCompuesto = new PedidoCompuesto(cliente);
            int pedidos = r.nextInt(10) + 10;
            for (int j = 0; j <= pedidos; j++) {
                Cuenta cuenta = cliente.getCuentas().get(r.nextInt(cliente.getCuentas().size()));
                PedidoSimple pedidoSimple = new PedidoSimple(cuenta);
                int lineasP = r.nextInt(5) + 1;
                for (int k = 0; k <= lineasP; k++) {
                    Producto producto = sp.todosProductos().get(r.nextInt(sp.todosProductos().size()));
                    sp.addLineaPedidoAPedidoSimple(producto, r.nextInt(4) + 1, pedidoSimple);
                }
                sp.addPedidoSimpleAPedido(pedidoSimple, pedidoCompuesto);
            }
            sp.addPedidoAPedidos(pedidoCompuesto);
        }
    }
}
