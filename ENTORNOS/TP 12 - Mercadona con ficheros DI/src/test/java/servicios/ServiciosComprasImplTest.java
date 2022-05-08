package servicios;

import dao.impl.DaoComprasImpl;
import modelo.Ingrediente;
import modelo.ProductoComprado;
import modelo.Productos.Producto;
import modelo.Productos.ProductoNormal;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import servicios.impl.ServiciosComprasImpl;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import javax.naming.AuthenticationNotSupportedException;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
public class ServiciosComprasImplTest {

    @InjectMocks
    private ServiciosComprasImpl serviciosCompras;

    @Mock
    private DaoComprasImpl daoCompras;

    @Nested
    @DisplayName("HAY STOCK")
    class HayStock {
        @Test
        @DisplayName("HAY STOCK VALIDO")
        void hayStockValido() {
            // given
            int stockAQuitar = 1;
            Producto producto = new ProductoNormal("producto", 1, 1, new ArrayList<>());

            // when
            boolean hayStock = serviciosCompras.hayStock(stockAQuitar, producto);

            // then
            assertAll(
                    () -> verify(daoCompras).quitarStock(1, producto),
                    () -> assertTrue(hayStock)
            );
        }

        @Test
        @DisplayName("Stock a quitar negativo")
        void hayStockNegativoValido() {
            // given
            int stockAQuitar = -1;
            Producto producto = new ProductoNormal("producto", 1, 1, new ArrayList<>());

            // when
            boolean hayStock = serviciosCompras.hayStock(stockAQuitar, producto);

            // then
            assertAll(
                    () -> verify(daoCompras, times(0)).quitarStock(1, producto),
                    () -> assertFalse(hayStock)
            );
        }

        @Test
        @DisplayName("Stock a quitar mayor que stock producto")
        void hayStockMayorQueProductoValido() {
            // given
            int stockAQuitar = 10;
            Producto producto = new ProductoNormal("producto", 1, 1, new ArrayList<>());

            // when
            boolean hayStock = serviciosCompras.hayStock(stockAQuitar, producto);

            // then
            assertAll(
                    () -> verify(daoCompras, times(0)).quitarStock(1, producto),
                    () -> assertFalse(hayStock)
            );
        }

    }

    @Nested
    @DisplayName("AGREGAR A LA COMPRA")
    class AgregarALaCompra {
        @Test
        @DisplayName("AGREGAR A LA COMPRA VALIDO")
        void agregarALaCompraValido() {
            // given
            ProductoComprado producto = new ProductoComprado(new ProductoNormal("producto", 1, 1, new ArrayList<>()), 1);
            Usuario usuario = new UsuarioNormal("dni1", "nombre1", new ArrayList<>());
            // when
            Usuario usuarioActualizado;
            when(daoCompras.agregarALaCompra(producto, usuario)).thenReturn(new UsuarioNormal("agregado", "agregado", new ArrayList<>()));
            usuarioActualizado = serviciosCompras.agregarALaCompra(producto, usuario);

            // then
            assertAll(
                    () -> verify(daoCompras).agregarALaCompra(producto, usuario),
                    () -> assertNotEquals(usuario, usuarioActualizado),
                    () -> assertEquals("agregado", usuarioActualizado.getNombre())
            );
        }

        @Test
        @DisplayName("Cantidad de productos no valida")
        void agregarALaCompraCantidadNoValido() {
            // given
            ProductoComprado producto = new ProductoComprado(new ProductoNormal("producto", 1, 1, new ArrayList<>()), 0);
            Usuario usuario = new UsuarioNormal("dni1", "nombre1", new ArrayList<>());
            // when
            Usuario usuarioActualizado;
            usuarioActualizado = serviciosCompras.agregarALaCompra(producto, usuario);

            // then
            assertAll(
                    () -> verify(daoCompras, times(0)).agregarALaCompra(producto, usuario),
                    () -> assertEquals(usuario, usuarioActualizado)
            );
        }

        @Test
        @DisplayName("User == null")
        void agregarALaCompraUserNullValido() {
            // given
            ProductoComprado producto = new ProductoComprado(new ProductoNormal("producto", 1, 1, new ArrayList<>()), 0);
            Usuario usuario = null;
            // when
            Usuario usuarioActualizado;
            usuarioActualizado = serviciosCompras.agregarALaCompra(producto, usuario);

            // then
            assertAll(
                    () -> verify(daoCompras, times(0)).agregarALaCompra(producto, usuario),
                    () -> assertEquals(usuario, usuarioActualizado)
            );
        }

