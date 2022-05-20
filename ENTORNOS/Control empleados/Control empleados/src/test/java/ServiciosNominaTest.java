import com.ctrempleados.dao.DaoNominas;
import com.ctrempleados.dao.common.ConstantesDao;
import com.ctrempleados.dao.impl.DaoEmpleadosImpl;
import com.ctrempleados.dao.impl.DaoFranquiciasImpl;
import com.ctrempleados.dao.impl.DaoNominasImpl;
import com.ctrempleados.dao.impl.DaoRegistrosImpl;
import com.ctrempleados.domain.modelo.Nomina;
import com.ctrempleados.domain.modelo.Registro;
import com.ctrempleados.domain.servicios.impl.ServiciosEmpleadosImpl;
import com.ctrempleados.domain.servicios.impl.ServiciosNominasImpl;
import io.vavr.control.Either;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ServiciosNominaTest {


    @InjectMocks
    ServiciosNominasImpl serviciosNominas;


    @Mock
    DaoNominasImpl daoNominas;
    @Mock
    DaoRegistrosImpl daoRegistros;


    @Nested
    @DisplayName("PAGAR NOMINA")
    class pagarNomina{
        @Test
        @DisplayName("Pagar nomina valida")
        void pagarValido(){

                //given
                Nomina nominaBD = new Nomina(5, "12345678", 15, new ArrayList<>(), LocalDate.now());
                nominaBD.setPendienteDeAbonar(true);
                nominaBD.setFechaFin(LocalDate.now().plusDays(5));
                when(daoNominas.getNomina(5)).thenReturn(Either.right(nominaBD));
                when(daoRegistros.getRegistros()).thenReturn(Either.right(new ArrayList<>()));
                when(daoNominas.deleteNomina(nominaBD)).thenReturn(true);
                when(daoNominas.addNomina(nominaBD)).thenReturn(true);
                Registro miReg = new Registro("12345678", "fran1", LocalDate.now().plusDays(1).atStartOfDay().minusMinutes(50));
                miReg.setHoraSalida(LocalDate.now().plusDays(1).atStartOfDay());
                when(daoRegistros.getRegistros()).thenReturn(Either.right(List.of(miReg)));


                // when
                boolean resul = serviciosNominas.pagarNomina(nominaBD);


                //then
//                assertThat(nominaBD.getTiempoTrabajado()).isEqualTo(Duration.ofMinutes(50));
                assertThat(nominaBD.getSueldo()).isEqualTo(nominaBD.getTiempoTrabajado().toMinutes() * (nominaBD.getSueldoHora() / 60));
                assertThat(resul).isTrue();
        }

        @Test
        @DisplayName("Pagar nomina no valido - nomina no existe")
        void pagarnoValido(){

            //given
            Nomina nominaBD = new Nomina(5, "12345678", 15, new ArrayList<>(), LocalDate.now());
            when(daoNominas.getNomina(5)).thenReturn(Either.left(ConstantesDao.NO_EXISTE_LA_NOMINA_CON_ID));


            // when
            boolean resul = serviciosNominas.pagarNomina(nominaBD);


            //then
            verify(daoNominas, times(0)).addNomina(any());
            verify(daoRegistros, times(0)).getRegistros();
            verify(daoNominas, times(0)).deleteNomina(any());
            assertThat(resul).isFalse();
        }

        @Test
        @DisplayName("Pagar nomina no valido - nomina no esta pendiente de abonar")
        void pagarnoValidonoPendiente(){

            //given
            Nomina nominaBD = new Nomina(5, "12345678", 15, new ArrayList<>(), LocalDate.now());
            nominaBD.setPendienteDeAbonar(false);
            when(daoNominas.getNomina(5)).thenReturn(Either.right(nominaBD));


            // when
            boolean resul = serviciosNominas.pagarNomina(nominaBD);


            //then
            verify(daoNominas, times(0)).addNomina(any());
            verify(daoRegistros, times(0)).getRegistros();
            verify(daoNominas, times(0)).deleteNomina(any());
            assertThat(resul).isFalse();
        }

        @Test
        @DisplayName("Pagar nomina  valida (tiempo 0) - hora salida nula")
        void pagarValidohorasalidanula(){

            //given
            Nomina nominaBD = new Nomina(5, "12345678", 15, new ArrayList<>(), LocalDate.now());
            nominaBD.setPendienteDeAbonar(true);
            when(daoNominas.getNomina(5)).thenReturn(Either.right(nominaBD));
            when(daoRegistros.getRegistros()).thenReturn(Either.right(new ArrayList<>()));
            when(daoNominas.deleteNomina(nominaBD)).thenReturn(true);
            when(daoNominas.addNomina(nominaBD)).thenReturn(true);
            Registro unRegistro = new Registro("12345678", "fran1", LocalDate.now().plusDays(1).atStartOfDay().minusMinutes(50));
            unRegistro.setHoraSalida(null);

            when(daoRegistros.getRegistros()).thenReturn(Either.right(List.of(unRegistro)));

            // when
            boolean resul = serviciosNominas.pagarNomina(nominaBD);


            //then
            assertThat(nominaBD.getTiempoTrabajado()).isEqualTo(Duration.ZERO);
            assertThat(nominaBD.getSueldo()).isEqualTo(nominaBD.getTiempoTrabajado().toMinutes() * (nominaBD.getSueldoHora() / 60));
            assertThat(resul).isTrue();
        }

        @Test
        @DisplayName("Pagar nomina  valida (tiempo 0)- hora salida antes que fin de la nomina")
        void pagarValidohorasalidaAntesQueHoraFinNomina(){

            //given
            Nomina nominaBD = new Nomina(5, "12345678", 15, new ArrayList<>(), LocalDate.now());
            nominaBD.setPendienteDeAbonar(true);
            when(daoNominas.getNomina(5)).thenReturn(Either.right(nominaBD));
            when(daoRegistros.getRegistros()).thenReturn(Either.right(new ArrayList<>()));
            when(daoNominas.deleteNomina(nominaBD)).thenReturn(true);
            when(daoNominas.addNomina(nominaBD)).thenReturn(true);
            Registro unRegistro = new Registro("12345678", "fran1", LocalDate.now().plusDays(1).atStartOfDay().minusMinutes(50));
            unRegistro.setHoraSalida(LocalDate.now().plusDays(1).atStartOfDay());

            when(daoRegistros.getRegistros()).thenReturn(Either.right(List.of(unRegistro)));

            // when
            boolean resul = serviciosNominas.pagarNomina(nominaBD);


            //then
            assertThat(nominaBD.getTiempoTrabajado()).isEqualTo(Duration.ZERO);
            assertThat(nominaBD.getSueldo()).isEqualTo(nominaBD.getTiempoTrabajado().toMinutes() * (nominaBD.getSueldoHora() / 60));
            assertThat(resul).isTrue();
        }


    }


    @Nested
    @DisplayName("Add Franquicia nomina")
    class franquiciaNomina{
        // el lunes :(

    }




}