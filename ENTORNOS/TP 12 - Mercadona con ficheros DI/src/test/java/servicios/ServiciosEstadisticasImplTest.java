package servicios;

import dao.impl.DaoEstadisticasImpl;
import modelo.Ingrediente;
import modelo.Productos.Producto;
import modelo.Productos.ProductoNormal;
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
import servicios.impl.ServiciosEstadisticasImpl;
import ui.common.Constantes;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
class ServiciosEstadisticasImplTest {

    @InjectMocks
    ServiciosEstadisticasImpl serviciosEstadisticasImpl;

    @Mock
    DaoEstadisticasImpl daoEstadisticasImpl;

    @Test
    @DisplayName("LISTA PRODUCTOS POR ORDEN DE COMPRA")
    void listarProductosPorOrdenCompra() {
        //given
        List<Map.Entry<String, Double>> productoList = new ArrayList<>();
        productoList.add(new AbstractMap.SimpleEntry<>("p1", 1.0));
        productoList.add(new AbstractMap.SimpleEntry<>("p2", 2.0));

        //when
        when(daoEstadisticasImpl.listaProductosPorOrdenDeCompra()).thenReturn(productoList);
        List<Map.Entry<String, Double>> resultado = serviciosEstadisticasImpl.listaProductosPorOrdenDeCompra();

        //then
        assertAll(
                () -> verify(daoEstadisticasImpl).listaProductosPorOrdenDeCompra(),
                () -> assertEquals(productoList, resultado)
        );
    }

    @Nested
    @DisplayName("LISTA PRODUCTOS CON INGREDIENTE")
    class ListaProductosConIngrediente {

        @Test
        @DisplayName("LISTA PRODUCTOS CON INGREDIENTE VALIDA")
        void listaProductosConIngredienteValida() {
            //given
            Ingrediente i1 = new Ingrediente("i1");
            List<Producto> productoList = Arrays.asList(new ProductoNormal("p1", 1, 15, new ArrayList<>()), new ProductoNormal("p2", 2, 15, new ArrayList<>()));

            //when
            when(daoEstadisticasImpl.listaProductosConIngrediente(i1)).thenReturn(productoList);
            List<Producto> resultado = serviciosEstadisticasImpl.listaProductosConIngrediente(i1);


            //then
            assertAll(
                    () -> verify(daoEstadisticasImpl).listaProductosConIngrediente(i1),
                    () -> assertEquals(productoList, resultado)
            );
        }

        @Test
        @DisplayName("Ingrediente == Constantes.Fin")
        void listaProductosConIngredienteNoValida() {
            //given
            Ingrediente fin = new Ingrediente(Constantes.FIN);
            //when
            List<Producto> resultado = serviciosEstadisticasImpl.listaProductosConIngrediente(fin);


            //then
            assertAll(
                    () -> verify(daoEstadisticasImpl, times(0)).listaProductosConIngrediente(fin),
                    () -> assertEquals(new ArrayList<>(), resultado)
            );
        }

    }

    @Test
    @DisplayName("LISTA USUARIOS POR GASTOS")
    void listarUsuariosPorGastos() {
        //given
        List<Usuario> usuarioList = Arrays.asList(new UsuarioNormal("u1", "n1", new ArrayList<>()), new UsuarioNormal("u2", "n2", new ArrayList<>()));

        //when
        when(daoEstadisticasImpl.listaClientesPorGasto()).thenReturn(usuarioList);
        List<Usuario> resultado = serviciosEstadisticasImpl.listaUsuariosPorGastos();

        //then
        assertAll(
                () -> verify(daoEstadisticasImpl).listaClientesPorGasto(),
                () -> assertEquals(usuarioList, resultado)
        );

    }

}
