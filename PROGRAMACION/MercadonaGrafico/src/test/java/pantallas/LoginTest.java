package pantallas;

import common.Constantes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Usuarios.UsuarioNormal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import servicios.impl.ServiciosUsuariosImpl;
import ui.pantallas.commonPantallas.Pantallas;
import ui.pantallas.login.LoginController;
import ui.pantallas.login.LoginViewModel;
import ui.pantallas.principal.PrincipalController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
public class LoginTest extends ApplicationTest {


    private PrincipalController principalController; // = mock(PrincipalController.class);;


    private ServiciosUsuariosImpl serviciosUsuarios; // = mock(ServiciosUsuariosImpl.class);


    private LoginViewModel loginViewModel;
    LoginController controller;

    @BeforeEach
    void setUp() {
        //principalController = mock(PrincipalController.class);
    }

    @Start
    public void start(Stage stage) throws IOException {

        principalController = mock(PrincipalController.class);
        serviciosUsuarios = mock(ServiciosUsuariosImpl.class);
        loginViewModel = new LoginViewModel(serviciosUsuarios);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(param -> new LoginController(loginViewModel));
        InputStream s = getClass().getResourceAsStream(Pantallas.LOGIN.getRuta());
        Parent fxmlParent = fxmlLoader.load(s);
        controller = fxmlLoader.getController();
        controller.setPrincipalController(principalController);


        stage.setScene(new Scene(fxmlParent, 1000, 1000));
        // stage.getScene().getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.show();

    }


    @Test
    @DisplayName("REGISTRO VALIDO")
    void do_registro(FxRobot robot) {
        //given

        //when(ServiciosUsuariosImpl.doLogin(any(Usuario.class))).thenReturn(true);

        //when
        robot.clickOn("#btnRegistro");

        //FxAssert.verifyThat("#btLogin").textIs("Login");

        //then
        verify(principalController).doRegistro(Pantallas.REGISTRO);
        robot.sleep(1000);
    }
    @Test
    @DisplayName("LOGIN ADMIN VALIDO")
    void should_contain_button_with_text(FxRobot robot) {
        //given
        robot.clickOn("#txtDNI");
        robot.write("a1");
        UsuarioNormal admin = new UsuarioNormal("a1", "admin", new ArrayList<>());
        admin.setAdmin(true);
        when(serviciosUsuarios.getUsuario(argThat(s -> s.equals("a1")))).thenReturn(admin);
        //when(ServiciosUsuariosImpl.doLogin(any(Usuario.class))).thenReturn(true);

        //when
        robot.clickOn("#btnEntrar");

        //FxAssert.verifyThat("#btLogin").textIs("Login");

        //then

        verify(principalController).onLoginHecho(argThat(usuario -> usuario.getNombre().equals("admin")));
        robot.sleep(1000);
    }


    @Test
    @DisplayName("LOGIN USER NORMAL VALIDO")
    void should_contain_button_with_text_NORMAL(FxRobot robot) {
        //given
        robot.clickOn("#txtDNI");
        robot.write("u1");
        UsuarioNormal user1 = new UsuarioNormal("u1", "user1", new ArrayList<>());
        when(serviciosUsuarios.getUsuario(argThat(s -> s.equals("u1")))).thenReturn(user1);
        //when(ServiciosUsuariosImpl.doLogin(any(Usuario.class))).thenReturn(true);

        //when
        robot.clickOn("#btnEntrar");

        //FxAssert.verifyThat("#btLogin").textIs("Login");

        //then

        verify(principalController).onLoginHecho(argThat(usuario -> usuario.getNombre().equals("user1")));
        robot.sleep(1000);
    }

    @Test
    @DisplayName("LOGIN NO VALIDO")
    void should_alert_error(FxRobot robot) {
        //given
        robot.clickOn("#txtDNI");
        robot.write("otro");
        when(serviciosUsuarios.getUsuario(argThat(s -> s.equals("otro")))).thenReturn(null);
        //when(ServiciosUsuariosImpl.doLogin(any(Usuario.class))).thenReturn(true);


        //when
        robot.clickOn("#btnEntrar");

        //then
        verify(principalController).sacarAlertError(Constantes.DNI_INEXISTENTE);
        robot.sleep(1000);


    }
}

