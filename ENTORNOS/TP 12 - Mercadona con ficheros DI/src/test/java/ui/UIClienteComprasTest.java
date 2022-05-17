package ui;

import jakarta.enterprise.inject.Produces;
import modelo.ProductoComprado;
import modelo.Productos.Producto;
import modelo.Productos.ProductoCaducable;
import modelo.Productos.ProductoNormal;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import servicios.ServiciosCompras;
import servicios.ServiciosProductos;
import servicios.ServiciosTarjetas;
import servicios.impl.ServiciosComprasImpl;
import servicios.impl.ServiciosProductosImpl;
import servicios.impl.ServiciosTarjetasImpl;
import ui.common.Constantes;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.stream.SystemOut;
import uk.org.webcompere.systemstubs.stream.input.LinesAltStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SystemStubsExtension.class)
public class UIClienteComprasTest {

    UIClienteCompras uiClienteCompras;

    @Mock
    private ServiciosTarjetas serviciosTarjetasImpl;
    @Mock
    private ServiciosCompras serviciosComprasImpl;
    @Mock
    private ServiciosProductos serviciosProductosImpl;

    private Scanner sc;

    @SystemStub
    private SystemOut systemOut;

    @Nested
    @DisplayName("PAGAR")
    class pagar {
        @Test
        @DisplayName("PAGAR VALIDO")
        void pagarValido() {

            //given
            Tarjeta t1 = new Tarjeta("t1", 4000);

            sc = new Scanner(new LinesAltStream("4", "t1", "6", "5"));
            uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl, sc);
            Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
            userLogueado.setCarrito(List.of(new ProductoComprado(new ProductoNormal("p1", 25, 4, new ArrayList<>()), 4)));
            when(serviciosTarjetasImpl.getTarjeta("t1", userLogueado)).thenReturn(t1);
            when(serviciosComprasImpl.pagar(t1, userLogueado)).thenReturn(true);
            //when
            uiClienteCompras.inicioUICompras(userLogueado);

            //then
            verify(serviciosComprasImpl).pagar(t1, userLogueado);
            assertThat(systemOut.getText()).contains(Constantes.LA_COMPRA_SE_REALIZO_CON_EXITO);
        }

        @Test
        @DisplayName("PAGAR NO VALIDO")
        void pagarNoValido() {

            //given
            Tarjeta t1 = new Tarjeta("t1", -5);

            sc = new Scanner(new LinesAltStream("4", "t1", "6", "5"));
            uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl, sc);
            Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
            userLogueado.setCarrito(List.of(new ProductoComprado(new ProductoNormal("p1", 25, 4, new ArrayList<>()), 4)));
            when(serviciosTarjetasImpl.getTarjeta("t1", userLogueado)).thenReturn(t1);
            when(serviciosComprasImpl.pagar(t1, userLogueado)).thenReturn(false);
            //when
            uiClienteCompras.inicioUICompras(userLogueado);

