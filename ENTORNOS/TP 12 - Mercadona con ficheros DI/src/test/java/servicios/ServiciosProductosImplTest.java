package servicios;

import dao.impl.DaoProductosImpl;
import modelo.Productos.Producto;
import modelo.Productos.ProductoNormal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import servicios.impl.ServiciosProductosImpl;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiciosProductosImplTest {

    @InjectMocks
    private ServiciosProductosImpl serviciosProductos;

    @Mock
    private DaoProductosImpl daoProductos;

    @Nested
    @DisplayName("AGREGAR PRODUCTO")
    class AgregarProducto {
        @Test
        @DisplayName("AGREGAR PRODUCTO CORRECTO")
        void addProducto() {
            //given
            Producto producto = new ProductoNormal("p1", 15, 10, new ArrayList<>());

            //when
            when(daoProductos.elProductoExiste(producto)).thenReturn(false);
            when(daoProductos.agregarProducto(producto)).thenReturn(true);
            boolean respuesta = serviciosProductos.agregarProducto(producto);

            //then
            verify(daoProductos).elProductoExiste(producto);
            verify(daoProductos).agregarProducto(producto);
            assertTrue(respuesta);
        }

        @Test
        @DisplayName("No se agrega - El producto ya existe")
        void addProductoNoValidoExisteYa() {
            //given
            Producto producto = new ProductoNormal("p1", 15, 10, new ArrayList<>());

            //when
            when(daoProductos.elProductoExiste(producto)).thenReturn(true);
            boolean respuesta = serviciosProductos.agregarProducto(producto);

            //then
            verify(daoProductos).elProductoExiste(producto);
            verify(daoProductos, never()).agregarProducto(producto);
            assertFalse(respuesta);
        }

        @Test
        @DisplayName("No se agrega - El nuevo producto no tiene nombre")
        void addProductoNoValidoNombre() {
            //given
            Producto producto = new ProductoNormal("", 15, 10, new ArrayList<>());

            //when
            when(daoProductos.elProductoExiste(producto)).thenReturn(false);
            boolean respuesta = serviciosProductos.agregarProducto(producto);

            //then
            verify(daoProductos).elProductoExiste(producto);
            verify(daoProductos, times(0)).agregarProducto(producto);
            assertFalse(respuesta);
        }


        @Test
        @DisplayName("No se agrega - El nuevo producto no tiene precio valido")
        void addProductoNoValidoPrecio() {
            //given
            Producto producto = new ProductoNormal("p1", 0, 10, new ArrayList<>());

            //when
            when(daoProductos.elProductoExiste(producto)).thenReturn(false);
            boolean respuesta = serviciosProductos.agregarProducto(producto);

            //then
            verify(daoProductos).elProductoExiste(producto);
            verify(daoProductos, times(0)).agregarProducto(producto);
            assertFalse(respuesta);
        }


        @Test
        @DisplayName("No se agrega - El nuevo producto no tiene stock valido")
        void addProductoNoValidoStock() {
            //given
            Producto producto = new ProductoNormal("p1", 15, -1, new ArrayList<>());

            //when
            when(daoProductos.elProductoExiste(producto)).thenReturn(false);
            boolean respuesta = serviciosProductos.agregarProducto(producto);

            //then
            verify(daoProductos).elProductoExiste(producto);
            verify(daoProductos, times(0)).agregarProducto(producto);
            assertFalse(respuesta);
        }
    }

    @Test
    @DisplayName("ELIMINAR PRODUCTO")
    void eliminarProducto() {
        //given
        Producto producto = new ProductoNormal("p1", 15, 10, new ArrayList<>());

        //when
        when(daoProductos.eliminarProducto(producto.getNombre())).thenReturn(true);
        boolean respuesta = serviciosProductos.eliminarProducto(producto.getNombre());

        //then
        verify(daoProductos).eliminarProducto(producto.getNombre());
        assertTrue(respuesta);

    }

    @Nested
    @DisplayName("MODIFICAR PRODUCTO COMPLETO")
    class ModificarProductoCompleto {
        @Test
        @DisplayName("MODIFICAR PRODUCTO COMPLETO VALIDO")
        void modificarProductoCompletoValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());
            Producto producto = new ProductoNormal("p1", 15, 10, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(0);
            boolean respuesta = serviciosProductos.modificarProducto(producto, prodViejo.getNombre());

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos).modificarProducto(0, producto);
            assertTrue(respuesta);
        }

        @Test
        @DisplayName("No se modifica producto - El producto a reemplazar no existe")
        void modificarProductoCompletoNoValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());
            Producto producto = new ProductoNormal("p1", 15, 10, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(-1);
            boolean respuesta = serviciosProductos.modificarProducto(producto, prodViejo.getNombre());

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).modificarProducto(-1, producto);
            assertFalse(respuesta);
        }

        @Test
        @DisplayName("No se modifica el producto - Nuevo nombre vacio")
        void modificarProductoCompletoNoValidoNuevoNombre() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());
            Producto producto = new ProductoNormal("", 15, 10, new ArrayList<>());

            //when
            boolean respuesta = serviciosProductos.modificarProducto(producto, prodViejo.getNombre());

            //then
            verify(daoProductos, times(0)).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).modificarProducto(0, producto);
            assertFalse(respuesta);
        }

        @Test
        @DisplayName("No se modifica el producto - Nuevo precio no valido")
        void modificarProductoCompletoNoValidoNuevoPrecio() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());
            Producto producto = new ProductoNormal("", -5, 10, new ArrayList<>());

            //when
            boolean respuesta = serviciosProductos.modificarProducto(producto, prodViejo.getNombre());

            //then
            verify(daoProductos, times(0)).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).modificarProducto(0, producto);
            assertFalse(respuesta);
        }

        @Test
        @DisplayName("No se modifica el producto - Nuevo stock no valido")
        void modificarProductoCompletoNoValidoNuevoStock() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());
            Producto producto = new ProductoNormal("p1", 15, -22, new ArrayList<>());

            //when
            boolean respuesta = serviciosProductos.modificarProducto(producto, prodViejo.getNombre());

            //then
            verify(daoProductos, times(0)).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).modificarProducto(0, producto);
            assertFalse(respuesta);
        }
    }

    @Nested
    @DisplayName("MODIFICAR PRODUCTO NOMBRE")
    class ModificarProductoNombre {
        @Test
        @DisplayName("MODIFICAR PRODUCTO NOMBRE VALIDO")
        void modificarProductoNombreValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(0);
            boolean respuesta = serviciosProductos.modificarProductoNombre(prodViejo.getNombre(), "pNuevo");

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos).modificarProductoNombre(0, "pNuevo");
            assertTrue(respuesta);
        }

        @Test
        @DisplayName("No se modifica el nombre - nuevo nombre vacio")
        void modificarProductoNombreNoValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(0);
            boolean respuesta = serviciosProductos.modificarProductoNombre(prodViejo.getNombre(), "");

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).modificarProductoNombre(0, "");
            assertFalse(respuesta);
        }

        @Test
        @DisplayName("No se modifica el nombre - no existe producto viejo")
        void modificarProductoProdViejoNoValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(-1);
            boolean respuesta = serviciosProductos.modificarProductoNombre(prodViejo.getNombre(), "p1");

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).modificarProductoNombre(-1, "p1");
            assertFalse(respuesta);
        }


    }

    @Nested
    @DisplayName("MODIFICAR PRODUCTO PRECIO")
    class ModificarProductoPrecio {
        @Test
        @DisplayName("MODIFICAR PRODUCTO PRECIO VALIDO")
        void modificarProductoPrecioValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(0);
            boolean respuesta = serviciosProductos.modificarProductoPrecio(prodViejo.getNombre(), 50);

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos).modificarProductoPrecio(0, 50);
            assertTrue(respuesta);
        }

        @Test
        @DisplayName("No se modifica el precio - nuevo precio no valido")
        void modificarProductoPrecioNoValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(0);
            boolean respuesta = serviciosProductos.modificarProductoPrecio(prodViejo.getNombre(), 0);

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).modificarProductoPrecio(0, 0);
            assertFalse(respuesta);
        }

        @Test
        @DisplayName("No se modifica el precio - no existe producto viejo")
        void modificarProductoProdViejoNoValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(-1);
            boolean respuesta = serviciosProductos.modificarProductoPrecio(prodViejo.getNombre(), 50);

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).modificarProductoPrecio(-1, 50);
            assertFalse(respuesta);
        }
    }
    @Nested
    @DisplayName("MODIFICAR PRODUCTO STOCK")
    class ModificarProductoStock {
        @Test
        @DisplayName("MODIFICAR PRODUCTO STOCK VALIDO")
        void modificarProductoStockValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(0);
            boolean respuesta = serviciosProductos.modificarProductoStock(prodViejo.getNombre(), 500);

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos).modificarProductoStock(0, 500);
            assertTrue(respuesta);
        }

        @Test
        @DisplayName("No se modifica el stock - nuevo stock vacio")
        void modificarProductoStockNoValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(0);
            boolean respuesta = serviciosProductos.modificarProductoStock(prodViejo.getNombre(), -1);

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).modificarProductoStock(0, -1);
            assertFalse(respuesta);
        }

        @Test
        @DisplayName("No se modifica el stock - no existe producto viejo")
        void modificarProductoProdViejoNoValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(-1);
            boolean respuesta = serviciosProductos.modificarProductoStock(prodViejo.getNombre(), 555);

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).modificarProductoStock(-1, 555);
            assertFalse(respuesta);
        }


    }

    @Nested
    @DisplayName("GET PRODUCTO")
    class GetProducto {
        @Test
        @DisplayName("GET PRODUCTO VALIDO")
        void getProductoValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(0);
            when(daoProductos.getProducto(0)).thenReturn(prodViejo);
            Producto resultado = serviciosProductos.getProducto(prodViejo.getNombre());

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos).getProducto(0);
            assertEquals(prodViejo, resultado);
        }

        @Test
        @DisplayName("No se devuelve el producto - no existe producto")
        void getProductoNoValido() {
            //given
            Producto prodViejo = new ProductoNormal("pViejo", 25, 25, new ArrayList<>());

            //when
            when(daoProductos.obtenerIndexProducto(prodViejo)).thenReturn(-1);
            Producto resultado = serviciosProductos.getProducto(prodViejo.getNombre());

            //then
            verify(daoProductos).obtenerIndexProducto(prodViejo);
            verify(daoProductos, times(0)).getProducto(-1);
            assertNull(resultado);
        }
    }

    @Test
    @DisplayName("GET LISTA PRODUCTOS")
    void getListaProductos() {
        //given
        List<Producto> listaProductos = new ArrayList<>();
        Producto prod1 = new ProductoNormal("p1", 25, 25, new ArrayList<>());
        Producto prod2 = new ProductoNormal("p2", 25, 25, new ArrayList<>());
        listaProductos.add(prod1);
        listaProductos.add(prod2);

        //when
        when(daoProductos.devolverLista()).thenReturn(listaProductos);
        List<Producto> resultado = serviciosProductos.getLista();

        //then
        verify(daoProductos).devolverLista();
        assertEquals(listaProductos, resultado);
    }
}