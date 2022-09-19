package servicios;

import dao.impl.DaoUsuariosImpl;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import servicios.impl.ServiciosUsuariosImpl;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
class ServiciosUsuariosImplTest {
    // a testear
    @InjectMocks
    ServiciosUsuariosImpl serviciosClientes;

    @Mock
    DaoUsuariosImpl daoClientes;


    @Nested
    @DisplayName("AGREGAR CLIENTE")
    class AgregarCliente {
        @Test
        @DisplayName("Agregar exitosamente un cliente")
        void addCliente() {
            //given
            UsuarioNormal c = new UsuarioNormal("dni", "nombre", new ArrayList<>());


            //when
            when(daoClientes.getUsuario("dni")).thenReturn(null);
            when(daoClientes.agregarUsuario(c)).thenReturn(true);

            boolean respuesta = serviciosClientes.agregarUsuario(c);

            //then
            assertThat(respuesta).isTrue();
        }

        @Test
        @DisplayName("El cliente está en la BD")
        void addClienteNoValido() {
            //given
            UsuarioNormal c = new UsuarioNormal("dni", "nombre", new ArrayList<>());


            //when
            when(daoClientes.getUsuario("dni")).thenReturn(c);
            boolean respuesta = serviciosClientes.agregarUsuario(c);

            //then
            assertFalse(respuesta);
        }


        @Test
        @DisplayName("Nombre no valido")
        void addClienteNoValidoNombre() {
            //given
            UsuarioNormal c = new UsuarioNormal("125", "", new ArrayList<>());

            //when
            boolean respuesta = serviciosClientes.agregarUsuario(c);

            //then
            assertThat(respuesta).isFalse();
            assertAll(
                    () -> {
                        verify(daoClientes, times(0)).agregarUsuario(any());
                    }
            );
        }

        @Test
        @DisplayName("DNI no valido")
        void addClienteNoValidoDNI() {
            //given
            UsuarioNormal c = new UsuarioNormal("", "pedro", new ArrayList<>());
            //when(daoClientes.agregarUsuario(any())).thenReturn(true);


            //when
            boolean respuesta = serviciosClientes.agregarUsuario(c);

            //then
            assertThat(respuesta).isFalse();
        }
    }

    @Nested
    @DisplayName("UPDATE CLIENTE")
    class BuscarCliente {
        @Test
        @DisplayName("Se actualiza el user")
        void updateCliente() {
            //given
            Usuario nuevo = new UsuarioNormal("dni", "nombre", new ArrayList<>());

            //when
            when(daoClientes.getUsuario("1")).thenReturn(new UsuarioNormal("1", "n", new ArrayList<>()));
            serviciosClientes.modificarUsuario(nuevo, "1");

            //then
            verify(daoClientes, times(1)).eliminarUsuario("1");
            verify(daoClientes, times(1)).agregarUsuario(nuevo);
        }

        @Test
        @DisplayName("No se actualiza porque no se encuentra el user a reemplazar")
        void updateClienteNoValidoUserNull() {
            //given
            Usuario nuevo = new UsuarioNormal("dni", "nombre", new ArrayList<>());

            //when
            when(daoClientes.getUsuario("1")).thenReturn(null);
            serviciosClientes.modificarUsuario(nuevo, "1");

            //then
            verify(daoClientes, times(1)).getUsuario(any());
            verify(daoClientes, times(0)).eliminarUsuario(any());
            verify(daoClientes, times(0)).agregarUsuario(any());
        }

        @Test
        @DisplayName("No se actualiza porque el nuevo nombre es '' ")
        void updateClienteNombreNoValido() {
            //given
            Usuario nuevo = new UsuarioNormal("dni", "", new ArrayList<>());

            //when
            when(daoClientes.getUsuario("1")).thenReturn(new UsuarioNormal("1", "n", new ArrayList<>()));
            serviciosClientes.modificarUsuario(nuevo, "1");

            //then
            verify(daoClientes, times(1)).getUsuario("1");
            verify(daoClientes, times(0)).eliminarUsuario(any());
            verify(daoClientes, times(0)).agregarUsuario(any());
        }

