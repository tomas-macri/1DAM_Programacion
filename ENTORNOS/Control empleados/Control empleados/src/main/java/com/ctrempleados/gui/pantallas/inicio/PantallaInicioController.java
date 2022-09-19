package com.ctrempleados.gui.pantallas.inicio;

import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.servicios.ServiciosEmpleados;
import com.ctrempleados.gui.pantallas.common.BasePantallaController;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lombok.extern.log4j.Log4j2;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Log4j2
public class PantallaInicioController extends BasePantallaController {

    private final ServiciosEmpleados serviciosEmpleados;

    @FXML
    private Hyperlink link;
    @FXML
    private Label lbBienvenido;

    @Inject
    public PantallaInicioController(ServiciosEmpleados serviciosEmpleados) {
        this.serviciosEmpleados = serviciosEmpleados;
    }

    public void initialize() {
        link.setText(ConstantesPantallas.CONTACTA_CON_EL_DESARROLLADOR);
        try (var inputStream = getClass().getResourceAsStream(ConstantesPantallas.MEDIA_MAIL_LOGO_PNG)) {
            assert inputStream != null;
            Image mailImage = new Image(inputStream);
            link.setGraphic(new ImageView(mailImage));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        link.setOnAction(event -> {
            this.sendAMailTicket();
        });
    }

    private void sendAMailTicket() {
        try {
            Desktop.getDesktop().browse(new URI(ConstantesPantallas.MAILTO_JORGEMARTIN_99_OUTLOOK_COM));
        } catch (IOException | URISyntaxException e) {
            log.error(e.getMessage(),e);
        }
    }


    private void animarPantalla() {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(lbBienvenido);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(1);
        translate.setInterpolator(Interpolator.LINEAR);
        translate.setFromX(getPrincipalController().getWidth());
        translate.setToX(lbBienvenido.getLayoutX());
        translate.play();
    }

    @Override
    public void principalCargado() {
        String bienvenida = ConstantesPantallas.BIENVENIDO;
        Either<String, Empleado> empleado = serviciosEmpleados.getEmpleadoCodigo(getPrincipalController()
                .getActualUserCode());
        if (empleado.isRight()) {
            lbBienvenido.setText(bienvenida + empleado.get().getNombre());
        } else {
            lbBienvenido.setText(bienvenida + empleado.getLeft());
        }
        animarPantalla();
    }
}