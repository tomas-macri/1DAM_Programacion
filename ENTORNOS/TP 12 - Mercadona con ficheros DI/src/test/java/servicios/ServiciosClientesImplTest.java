package servicios;

import dao.impl.DaoUsuariosImpl;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.junit.jupiter.api.DisplayName;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
class ServiciosClientesImplTest {
    // a testear
    @InjectMocks
    ServiciosUsuariosImpl serviciosClientes;

    @Mock
    DaoUsuariosImpl daoClientes;


    @Test
    @DisplayName("Agregar exitosamente un cliente")
    void addCliente() {
        //given
        UsuarioNormal c = new UsuarioNormal("dni", "nombre", new ArrayList<>());
        when(daoClientes.agregarUsuario(c)).thenReturn(true);


        //when
        boolean respuesta = serviciosClientes.agregarUsuario(c);

        //then
        assertThat(respuesta).isTrue();
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


    @Test
    void updateCliente() {
        UsuarioNormal nuevo = new UsuarioNormal("dni", "nombre", new ArrayList<>());



    }

    @Test
    void getClientes() {
    }

    @Test
    void eliminarCliente() {
    }

    @Test
    void buscarCliente() {
    }
}