            //then
            verify(serviciosComprasImpl).pagar(t1, userLogueado);
            assertThat(systemOut.getText()).contains(Constantes.HUBO_UN_PROBLEMA_A_LA_HORA_DE_REALIZAR_LA_COMPRA);
        }
    }

    @Nested
    @DisplayName("AGREGAR A LA COMPRA")
    class agregarALaCompra {
        @Test
        @DisplayName("AGREGAR A LA COMPRA VALIDO")
        void agregarALaCompraValido() {

            //given
            sc = new Scanner(new LinesAltStream("1", "p1", "1", "fin", "6", "5"));
            Producto p1 = new ProductoNormal("p1", 25, 1, new ArrayList<>());
            when(serviciosProductosImpl.getProducto("p1")).thenReturn(p1);

            when(serviciosComprasImpl.hayStock(1, p1)).thenReturn(true);
            ProductoComprado prod = new ProductoComprado(p1, 1);
            Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
            when(serviciosComprasImpl.agregarALaCompra(prod, userLogueado)).thenReturn(new UsuarioNormal("funciona", "funciona", new ArrayList<>()));
            userLogueado = serviciosComprasImpl.agregarALaCompra(prod, userLogueado);
            uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl, sc);

            //when
            uiClienteCompras.inicioUICompras(userLogueado);

            //then
            verify(serviciosComprasImpl).hayStock(1, p1);
            assertThat(systemOut.getText()).contains(Constantes.SE_AGREGARON + "1" + Constantes.DE + p1.toString() + Constantes.A_LA_LISTA_DE_COMPRAS);
            assertThat(userLogueado.getNombre()).isEqualTo("funciona");
        }

        @Test
        @DisplayName("AGREGAR A LA COMPRA STOCK NO VALIDO")
        void agregarALaCompraStockNoValido() {

            //given
            sc = new Scanner(new LinesAltStream("1", "p1", "-1", "fin", "6", "5"));
            Producto p1 = new ProductoNormal("p1", 25, 1, new ArrayList<>());
            when(serviciosProductosImpl.getProducto("p1")).thenReturn(p1);

            when(serviciosComprasImpl.hayStock(-1, p1)).thenReturn(false);
            Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
            uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl, sc);

            //when
            uiClienteCompras.inicioUICompras(userLogueado);

            //then
            verify(serviciosComprasImpl).hayStock(-1, p1);
            assertThat(systemOut.getText()).contains(Constantes.EL_STOCK_QUE_INGRESÓ_ES_MENOR_QUE_1_O_ES_MAYOR_AL_STOCK_QUE_NOS_QUEDA_DEL_PRODUCTO);
        }


        @Test
        @DisplayName("AGREGAR A LA COMPRA PROD NO VALIDO")
        void agregarALaCompraProdNoValido() {

            //given
            sc = new Scanner(new LinesAltStream("1", "p1", "fin", "6", "5"));
            Producto p1 = new ProductoNormal("p1", 25, 1, new ArrayList<>());
            when(serviciosProductosImpl.getProducto("p1")).thenReturn(null);
            Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
            uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl, sc);

            //when
            uiClienteCompras.inicioUICompras(userLogueado);

            //then
            verify(serviciosComprasImpl, times(0)).hayStock(-1, p1);
            assertThat(systemOut.getLines().filter(line -> line.contains(Constantes.INGRESE_EL_NOMBRE_DEL_PRODUCTO_QUE_QUIERE_AGREGAR_A_LA_COMPRA_O_FIN_PARA_TERMINAR)).count())
                    .isEqualTo(2);
        }

        @Test
        @DisplayName("AGREGAR A LA COMPRA FIN DIRECTO")
        void agregarALaCompraFinDirecto() {

            //given
            sc = new Scanner(new LinesAltStream("1", "fin", "6", "5"));
            Producto p1 = new ProductoNormal("p1", 25, 1, new ArrayList<>());
            when(serviciosProductosImpl.getProducto("fin")).thenReturn(null);
            Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
            uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl, sc);

            //when
            uiClienteCompras.inicioUICompras(userLogueado);

            //then
            verify(serviciosComprasImpl, times(0)).hayStock(-1, p1);
            assertThat(systemOut.getLines().filter(line -> line.contains(Constantes.INGRESE_EL_NOMBRE_DEL_PRODUCTO_QUE_QUIERE_AGREGAR_A_LA_COMPRA_O_FIN_PARA_TERMINAR)).count())
                    .isEqualTo(1);
        }

    }


    @Nested
    @DisplayName("ELIMINAR DE LA COMPRA")
    class eliminar {

        @Test
        @DisplayName("ELIMINAR DE LA COMPRA PROD VALIDO")
        void eliminarDeLaCompraProdValido() {

            //given
            sc = new Scanner(new LinesAltStream("2", "p1", "6", "5"));
            Producto p1 = new ProductoNormal("p1", 25, 1, new ArrayList<>());
            when(serviciosProductosImpl.getProducto("p1")).thenReturn(p1);
            Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
            when(serviciosComprasImpl.eliminarDeLaCompra(p1, userLogueado)).thenReturn(true);
            uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl, sc);

            //when
            uiClienteCompras.inicioUICompras(userLogueado);

            //then
            assertThat(systemOut.getText()).contains(Constantes.EL_PRODUCTO_SE_HA_ELIMINADO_CORRECTAMENTE_DE_LA_LISTA_DE_COMPRAS);
        }


        @Test
        @DisplayName("ELIMINAR DE LA COMPRA PROD VALIDO")
        void eliminarDeLaCompraProdNoValido() {

            //given
            sc = new Scanner(new LinesAltStream("2", "p1", "6", "5"));
            Producto p1 = new ProductoNormal("p1", 25, 1, new ArrayList<>());
            when(serviciosProductosImpl.getProducto("p1")).thenReturn(p1);
            Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
            when(serviciosComprasImpl.eliminarDeLaCompra(p1, userLogueado)).thenReturn(false);
            uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl, sc);

            //when
            uiClienteCompras.inicioUICompras(userLogueado);

            //then
            assertThat(systemOut.getText()).contains(Constantes.EL_PRODUTO_QUE_DESEA_ELIMINAR_NO_ESTABA_EN_LA_LISTA_DE_COMPRAS);
        }
    }


    @Test
    @DisplayName("MOSTRAR CARRITO")
    void mostrarCarrito(){
        //given
        Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
        ProductoComprado p1 = new ProductoComprado(new ProductoNormal("p1", 25, 122, new ArrayList<>()), 1);
        ProductoComprado p2 = new ProductoComprado(new ProductoNormal("p2", 5, 100, new ArrayList<>()), 1);
        userLogueado.setCarrito(List.of(p1, p2));
        sc = new Scanner(new LinesAltStream("3", "6", "5"));
        when(serviciosComprasImpl.getListaCompra(userLogueado)).thenReturn(userLogueado.getCarrito());
        uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl, sc);
        //when
        uiClienteCompras.inicioUICompras(userLogueado);

        //then
        assertThat(systemOut.getText()).contains(userLogueado.getCarrito().toString());


    }


    @Test
    @DisplayName("MOSTRAR PRODUCTOS SIN ALERGIAS")
    void mostrarProdsSinAlergia(){
        //given
        Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
        ProductoComprado p1 = new ProductoComprado(new ProductoNormal("p1", 25, 122, new ArrayList<>()), 1);
        ProductoComprado p2 = new ProductoComprado(new ProductoNormal("p2", 5, 100, new ArrayList<>()), 1);
        userLogueado.setCarrito(List.of(p1, p2));
        sc = new Scanner(new LinesAltStream("5", "6", "5"));
        when(serviciosProductosImpl.getLista()).thenReturn(List.of(p1.getProducto(), p2.getProducto()));
        when(serviciosComprasImpl.getProductosSinAlergia(userLogueado, serviciosProductosImpl.getLista())).thenReturn(List.of(p1.getProducto()));
        uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl, sc);
        //when
        uiClienteCompras.inicioUICompras(userLogueado);

        //then
        assertThat(systemOut.getText()).contains(p1.getProducto().toString());
    }
}




