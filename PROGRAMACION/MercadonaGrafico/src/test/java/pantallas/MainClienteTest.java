package pantallas;


import common.Constantes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import servicios.impl.ServiciosTarjetasImpl;
import servicios.impl.ServiciosUsuariosImpl;
import ui.pantallas.commonPantallas.Pantallas;
import ui.pantallas.login.LoginController;
import ui.pantallas.mainCliente.MainClienteController;
import ui.pantallas.mainCliente.MainClienteViewModel;
import ui.pantallas.principal.PrincipalController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
public class MainClienteTest {

    private PrincipalController principalController; // = mock(PrincipalController.class);;


    private ServiciosTarjetasImpl serviciosTarjetas; // = mock(ServiciosUsuariosImpl.class);
    private ServiciosUsuariosImpl serviciosUsuarios; // = mock(ServiciosUsuariosImpl.class);


    private MainClienteViewModel mainClienteViewModel;

    MainClienteController controller;

    @BeforeEach
    void setUp() {
        //principalController = mock(PrincipalController.class);
    }

    @Start
    public void start(Stage stage) throws IOException {

        principalController = mock(PrincipalController.class);
        serviciosUsuarios = mock(ServiciosUsuariosImpl.class);
        serviciosTarjetas = mock(ServiciosTarjetasImpl.class);
        mainClienteViewModel = new MainClienteViewModel(serviciosUsuarios, serviciosTarjetas);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(param -> new MainClienteController(mainClienteViewModel));
        InputStream s = getClass().getResourceAsStream(Pantallas.MAINCLIENTE.getRuta());
        Parent fxmlParent = fxmlLoader.load(s);
        controller = fxmlLoader.getController();
        controller.setPrincipalController(principalController);


        stage.setScene(new Scene(fxmlParent, 1000, 1000));
        // stage.getScene().getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.show();
    }

    @Test
    @DisplayName("AGREGAR TARJETA VALIDA")
    void agregarTarjetaValida(FxRobot robot) {
        //given
        Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
        Tarjeta tarjeta = new Tarjeta("t4", 1000);
        when(principalController.getUsuarioLogueado()).thenReturn(userLogueado);
        robot.clickOn("#txtNombre");
        robot.write("t4");
        robot.clickOn("#txtSaldo");
        robot.write("10000");

        //when
        robot.clickOn("#btnAgregarTarjeta");


        //then
        verify(serviciosTarjetas).agregarTarjeta(tarjeta, userLogueado);
        verify(principalController, never()).sacarAlertError(Constantes.NO_SE_HA_PODIDO_AGREGAR_LA_TARJETA);
        robot.sleep(5000);
    }

    @Test
    @DisplayName("AGREGAR TARJETA NO VALIDA")
    void agregarTarjetaNoValida(FxRobot robot) {
        //given
        Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
        Tarjeta tarjeta = new Tarjeta("t4", -1000);
        when(principalController.getUsuarioLogueado()).thenReturn(userLogueado);
        robot.clickOn("#txtNombre");
        robot.write("t4");
        robot.clickOn("#txtSaldo");
        robot.write("-1000");

        //when
        robot.clickOn("#btnAgregarTarjeta");


        //then
        assertThat(serviciosTarjetas.agregarTarjeta(tarjeta, userLogueado)).isFalse();
        //verify(principalController).sacarAlertError(Constantes.NO_SE_HA_PODIDO_AGREGAR_LA_TARJETA);

    }

}
