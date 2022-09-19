package dao;

import dao.impl.DaoComprasImpl;
import modelo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class DaoComprasTest {

    @Test
    @DisplayName("QUITAR STOCK VALIDO")
    void quitarStock(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoComprasImpl daoComprasImpl = new DaoComprasImpl(bd);

        Producto productoPrueba = new Producto("prod1", 15, 12, ingredienteArrayList);
        daoComprasImpl.quitarStock(1, productoPrueba);
        assertEquals(11, productoPrueba.getStock());

    }

    @Test
    @DisplayName("AGREGAR A LA COMPRA VALIDO")
    void agregarALaCompraValido(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoComprasImpl daoComprasImpl = new DaoComprasImpl(bd);

        ProductoComprado productoPrueba = new ProductoComprado(new Producto("prod1", 15, 12, ingredienteArrayList), 5);
        Usuario userComprando = listaBD.get("dni1");

        boolean seAgrego = daoComprasImpl.agregarALaCompra(productoPrueba, userComprando);
        assertTrue(seAgrego);
    }

    @Test
    @DisplayName("ELIMINAR DE LA COMPRA VALIDO")
    void eliminarDeLaCompraValido(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoComprasImpl daoComprasImpl = new DaoComprasImpl(bd);

        ProductoComprado productoPrueba = new ProductoComprado(new Producto("prod1", 15, 12, ingredienteArrayList), 5);
        Usuario userComprando = listaBD.get("dni1");
        userComprando.getCarrito().add(productoPrueba);

        boolean seElimino = daoComprasImpl.eliminarDeLaCompra(new Producto("prod1"), userComprando);
        assertTrue(seElimino);
    }

    @Test
    @DisplayName("ELIMINAR DE LA COMPRA NO VALIDO")
    void eliminarDeLaCompraNoValido(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoComprasImpl daoComprasImpl = new DaoComprasImpl(bd);

        Usuario userComprando = listaBD.get("dni1");

        boolean seElimino = daoComprasImpl.eliminarDeLaCompra(new Producto("prod1"), userComprando);
        assertFalse(seElimino);
    }

    @Test
    @DisplayName("PAGAR")
    void pagarValido(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoComprasImpl daoComprasImpl = new DaoComprasImpl(bd);

        ProductoComprado productoPrueba = new ProductoComprado(new Producto("prod1", 10, 12, ingredienteArrayList), 5);
        Usuario userComprando = listaBD.get("dni1");

        Tarjeta tarjetita = new Tarjeta("t1", 200);
        userComprando.getListaTarjetas().add(tarjetita);
        userComprando.getCarrito().add(productoPrueba);

        AtomicInteger valorFinalCompra = new AtomicInteger();
        userComprando.getCarrito().forEach(productoComprado -> valorFinalCompra.addAndGet((int) (productoComprado.getCantidad() * productoComprado.getProducto().getPrecio()))
        );

        List<ProductoComprado> compra = userComprando.getCarrito();
        daoComprasImpl.pagar(tarjetita, valorFinalCompra.get(), userComprando, 100);
        assertEquals(150, tarjetita.getSaldo());
        assertIterableEquals(compra, userComprando.getComprasPrevias().get(userComprando.getComprasPrevias().size()-1));
    }


    @Test
    @DisplayName("GET CARRITO")
    void getCarritoValido(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoComprasImpl daoComprasImpl = new DaoComprasImpl(bd);

        ProductoComprado productoPrueba = new ProductoComprado(new Producto("prod1", 10, 12, ingredienteArrayList), 5);
        ProductoComprado productoPrueba2 = new ProductoComprado(new Producto("prod2", 12, 13, ingredienteArrayList), 4);

        Usuario userComprando = listaBD.get("dni1");

        userComprando.getCarrito().add(productoPrueba);
        userComprando.getCarrito().add(productoPrueba2);


        List<ProductoComprado> compra = userComprando.getCarrito();
        List<ProductoComprado> carritoActual = daoComprasImpl.devolverLista(userComprando);
        assertEquals(compra, carritoActual);
    }

    @Test
    @DisplayName("GET ComprasPrevias")
    void getComprasPreviasValido(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoComprasImpl daoComprasImpl = new DaoComprasImpl(bd);


        Usuario userComprando = listaBD.get("dni1");

        List<List<ProductoComprado>> comprasHistoricas = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            ProductoComprado productoPrueba = new ProductoComprado(new Producto("prod1", random.nextInt(10) , random.nextInt(10), ingredienteArrayList), random.nextInt(5));
            ProductoComprado productoPrueba2 = new ProductoComprado(new Producto("prod2", random.nextInt(10) , random.nextInt(10), ingredienteArrayList), random.nextInt(5));

            userComprando.getCarrito().add(productoPrueba);
            userComprando.getCarrito().add(productoPrueba2);

            List<ProductoComprado> compra = userComprando.getCarrito();
            userComprando.getComprasPrevias().add(compra);
            comprasHistoricas.add(compra);
            userComprando.setCarrito(new ArrayList<>());
        }

        assertEquals(comprasHistoricas, daoComprasImpl.devolverComprasPrevias(userComprando));
    }

    @Test
    @DisplayName("GET ComprasPrevias")
    void getProductosSinAlergiasValido() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoComprasImpl daoComprasImpl = new DaoComprasImpl(bd);

        Usuario userComprando = listaBD.get("dni1");

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing3"));
        ingredienteArrayList.add(new Ingrediente("ing4"));

        List<Producto> productoList = new ArrayList<>();
        productoList.add(new Producto("prod1", 10, 15, ingredienteArrayList));

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing5"));
        ingredienteArrayList.add(new Ingrediente("ing6"));
        Producto prod2 = new Producto("prod2", 20, 20, ingredienteArrayList);
        productoList.add(prod2);


        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing7"));
        ingredienteArrayList.add(new Ingrediente("ing8"));
        Producto prod3 = new Producto("prod3", 30, 22, ingredienteArrayList);
        productoList.add(prod3);

        List<Producto> productosValidos = new ArrayList<>();
        productosValidos.add(prod2);
        productosValidos.add(prod3);

        assertIterableEquals(productosValidos, daoComprasImpl.getProductosSinAlergias(userComprando, productoList));


    }

    @Test
    @DisplayName("GET DINERO GASTADO VALIDO")
    void getDineroGastadoValido(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoComprasImpl daoComprasImpl = new DaoComprasImpl(bd);


        Usuario userComprando = listaBD.get("dni1");

        List<List<ProductoComprado>> comprasHistoricas = new ArrayList<>();

        Random random = new Random();

        double acumDineroGastado = 0;

        for (int i = 0; i < 3; i++) {
            ProductoComprado productoPrueba = new ProductoComprado(new Producto("prod1", 10 , random.nextInt(10), ingredienteArrayList), 3);
            ProductoComprado productoPrueba2 = new ProductoComprado(new Producto("prod2", 5 , random.nextInt(10), ingredienteArrayList), 2);
            acumDineroGastado+= 40;
            userComprando.getCarrito().add(productoPrueba);
            userComprando.getCarrito().add(productoPrueba2);

            List<ProductoComprado> compra = userComprando.getCarrito();
            userComprando.getComprasPrevias().add(compra);
            comprasHistoricas.add(compra);
            userComprando.setCarrito(new ArrayList<>());
        }

        assertEquals(acumDineroGastado, daoComprasImpl.dineroGastadoDeCliente(userComprando));
    }




}
