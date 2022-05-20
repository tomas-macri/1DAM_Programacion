package gui;

import com.ctrempleados.domain.modelo.Franquicia;
import com.ctrempleados.domain.servicios.ServiciosFranquicias;
import com.ctrempleados.domain.servicios.impl.ServiciosFranquiciasImpl;
import com.ctrempleados.domain.servicios.impl.ServiciosNominasImpl;
import com.ctrempleados.gui.main.common.ConstantesMain;
import com.ctrempleados.gui.pantallas.adm_franquicias.AdmFranquiciasController;
import com.ctrempleados.gui.pantallas.adm_franquicias.AdmFranquiciasViewModel;
import com.ctrempleados.gui.pantallas.common.Pantallas;
import com.ctrempleados.gui.pantallas.principal.PrincipalController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.vavr.control.Either;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
public class PantallaFranquiciasTest extends ApplicationTest {

    private PrincipalController principalController;
    AdmFranquiciasController admFranquiciasController;
    AdmFranquiciasViewModel admFranquiciasViewModel;
    ServiciosFranquiciasImpl serviciosFranquicias;

    @Start
    public void start(Stage stage) throws IOException {

        principalController = mock(PrincipalController.class);
        admFranquiciasController = mock(AdmFranquiciasController.class);
        serviciosFranquicias = mock(ServiciosFranquiciasImpl.class);
        admFranquiciasViewModel = new AdmFranquiciasViewModel(serviciosFranquicias);


        when(serviciosFranquicias.getFranquicias()).thenReturn(Either.right(List.of(new Franquicia("f1", "micasa", 500))));


        FXMLLoader fxmlLoader = new FXMLLoader();
        ResourceBundle r = ResourceBundle.getBundle(ConstantesMain.I_18_N_TEXTOS_UI);
        fxmlLoader.setResources(r);
        fxmlLoader.setControllerFactory(param -> new AdmFranquiciasController(admFranquiciasViewModel));
        InputStream s = getClass().getResourceAsStream(Pantallas.FRANQUICIAS_ADMIN.getRuta());
        Parent fxmlParent = fxmlLoader.load(s);
        admFranquiciasController = fxmlLoader.getController();
        admFranquiciasController.setPrincipalController(principalController);


        stage.setScene(new Scene(fxmlParent, 1000, 1000));
        // stage.getScene().getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.show();
    }

    @Test
    @DisplayName("Anular seleccion valido")
    void anularSeleccion(FxRobot robot) {
//given
        Franquicia f = new Franquicia("f1", "micasa", 500);


        //then
        MFXTableView<Franquicia> tabla = robot.lookup("#tablaFranquicias").queryAs(MFXTableView.class);

        robot.interact(() -> tabla.getSelectionModel().selectItem(f));
        robot.clickOn("#tablaFranquicias");

        tabla.getCell(0).setId("cell0");


        robot.clickOn("#cell0");

        MFXButton btnAnular = robot.lookup("#unselect").queryAs(MFXButton.class);
        MFXButton btnModificar = robot.lookup("#updateFranquicia").queryAs(MFXButton.class);
        MFXButton btnEliminar = robot.lookup("#deleteFranquicia").queryAs(MFXButton.class);
        MFXButton btnAddFranquicia = robot.lookup("#addFranquicia").queryAs(MFXButton.class);
        MFXTextField txtNombre = robot.lookup("#txtNombre").queryAs(MFXTextField.class);
        MFXTextField txtUbicacion = robot.lookup("#txtUbicacion").queryAs(MFXTextField.class);

        assertAll(
                () -> assertThat(btnAddFranquicia.isVisible()).isFalse(),
                () -> assertThat(btnEliminar.isVisible()).isTrue(),
                () -> assertThat(btnModificar.isVisible()).isTrue(),
                () -> assertThat(txtNombre.getText()).isEqualTo("f1"),
                () -> assertThat(txtUbicacion.getText()).isEqualTo("micasa"),
                () -> assertThat(tabla.getSelectionModel().getSelection()).size().isEqualTo(1)
        );

        robot.sleep(2000);

        robot.clickOn(btnAnular);
        assertAll(
                () -> assertThat(btnAddFranquicia.isVisible()).isTrue(),
                () -> assertThat(btnEliminar.isVisible()).isFalse(),
                () -> assertThat(btnModificar.isVisible()).isFalse(),
                () -> assertThat(txtNombre.getText()).isEmpty(),
                () -> assertThat(txtUbicacion.getText()).isEmpty(),
                () -> assertThat(tabla.getSelectionModel().getSelection()).isEmpty()
        );

    }
}
