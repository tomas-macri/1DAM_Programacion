package ui.pantallas.login;


import io.github.palexdev.materialfx.controls.MFXPasswordField;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.extern.log4j.Log4j2;
import ui.pantallas.commonPantallas.BasePantallaController;
import ui.pantallas.commonPantallas.Pantallas;

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
}