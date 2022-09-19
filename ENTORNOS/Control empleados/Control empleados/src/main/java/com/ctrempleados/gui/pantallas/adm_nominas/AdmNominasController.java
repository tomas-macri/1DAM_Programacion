package com.ctrempleados.gui.pantallas.adm_nominas;

import com.ctrempleados.domain.modelo.Nomina;
import com.ctrempleados.gui.pantallas.common.BasePantallaController;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.Comparator;

public class AdmNominasController extends BasePantallaController {

    private Nomina nominaSeleccionada;
    private final AdmNominasViewModel viewModel;

    @FXML
    private MFXTableView<Nomina> tablaNominas;
    @FXML
    private MFXTableColumn<Nomina> columnId;
    @FXML
    private MFXTableColumn<Nomina> columnDni;
    @FXML
    private MFXTableColumn<Nomina> columnSueldoHora;
    @FXML
    private MFXTableColumn<Nomina> columnFechaInicio;
    @FXML
    private MFXTableColumn<Nomina> columnFechaFin;
    @FXML
    private MFXTableColumn<Nomina> columnDias;
    @FXML
    private MFXTableColumn<Nomina> columnTiempo;
    @FXML
    private MFXTableColumn<Nomina> columnSueldo;
    @FXML
    private MFXTableColumn<Nomina> columnPendiente;

    @FXML
    private MFXButton unselect;
    @FXML
    private MFXButton pagarNomina;
    @FXML
    private MFXButton pagarTodasNominas;
    @FXML
    private MFXButton crearTodasNominas;

    @Inject
    public AdmNominasController(AdmNominasViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void initialize() {
        unselect.setVisible(false);
        pagarNomina.setVisible(false);

        columnId.setRowCellFactory(empleado -> new MFXTableRowCell<>(Nomina::getId));
        columnDni.setRowCellFactory(empleado -> new MFXTableRowCell<>(Nomina::getDniEmpleado));
        columnSueldoHora.setRowCellFactory(empleado -> new MFXTableRowCell<>(Nomina::getSueldoHora));
        columnFechaInicio.setRowCellFactory(empleado -> new MFXTableRowCell<>(Nomina::getFechaInicio));
        columnFechaFin.setRowCellFactory(empleado -> new MFXTableRowCell<>(Nomina::getFechaFin));
        columnDias.setRowCellFactory(empleado -> new MFXTableRowCell<>(Nomina::imprimirComputoDias));
        columnTiempo.setRowCellFactory(empleado -> new MFXTableRowCell<>(Nomina::imprimirTiempoTrabajado));
        columnSueldo.setRowCellFactory(empleado -> new MFXTableRowCell<>(Nomina::getSueldo));
        columnPendiente.setRowCellFactory(empleado -> new MFXTableRowCell<>(Nomina::isPendienteDeAbonar));

        columnId.setComparator(Comparator.comparing(Nomina::getId));
        columnDni.setComparator(Comparator.comparing(Nomina::getDniEmpleado));
        columnSueldoHora.setComparator(Comparator.comparing(Nomina::getSueldoHora));
        columnDias.setComparator(Comparator.comparing(Nomina::imprimirComputoDias));
        columnTiempo.setComparator(Comparator.comparing(Nomina::imprimirTiempoTrabajado));
        columnSueldo.setComparator(Comparator.comparing(Nomina::getSueldo));
        columnPendiente.setComparator(Comparator.comparing(Nomina::isPendienteDeAbonar));

        tablaNominas.getItems().clear();
        tablaNominas.getItems().addAll(viewModel.getNominas());

        tablaNominas.getSelectionModel().setAllowsMultipleSelection(false);
        tablaNominas.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.values().stream().findFirst().ifPresent(nomina -> {
                    nominaSeleccionada = nomina;
                    unselect.setVisible(true);
                    pagarNomina.setVisible(nomina.isPendienteDeAbonar());
                    pagarTodasNominas.setVisible(false);
                    crearTodasNominas.setVisible(false);
                });
            }
        });

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getError() != null) {
                getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, newValue.getError());
            }
            if (newValue.isCrearNominas()) {
                getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.INFORMACION, ConstantesPantallas.NOMINA_S_CREADA_S_CORRECTAMENTE);
                tablaNominas.getItems().clear();
                tablaNominas.getItems().addAll(viewModel.getNominas());
                onBtnAnularSeleccionClicked();
            }
            if (newValue.isPagarNominas()) {
                getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.INFORMACION, ConstantesPantallas.NOMINA_S_PAGADA_S_CORRECTAMENTE);
                tablaNominas.getItems().clear();
                tablaNominas.getItems().addAll(viewModel.getNominas());
                onBtnAnularSeleccionClicked();
            }
        });
    }

    @FXML
    private void onBtnAnularSeleccionClicked() {
        tablaNominas.getSelectionModel().clearSelection();
        nominaSeleccionada = null;
        unselect.setVisible(false);
        pagarNomina.setVisible(false);
        pagarTodasNominas.setVisible(true);
        crearTodasNominas.setVisible(true);
    }

    @FXML
    private void onBtnPagarNominaClicked() {
        viewModel.pagarNomina(nominaSeleccionada);
    }

    @FXML
    private void onBtnPagarTodasNominasClicked() {
        viewModel.pagarTodasNominas();
    }

    @FXML
    private void onBtnCrearTodasNominasClicked() {
        viewModel.crearTodasNominas();
    }
}