        @Test
        @DisplayName("No se actualiza porque el nuevo dni es '' ")
        void updateClienteDNINoValido() {
            //given
            Usuario nuevo = new UsuarioNormal("", "nombreeeee", new ArrayList<>());

            //when
            when(daoClientes.getUsuario("1")).thenReturn(new UsuarioNormal("1", "n", new ArrayList<>()));
            serviciosClientes.modificarUsuario(nuevo, "1");

            //then
            assertAll(
                    () -> {
                        verify(daoClientes, times(1)).getUsuario("1");
                        verify(daoClientes, times(0)).eliminarUsuario(any());
                        verify(daoClientes, times(0)).agregarUsuario(any());
                    }
            );

        }
    }

    @Nested
    @DisplayName("GET CLIENTE")
    class GetCliente {
        @Test
        @DisplayName("Se obtiene el cliente")
        void getClientes() {
            //given
            Usuario nuevo = new UsuarioNormal("dni", "nombre", new ArrayList<>());

            //when
            when(daoClientes.getUsuario(nuevo.getDni())).thenReturn(nuevo);
            Usuario respuesta = serviciosClientes.getUsuario(nuevo.getDni());

            //then
            assertEquals(nuevo, respuesta);
        }
    }

    @Nested
    @DisplayName("ELIMINAR CLIENTE")
    class EliminarCliente {
        @Test
        @DisplayName("Se elimina el cliente")
        void eliminarCliente() {
            //given
            Usuario nuevo = new UsuarioNormal("dni", "nombre", new ArrayList<>());


            //when
            when(daoClientes.getUsuario(nuevo.getDni())).thenReturn(nuevo);
            when(daoClientes.eliminarUsuario(nuevo.getDni())).thenReturn(nuevo);
            Usuario respuesta = serviciosClientes.eliminarUsuario(nuevo.getDni());

            //then
            verify(daoClientes, times(1)).eliminarUsuario(nuevo.getDni());
            assertEquals(nuevo, respuesta);
        }

        @Test
        @DisplayName("El cliente a eliminar no se encuentra en la bd")
        void eliminarClienteNoValido() {
            //given
            Usuario nuevo = new UsuarioNormal("dni", "nombre", new ArrayList<>());


            //when
            when(daoClientes.getUsuario(nuevo.getDni())).thenReturn(null);
            Usuario respuesta = serviciosClientes.eliminarUsuario(nuevo.getDni());

            //then
            verify(daoClientes, times(0)).eliminarUsuario(nuevo.getDni());
            assertNull(respuesta);
        }

        @Test
        @DisplayName("Cliente a eliminar == null")
        void eliminarClienteNullNoValido() {
            //given
            Usuario nuevo;

            //when
            nuevo = serviciosClientes.eliminarUsuario(null);

            //then
            verify(daoClientes, times(0)).eliminarUsuario(any());
            assertNull(nuevo);
        }
    }

    @Test
    @DisplayName("EL USUARIO EXISTE")
    void buscarCliente() {
        //given
        Usuario nuevo = new UsuarioNormal("dni", "nombre", new ArrayList<>());

        //when
        when(daoClientes.getUsuario(nuevo.getDni())).thenReturn(nuevo);
        boolean respuesta = serviciosClientes.elUsuarioExiste(nuevo.getDni());
        //then
        assertTrue(respuesta);
    }

    @Test
    @DisplayName("GET LISTA USUARIOS")
    void getListaUsuarios() {
        //given
        Usuario nuevo = new UsuarioNormal("dni", "nombre", new ArrayList<>());
        Usuario nuevo2 = new UsuarioNormal("dni2", "nombre2", new ArrayList<>());
        List<Usuario> lista = new ArrayList<>();
        lista.add(nuevo);
        //when
        when(daoClientes.devolverLista()).thenReturn(lista);
        List<Usuario> respuesta = serviciosClientes.getLista();

        //then
        assertEquals(lista, respuesta);
    }
}