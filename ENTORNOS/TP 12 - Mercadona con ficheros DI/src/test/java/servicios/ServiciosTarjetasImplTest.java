package servicios;

import dao.impl.DaoTarjetasImpl;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import servicios.impl.ServiciosTarjetasImpl;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
public class ServiciosTarjetasImplTest {
    @InjectMocks
    private ServiciosTarjetasImpl serviciosTarjetas;

    @Mock
    private DaoTarjetasImpl daoTarjetas;


    @Nested
    @DisplayName("AGREGAR TARJETA")
    class AgregarTarjeta {
        @Test
        @DisplayName("AGREGAR TARJETA VALIDO")
        void agregarTarjetaValido() {
            // given
            Usuario usuario = new UsuarioNormal("1", "Juan", new ArrayList<>());
            Tarjeta tarjeta = new Tarjeta("t1", 1500);

            // when
            when(daoTarjetas.laTarjetaExiste(tarjeta.getNombre(), usuario)).thenReturn(false);
            boolean seAgrego = serviciosTarjetas.agregarTarjeta(tarjeta, usuario);

            // then
            verify(daoTarjetas).laTarjetaExiste(tarjeta.getNombre(), usuario);
            verify(daoTarjetas).agregarTarjeta(tarjeta, usuario);
            assertTrue(seAgrego);
        }

        @Test
        @DisplayName("No se agrega tarjeta - ya existe")
        void agregarTarjetaExistenteNoValido() {
            // given
            Usuario usuario = new UsuarioNormal("1", "Juan", new ArrayList<>());
            Tarjeta tarjeta = new Tarjeta("t1", 1500);

            // when
            when(daoTarjetas.laTarjetaExiste(tarjeta.getNombre(), usuario)).thenReturn(true);
            boolean seAgrego = serviciosTarjetas.agregarTarjeta(tarjeta, usuario);

            // then
            verify(daoTarjetas).laTarjetaExiste(tarjeta.getNombre(), usuario);
            verify(daoTarjetas, times(0)).agregarTarjeta(tarjeta, usuario);
            assertFalse(seAgrego);
        }

        @Test
        @DisplayName("No se agrega tarjeta - nombre vacio")
        void agregarTarjetaNombreNoValido() {
            // given
            Usuario usuario = new UsuarioNormal("1", "Juan", new ArrayList<>());
            Tarjeta tarjeta = new Tarjeta("", 1500);

            // when
            when(daoTarjetas.laTarjetaExiste(tarjeta.getNombre(), usuario)).thenReturn(false);
            boolean seAgrego = serviciosTarjetas.agregarTarjeta(tarjeta, usuario);

            // then
            verify(daoTarjetas).laTarjetaExiste(tarjeta.getNombre(), usuario);
            verify(daoTarjetas, times(0)).agregarTarjeta(tarjeta, usuario);
            assertFalse(seAgrego);
        }

        @Test
        @DisplayName("No se agrega tarjeta - saldo negativo")
        void agregarTarjetaSaldoNoValido() {
            // given
            Usuario usuario = new UsuarioNormal("1", "Juan", new ArrayList<>());
            Tarjeta tarjeta = new Tarjeta("t1", -1);

            // when
            when(daoTarjetas.laTarjetaExiste(tarjeta.getNombre(), usuario)).thenReturn(false);
            boolean seAgrego = serviciosTarjetas.agregarTarjeta(tarjeta, usuario);

            // then
            verify(daoTarjetas).laTarjetaExiste(tarjeta.getNombre(), usuario);
            verify(daoTarjetas, times(0)).agregarTarjeta(tarjeta, usuario);
            assertFalse(seAgrego);
        }
    }

    @Test
    @DisplayName("LA TARJETA EXISTE")
    void laTarjetaExiste() {
        // given
        Usuario usuario = new UsuarioNormal("1", "Juan", new ArrayList<>());
        Tarjeta tarjeta = new Tarjeta("t1", 1500);

        // when
        when(daoTarjetas.laTarjetaExiste(tarjeta.getNombre(), usuario)).thenReturn(true);
        boolean seEjecuto = serviciosTarjetas.laTarjetaExiste(tarjeta.getNombre(), usuario);

        // then
        verify(daoTarjetas).laTarjetaExiste(tarjeta.getNombre(), usuario);
        assertTrue(seEjecuto);
    }


    @Test
    @DisplayName("GET LISTA DE TARJETAS")
    void listaTarjetasValida() {
        // given
        Usuario usuario = new UsuarioNormal("1", "Juan", new ArrayList<>());
        List<Tarjeta> tarjetas = new ArrayList<>();
        Tarjeta tarjeta = new Tarjeta("t1", 1500);
        Tarjeta tarjeta2 = new Tarjeta("t2", 1500);
        tarjetas.add(tarjeta);
        tarjetas.add(tarjeta2);

        // when
        when(daoTarjetas.devolverLista(usuario)).thenReturn(tarjetas);
        List<Tarjeta> resultado  = serviciosTarjetas.devolverLista(usuario);

        // then
        verify(daoTarjetas).devolverLista(usuario);
        assertEquals(tarjetas, resultado);
    }


    @Test
    @DisplayName("GET TARJETA")
    void getTarjeta() {
        // given
        Usuario usuario = new UsuarioNormal("1", "Juan", new ArrayList<>());
        Tarjeta tarjeta = new Tarjeta("ok", 100);

        // when
        when(daoTarjetas.getTarjeta(tarjeta.getNombre(), usuario)).thenReturn(new Tarjeta("ok", 100));
        Tarjeta resultado = serviciosTarjetas.getTarjeta(tarjeta.getNombre(), usuario);

        // then
        verify(daoTarjetas).getTarjeta(tarjeta.getNombre(), usuario);
        assertEquals(tarjeta, resultado);
    }
}


