package dao;

import dao.impl.DaoEstadisticasImpl;
import dao.impl.DataBase;
import modelo.Ingrediente;
import modelo.Productos.*;
import modelo.ProductoComprado;
import modelo.Usuarios.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
class DaoEstadisticasTest {
    @InjectMocks
    private DaoEstadisticasImpl daoEstadisticas;

    @Mock
    private DataBase bd;

    @Nested
    @DisplayName("LISTA DE PRODUCTOS POR ORDEN DE COMPRA")
    class ListaDeProductosPorOrdenDeCompra {
        @Test
        @DisplayName("LISTA DE PRODUCTOS POR ORDEN DE COMPRA VALIDA")
        void testListaProductosPorOrdenDeCompra() {
            //Givn

            List<Producto> listaProductos = new ArrayList<>();
            Producto prod1 = new ProductoNormal("prod1", 150, 1000, new ArrayList<>());
            Producto prod2 = new ProductoNormal("prod2", 250, 2000, new ArrayList<>());
            Producto prod3 = new ProductoNormal("prod3", 300, 3000, new ArrayList<>());
            listaProductos.add(prod1);
            listaProductos.add(prod2);
            listaProductos.add(prod3);

            LinkedHashMap<String, Usuario> listaBDUsuarios = new LinkedHashMap<>();
            Usuario user1 = new UsuarioNormal("dni1", "user1", new ArrayList<>());
            Usuario user2 = new UsuarioNormal("dni2", "user2", new ArrayList<>());
            Usuario user3 = new UsuarioNormal("dni3", "user3", new ArrayList<>());
            listaBDUsuarios.put(user1.getDni(), user1);
            listaBDUsuarios.put(user2.getDni(), user2);
            listaBDUsuarios.put(user3.getDni(), user3);

            user1.getCarrito().add(new ProductoComprado(prod2, 10));
            user1.getCarrito().add(new ProductoComprado(prod3, 5));
            user1.getCarrito().add(new ProductoComprado(prod1, 1));

            user1.getComprasPrevias().add(user1.getCarrito());

            user1.setCarrito(new ArrayList<>());

            user1.getCarrito().add(new ProductoComprado(prod2, 5));
            user1.getCarrito().add(new ProductoComprado(prod3, 1));
            user1.getCarrito().add(new ProductoComprado(prod1, 2));

            user1.getComprasPrevias().add(user1.getCarrito());


            user2.getCarrito().add(new ProductoComprado(prod2, 10));
            user2.getCarrito().add(new ProductoComprado(prod3, 5));
            user2.getCarrito().add(new ProductoComprado(prod1, 1));

            user2.getComprasPrevias().add(user2.getCarrito());

            user2.setCarrito(new ArrayList<>());

            user2.getCarrito().add(new ProductoComprado(prod2, 5));
            user2.getCarrito().add(new ProductoComprado(prod3, 1));
            user2.getCarrito().add(new ProductoComprado(prod1, 2));

            user2.getComprasPrevias().add(user2.getCarrito());


            user3.getCarrito().add(new ProductoComprado(prod2, 10));
            user3.getCarrito().add(new ProductoComprado(prod3, 5));
            user3.getCarrito().add(new ProductoComprado(prod1, 1));

            user3.getComprasPrevias().add(user3.getCarrito());

            user3.setCarrito(new ArrayList<>());

            user3.getCarrito().add(new ProductoComprado(prod2, 5));
            user3.getCarrito().add(new ProductoComprado(prod3, 1));
            user3.getCarrito().add(new ProductoComprado(prod1, 2));

            user3.getComprasPrevias().add(user3.getCarrito());

            //When
            when((bd).loadUsuarios()).thenReturn(listaBDUsuarios);

            //Then
            List<Map.Entry<String, Double>> listaEstadisticas = daoEstadisticas.listaProductosPorOrdenDeCompra();

            assertAll(
                    () -> verify(bd).loadUsuarios(),
                    () -> assertEquals(3, listaEstadisticas.size()),
                    () -> assertEquals(prod2.getNombre(), listaEstadisticas.get(0).getKey()),
                    () -> assertEquals(prod3.getNombre(), listaEstadisticas.get(1).getKey()),
                    () -> assertEquals(prod1.getNombre(), listaEstadisticas.get(2).getKey())
            );


        }

        @Test
    @DisplayName("NO HAY USUARIOS EN LA BD")
        void testListaProductosPorOrdenDeCompraNoValido() {
            //Given

            List<Producto> listaProductos = new ArrayList<>();
            Producto prod1 = new ProductoNormal("prod1", 150, 1000, new ArrayList<>());
            Producto prod2 = new ProductoNormal("prod2", 250, 2000, new ArrayList<>());
            Producto prod3 = new ProductoNormal("prod3", 300, 3000, new ArrayList<>());
            listaProductos.add(prod1);
            listaProductos.add(prod2);
            listaProductos.add(prod3);

            LinkedHashMap<String, Usuario> listaBDUsuarios = new LinkedHashMap<>();

            //When
            when((bd).loadUsuarios()).thenReturn(listaBDUsuarios);

            //Then
            List<Map.Entry<String, Double>> listaEstadisticas = daoEstadisticas.listaProductosPorOrdenDeCompra();

            assertAll(
                    () -> verify(bd).loadUsuarios(),
                    () -> assertEquals(new ArrayList<>(), listaEstadisticas)
            );


        }
    }

    @Nested
    @DisplayName("Test de listaProductosPorOrdenDeCompra")
    class ListaProductosPorOrdenDeCompraTest {
        @Test
        @DisplayName("Lista de productos con un ingrediente VALIDA")
        void listaProductosConIngredienteValida() {
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));

