package com.ctrempleados.gui.pantallas.emp_registros;

import com.ctrempleados.domain.modelo.Registro;
import com.ctrempleados.gui.pantallas.common.BasePantallaController;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.Comparator;

public class EmpRegistrosController extends BasePantallaController {

    private final EmpRegistrosViewModel viewModel;
    private Registro registroSeleccionado;

    @FXML
    private MFXTableView<Registro> tablaRegistros;

    @FXML
    private MFXTableColumn<Registro> columnId;
    @FXML
    private MFXTableColumn<Registro> columnDni;
    @FXML
    private MFXTableColumn<Registro> columnFranquicia;
    @FXML
    private MFXTableColumn<Registro> columnHoraEntrada;
    @FXML
    private MFXTableColumn<Registro> columnHoraSalida;
    @FXML
    private MFXTableColumn<Registro> columnComputoTiempo;
    @FXML
    private MFXTableColumn<Registro> columnValidado;

    @FXML
    private MFXButton unselect;
    @FXML
    private MFXButton modificarRegistro;

    @FXML
    private MFXTextField txtHoraInicio;
    @FXML
    private MFXTextField txtHoraFin;

    @Inject
    public EmpRegistrosController(EmpRegistrosViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void initialize() {
        unselect.setVisible(false);
        modificarRegistro.setVisible(false);

        columnId.setRowCellFactory(empleado -> new MFXTableRowCell<>(Registro::getId));
        columnDni.setRowCellFactory(empleado -> new MFXTableRowCell<>(Registro::getDniEmpleado));
        columnFranquicia.setRowCellFactory(empleado -> new MFXTableRowCell<>(Registro::getNombreFranquicia));
        columnHoraEntrada.setRowCellFactory(empleado -> new MFXTableRowCell<>(Registro::imprimirHoraEntrada));
        columnHoraSalida.setRowCellFactory(empleado -> new MFXTableRowCell<>(Registro::imprimirHoraSalida));
        columnComputoTiempo.setRowCellFactory(empleado -> new MFXTableRowCell<>(Registro::imprimirComputoTiempo));
        columnValidado.setRowCellFactory(empleado -> new MFXTableRowCell<>(Registro::isValidado));

        columnId.setComparator(Comparator.comparing(Registro::getId));
        columnDni.setComparator(Comparator.comparing(Registro::getDniEmpleado));
        columnFranquicia.setComparator(Comparator.comparing(Registro::getNombreFranquicia));
        columnHoraEntrada.setComparator(Comparator.comparing(Registro::imprimirHoraEntrada));
        columnHoraSalida.setComparator(Comparator.comparing(Registro::imprimirHoraSalida));
        columnComputoTiempo.setComparator(Comparator.comparing(Registro::imprimirComputoTiempo));
        columnValidado.setComparator(Comparator.comparing(Registro::isValidado));

        tablaRegistros.getSelectionModel().setAllowsMultipleSelection(false);
        tablaRegistros.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.values().stream().findFirst().ifPresent(registro -> {
                    registroSeleccionado = registro;
                    unselect.setVisible(true);
                    modificarRegistro.setVisible(true);
                    txtHoraInicio.setText(registro.imprimirHoraEntrada());
                    txtHoraFin.setText(registro.imprimirHoraSalida());
                });
            }
        });

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getError() != null) {
                getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, newValue.getError());
            }
            if (newValue.isRegistroModificado()) {
                getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.INFORMACION, ConstantesPantallas.REGISTRO_VALIDADO_CORRECTAMENTE);
                tablaRegistros.getItems().clear();
                tablaRegistros.getItems().addAll(viewModel.getRegistrosEmpleado(getPrincipalController().getActualUserCode()));
                onBtnAnularSeleccionClicked();
            }
        });
    }

    @Override
    public void principalCargado() {
        tablaRegistros.getItems().clear();
        tablaRegistros.getItems().addAll(viewModel.getRegistrosEmpleado(getPrincipalController().getActualUserCode()));
    }


    @FXML
    private void onBtnAnularSeleccionClicked() {
        tablaRegistros.getSelectionModel().clearSelection();
        registroSeleccionado = null;
        unselect.setVisible(false);
        modificarRegistro.setVisible(false);
        txtHoraFin.setText("");
        txtHoraInicio.setText("");
    }

    @FXML
    private void onBtnModificarRegistroClicked() {
        if (registroSeleccionado != null) {
            String horaInicio = txtHoraInicio.getText();
            String horaFin = txtHoraFin.getText();
            viewModel.modificarRegistro(registroSeleccionado, horaInicio, horaFin);
        }
    }
}
