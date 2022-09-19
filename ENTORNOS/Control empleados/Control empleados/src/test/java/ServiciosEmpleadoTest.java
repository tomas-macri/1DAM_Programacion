import com.ctrempleados.dao.DaoFranquicias;
import com.ctrempleados.dao.common.ConstantesDao;
import com.ctrempleados.dao.impl.DaoEmpleadosImpl;
import com.ctrempleados.dao.impl.DaoFranquiciasImpl;
import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Franquicia;
import com.ctrempleados.domain.servicios.common.ConstantesServicios;
import com.ctrempleados.domain.servicios.impl.ServiciosEmpleadosImpl;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Array;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiciosEmpleadoTest {

    @InjectMocks
    ServiciosEmpleadosImpl serviciosEmpleados;


    @Mock
    DaoEmpleadosImpl daoEmpleados;
    @Mock
    DaoFranquiciasImpl daoFranquicias;



    @Nested
    @DisplayName("CREAR EMPLEADO")
    class crearEmpleadoTest{
        @Test
        @DisplayName("CREAR EMPLEADO VALIDO")
        void crearEmpValido(){
            //given
            Empleado nuevoEm = new Empleado("nuevo888", "nomNuevo", "apeNuevo", 88,15, "fran1");
            when(daoEmpleados.getEmpleado("nuevo888")).thenReturn(Either.left(ConstantesDao.NO_EXISTE_EL_EMPLEADO_CON_DNI));
            when(daoFranquicias.getFranquicia("fran1")).thenReturn(Either.right(new Franquicia("fran1", "mi casa", 500)));
            when(daoEmpleados.getEmpleados()).thenReturn(Either.right(new ArrayList<>()));

            when(daoEmpleados.addEmpleado(nuevoEm)).thenReturn(true);
            //when
            boolean respuesta = serviciosEmpleados.crearEmpleado("nuevo888", "nomNuevo", "apeNuevo", 15,"fran1");
            //then

            assertAll(
              () -> {
                  verify(daoEmpleados).addEmpleado(any());
                  assertThat(respuesta).isTrue();
              }
            );

        }


        @Test
        @DisplayName("CREAR EMPLEADO NO VALIDO - Emp existe")
        void crearEmpNoValidoExiste(){
            //given
            Empleado nuevoEm = new Empleado("nuevo888", "nomNuevo", "apeNuevo", 88,15, "fran1");
            when(daoEmpleados.getEmpleado("nuevo888")).thenReturn(Either.right(nuevoEm));
            when(daoFranquicias.getFranquicia("fran1")).thenReturn(Either.right(new Franquicia("fran1", "mi casa", 500)));
            when(daoEmpleados.getEmpleados()).thenReturn(Either.right(new ArrayList<>()));

            //when
            boolean respuesta = serviciosEmpleados.crearEmpleado("nuevo888", "nomNuevo", "apeNuevo", 15,"fran1");
            //then

            assertAll(
                    () -> {
                        verify(daoEmpleados, times(0)).addEmpleado(any());
                        assertThat(respuesta).isFalse();
                    }
            );

        }

        @Test
        @DisplayName("CREAR EMPLEADO NO VALIDO - Franquicia no existe")
        void crearEmpNoValidoFranNoExiste(){
            //given
            Empleado nuevoEm = new Empleado("nuevo888", "nomNuevo", "apeNuevo", 88,15, "fran1");
            when(daoEmpleados.getEmpleado("nuevo888")).thenReturn(Either.left(ConstantesDao.NO_EXISTE_EL_EMPLEADO_CON_DNI));
            when(daoFranquicias.getFranquicia("fran1")).thenReturn(Either.left(ConstantesDao.NO_EXISTE_LA_FRANQUICIA));
            when(daoEmpleados.getEmpleados()).thenReturn(Either.right(new ArrayList<>()));

            //when
            boolean respuesta = serviciosEmpleados.crearEmpleado("nuevo888", "nomNuevo", "apeNuevo", 15,"fran1");
            //then

            assertAll(
                    () -> {
                        verify(daoEmpleados, times(0)).addEmpleado(any());
                        assertThat(respuesta).isFalse();
                    }
            );

        }

        @Test
        @DisplayName("CREAR EMPLEADO NO VALIDO - Sueldo bajo")
        void crearEmpNoValidoSueldoBajo(){
            //given
            Empleado nuevoEm = new Empleado("nuevo888", "nomNuevo", "apeNuevo", 88,5, "fran1");
            when(daoEmpleados.getEmpleado("nuevo888")).thenReturn(Either.left(ConstantesDao.NO_EXISTE_EL_EMPLEADO_CON_DNI));
            when(daoFranquicias.getFranquicia("fran1")).thenReturn(Either.right(new Franquicia("fran1", "mi casa", 500)));
            when(daoEmpleados.getEmpleados()).thenReturn(Either.right(new ArrayList<>()));

            //when
            boolean respuesta = serviciosEmpleados.crearEmpleado("nuevo888", "nomNuevo", "apeNuevo", 5,"fran1");
            //then

            assertAll(
                    () -> {
                        verify(daoEmpleados, times(0)).addEmpleado(any());
                        assertThat(respuesta).isFalse();
                    }
            );

        }

        @Test
        @DisplayName("CREAR EMPLEADO NO VALIDO - Sueldo alto")
        void crearEmpNoValidoSueldoAlto(){
            //given
            Empleado nuevoEm = new Empleado("nuevo888", "nomNuevo", "apeNuevo", 88,150, "fran1");
            when(daoEmpleados.getEmpleado("nuevo888")).thenReturn(Either.left(ConstantesDao.NO_EXISTE_EL_EMPLEADO_CON_DNI));
            when(daoFranquicias.getFranquicia("fran1")).thenReturn(Either.right(new Franquicia("fran1", "mi casa", 500)));
            when(daoEmpleados.getEmpleados()).thenReturn(Either.right(new ArrayList<>()));

            //when
            boolean respuesta = serviciosEmpleados.crearEmpleado("nuevo888", "nomNuevo", "apeNuevo", 150,"fran1");
            //then

            assertAll(
                    () -> {
                        verify(daoEmpleados, times(0)).addEmpleado(any());
                        assertThat(respuesta).isFalse();
                    }
            );

        }

        @Test
        @DisplayName("CREAR EMPLEADO NO VALIDO - Nombre no valido")
        void crearEmpNoValidoNombreNoValido(){
            //given
            Empleado nuevoEm = new Empleado("nuevo888", "", "apeNuevo", 88,15, "fran1");
            when(daoEmpleados.getEmpleado("nuevo888")).thenReturn(Either.left(ConstantesDao.NO_EXISTE_EL_EMPLEADO_CON_DNI));
            when(daoFranquicias.getFranquicia("fran1")).thenReturn(Either.right(new Franquicia("fran1", "mi casa", 500)));
            when(daoEmpleados.getEmpleados()).thenReturn(Either.right(new ArrayList<>()));

            //when
            boolean respuesta = serviciosEmpleados.crearEmpleado("nuevo888", "", "apeNuevo", 15,"fran1");
            //then

            assertAll(
                    () -> {
                        verify(daoEmpleados, times(0)).addEmpleado(any());
                        assertThat(respuesta).isFalse();
                    }
            );

        }

        @DisplayName("CREAR EMPLEADO NO VALIDO - Apellido no valido")
        void crearEmpNoValidoApeNoValido(){
            //given
            Empleado nuevoEm = new Empleado("nuevo888", "nomNuevo", "", 88,15, "fran1");
            when(daoEmpleados.getEmpleado("nuevo888")).thenReturn(Either.left(ConstantesDao.NO_EXISTE_EL_EMPLEADO_CON_DNI));
            when(daoFranquicias.getFranquicia("fran1")).thenReturn(Either.right(new Franquicia("fran1", "mi casa", 500)));
            when(daoEmpleados.getEmpleados()).thenReturn(Either.right(new ArrayList<>()));

            //when
            boolean respuesta = serviciosEmpleados.crearEmpleado("nuevo888", "nomNuevo", "", 15,"fran1");
            //then

            assertAll(
                    () -> {
                        verify(daoEmpleados, times(0)).addEmpleado(any());
                        assertThat(respuesta).isFalse();
                    }
            );

        }
        @DisplayName("CREAR EMPLEADO NO VALIDO - DNI no valido")
        void crearEmpNoValidoDNINoValido(){
            //given
            Empleado nuevoEm = new Empleado("nuevo", "nomNuevo", "apeNuevo", 88,15, "fran1");
            when(daoEmpleados.getEmpleado("nuevo888")).thenReturn(Either.left(ConstantesDao.NO_EXISTE_EL_EMPLEADO_CON_DNI));
            when(daoFranquicias.getFranquicia("fran1")).thenReturn(Either.right(new Franquicia("fran1", "mi casa", 500)));
            when(daoEmpleados.getEmpleados()).thenReturn(Either.right(new ArrayList<>()));

            //when
            boolean respuesta = serviciosEmpleados.crearEmpleado("nuevo", "nomNuevo", "apeNuevo", 15,"fran1");
            //then

            assertAll(
                    () -> {
                        verify(daoEmpleados, times(0)).addEmpleado(any());
                        assertThat(respuesta).isFalse();
                    }
            );

        }

    }

}