            ArrayList<Producto> listaBDProductos = new ArrayList<>();

            Producto prod1 = new ProductoNormal("prod1", 1, 5
                    , ingredienteArrayList);

            ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing4"));
            ingredienteArrayList.add(new Ingrediente("ing5"));
            ingredienteArrayList.add(new Ingrediente("ing6"));


            Producto prod2 = new ProductoNormal("prod2", 2, 2
                    , ingredienteArrayList);

            ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            Producto prod3 = new ProductoNormal("prod3", 3, 10
                    , ingredienteArrayList);

            listaBDProductos.add(prod1);
            listaBDProductos.add(prod2);
            listaBDProductos.add(prod3);

            when(bd.loadProductos()).thenReturn(listaBDProductos);

            List<Producto> listaProdsConIng2 = new ArrayList<>();
            listaProdsConIng2.add(prod1);
            listaProdsConIng2.add(prod3);

            List<Producto> listaProdsConIng5 = new ArrayList<>();
            listaProdsConIng5.add(prod2);

            List<Producto> listResultIng2 = daoEstadisticas.listaProductosConIngrediente(new Ingrediente("ing2"));

            assertAll(
                    () -> verify(bd).loadProductos(),
                    () -> assertEquals(listaProdsConIng2, listResultIng2)
            );
        }

        @Test
        @DisplayName("No hay productos en la bd Valido")
        void listaProductosConIngredienteNoValida() {

            List<Producto> listaBDProductos = new ArrayList<>();
            when(bd.loadProductos()).thenReturn(listaBDProductos);

            List<Producto> listProductos = daoEstadisticas.listaProductosConIngrediente(new Ingrediente("ing"));

            assertAll(
                    () -> verify(bd).loadProductos(),
                    () -> assertEquals(new ArrayList<>(), listProductos)
            );
        }
    }

    @Nested
    @DisplayName("LISTAR CLIENTES ORDENADOS POR GASTOS")
    class ListarClientesOrdenadosPorGastos {
        @Test
        @DisplayName("LISTA CLIENTES ORDEN DE GASTO VALIDA")
        void listaClientesGasto() {

            //GIVEN
            LinkedHashMap<String, Usuario> listaBDUsuarios = new LinkedHashMap<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));

            Usuario user1 = new UsuarioNormal("dni1", "user1", ingredienteArrayList);
            Usuario user2 = new UsuarioNormal("dni2", "user2", ingredienteArrayList);
            Usuario user3 = new UsuarioNormal("dni3", "user3", ingredienteArrayList);


            listaBDUsuarios.put(user1.getDni(), user1);
            listaBDUsuarios.put(user2.getDni(), user2);
            listaBDUsuarios.put(user3.getDni(), user3);


            Producto prod1 = new ProductoNormal("prod1", 10, 205
                    , ingredienteArrayList);

            Producto prod2 = new ProductoNormal("prod2", 20, 200
                    , ingredienteArrayList);

            Producto prod3 = new ProductoNormal("prod3", 30, 1000
                    , ingredienteArrayList);


            user1.getCarrito().add(new ProductoComprado(prod2, 10));
            user1.getCarrito().add(new ProductoComprado(prod3, 10));
            user1.getCarrito().add(new ProductoComprado(prod1, 10));

            user1.getComprasPrevias().add(user1.getCarrito());

            user1.setCarrito(new ArrayList<>());

            user1.getCarrito().add(new ProductoComprado(prod2, 5));
            user1.getCarrito().add(new ProductoComprado(prod3, 5));
            user1.getCarrito().add(new ProductoComprado(prod1, 5));

            user1.getComprasPrevias().add(user1.getCarrito());


            user2.getCarrito().add(new ProductoComprado(prod2, 30));
            user2.getCarrito().add(new ProductoComprado(prod3, 30));
            user2.getCarrito().add(new ProductoComprado(prod1, 30));

            user2.getComprasPrevias().add(user2.getCarrito());

            user2.setCarrito(new ArrayList<>());

            user2.getCarrito().add(new ProductoComprado(prod2, 15));
            user2.getCarrito().add(new ProductoComprado(prod3, 15));
            user2.getCarrito().add(new ProductoComprado(prod1, 15));

            user2.getComprasPrevias().add(user2.getCarrito());


            user3.getCarrito().add(new ProductoComprado(prod2, 20));
            user3.getCarrito().add(new ProductoComprado(prod3, 20));
            user3.getCarrito().add(new ProductoComprado(prod1, 20));

            user3.getComprasPrevias().add(user3.getCarrito());

            user3.setCarrito(new ArrayList<>());

            user3.getCarrito().add(new ProductoComprado(prod2, 15));
            user3.getCarrito().add(new ProductoComprado(prod3, 11));
            user3.getCarrito().add(new ProductoComprado(prod1, 10));

            user3.getComprasPrevias().add(user3.getCarrito());

            //WHEN
            when(bd.loadUsuarios()).thenReturn(listaBDUsuarios);

            //THEN

            List<Usuario> listaUsuariosOrdenCompra = new ArrayList<>();
            listaUsuariosOrdenCompra.add(user2);
            listaUsuariosOrdenCompra.add(user3);
            listaUsuariosOrdenCompra.add(user1);

            List<Usuario> listaResultado = daoEstadisticas.listaClientesPorGasto();

            assertAll(
                    () -> verify(bd).loadUsuarios(),
                    () -> assertEquals(listaUsuariosOrdenCompra, listaResultado)

            );

        }

        @Test
        public void noHayClientesEnLaBD() {

        }

    }
}
