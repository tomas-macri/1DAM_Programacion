package dao;

import modelo.Ingrediente;
import modelo.Producto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class DaoProductosTest {
        @Test
        @DisplayName("SE AGREGO CORRECTAMENTE")
        void addProductoValidoTest(){
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

            Producto nuevoProduct = new Producto("prod4", 50,15
                    , new ArrayList<>());
            boolean seAgrego = daoProductos.agregarProducto(nuevoProduct);

            assertEquals(nuevoProduct, listaBD.get(listaBD.indexOf(nuevoProduct)));
            assertTrue(seAgrego);
        }

        @Test
        @DisplayName("NO SE AGREGA Producto")
        void addProductoNoValidoTest(){
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));



            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

            Producto nuevoProduct = new Producto("prod1", 55, 15
                    , new ArrayList<>());
            boolean seAgrego = daoProductos.agregarProducto(nuevoProduct);

            assertEquals(nuevoProduct, listaBD.get(listaBD.indexOf(nuevoProduct)));
            assertFalse(seAgrego);
        }

        @Test
        @DisplayName("SE ELIMINO CORRECTAMENTE")
        void deleteProductoValidoTest() {
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));
            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

            boolean ProductEliminado = daoProductos.eliminarProducto("prod1");
            assertTrue(ProductEliminado);
        }

        @Test
        @DisplayName("NO SE ELIMINA Producto")
        void deleteProductoNoValidoTest() {
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));



            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

           boolean ProductEliminado = daoProductos.eliminarProducto("prod4");

            assertFalse(ProductEliminado);
        }

        @Test
        @DisplayName("SE DEVUELVE EL Producto")
        void getProductoValidoTest() {
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));



            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

            Producto prodObtenido = daoProductos.getProducto(listaBD.indexOf(new Producto("prod1")));

            assertEquals(new Producto("prod1", 1, 5
                    , ingredienteArrayList), prodObtenido);
        }

        @Test
        @DisplayName("NO SE DEVUELVE EL Producto")
        void getProductoNoValidoTest() {
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));



            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

            Producto Producto = daoProductos.getProducto(listaBD.indexOf(new Producto("prod4")));

            assertNull(Producto);
        }

        @Test
        @DisplayName("SE MODIFICA EL NOMBRE DEL Producto")
        void modificarProductoNombreValidoTest() {
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));



            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

            boolean seCambio = daoProductos.modificarProductoNombre(listaBD.indexOf(new Producto("prod1")), "nuevoProd1");

            assertTrue(seCambio);
        }

        @Test
        @DisplayName("NO SE MODIFICA EL NOMBRE del Producto")
        void moodificarProductoNombreNoValidoTest() {
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            
            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

            boolean seCambio = daoProductos.modificarProductoNombre(listaBD.indexOf(new Producto("prod4")), "nuevoProd4");

            assertFalse(seCambio);
        }


    @Test
    @DisplayName("SE MODIFICA EL PRECIO DEL Producto")
    void modificarProductoPrecioValidoTest() {
        ArrayList<Producto> listaBD = new ArrayList<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.add(new Producto("prod1", 1, 5
                , ingredienteArrayList));
        listaBD.add(new Producto("prod2", 2, 2
                , ingredienteArrayList));
        listaBD.add(new Producto("prod3", 3, 10
                , ingredienteArrayList));

        BD bdProductos = new BD();
        bdProductos.listaProductos = listaBD;

        DaoProductos daoProductos = new DaoProductos(bdProductos);

        boolean seCambio = daoProductos.modificarProductoPrecio(listaBD.indexOf(new Producto("prod1")), 85);

        assertTrue(seCambio);
    }

    @Test
    @DisplayName("NO SE MODIFICA EL PRECIO del Producto")
    void moodificarProductoPrecioNoValidoTest() {
        ArrayList<Producto> listaBD = new ArrayList<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.add(new Producto("prod1", 1, 5
                , ingredienteArrayList));
        listaBD.add(new Producto("prod2", 2, 2
                , ingredienteArrayList));
        listaBD.add(new Producto("prod3", 3, 10
                , ingredienteArrayList));

        BD bdProductos = new BD();
        bdProductos.listaProductos = listaBD;

        DaoProductos daoProductos = new DaoProductos(bdProductos);

        boolean seCambio = daoProductos.modificarProductoPrecio(listaBD.indexOf(new Producto("prod4")), 85);

        assertFalse(seCambio);
    }



    @Test
    @DisplayName("SE MODIFICA EL STOCK DEL Producto")
    void modificarProductoStockValidoTest() {
        ArrayList<Producto> listaBD = new ArrayList<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.add(new Producto("prod1", 1, 5
                , ingredienteArrayList));
        listaBD.add(new Producto("prod2", 2, 2
                , ingredienteArrayList));
        listaBD.add(new Producto("prod3", 3, 10
                , ingredienteArrayList));

        BD bdProductos = new BD();
        bdProductos.listaProductos = listaBD;

        DaoProductos daoProductos = new DaoProductos(bdProductos);

        boolean seCambio = daoProductos.modificarProductoStock(listaBD.indexOf(new Producto("prod1")), 55);

        assertTrue(seCambio);
    }

    @Test
    @DisplayName("NO SE MODIFICA EL STOCK del Producto")
    void moodificarProductoStockNoValidoTest() {
        ArrayList<Producto> listaBD = new ArrayList<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.add(new Producto("prod1", 1, 5
                , ingredienteArrayList));
        listaBD.add(new Producto("prod2", 2, 2
                , ingredienteArrayList));
        listaBD.add(new Producto("prod3", 3, 10
                , ingredienteArrayList));

        BD bdProductos = new BD();
        bdProductos.listaProductos = listaBD;

        DaoProductos daoProductos = new DaoProductos(bdProductos);

        //// las validaciones de que el stock y el precio sean positivos deberian hacerse en servicios, por lo que no las compruebo en el dao
        // las validaciones de que el stock y el precio sean positivos deberian hacerse en servicios, por lo que no las compruebo en el dao
        // las validaciones de que el stock y el precio sean positivos deberian hacerse en servicios, por lo que no las compruebo en el dao
        // las validaciones de que el stock y el precio sean positivos deberian hacerse en servicios, por lo que no las compruebo en el dao
        // las validaciones de que el stock y el precio sean positivos deberian hacerse en servicios, por lo que no las compruebo en el dao
        // las validaciones de que el stock y el precio sean positivos deberian hacerse en servicios, por lo que no las compruebo en el dao
        // las validaciones de que el stock y el precio sean positivos deberian hacerse en servicios, por lo que no las compruebo en el dao
        boolean seCambio = daoProductos.modificarProductoStock(listaBD.indexOf(new Producto("prod4")), 55);

        assertFalse(seCambio);
    }



    @Test
    @DisplayName("SE MODIFICA EL Producto ENTERO")
    void modificarProductoEnteroValidoTest() {
        ArrayList<Producto> listaBD = new ArrayList<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.add(new Producto("prod1", 1, 5
                , ingredienteArrayList));
        listaBD.add(new Producto("prod2", 2, 2
                , ingredienteArrayList));
        listaBD.add(new Producto("prod3", 3, 10
                , ingredienteArrayList));

        BD bdProductos = new BD();
        bdProductos.listaProductos = listaBD;

        DaoProductos daoProductos = new DaoProductos(bdProductos);

        boolean seCambio = daoProductos.modificarProducto(listaBD.indexOf(new Producto("prod1")), new Producto("prodNuevo", 50, 65, new ArrayList<>()));

        assertTrue(seCambio);
    }

    @Test
    @DisplayName("NO SE MODIFICA EL Producto ENTERO")
    void moodificarProductoEnteroNoValidoTest() {
        ArrayList<Producto> listaBD = new ArrayList<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.add(new Producto("prod1", 1, 5
                , ingredienteArrayList));
        listaBD.add(new Producto("prod2", 2, 2
                , ingredienteArrayList));
        listaBD.add(new Producto("prod3", 3, 10
                , ingredienteArrayList));

        BD bdProductos = new BD();
        bdProductos.listaProductos = listaBD;

        DaoProductos daoProductos = new DaoProductos(bdProductos);

        boolean seCambio = daoProductos.modificarProducto(listaBD.indexOf(new Producto("prod4")), new Producto("prodNuevo", 50, 65, new ArrayList<>()));

        assertFalse(seCambio);
    }


    @Test
        @DisplayName("EXISTE EL Producto")
        void existeProductoTest() {
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            
            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

            boolean existe = daoProductos.elProductoExiste(new Producto("prod1"));

            assertTrue(existe);
        }

        @Test
        @DisplayName("NO EXISTE EL Producto")
        void noExisteProductoTest() {
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

            boolean existe = daoProductos.elProductoExiste(new Producto("prod4"));

            assertFalse(existe);
        }

        @Test
        @DisplayName("INDEX PRODUCTO VALIDO")
        void indexProductValido(){
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);
            Producto producto = new Producto("prod1");
            int index = daoProductos.obtenerIndexProducto(producto);
            assertEquals(0, index);
        }

    @Test
    @DisplayName("INDEX PRODUCTO INEXISTENTE")
    void indexProductNoValido(){
        ArrayList<Producto> listaBD = new ArrayList<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.add(new Producto("prod1", 1, 5
                , ingredienteArrayList));
        listaBD.add(new Producto("prod2", 2, 2
                , ingredienteArrayList));
        listaBD.add(new Producto("prod3", 3, 10
                , ingredienteArrayList));

        BD bdProductos = new BD();
        bdProductos.listaProductos = listaBD;

        DaoProductos daoProductos = new DaoProductos(bdProductos);
        Producto producto = new Producto("prod4");
        int index = daoProductos.obtenerIndexProducto(producto);
        assertEquals(-1, index);
    }

        @Test
        @DisplayName("LISTA Productos VALIDA")
        void listaProductsValida(){
            ArrayList<Producto> listaBD = new ArrayList<>();
            ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
            ingredienteArrayList.add(new Ingrediente("ing1"));
            ingredienteArrayList.add(new Ingrediente("ing2"));
            ingredienteArrayList.add(new Ingrediente("ing3"));


            listaBD.add(new Producto("prod1", 1, 5
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod2", 2, 2
                    , ingredienteArrayList));
            listaBD.add(new Producto("prod3", 3, 10
                    , ingredienteArrayList));

            BD bdProductos = new BD();
            bdProductos.listaProductos = listaBD;

            DaoProductos daoProductos = new DaoProductos(bdProductos);

            assertEquals(listaBD, daoProductos.devolverLista());
        }


    }

