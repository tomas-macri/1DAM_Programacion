package dao;

import dao.impl.DaoComprasImpl;
import dao.impl.DataBase;
import jakarta.ws.rs.core.Link;
import modelo.*;
import modelo.Productos.Producto;
import modelo.Productos.ProductoNormal;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
class DaoComprasTest {


    @InjectMocks
    DaoComprasImpl daoComprasImpl;

    @Mock
    DataBase bd;

    @Captor
    private ArgumentCaptor<List<Producto>> captorProductos;

    @Captor
    private ArgumentCaptor<LinkedHashMap<String, Usuario>> captorUsuarios;

    @Nested
    @DisplayName("QUITAR STOCK")
    class QuitarStock {
        @Test
        @DisplayName("QUITAR STOCK VALIDO")
        void quitarStock() {

            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));

            Producto producto = new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList);
            productosBD.add(producto);
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));


            //when
            when(bd.loadProductos()).thenReturn(productosBD);

            daoComprasImpl.quitarStock(1, producto);
            assertAll(
                    () -> {
                        verify(bd).saveProductos(captorProductos.capture());
                        List<Producto> productoList = captorProductos.getValue();
                        assertEquals(3, productoList.size());
                        assertEquals(4, productoList.get(0).getStock());
                    }
            );

        }

        @Test
        @DisplayName("No existe el producto a quitar stock")
        void quitarStockProdNoExistente() {

            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));

            Producto producto = new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList);
            productosBD.add(producto);
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));


            //when
            when(bd.loadProductos()).thenReturn(productosBD);

            daoComprasImpl.quitarStock(1, new ProductoNormal("prod4", 4, 10, new ArrayList<>()));
            assertAll(
                    () -> verify(bd, times(0)).saveProductos(productosBD)
            );


        }

        @Test
        @DisplayName("Lista productos == null")
        void quitarStockListNull() {

            //given
            ArrayList<Producto> productosBD = null;

            //when
            when(bd.loadProductos()).thenReturn(productosBD);
            daoComprasImpl.quitarStock(1, new ProductoNormal("prod1", 4, 10, new ArrayList<>()));

            //then
            assertAll(
                    () -> verify(bd, times(0)).saveProductos(productosBD)
            );
        }
    }

    @Nested
    @DisplayName("AGREGAR A LA COMPRA")
    class AgregarACompra {
        @Test
        @DisplayName("AGREGAR A LA COMPRA VALIDO")
        void agregarALaCompraValido() {
            //given
            LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            listaBD.put("dni1", new UsuarioNormal("dni1", "user1", ingredienteArrayList));
            listaBD.put("dni2", new UsuarioNormal("dni2", "user2", ingredienteArrayList));
            listaBD.put("dni3", new UsuarioNormal("dni3", "user3", ingredienteArrayList));

            //when
            when(bd.loadUsuarios()).thenReturn(listaBD);


            ProductoComprado productoPrueba = new ProductoComprado(new ProductoNormal("prod1", 15, 12, ingredienteArrayList), 5);
            Usuario userComprando = listaBD.get("dni1");

            Usuario usuario = daoComprasImpl.agregarALaCompra(productoPrueba, userComprando);

            //then
            assertAll(
                    () -> {
                        verify(bd).saveUsuarios(captorUsuarios.capture());
                        LinkedHashMap<String, Usuario> usuarios = captorUsuarios.getValue();
                        assertEquals(userComprando.getCarrito(), usuarios.get("dni1").getCarrito());
                        assertEquals(usuarios.get("dni1").getCarrito().get(usuarios.get("dni1").getCarrito().size() - 1), productoPrueba);
                        assertEquals(usuario, userComprando);
                    }
            );
        }

        @Test
        @DisplayName("Lista de Usuarios == null")
        void agregarALaCompraNoValido() {
            //given
            LinkedHashMap<String, Usuario> listaBD = null;

            //when
            when(bd.loadUsuarios()).thenReturn(listaBD);
            ProductoComprado productoPrueba = new ProductoComprado(new ProductoNormal("prod1", 15, 12, new ArrayList<>()), 5);
            Usuario userComprando = new UsuarioNormal("dni1", "user1", new ArrayList<>());

            Usuario usuario = daoComprasImpl.agregarALaCompra(productoPrueba, userComprando);

            //then
            assertAll(
                    () -> {
                        verify(bd, times(0)).saveUsuarios(listaBD);
                        assertEquals(0, usuario.getCarrito().size());
                        assertNull(listaBD);
                    }
            );
        }

    }

    @Nested
    @DisplayName("QUITAR DE LA COMPRA")
    class QuitarDeCompra {
        @Test
        @DisplayName("ELIMINAR DE LA COMPRA VALIDO")
        void eliminarDeLaCompraValido() {
            //given
            LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            listaBD.put("dni1", new UsuarioNormal("dni1", "user1", ingredienteArrayList));
            listaBD.put("dni2", new UsuarioNormal("dni2", "user2", ingredienteArrayList));
            listaBD.put("dni3", new UsuarioNormal("dni3", "user3", ingredienteArrayList));

            //when
            when(bd.loadUsuarios()).thenReturn(listaBD);


            ProductoComprado productoPrueba = new ProductoComprado(new ProductoNormal("prod1", 15, 12, ingredienteArrayList), 5);
            Usuario userComprando = listaBD.get("dni1");
            userComprando.getCarrito().add(productoPrueba);

            //then
            boolean seElimino = daoComprasImpl.eliminarDeLaCompra(new ProductoNormal("prod1"), userComprando);
            assertAll(
                    () -> {
                        verify(bd).saveUsuarios(captorUsuarios.capture());
                        LinkedHashMap<String, Usuario> usuarios = captorUsuarios.getValue();
                        assertEquals(0, usuarios.get("dni1").getCarrito().size());
                        assertEquals(userComprando, usuarios.get("dni1"));
                        assertTrue(seElimino);
                    }
            );
        }

        @Test
        @DisplayName("NO EXISTE PRODUCTO A ELIMINAR DE LA COMPRA")
        void eliminarDeLaCompraNoValido() {
            LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));

            Usuario userATestear = new UsuarioNormal("dni1", "user1", ingredienteArrayList);

            userATestear.setCarrito(Arrays.asList(new ProductoComprado(new ProductoNormal("prod1", 15, 12, ingredienteArrayList), 5)));

            listaBD.put("dni1", userATestear);
            listaBD.put("dni2", new UsuarioNormal("dni2", "user2", ingredienteArrayList));
            listaBD.put("dni3", new UsuarioNormal("dni3", "user3", ingredienteArrayList));


            //when
            when(bd.loadUsuarios()).thenReturn(listaBD);
            Usuario userComprando = listaBD.get("dni1");


            boolean seElimino = daoComprasImpl.eliminarDeLaCompra(new ProductoNormal("prod3333"), userComprando);
            assertAll(
                    () -> {
                        verify(bd).saveUsuarios(captorUsuarios.capture());
                        LinkedHashMap<String, Usuario> usuarios = captorUsuarios.getValue();
                        assertEquals(1, usuarios.get("dni1").getCarrito().size());
                        assertEquals(userComprando, usuarios.get("dni1"));
                        assertFalse(seElimino);
                    }
            );
        }

        @Test
        @DisplayName("Lista de Usuarios == null")
        void quitarDeLaCompraNoValido() {
            //given
            LinkedHashMap<String, Usuario> listaBD = null;

            //when
            when(bd.loadUsuarios()).thenReturn(listaBD);
            Producto productoPrueba = new ProductoNormal("prod1", 15, 12, new ArrayList<>());
            Usuario userComprando = new UsuarioNormal("dni1", "user1", new ArrayList<>());

            boolean seElimino = daoComprasImpl.eliminarDeLaCompra(productoPrueba, userComprando);

            //then
            assertAll(
                    () -> {
                        verify(bd, times(0)).saveUsuarios(listaBD);
                        assertNull(listaBD);
                        assertFalse(seElimino);
                    }
            );
        }
    }

    @Nested
    @DisplayName("PAGAR")
    class Pagar {
        @Test
        @DisplayName("PAGAR")
        void pagarValido() {
            //given
            LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            listaBD.put("dni1", new UsuarioNormal("dni1", "user1", ingredienteArrayList));
            listaBD.put("dni2", new UsuarioNormal("dni2", "user2", ingredienteArrayList));
            listaBD.put("dni3", new UsuarioNormal("dni3", "user3", ingredienteArrayList));

            //when
            when(bd.loadUsuarios()).thenReturn(listaBD);
            ProductoComprado productoPrueba = new ProductoComprado(new ProductoNormal("prod1", 10, 12, ingredienteArrayList), 5);
            Usuario userComprando = listaBD.get("dni1");
            Tarjeta tarjetita = new Tarjeta("t1", 200);
            userComprando.getListaTarjetas().add(tarjetita);
            userComprando.getCarrito().add(productoPrueba);

            AtomicInteger valorFinalCompra = new AtomicInteger();
            userComprando.getCarrito().forEach(productoComprado -> valorFinalCompra.addAndGet((int) (productoComprado.getCantidad() * productoComprado.getProducto().getPrecio()))
            );

            List<ProductoComprado> compra = userComprando.getCarrito();
            daoComprasImpl.pagar(tarjetita, valorFinalCompra.get(), userComprando, 100);

            //then
            assertAll(
                    () -> {
                        verify(bd).saveUsuarios(captorUsuarios.capture());
                        LinkedHashMap<String, Usuario> usuarios = captorUsuarios.getValue();
                        assertEquals(userComprando, usuarios.get("dni1"));
                        assertEquals(150, tarjetita.getSaldo());
                        assertIterableEquals(compra, usuarios.get("dni1").getComprasPrevias().get(userComprando.getComprasPrevias().size() - 1));
                    }
            );


        }


        @Test
        @DisplayName("Lista de Usuarios == null")
        void pagarNoValido() {
            //given
            LinkedHashMap<String, Usuario> listaBD = null;

            //when
            when(bd.loadUsuarios()).thenReturn(listaBD);
            ProductoComprado productoPrueba = new ProductoComprado(new ProductoNormal("prod1", 10, 12, new ArrayList<>()), 5);
            Usuario userComprando = new UsuarioNormal("dni1", "user1", new ArrayList<>());
            Tarjeta tarjetita = new Tarjeta("t1", 200);
            userComprando.getListaTarjetas().add(tarjetita);
            userComprando.getCarrito().add(productoPrueba);

            AtomicInteger valorFinalCompra = new AtomicInteger();
            userComprando.getCarrito().forEach(productoComprado -> valorFinalCompra.addAndGet((int) (productoComprado.getCantidad() * productoComprado.getProducto().getPrecio()))
            );

            List<ProductoComprado> compra = userComprando.getCarrito();
            daoComprasImpl.pagar(tarjetita, valorFinalCompra.get(), userComprando, 100);

            //then
            assertAll(
                    () -> {
                        verify(bd, times(0)).saveUsuarios(listaBD);
                        assertNull(listaBD);
                    }
            );
        }
    }

    @Test
    @DisplayName("GET CARRITO")
    void getCarritoValido() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new UsuarioNormal("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new UsuarioNormal("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new UsuarioNormal("dni3", "user3", ingredienteArrayList));

        when(bd.loadUsuarios()).thenReturn(listaBD);


        ProductoComprado productoPrueba = new ProductoComprado(new ProductoNormal("prod1", 10, 12, ingredienteArrayList), 5);
        ProductoComprado productoPrueba2 = new ProductoComprado(new ProductoNormal("prod2", 12, 13, ingredienteArrayList), 4);

        Usuario userComprando = listaBD.get("dni1");

        userComprando.getCarrito().add(productoPrueba);
        userComprando.getCarrito().add(productoPrueba2);


        List<ProductoComprado> compra = userComprando.getCarrito();
        List<ProductoComprado> carritoActual = daoComprasImpl.devolverLista(userComprando);
        assertEquals(compra, carritoActual);
        assertTrue(carritoActual.contains(productoPrueba));
        assertTrue(carritoActual.contains(productoPrueba2));
    }

    @Test
    @DisplayName("GET COMPRAS PREVIAS")
    void getComprasPreviasValido() {
        //given
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new UsuarioNormal("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new UsuarioNormal("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new UsuarioNormal("dni3", "user3", ingredienteArrayList));

        //when
        when(bd.loadUsuarios()).thenReturn(listaBD);


        Usuario userComprando = listaBD.get("dni1");

        List<List<ProductoComprado>> comprasHistoricas = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            ProductoComprado productoPrueba = new ProductoComprado(new ProductoNormal("prod1", random.nextInt(10), random.nextInt(10), ingredienteArrayList), random.nextInt(5));
            ProductoComprado productoPrueba2 = new ProductoComprado(new ProductoNormal("prod2", random.nextInt(10), random.nextInt(10), ingredienteArrayList), random.nextInt(5));

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
    @DisplayName("GET PRODUCTOS SIN ALERGIAS")
    void getProductosSinAlergiasValido() {
        //given
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new UsuarioNormal("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new UsuarioNormal("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new UsuarioNormal("dni3", "user3", ingredienteArrayList));


        Usuario userComprando = listaBD.get("dni1");

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing3"));
        ingredienteArrayList.add(new Ingrediente("ing4"));

        List<Producto> productoList = new ArrayList<>();
        productoList.add(new ProductoNormal("prod1", 10, 15, ingredienteArrayList));

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing5"));
        ingredienteArrayList.add(new Ingrediente("ing6"));
        Producto prod2 = new ProductoNormal("prod2", 20, 20, ingredienteArrayList);
        productoList.add(prod2);


        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing7"));
        ingredienteArrayList.add(new Ingrediente("ing8"));
        Producto prod3 = new ProductoNormal("prod3", 30, 22, ingredienteArrayList);
        productoList.add(prod3);

        List<Producto> productosValidos = new ArrayList<>();
        productosValidos.add(prod2);
        productosValidos.add(prod3);
        //when
        List<Producto> productosResult = daoComprasImpl.getProductosSinAlergias(userComprando, productoList);
        //then
        assertIterableEquals(productosValidos, productosResult);
    }

    @Test
    @DisplayName("GET DINERO GASTADO VALIDO")
    void getDineroGastadoValido(){
        //given
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.put("dni1", new UsuarioNormal("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new UsuarioNormal("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new UsuarioNormal("dni3", "user3", ingredienteArrayList));


        Usuario userComprando = listaBD.get("dni1");

        List<List<ProductoComprado>> comprasHistoricas = new ArrayList<>();

        Random random = new Random();

        double acumDineroGastado = 0;

        for (int i = 0; i < 3; i++) {
            ProductoComprado productoPrueba = new ProductoComprado(new ProductoNormal("prod1", 10 , random.nextInt(10), ingredienteArrayList), 3);
            ProductoComprado productoPrueba2 = new ProductoComprado(new ProductoNormal("prod2", 5 , random.nextInt(10), ingredienteArrayList), 2);
            acumDineroGastado+= 40;
            userComprando.getCarrito().add(productoPrueba);
            userComprando.getCarrito().add(productoPrueba2);

            List<ProductoComprado> compra = userComprando.getCarrito();
            userComprando.getComprasPrevias().add(compra);
            comprasHistoricas.add(compra);
            userComprando.setCarrito(new ArrayList<>());
        }

        //when
        double dineroGastado = daoComprasImpl.dineroGastadoDeCliente(userComprando);

        //then
        assertEquals(acumDineroGastado, dineroGastado);
    }

}