        @Test
        @DisplayName("Producto comprado == null")
        void agregarALaCompraProdNullValido() {
            // given
            ProductoComprado producto = null;
            Usuario usuario = new UsuarioNormal("dni1", "nombre1", new ArrayList<>());
            // when
            Usuario usuarioActualizado;
            usuarioActualizado = serviciosCompras.agregarALaCompra(producto, usuario);

            // then
            assertAll(
                    () -> verify(daoCompras, times(0)).agregarALaCompra(producto, usuario),
                    () -> assertEquals(usuario, usuarioActualizado)
            );
        }
    }

    @Nested
    @DisplayName("ELIMINAR A LA COMPRA")
    class EliminarDeLaCompra {
        @Test
        @DisplayName("ELIMINAR DE LA COMPRA VALIDO")
        void eliminarDeLaCompraValido() {
            // given
            Producto producto = new ProductoNormal("producto", 1, 1, new ArrayList<>());
            Usuario usuario = new UsuarioNormal("dni1", "nombre1", new ArrayList<>());
            // when

            when(daoCompras.eliminarDeLaCompra(producto, usuario)).thenReturn(true);
            boolean eliminaValido = serviciosCompras.eliminarDeLaCompra(producto, usuario);

            // then
            assertAll(
                    () -> verify(daoCompras).eliminarDeLaCompra(producto, usuario),
                    () -> assertTrue(eliminaValido)
            );
        }

        @Test
        @DisplayName("User == null")
        void agregarALaCompraUserNullValido() {
            // given
            Producto producto = new ProductoNormal("producto", 1, 1, new ArrayList<>());
            Usuario usuario = null;
            // when

            boolean eliminaValido = serviciosCompras.eliminarDeLaCompra(producto, usuario);

            // then
            assertAll(
                    () -> verify(daoCompras, times(0)).eliminarDeLaCompra(producto, usuario),
                    () -> assertFalse(eliminaValido)
            );
        }

        @Test
        @DisplayName("Producto comprado == null")
        void agregarALaCompraProdNullValido() {
            // given
            Producto producto = null;
            Usuario usuario = new UsuarioNormal("dni1", "nombre1", new ArrayList<>());
            // when

            boolean eliminaValido = serviciosCompras.eliminarDeLaCompra(producto, usuario);

            // then
            assertAll(
                    () -> verify(daoCompras, times(0)).eliminarDeLaCompra(producto, usuario),
                    () -> assertFalse(eliminaValido)
            );
        }
    }

    @Test
    @DisplayName("GET LISTA COMPRA")
    void getListaCompra() {
        //given
        Usuario usuario = new UsuarioNormal("dni1", "nombre1", new ArrayList<>());
        List<ProductoComprado> listaProductos = Arrays.asList(new ProductoComprado(new ProductoNormal("p1", 10, 15, new ArrayList<>()), 15),
                new ProductoComprado(new ProductoNormal("p2", 100, 155, new ArrayList<>()), 20));

        //when
        when(daoCompras.devolverLista(usuario)).thenReturn(listaProductos);
        List<ProductoComprado> listaProductosCarrito = serviciosCompras.getListaCompra(usuario);

        //then
        assertAll(
                () -> verify(daoCompras).devolverLista(usuario),
                () -> assertEquals(listaProductos, listaProductosCarrito)
        );
    }

    @Test
    @DisplayName("GET COMPRAS PREVIAS")
    void getListaComprasPrevias() {
        //given
        Usuario userComprando = new UsuarioNormal("dni1", "nombre1", new ArrayList<>());
        List<List<ProductoComprado>> comprasHistoricas = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            ProductoComprado productoPrueba = new ProductoComprado(new ProductoNormal("prod1", random.nextInt(10), random.nextInt(10), new ArrayList<>()), random.nextInt(5));
            ProductoComprado productoPrueba2 = new ProductoComprado(new ProductoNormal("prod2", random.nextInt(10), random.nextInt(10), new ArrayList<>()), random.nextInt(5));

            userComprando.getCarrito().add(productoPrueba);
            userComprando.getCarrito().add(productoPrueba2);

            List<ProductoComprado> compra = userComprando.getCarrito();
            userComprando.getComprasPrevias().add(compra);
            comprasHistoricas.add(compra);
            userComprando.setCarrito(new ArrayList<>());
        }
        //when
        when(daoCompras.devolverComprasPrevias(userComprando)).thenReturn(comprasHistoricas);
        List<List<ProductoComprado>> comprasPreviasResultado = serviciosCompras.getComprasPrevias(userComprando);

