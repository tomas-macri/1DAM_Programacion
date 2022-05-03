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

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

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
    @DisplayName("No se agrega un cliente")
    void addClienteNoValido() {

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