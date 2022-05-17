package ui;

import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import servicios.ServiciosUsuarios;
import ui.common.Constantes;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.stream.SystemOut;
import uk.org.webcompere.systemstubs.stream.input.LinesAltStream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SystemStubsExtension.class)
public class MainLoginTest {
    MainLogin mainLogin;

    @Mock
    private ServiciosUsuarios serviciosUsuariosImpl;
    @Mock
    private MainAdmin mainAdmin;
    @Mock
    private MainClientes mainClientes;

    private Scanner sc;

    @SystemStub
    SystemOut systemOut;


    @Test
    @DisplayName("LOGIN NO VALIDO")
    public void loginNoValido() {
        //given
        sc = new Scanner(new LinesAltStream("1", "fin"));
        mainLogin = new MainLogin(serviciosUsuariosImpl, mainAdmin, mainClientes, sc);

        //when
        mainLogin.inicioLogin();

        //then
        assertThat(systemOut.getLines().collect(Collectors.toList()).contains(Constantes.USUARIO_INEXISTENTE));
        assertThat(systemOut.getLines().filter(s -> s.contains(Constantes.BIENVENIDO_AL_MERCADONA)).count()).isEqualTo(2);
        assertThat(systemOut.getLines().filter(s -> s.contains(Constantes.INGRESE_SU_DNI_O_FIN_PARA_SALIR)).count()).isEqualTo(2);
    }

    @Test
    @DisplayName("LOGIN NO VALIDO Y DESPUES ADMIN")
    public void loginNoValidoPeroDespuesAdmin() {
        //given
        sc = new Scanner(new LinesAltStream("1", "a1", "fin"));
        mainLogin = new MainLogin(serviciosUsuariosImpl, mainAdmin, mainClientes, sc);
        when(serviciosUsuariosImpl.getUsuario("1")).thenReturn(null);
        Usuario admin = new UsuarioNormal("a1", "admin", new ArrayList<>());
        admin.setAdmin(true);
        when(serviciosUsuariosImpl.getUsuario("a1")).thenReturn(admin);

        //when
        mainLogin.inicioLogin();


        //then
        assertThat(systemOut.getLines().collect(Collectors.toList()).contains(Constantes.USUARIO_INEXISTENTE));
        verify(mainAdmin).inicioMenuAdmin();
    }

    @Test
    @DisplayName("LOGIN NO VALIDO Y DESPUES ADMIN")
    void loginValidoCliente() {
        //given
        sc = new Scanner(new LinesAltStream("u1", "fin"));
        mainLogin = new MainLogin(serviciosUsuariosImpl, mainAdmin, mainClientes, sc);
        Usuario usuario = new UsuarioNormal("u1", "user1", new ArrayList<>());
        when(serviciosUsuariosImpl.getUsuario("u1")).thenReturn(usuario);

        //when
        mainLogin.inicioLogin();


        //then
        assertThat(systemOut.getLines().collect(Collectors.toList()).contains(Constantes.BIENVENIDO_CLIENTE));
        verify(mainClientes).inicioMenuClientes(usuario);
    }
}
