package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import config.Configuracion;
import dao.impl.DataBase;
import di.GsonProducer;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Link;
import lombok.extern.log4j.Log4j2;

import modelo.ProductoComprado;
import modelo.Productos.Producto;
import modelo.Productos.ProductoCaducable;
import modelo.Productos.ProductoNormal;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioEspecial;
import modelo.Usuarios.UsuarioNormal;
import nl.altindag.log.LogCaptor;
import nl.altindag.log.model.LogEvent;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ExtendWith(WeldJunit5Extension.class)
@Log4j2
class DataBaseTest {


    //clase a probar
    private DataBase database;


    private static SeContainer container;

    @BeforeAll
    static void beforeAll() {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        container = initializer.initialize();


    }

    @BeforeEach
    void setUp() {
        try {
            Files.delete(Paths.get("test/data/Usuario.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.delete(Paths.get("test/data/Producto.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        database = container.select(DataBase.class).get();
    }

    @AfterAll
    static void afterAll() {
        container.close();
    }


    @Test
    @DisplayName("LOAD USUARIOS NORMALES Y ESPECIALES")
    void loadUsuarios() {
        //given
        LinkedHashMap<String, Usuario> resultado;
        try {
            Files.copy(Paths.get("test/data/usuarioLoadTest.json"), Paths.get("test/data/Usuario.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //when
        resultado = database.loadUsuarios();

        //then
        assertThat(resultado).hasSize(3);
        assertThat(resultado.get("u1")).isInstanceOf(UsuarioNormal.class);
        assertThat(resultado.get("e1")).isInstanceOf(UsuarioEspecial.class);
        assertThat(resultado.get("u1").getComprasPrevias().get(0).get(0).getProducto()).isInstanceOf(ProductoCaducable.class);
        assertThat(resultado.get("u1").getComprasPrevias().get(0).get(1).getProducto()).isInstanceOf(ProductoNormal.class);
    }


    @Test
    @DisplayName("LOAD USUARIOS FICHERO EXISTE")
    void loadUsuariosFicheroNoExiste() {
        //given
        LinkedHashMap<String, Usuario> resultado;
        LogCaptor logCaptor = LogCaptor.forClass(DataBase.class);

        //when
        resultado = database.loadUsuarios();

        //then
        List<LogEvent> logEvents = logCaptor.getLogEvents();
        assertThat(logEvents).hasSize(1);
        LogEvent logEvent = logEvents.get(0);
        assertThat(logEvent.getLevel()).isEqualTo("ERROR");
        assertThat(logEvent.getThrowable()).isPresent();

        assertThat(resultado).isEmpty();
    }


    @Nested
    @DisplayName("SAVE USUARIOS")
    class SaveUsuarios {
        @Test
        @DisplayName("SAVE USUARIO NORMAL")
        void saveUsuarios() {
            try {
                Files.copy(Paths.get("test/data/usuarioLoadTest.json"), Paths.get("test/data/Usuario.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //given
            LinkedHashMap<String, Usuario> usuarios = new LinkedHashMap<>();
            usuarios.put("123", new UsuarioNormal("123", "Juan", new ArrayList<>()));

            LinkedHashMap<String, Usuario> resultado = new LinkedHashMap<>();

            //when
            boolean retorno = database.saveUsuarios(usuarios);

            //then
            GsonProducer dp = new GsonProducer();


            Gson gson = dp.getGson();
            Type userListType = new TypeToken<LinkedHashMap<String, Usuario>>() {
            }.getType();


            try (FileReader r = new FileReader("test/data/Usuario.json")) {
                resultado = gson.fromJson(
                        r,
                        userListType);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            //        assertThat(new File("test/data/Usuario.json"))
            //                .hasContent("[{\"dni\":\"123\",\"nombre\":\"Juan\"}]");
            assertThat(resultado).hasSize(1);
            assertThat(resultado.get("123").getNombre()).isEqualTo("Juan");
            assertThat(retorno).isTrue();
            //assertThat(resultado).containsEntry("", new UsuarioNormal("e1", "especial", new ArrayList<>()));
        }

        @Test
        @DisplayName("SAVE USUARIO ESPECIAL CON PRODUCTOS CADUCABLES COMPRADOS")
        void saveUsuarioEspecial() {
            try {
                Files.copy(Paths.get("test/data/usuarioLoadTest.json"), Paths.get("test/data/Usuario.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //given
            LinkedHashMap<String, Usuario> usuarios = new LinkedHashMap<>();
            Usuario userEspecial = new UsuarioEspecial("123", "Juan", new ArrayList<>(), 30);
            List<ProductoComprado> productos = new ArrayList<>();
            productos.add(new ProductoComprado(new ProductoCaducable("pcad1", 150, 10, new ArrayList<>(), LocalDateTime.now().plusDays(10)), 5));
            productos.add(new ProductoComprado(new ProductoNormal("p2", 150, 10, new ArrayList<>()), 5));
            userEspecial.getComprasPrevias().add(productos);
            usuarios.put("123", userEspecial);
            LinkedHashMap<String, Usuario> resultado = new LinkedHashMap<>();

            //when
            boolean retorno = database.saveUsuarios(usuarios);

            //then
            GsonProducer dp = new GsonProducer();


            Gson gson = dp.getGson();
            Type userListType = new TypeToken<LinkedHashMap<String, Usuario>>() {
            }.getType();


            try (FileReader r = new FileReader("test/data/Usuario.json")) {
                resultado = gson.fromJson(
                        r,
                        userListType);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            //        assertThat(new File("test/data/Usuario.json"))
            //                .hasContent("[{\"dni\":\"123\",\"nombre\":\"Juan\"}]");
            assertThat(resultado).hasSize(1);
            assertThat(resultado.get("123").getNombre()).isEqualTo("Juan");
            assertThat(resultado.get("123").getComprasPrevias().get(0).get(0).getProducto()).isInstanceOf(ProductoCaducable.class);
            assertThat(resultado.get("123")).isInstanceOf(UsuarioEspecial.class);
            assertThat(retorno).isTrue();
            //assertThat(resultado).containsEntry("", new UsuarioNormal("e1", "especial", new ArrayList<>()));
        }
    }


    // PRODUCTOS

    @Test
    void loadProductosFicheroNoExiste() {
        //given
        List<Producto> resultado;
        LogCaptor logCaptor = LogCaptor.forClass(DataBase.class);

        //when
        resultado = database.loadProductos();

        //then
        List<LogEvent> logEvents = logCaptor.getLogEvents();
        assertThat(logEvents).hasSize(1);
        LogEvent logEvent = logEvents.get(0);
        assertThat(logEvent.getLevel()).isEqualTo("ERROR");
        assertThat(logEvent.getThrowable()).isPresent();

        assertThat(resultado).isEmpty();
    }


    @Test
    @DisplayName("LOAD PRODUCTOS NORMALES Y CADUCABLES")
    void loadProductos() {
        //given
        List<Producto> resultado;
        try {
            Files.copy(Paths.get("test/data/productoLoadTest.json"), Paths.get("test/data/Producto.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //when
        resultado = database.loadProductos();

        //then
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0)).isInstanceOf(ProductoCaducable.class);
        assertThat(resultado.get(1)).isInstanceOf(ProductoNormal.class);
        assertThat(resultado.get(0).getNombre()).isEqualTo("p1");
        assertThat(resultado.get(1).getNombre()).isEqualTo("p2");
    }


    @Nested
    @DisplayName("SAVE PRODUCTOS")
    class SaveProductos {
        @Test
        @DisplayName("SAVE PRODUCTO NORMAL")
        void saveProductos() {
            try {
                Files.copy(Paths.get("test/data/usuarioLoadTest.json"), Paths.get("test/data/Usuario.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //given
            List<Producto> productos = new ArrayList<>();
            productos.add(new ProductoNormal("p1", 15, 100, new ArrayList<>()));
            productos.add(new ProductoNormal("p2", 2, 10, new ArrayList<>()));

            List<Producto> resultado = new ArrayList<>();

            //when
            boolean retorno = database.saveProductos(productos);

            //then
            GsonProducer dp = new GsonProducer();


            Gson gson = dp.getGson();
            Type productListType = new TypeToken<List<Producto>>() {
            }.getType();


            try (FileReader r = new FileReader("test/data/Producto.json")) {
                resultado = gson.fromJson(
                        r,
                        productListType);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            //        assertThat(new File("test/data/Usuario.json"))
            //                .hasContent("[{\"dni\":\"123\",\"nombre\":\"Juan\"}]");
            assertThat(resultado).hasSize(2);
            assertThat(resultado.get(0).getNombre()).isEqualTo("p1");
            assertThat(resultado.get(1).getNombre()).isEqualTo("p2");
            assertThat(retorno).isTrue();
            //assertThat(resultado).containsEntry("", new UsuarioNormal("e1", "especial", new ArrayList<>()));
        }

        @Test
        @DisplayName("SAVE PRODUCTOS CADUCABLES")
        void saveProductoEspecial() {
            try {
                Files.copy(Paths.get("test/data/usuarioLoadTest.json"), Paths.get("test/data/Usuario.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //given
            List<Producto> productos = new ArrayList<>();
            productos.add(new ProductoCaducable("p1", 15, 100, new ArrayList<>(), LocalDateTime.now().plusDays(5)));
            productos.add(new ProductoCaducable("p2", 2, 10, new ArrayList<>(), LocalDateTime.now().plusMinutes(5555)));

            List<Producto> resultado = new ArrayList<>();

            //when
            boolean retorno = database.saveProductos(productos);

            //then
            GsonProducer dp = new GsonProducer();


            Gson gson = dp.getGson();
            Type productListType = new TypeToken<List<Producto>>() {
            }.getType();


            try (FileReader r = new FileReader("test/data/Producto.json")) {
                resultado = gson.fromJson(
                        r,
                        productListType);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            //        assertThat(new File("test/data/Usuario.json"))
            //                .hasContent("[{\"dni\":\"123\",\"nombre\":\"Juan\"}]");
            assertThat(resultado).hasSize(2);
            assertThat(resultado.get(0).getNombre()).isEqualTo("p1");
            assertThat(resultado.get(0)).isInstanceOf(ProductoCaducable.class);
            assertThat(resultado.get(1).getNombre()).isEqualTo("p2");
            assertThat(resultado.get(1)).isInstanceOf(ProductoCaducable.class);
            assertThat(retorno).isTrue();
            //assertThat(resultado).containsEntry("", new UsuarioNormal("e1", "especial", new ArrayList<>()));
        }
    }
}


