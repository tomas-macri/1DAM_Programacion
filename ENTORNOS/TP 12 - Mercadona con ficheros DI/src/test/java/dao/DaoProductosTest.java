package dao;

import dao.impl.DaoProductosImpl;
import dao.impl.DataBase;
import modelo.Ingrediente;
import modelo.Productos.Producto;
import modelo.Productos.ProductoNormal;
import modelo.Usuarios.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
class DaoProductosTest {

    @InjectMocks
    private DaoProductosImpl daoProductosImpl;

    @Mock
    private DataBase bd;

    @Captor
    private ArgumentCaptor<List<Producto>> captor;

    @Nested
    @DisplayName("AGREGAR PRODUCTO")
    class AgregarProductoTest {
        @Test
        @DisplayName("SE AGREGO CORRECTAMENTE")
        void addProductoValidoTest() {

            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));


            when(bd.loadProductos()).thenReturn(productosBD);


            Producto nuevoProduct = new ProductoNormal("prod4", 50, 15
                    , new ArrayList<>());
            boolean seAgrego = daoProductosImpl.agregarProducto(nuevoProduct);

            //then
            assertAll(
                    () -> {
                        verify(bd).saveProductos(captor.capture());
                        List<Producto> productos = captor.getValue();
                        Assertions.assertThat(productos).contains(nuevoProduct);
                    },
                    () -> assertEquals(nuevoProduct, productosBD.get(productosBD.indexOf(nuevoProduct))),
                    () -> assertTrue(seAgrego)
            );
        }

        @Test
        @DisplayName("LISTA DE PRODUCTOS NULL")
        void addProductoNoValidoTest() {
            //given
            ArrayList<Producto> productosBD = null;

            //when
            when(bd.loadProductos()).thenReturn(productosBD);

            Producto nuevoProduct = new ProductoNormal("prod1", 55, 15
                    , new ArrayList<>());
            boolean seAgrego = daoProductosImpl.agregarProducto(nuevoProduct);

            //then
            assertAll(
                    () -> verify(bd, times(0)).saveProductos(productosBD),
                    () -> assertNull(productosBD),
                    () -> assertFalse(seAgrego)
            );
        }
    }

    @Nested
    @DisplayName("ELIMINAR PRODUCTO")
    class EliminarProductoTest {
        @Test
        @DisplayName("SE ELIMINO CORRECTAMENTE")
        void deleteProductoValidoTest() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));

            Producto producto = new ProductoNormal("prod1", 1, 5, ingredienteArrayList);

            productosBD.add(producto);
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            //when
            when(bd.loadProductos()).thenReturn(productosBD);
            boolean productEliminado = daoProductosImpl.eliminarProducto("prod1");

            //then
            assertAll(() -> verify(bd).saveProductos(productosBD),
                    () -> assertTrue(productEliminado),
                    () -> {
                        verify(bd).saveProductos(captor.capture());
                        List<Producto> productoList = captor.getValue();
                        Assertions.assertThat(producto).isNotIn(productoList);
                    }
            );
        }

        @Test
        @DisplayName("PRODUCTO A ELIMINAR NO EXISTE")
        void deleteProductoNoValidoTest() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            // WHEN
            when(bd.loadProductos()).thenReturn(productosBD);
            boolean ProductEliminado = daoProductosImpl.eliminarProducto("prod4");

            //THEN
            assertFalse(ProductEliminado);
        }


        @Test
        @DisplayName("LISTA DE PRODUCTOS ES NULL")
        void deleteProductoListaNullTest() {
            //given
            ArrayList<Producto> productosBD = null;

            //when
            when(bd.loadProductos()).thenReturn(productosBD);

            boolean seElimino = daoProductosImpl.eliminarProducto("prod1");

            //then
            assertAll(
                    () -> verify(bd, times(0)).saveProductos(productosBD),
                    () -> assertNull(productosBD),
                    () -> assertFalse(seElimino)
            );
        }

    }

    @Nested
    @DisplayName("GET PRODUCTOS")
    class GetProductosTest {
        @Test
        @DisplayName("SE DEVUELVE EL Producto")
        void getProductoValidoTest() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            // when
            when(bd.loadProductos()).thenReturn(productosBD);

            //

            Producto prodObtenido = daoProductosImpl.getProducto(productosBD.indexOf(new ProductoNormal("prod1")));

            assertAll(
                    () -> assertEquals(productosBD.get(0), prodObtenido),
                    () -> assertEquals(productosBD.get(0), prodObtenido)
            );

        }

        @Test
        @DisplayName("NO SE DEVUELVE EL Producto")
        void getProductoNoValidoTest() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            // when
            when(bd.loadProductos()).thenReturn(productosBD);

            Producto producto = daoProductosImpl.getProducto(productosBD.indexOf(new ProductoNormal("prod4")));

            //then
            assertNull(producto);
        }


        @Test
        @DisplayName("Lista productos == null")
        void getProductoNoValidoNullListTest() {
            //given
            ArrayList<Producto> productosBD = null;

            // when
            when(bd.loadProductos()).thenReturn(productosBD);

            Producto producto = daoProductosImpl.getProducto(0);

            //then
            assertNull(producto);
        }
    }

    @Nested
    @DisplayName("MODIFICAR NOMBRE PRODUCTO")
    class ModificarNombreProductoTest {


        @Test
        @DisplayName("SE MODIFICA EL NOMBRE DEL Producto")
        void modificarProductoNombreValidoTest() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            when(bd.loadProductos()).thenReturn(productosBD);

            //
            daoProductosImpl.modificarProductoNombre(productosBD.indexOf(new ProductoNormal("prod1")), "nuevoProd1");
//            boolean seCambio = daoProductosImpl.modificarProductoNombre(productosBD.indexOf(new ProductoNormal("prod1")), "nuevoProd1");

            //then
            assertAll(
                    () -> {
                        verify(bd).saveProductos(captor.capture());
                        List<Producto> productos = captor.getValue();
                        assertEquals("nuevoProd1", productos.get(0).getNombre());
                        assertEquals(productosBD.size(), productos.size());
                    }
            );
        }

        @Test
        @DisplayName("Lista Productos == null")
        void moodificarProductoNombreNoValidoTest() {
            //given
            ArrayList<Producto> productosBD = null;

            // when
            when(bd.loadProductos()).thenReturn(productosBD);
            daoProductosImpl.modificarProductoNombre(0, "nuevoProd1");
            //then
            assertAll(
                    () -> {
                        verify(bd, times(0)).saveProductos(any());

                    }
            );
        }
    }

    @Nested
    @DisplayName("MODIFICAR PRECIO PRODUCTO")
    class ModificarPrecioProductoTest {

        @Test
        @DisplayName("SE MODIFICA EL PRECIO DEL Producto")
        void modificarProductoPrecioValidoTest() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            //when
            when(bd.loadProductos()).thenReturn(productosBD);
            daoProductosImpl.modificarProductoPrecio(productosBD.indexOf(new ProductoNormal("prod1")), 10);

            //then
            assertAll(
                    () -> {
                        verify(bd).saveProductos(captor.capture());
                        List<Producto> productos = captor.getValue();
                        assertEquals(10, productos.get(0).getPrecio());
                        assertEquals(productosBD.size(), productos.size());
                    }
            );
        }

        @Test
        @DisplayName("Lista productos == null")
        void modificarProductoPrecioNoValidoTest() {
            //given
            ArrayList<Producto> productosBD = null;

            // when
            when(bd.loadProductos()).thenReturn(productosBD);
            daoProductosImpl.modificarProductoPrecio(0, 10);
            //then
            assertAll(
                    () -> {
                        verify(bd, times(0)).saveProductos(any());
                    }
            );

        }
    }

    @Nested
    @DisplayName("MODIFICAR STOCK PRODUCTO")
    class ModificarStockProductoTest {


        @Test
        @DisplayName("SE MODIFICA EL STOCK DEL Producto")
        void modificarProductoStockValidoTest() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            //when
            when(bd.loadProductos()).thenReturn(productosBD);
            daoProductosImpl.modificarProductoStock(productosBD.indexOf(new ProductoNormal("prod1")), 10);

            //then
            assertAll(
                    () -> {
                        verify(bd).saveProductos(captor.capture());
                        List<Producto> productos = captor.getValue();
                        assertEquals(10, productos.get(0).getStock());
                        assertEquals(productosBD.size(), productos.size());
                    }
            );
        }

        @Test
        @DisplayName("Lista de Productos == null")
        void moodificarProductoStockNoValidoTest() {
            //given
            ArrayList<Producto> productosBD = null;

            // when
            when(bd.loadProductos()).thenReturn(productosBD);
            daoProductosImpl.modificarProductoStock(0, 555);
            //then
            assertAll(
                    () -> {
                        verify(bd, times(0)).saveProductos(any());
                    }
            );
        }
    }

    @Nested
    @DisplayName("MODIFICAR PRODUCTO COMPLETO")
    class ModificarProductoCompletoTest {
        @Test
        @DisplayName("SE MODIFICA EL Producto ENTERO")
        void modificarProductoEnteroValidoTest() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));

            Producto prodReemplazar = new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList);

            productosBD.add(prodReemplazar);
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));


            //when
            when(bd.loadProductos()).thenReturn(productosBD);
            Producto prodNuevo = new ProductoNormal("prodNuevo", 50, 65, new ArrayList<>());
            daoProductosImpl.modificarProducto(productosBD.indexOf(new ProductoNormal("prod1")), prodNuevo);

            //then
            assertAll(
                    () -> {
                        verify(bd).saveProductos(captor.capture());
                        List<Producto> productos = captor.getValue();
                        assertEquals(productosBD.size(), productos.size());
                        assertTrue(productos.contains(prodNuevo));
                        assertFalse(productos.contains(prodReemplazar));
                    }
            );
        }

        @Test
        @DisplayName("Lista de productos  == null")
        void modificarProductoEnteroNoValidoTest() {
            //given
            ArrayList<Producto> productosBD = null;

            // when
            when(bd.loadProductos()).thenReturn(productosBD);
            daoProductosImpl.modificarProducto(0, new ProductoNormal("prodNuevo", 50, 65, new ArrayList<>()));
            //then
            assertAll(
                    () -> {
                        verify(bd, times(0)).saveProductos(any());
                    }
            );
        }
    }

    @Nested
    @DisplayName("PRODUCTO EXISTE")
    class ProductoExisteTest {
        @Test
        @DisplayName("EXISTE EL Producto")
        void existeProductoTest() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            //when
            when(bd.loadProductos()).thenReturn(productosBD);

            boolean existe = daoProductosImpl.elProductoExiste(new ProductoNormal("prod1"));

            assertTrue(existe);
        }

        @Test
        @DisplayName("NO EXISTE EL Producto")
        void noExisteProductoTest() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            //when
            when(bd.loadProductos()).thenReturn(productosBD);
            boolean existe = daoProductosImpl.elProductoExiste(new ProductoNormal("prod4"));

            //then
            assertFalse(existe);
        }

        @Test
        @DisplayName("Lista de productos  == null")
        void noExisteProductoNullListTest() {
            //given
            ArrayList<Producto> productosBD = null;
            //when
            when(bd.loadProductos()).thenReturn(productosBD);
            boolean existe = daoProductosImpl.elProductoExiste(new ProductoNormal("prod1"));

            //then
            assertFalse(existe);
        }
    }

    @Nested
    @DisplayName("DEVOLVER INDEXS PRODUCTOS")
    class DevolverIndexsProductosTest {

        @Test
        @DisplayName("INDEX PRODUCTO VALIDO")
        void indexProductValido() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            // when
            when(bd.loadProductos()).thenReturn(productosBD);
            Producto producto = new ProductoNormal("prod1");

            int index = daoProductosImpl.obtenerIndexProducto(producto);
            assertEquals(0, index);
        }

        @Test
        @DisplayName("INDEX PRODUCTO INEXISTENTE")
        void indexProductNoValido() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            // when
            when(bd.loadProductos()).thenReturn(productosBD);
            Producto producto = new ProductoNormal("prod4");
            int index = daoProductosImpl.obtenerIndexProducto(producto);

            //then
            assertEquals(-1, index);
        }

        @Test
        @DisplayName("Lista de productos  == null")
        void noExisteProductoNullListTest() {
            //given
            ArrayList<Producto> productosBD = null;
            //when
            when(bd.loadProductos()).thenReturn(productosBD);
            int index = daoProductosImpl.obtenerIndexProducto(new ProductoNormal("prod1"));

            //then
            assertEquals(-1, index);
        }
    }

    @Nested
    @DisplayName("DEVOLVER LISTA DE PRODUCTOS")
    class DevolverListaProductosTest {
        @Test
        @DisplayName("LISTA Productos VALIDA")
        void listaProductsValida() {
            //given
            ArrayList<Producto> productosBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            productosBD.add(new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList));
            productosBD.add(new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList));

            // when
            when(bd.loadProductos()).thenReturn(productosBD);

            //then
            assertEquals(productosBD, daoProductosImpl.devolverLista());
        }
        @Test
        @DisplayName("Lista de productos  == null")
        void listaVaciaNullListTest() {
            //given
            ArrayList<Producto> productosBD = null;
            //when
            when(bd.loadProductos()).thenReturn(productosBD);
            List<Producto> lista = daoProductosImpl.devolverLista();

            //then
            assertEquals(new ArrayList<>(), lista);
        }
    }
}
