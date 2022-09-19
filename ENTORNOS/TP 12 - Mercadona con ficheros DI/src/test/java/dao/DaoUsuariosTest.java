package dao;

import dao.impl.DaoUsuariosImpl;
import dao.impl.DataBase;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
class DaoUsuariosTest {

    @InjectMocks
    DaoUsuariosImpl daoUsuarios;

    @Mock
    DataBase bd;

    @Captor
    ArgumentCaptor<LinkedHashMap<String, Usuario>> captor;

    @Nested
    @DisplayName("Agregar Usuario")
    class AgregarUsuarioTest {
        @Test
        @DisplayName("SE AGREGO CORRECTAMENTE")
        void addUsuarioValidoTest() {

            //Given
            Usuario nuevoUser = new UsuarioNormal("456", "user4", new ArrayList<>());

            //When

            when(bd.loadUsuarios()).thenReturn(new LinkedHashMap<>());
            /*doAnswer(invocation -> {
                map.putAll(invocation.getArgument(0));
                return null;
            }).when(bd).saveUsuarios(map);*/


            boolean seAgrego = daoUsuarios.agregarUsuario(nuevoUser);

            //Then
            assertAll(//() -> Assertions.assertThat(map).containsEntry("dni", nuevoUser),
                    () -> assertTrue(seAgrego),
                    () -> {
                        verify(bd).saveUsuarios(captor.capture());
                        LinkedHashMap<String, Usuario> clientes = captor.getValue();
                        Assertions.assertThat(clientes).containsEntry("456", nuevoUser);
                    }

            );

        }

        @Test
        @DisplayName("NO SE AGREGA USUARIO PORQUE YA EXISTE")
        void addUsuarioNoValidoTest() {

            //Given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When

            when(bd.loadUsuarios()).thenReturn(base);

            Usuario nuevoUser = new UsuarioNormal("123", "user4", new ArrayList<>());
            boolean seAgrego = daoUsuarios.agregarUsuario(nuevoUser);

            //Then
            assertAll(() -> verify(bd, times(0)).saveUsuarios(base),
                    () -> assertFalse(seAgrego)

            );
        }
    }

    @Nested
    @DisplayName("ELIMINAR USUARIOS")
    class eliminarUsuarios {
        @Test
        @DisplayName("SE ELIMINA USER CORRECTAMENTE")
        void deleteUsuarioValidoTest() {

            //Given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When

            when(bd.loadUsuarios()).thenReturn(base);

            Usuario eliminado = daoUsuarios.eliminarUsuario("123");

            //Then
            assertAll(() -> verify(bd).saveUsuarios(base),
                    () -> assertEquals(baseCliente, eliminado),
                    () -> {
                        verify(bd).saveUsuarios(captor.capture());
                        LinkedHashMap<String, Usuario> clientes = captor.getValue();
                        Assertions.assertThat(clientes).doesNotContainKey("123");
                    }

            );
        }

        @Test
        @DisplayName("NO SE EXISTE USER A ELIMINAR")
        void deleteUsuarioNoValidoTest() {

            //Given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When

            when(bd.loadUsuarios()).thenReturn(base);

            Usuario eliminado = daoUsuarios.getUsuario("456");

            //Then
            assertAll(
                        () -> assertTrue(base.containsValue(baseCliente)),
                        () -> assertNull(eliminado)

            );
        }
    }

    @Nested
    @DisplayName("DEVOLVER USUARIO")
    class devolverUser {
        @Test
        @DisplayName("SE DEVUELVE EL USUARIO")
        void getUsuarioValidoTest() {
            //Given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When

            when(bd.loadUsuarios()).thenReturn(base);

            Usuario eliminado = daoUsuarios.getUsuario("123");

            //Then
            assertEquals(baseCliente, eliminado);
        }

        @Test
        @DisplayName("NO EXISTE EL USUARIO A DEVOLVER")
        void getUsuarioNoValidoTest() {
            //Given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When

            when(bd.loadUsuarios()).thenReturn(base);

            Usuario eliminado = daoUsuarios.getUsuario("456");

            //Then
            assertNull(eliminado);
        }
    }
    @Nested
    @DisplayName("MODIFICAR USUARIO")
    class modificarUser {
        @Test
        @DisplayName("SE MODIFICA EL USUARIO")
        void modificarUsuarioValidoTest() {
            //Given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When
            when(bd.loadUsuarios()).thenReturn(base);
            UsuarioNormal modificado = new UsuarioNormal("123", "pedro", new ArrayList<>());

            boolean seCambia = daoUsuarios.modificarUsuarioNombre("123", baseCliente, modificado);

            //Then
            assertAll(
                    () -> assertTrue(seCambia),
                    () -> {
                        verify(bd).saveUsuarios(captor.capture());
                        LinkedHashMap<String, Usuario> clientes = captor.getValue();
                        Assertions.assertThat(clientes).containsValue(modificado);
                    }
            );

        }

        @Test
        @DisplayName("NO SE MODIFICA EL USUARIO")
        void modificarUsuarioNoValidoTest() {
            //Given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When
            when(bd.loadUsuarios()).thenReturn(base);
            UsuarioNormal modificado = new UsuarioNormal("555", "pedro", new ArrayList<>());

            boolean seCambia = daoUsuarios.modificarUsuarioNombre("555", baseCliente, modificado);

            //Then
            assertAll(
                    () -> assertFalse(seCambia),
                    () -> {
                        verify(bd).saveUsuarios(captor.capture());
                        LinkedHashMap<String, Usuario> clientes = captor.getValue();
                        Assertions.assertThat(clientes).containsValue(baseCliente);
                        Assertions.assertThat(clientes).doesNotContainValue(modificado);
                    }
            );

        }
    }

    @Nested
    @DisplayName("EXISTE USUARIO")
    class existeUser {
        @Test
        @DisplayName("EXISTE EL USUARIO")
        void existeUsuarioTest() {
            //Given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            when(bd.loadUsuarios()).thenReturn(base);

            //When
            Usuario respuesta = daoUsuarios.getUsuario("123");


            //Then
            assertAll(
                    () -> Assertions.assertThat(respuesta.getDni()).isEqualTo(baseCliente.getDni()),
                    () -> Assertions.assertThat(respuesta.getNombre()).isEqualTo(baseCliente.getNombre())
            );

        }

        @Test
        @DisplayName("NO EXISTE EL USUARIO")
        void noExisteUsuarioTest() {
            //Given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            when(bd.loadUsuarios()).thenReturn(base);

            //When
            Usuario respuesta = daoUsuarios.getUsuario("456");


            //Then
            assertNull(respuesta);

        }
    }

    @Test
    @DisplayName("LISTA USUARIOS VALIDA")
    void listaUsersValida(){
            //Given
            UsuarioNormal baseCliente = new UsuarioNormal("123","juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            when(bd.loadUsuarios()).thenReturn(base);

            //When
            List<Usuario> respuesta = daoUsuarios.devolverLista();


            //Then
            assertAll(
                    () -> Assertions.assertThat(respuesta.size()).isEqualTo(base.values().size()),
                    () -> Assertions.assertThat(base.values().containsAll(respuesta))
            );

        }
    }



