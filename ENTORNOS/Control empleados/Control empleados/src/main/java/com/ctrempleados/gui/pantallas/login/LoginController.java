package com.ctrempleados.gui.pantallas.login;


import com.ctrempleados.gui.pantallas.common.BasePantallaController;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class LoginController extends BasePantallaController {

    private final LoginViewModel loginViewModel;

    @FXML
    private ImageView logo;

    @FXML
    private MFXTextField txtCodigo;
    @FXML
    private MFXTextField txtDNI;

    @FXML
    private Label reloj;

    @Inject
    public LoginController(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    public void initialize() {
        try (var inputStream = getClass().getResourceAsStream(ConstantesPantallas.MEDIA_LOGO_PNG)) {
            assert inputStream != null;
            Image logoImage = new Image(inputStream);
            logo.setImage(logoImage);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                reloj.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern(ConstantesPantallas.YYYY_MM_DD_HH_MM_SS)));
            }
        };
        timer.start();

        loginViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.getError() != null) {
                this.getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, newState.getError());
            }
            if (newState.isLoginOK()) {
                //cambiar de pantalla
                this.getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.LOGIN, ConstantesPantallas.LOGIN_REALIZADO_CODIGO_DE_ACCESO + txtCodigo.getText());
                this.getPrincipalController().onLoginHecho(Integer.parseInt(txtCodigo.getText()));
            }
        });
    }

    @FXML
    private void doLoginCode() {
        loginViewModel.doLoginCode(txtCodigo.getText());
    }

    @FXML
    private void doLoginDNI() {
        String code = loginViewModel.getAccessCode(txtDNI.getText());
        if (code != null) {
            txtCodigo.setText(code);
            loginViewModel.doLoginCode(code);
        }
    }
}