        //then
        assertAll(
                () -> verify(daoCompras).devolverComprasPrevias(userComprando),
                () -> assertEquals(comprasHistoricas, comprasPreviasResultado)
        );
    }

    @Test
    @DisplayName("GET PRODUCTOS SIN ALERGIAS")
    void getProductosSinAlergiasValido() {
        //given

        Usuario userComprando = new UsuarioNormal("dni1", "n1", new ArrayList<>());

        List<Producto> productoList = new ArrayList<>();
        productoList.add(new ProductoNormal("prod1", 10, 15, new ArrayList<>()));

        Producto prod2 = new ProductoNormal("prod2", 20, 20, new ArrayList<>());
        productoList.add(prod2);

        //when
        when(daoCompras.getProductosSinAlergias(userComprando, productoList)).thenReturn(productoList);
        List<Producto> productosResult = serviciosCompras.getProductosSinAlergia(userComprando, productoList);
        //then
        verify(daoCompras).getProductosSinAlergias(userComprando, productoList);
        assertIterableEquals(productoList, productosResult);
    }

    @Test
    @DisplayName("GET DINERO GASTADO POR CLIENTE")
    void getDineroGastadoPorCliente() {
        //given
        Usuario userComprando = new UsuarioNormal("dni1", "n1", new ArrayList<>());

        //when
        double dineroGastado = 0.0;
        when(daoCompras.dineroGastadoDeCliente(userComprando)).thenReturn(10.5);
        dineroGastado = serviciosCompras.dineroGastadoPorCliente(userComprando);

        //then
        verify(daoCompras).dineroGastadoDeCliente(userComprando);
        assertEquals(10.5, dineroGastado);
    }

    @Nested
    @DisplayName("PAGAR")
    class Pagar {
        @Test
        @DisplayName("PAGAR VALIDO")
        void pagar() {
            //given
            Usuario userComprando = new UsuarioNormal("dni1", "n1", new ArrayList<>());
            Tarjeta tarjeta = new Tarjeta("t1", 1550);
            List<ProductoComprado> compra = new ArrayList<>();
            compra.add(new ProductoComprado(new ProductoNormal("prod1", 1, 15, new ArrayList<>()), 1));
            compra.add(new ProductoComprado(new ProductoNormal("prod2", 2, 20, new ArrayList<>()), 1));
            userComprando.setCarrito(compra);
            //when
            boolean pago = serviciosCompras.pagar(tarjeta, userComprando);

            //then
            verify(daoCompras).pagar(tarjeta, 3, userComprando, 100);
            assertTrue(pago);
        }

        @Test
        @DisplayName("Carrito vacio")
        void pagarNoValidoCarritoVacio() {
            //given
            Usuario userComprando = new UsuarioNormal("dni1", "n1", new ArrayList<>());
            Tarjeta tarjeta = new Tarjeta("t1", 1550);

            //when
            boolean pago = serviciosCompras.pagar(tarjeta, userComprando);

            //then
            verify(daoCompras, times(0)).pagar(tarjeta, 3, userComprando, 100);
            assertFalse(pago);
        }


        @Test
        @DisplayName("Saldo Tarjeta insuficiente")
        void pagarNoValidoSaldoBajo() {
            //given
            Usuario userComprando = new UsuarioNormal("dni1", "n1", new ArrayList<>());
            Tarjeta tarjeta = new Tarjeta("t1", 1);
            List<ProductoComprado> compra = new ArrayList<>();
            compra.add(new ProductoComprado(new ProductoNormal("prod1", 100, 15, new ArrayList<>()), 100));
            compra.add(new ProductoComprado(new ProductoNormal("prod2", 200, 20, new ArrayList<>()), 100));
            userComprando.setCarrito(compra);

            //when
            boolean pago = serviciosCompras.pagar(tarjeta, userComprando);

            //then
            verify(daoCompras, times(0)).pagar(tarjeta, 30000, userComprando, 100);
            assertFalse(pago);
        }

        @Test
        @DisplayName("User == null")
        void pagarNoValidoUserNull() {
            //given
            Usuario userComprando = null;
            Tarjeta tarjeta = new Tarjeta("t1", 1);
            List<ProductoComprado> compra = new ArrayList<>();


            //when
            boolean pago = serviciosCompras.pagar(tarjeta, userComprando);

            //then
            verify(daoCompras, times(0)).pagar(tarjeta, 5, userComprando, 100);
            assertFalse(pago);
        }

        @Test
        @DisplayName("tarjeta.nombre == error")
        void pagarNoValidoTarjetaError() {
            //given
            Usuario userComprando = new UsuarioNormal("dni1", "n1", new ArrayList<>());
            Tarjeta tarjeta = new Tarjeta("error", 150);
            List<ProductoComprado> compra = new ArrayList<>();
            compra.add(new ProductoComprado(new ProductoNormal("prod1", 100, 15, new ArrayList<>()), 100));
            compra.add(new ProductoComprado(new ProductoNormal("prod2", 200, 20, new ArrayList<>()), 100));
            userComprando.setCarrito(compra);


            //when
            boolean pago = serviciosCompras.pagar(tarjeta, userComprando);

            //then
            verify(daoCompras, times(0)).pagar(tarjeta, 3, userComprando, 100);
            assertFalse(pago);
        }
    }
}

