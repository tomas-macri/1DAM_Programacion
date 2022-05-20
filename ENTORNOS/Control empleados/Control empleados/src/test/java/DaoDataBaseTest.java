import com.ctrempleados.dao.Database;
import com.ctrempleados.dao.common.ConstantesDao;
import com.ctrempleados.dao.impl.DatabaseImpl;
import com.ctrempleados.domain.modelo.Empleado;
import io.vavr.control.Either;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import lombok.extern.log4j.Log4j2;
import nl.altindag.log.LogCaptor;
import nl.altindag.log.model.LogEvent;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
@ExtendWith(WeldJunit5Extension.class)
@Log4j2
public class DaoDataBaseTest {

    //clase a probar
    private DatabaseImpl database;


    private static SeContainer container;

    @BeforeAll
    static void beforeAll() {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        container = initializer.initialize();


    }

    @BeforeEach
    void setUp() {
        try {
            Files.delete(Paths.get("test/data/Empleado.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        database = container.select(DatabaseImpl.class).get();
    }

    @AfterAll
    static void afterAll() {
        container.close();
    }


    @Nested
    class readjson {
        @Test
        @DisplayName("readjsonempleados - VALIDO")
        void readjsonvalido(){

                //given
                Either<String, List<Empleado>> resultado;
                try {
                    Files.copy(Paths.get("test/data/empleadosLoadTest.json"), Paths.get("test/data/Empleado.json"));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //when
                resultado = database.readJSONEmpleados();

                //then

                assertThat(resultado.isRight()).isTrue();
                assertThat(resultado.getOrNull()).isNotNull();
                assertThat(resultado.get().size()).isEqualTo(3);
                assertThat(resultado.get().get(0).getDni()).isEqualTo("999");
                assertThat(resultado.get().get(1).getNombre()).isEqualTo("Empleado 2");
                assertThat(resultado.get().get(1).getCodigoAcceso()).isEqualTo(1001);

        }
    }

    @Test
    @DisplayName("readjsonempleados - NO VALIDO")
    void readjsonnovalido(){
        //given
        Either<String, List<Empleado>> resultado;


        //when
        resultado = database.readJSONEmpleados();

        //then
        assertThat(resultado.isLeft()).isTrue();
        assertThat(resultado.getLeft()).isEqualTo(ConstantesDao.ERROR_AL_LEER_EL_ARCHIVO_JSON_DE_EMPLEADOS);
    }
}



