package ui.pantallas.login;


import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import modelo.Usuarios.Usuario;

import ui.pantallas.commonPantallas.BasePantallaController;
import ui.pantallas.commonPantallas.Pantallas;

import java.util.ArrayList;
import java.util.Optional;

@Log4j2
public class LoginController extends BasePantallaController {

    private LoginViewModel loginViewModel;

    @FXML
    private MFXPasswordField txtDNI;



    @Inject
    public LoginController(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    public void initialize() {
        loginViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.getError()!=null)
            {
                this.getPrincipalController().sacarAlertError(newState.getError());
            }
            if (newState.getUserLogueado() != null)
            {
                //cambiar de pantalla
                this.getPrincipalController().onLoginHecho(newState.getUserLogueado());
            }
        });
    }

    public void clickLogin(ActionEvent actionEvent) {

        loginViewModel.doLogin(txtDNI.getText());
    }

    public void clickRegistro(ActionEvent actionEvent) {
        this.getPrincipalController().doRegistro(Pantallas.REGISTRO);
    }
}