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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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

        database = container.select(DataBase.class).get();
    }

    @AfterAll
    static void afterAll() {
        container.close();
    }

    @Test
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


    @Test
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
    @Disabled
    void saveUsuariosMalFichero()  {
        //given
        LinkedHashMap<String, Usuario> usuarios = new LinkedHashMap<>();
        usuarios.put("123", new UsuarioNormal("123", "Juan", new ArrayList<>()));
//        try {
//            Files.delete(Paths.get("test/data/Usuario.json"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //when
        boolean retorno = database.saveUsuarios(usuarios);

        //then
        assertAll(() ->assertThat(new File("test/data/Usuario.json")).doesNotExist(),
                () -> assertThat(retorno).isFalse());
    }
    @Test
    @Disabled
    void saveUsuarios() {
        //given
        LinkedHashMap<String, Usuario> usuarios = new LinkedHashMap<>();
        usuarios.put("123", new UsuarioNormal("123", "Juan", new ArrayList<>()));
        usuarios.put("456", new UsuarioEspecial("456", "Juan", new ArrayList<>(), 30));
        LinkedHashMap<String, Usuario> resultado = new LinkedHashMap<>();

        //when
        boolean retorno = database.saveUsuarios(usuarios);

        //then
        GsonProducer dp = new GsonProducer();


        Gson gson = dp.getGson();
        Type userListType = new TypeToken<ArrayList<Usuario>>() {
        }.getType();


        try(FileReader r = new FileReader("test/data/Usuario.json")) {
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
        assertThat(resultado).containsEntry("123", new UsuarioNormal("123", "Juan", new ArrayList<>()));
    }
}

