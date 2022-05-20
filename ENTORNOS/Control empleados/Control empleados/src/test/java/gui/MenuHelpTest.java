package gui;


import com.ctrempleados.domain.servicios.impl.ServiciosEmpleadosImpl;
import com.ctrempleados.gui.main.common.ConstantesMain;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import com.ctrempleados.gui.pantallas.common.Pantallas;
import com.ctrempleados.gui.pantallas.inicio.PantallaInicioController;
import com.ctrempleados.gui.pantallas.login.LoginController;
import com.ctrempleados.gui.pantallas.login.LoginViewModel;
import com.ctrempleados.gui.pantallas.principal.PrincipalController;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
 class MenuHelpTest extends ApplicationTest {

    private PrincipalController principalController;


    @Start
    public void start(Stage stage) throws IOException {

        principalController = mock(PrincipalController.class);




        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        FXMLLoader fxmlLoader = new FXMLLoader();
        ResourceBundle r = ResourceBundle.getBundle(ConstantesMain.I_18_N_TEXTOS_UI);
        fxmlLoader.setResources(r);
        fxmlLoader.setControllerFactory(param -> container.select(PrincipalController.class).get());
        InputStream s = getClass().getResourceAsStream("/fxml/pantallaPrincipal.fxml");
        Parent fxmlParent = fxmlLoader.load(s);
        principalController = fxmlLoader.getController();


        stage.setScene(new Scene(fxmlParent));

        stage.show();

    }

    @Test
    @DisplayName("REGISTRO VALIDO")
    void do_registro(FxRobot robot) {
        //given
        robot.interact(() -> {
            principalController.onLoginHecho(999);
        });

        //when
        robot.clickOn("#menuHelp");
        robot.clickOn("#menuItemAcercaDe");


        //then

        DialogPane a  = robot.lookup("#alert").queryAs(DialogPane.class);
        assertThat(a.getHeaderText()).isEqualTo(ConstantesPantallas.ACERCA_DE);
        assertThat(a.getContentText()).isEqualTo(ConstantesPantallas.DATOS_AUTOR);
            robot.sleep(1000);

        robot.sleep(1500);
    }

}
