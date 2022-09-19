package com.ctrempleados.gui.pantallas.emp_fichar;

import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.gui.pantallas.common.BasePantallaController;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.github.palexdev.materialfx.controls.MFXButton;
import jakarta.inject.Inject;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmpFicharController extends BasePantallaController {

    private final EmpFicharViewModel viewModel;
    private Empleado empleadoLogueado;
    private LocalDateTime horaEntrada;
    private boolean entradaRegistrada;

    @FXML
    private MFXButton ficharEntrada;
    @FXML
    private MFXButton ficharSalida;
    @FXML
    private Label reloj;
    @FXML
    private Label timerLabel;
    @FXML
    private Label franquiciaLabel;

    @Inject
    public EmpFicharController(EmpFicharViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void initialize() {
        ficharEntrada.setDisable(true);
        ficharSalida.setDisable(true);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                reloj.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern(ConstantesPantallas.YYYY_MM_DD_HH_MM_SS)));
            }
        };
        timer.start();

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getError() != null) {
                getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, newValue.getError());
            }
            if (newValue.isEntradaRegistrada()) {
                horaEntrada = viewModel.getHoraEntrada(empleadoLogueado);
                ficharEntrada.setDisable(true);
                ficharSalida.setDisable(false);
            }
            if (newValue.isSalidaRegistrada()) {
                horaEntrada = null;
                ficharEntrada.setDisable(false);
                ficharSalida.setDisable(true);
            }
        });
    }

    @Override
    public void principalCargado() {
        empleadoLogueado = viewModel.getEmpleadoLogueado(getPrincipalController().getActualUserCode());
        entradaRegistrada = viewModel.isEntradaRegistrada(empleadoLogueado);
        if (empleadoLogueado.getNombreFranquicia() != null) {
            if (entradaRegistrada) {
                ficharEntrada.setDisable(true);
                ficharSalida.setDisable(false);
            } else {
                ficharEntrada.setDisable(false);
                ficharSalida.setDisable(true);
            }
            franquiciaLabel.setText(empleadoLogueado.getNombreFranquicia());
        } else {
            franquiciaLabel.setText(ConstantesPantallas.NO_TIENE_FRANQUICIA_ASIGNADA_CONTACTE_CON_EL_ADMINISTRADOR);
        }
        horaEntrada = viewModel.getHoraEntrada(empleadoLogueado);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (horaEntrada != null) {
                    timerLabel.setText(Duration.between(horaEntrada, LocalDateTime.now()).toHours() + ConstantesPantallas.HORAS +
                            (Duration.between(horaEntrada, LocalDateTime.now()).toMinutes() - Duration.between(horaEntrada, LocalDateTime.now()).toHours() * 60) + " minutos");
                } else {
                    timerLabel.setText(ConstantesPantallas.NO_SE_HA_INICIADO_EL_REGISTRO);
                }
            }
        };
        timer.start();
    }

    @FXML
    private void onBtnFicharEntradaClicked(ActionEvent actionEvent) {
        viewModel.registrarEntrada(empleadoLogueado);
    }

    @FXML
    private void onBtnFicharSalidaClicked(ActionEvent actionEvent) {
        viewModel.registrarSalida(empleadoLogueado);
    }
}
