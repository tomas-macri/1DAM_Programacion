package com.ctrempleados.gui.pantallas.adm_empleados;

import com.ctrempleados.domain.modelo.Empleado;
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


public class AdmEmpleadosController extends BasePantallaController {

    private final AdmEmpleadosViewModel viewModel;
    private Empleado empleadoSeleccionado;

    @FXML
    private MFXTableView<Empleado> tablaEmpleados;
    @FXML
    private MFXTableColumn<Empleado> columnDNI;
    @FXML
    private MFXTableColumn<Empleado> columnNombre;
    @FXML
    private MFXTableColumn<Empleado> columnApellido;
    @FXML
    private MFXTableColumn<Empleado> columnCodAcceso;
    @FXML
    private MFXTableColumn<Empleado> columnSueldoHora;
    @FXML
    private MFXTableColumn<Empleado> columnFranquicia;

    @FXML
    private MFXButton addEmpleado;
    @FXML
    private MFXButton unselect;
    @FXML
    private MFXButton updateEmpleado;
    @FXML
    private MFXButton deleteEmpleado;
    @FXML
    private MFXButton updateFranquicia;
    @FXML
    private MFXButton deleteFranquicia;

    @FXML
    private MFXTextField txtDNI;
    @FXML
    private MFXTextField txtNombre;
    @FXML
    private MFXTextField txtApellido;
    @FXML
    private MFXTextField txtSueldoHora;
    @FXML
    private MFXTextField txtFranquicia;

    @Inject
    public AdmEmpleadosController(AdmEmpleadosViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void initialize() {
        unselect.setVisible(false);

        updateEmpleado.setVisible(false);
        deleteEmpleado.setVisible(false);
        deleteFranquicia.setVisible(false);
        updateFranquicia.setVisible(false);

        columnDNI.setRowCellFactory(empleado -> new MFXTableRowCell<>(Empleado::getDni));
        columnNombre.setRowCellFactory(empleado -> new MFXTableRowCell<>(Empleado::getNombre));
        columnApellido.setRowCellFactory(empleado -> new MFXTableRowCell<>(Empleado::getApellido));
        columnCodAcceso.setRowCellFactory(empleado -> new MFXTableRowCell<>(Empleado::getCodigoAcceso));
        columnSueldoHora.setRowCellFactory(empleado -> new MFXTableRowCell<>(Empleado::getSueldoHora));
        columnFranquicia.setRowCellFactory(empleado -> new MFXTableRowCell<>(Empleado::getNombreFranquicia));

        columnDNI.setComparator(Comparator.comparing(Empleado::getDni));
        columnNombre.setComparator(Comparator.comparing(Empleado::getNombre));
        columnApellido.setComparator(Comparator.comparing(Empleado::getApellido));
        columnCodAcceso.setComparator(Comparator.comparing(Empleado::getCodigoAcceso));
        columnSueldoHora.setComparator(Comparator.comparing(Empleado::getSueldoHora));
        columnFranquicia.setComparator(Comparator.comparing(Empleado::getNombreFranquicia));

        tablaEmpleados.getItems().clear();
        tablaEmpleados.getItems().addAll(viewModel.getEmpleados());

        tablaEmpleados.getSelectionModel().setAllowsMultipleSelection(false);
        tablaEmpleados.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.values().stream().findFirst().ifPresent(empleado -> {
                    if (!empleado.getDni().equalsIgnoreCase(ConstantesPantallas.ADMIN_CODE)) {
                        empleadoSeleccionado = empleado;
                        unselect.setVisible(true);
                        updateEmpleado.setVisible(true);
                        deleteEmpleado.setVisible(true);
                        deleteFranquicia.setVisible(true);
                        updateFranquicia.setVisible(true);
                        addEmpleado.setVisible(false);
                        txtDNI.setText(empleado.getDni());
                        txtDNI.setDisable(true);
                        txtNombre.setText(empleado.getNombre());
                        txtApellido.setText(empleado.getApellido());
                        txtSueldoHora.setText(String.valueOf(empleado.getSueldoHora()));
                        if (empleado.getNombreFranquicia() != null) {
                            txtFranquicia.setText(empleado.getNombreFranquicia());
                        } else {
                            txtFranquicia.setText("");
                        }
                    }
                });
            }
        });

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getError() != null) {
                getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, newValue.getError());
            }
            if (newValue.isEmpleadoNuevo()) {
                getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.INFORMACION, ConstantesPantallas.EMPLEADO_CREADO_CORRECTAMENTE);
                onBtnAnularSeleccionClicked();
                tablaEmpleados.getItems().clear();
                tablaEmpleados.getItems().addAll(viewModel.getEmpleados());
            }
            if (newValue.isEmpleadoModificado()) {
                getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.INFORMACION, ConstantesPantallas.EMPLEADO_ACTUALIZADO_CORRECTAMENTE);
                onBtnAnularSeleccionClicked();
                tablaEmpleados.getItems().clear();
                tablaEmpleados.getItems().addAll(viewModel.getEmpleados());
            }
            if (newValue.isEmpleadoEliminado()) {
                getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.INFORMACION, ConstantesPantallas.EMPLEADO_ELIMINADO_CORRECTAMENTE);
                onBtnAnularSeleccionClicked();
                tablaEmpleados.getItems().clear();
                tablaEmpleados.getItems().addAll(viewModel.getEmpleados());
            }
        });
    }


    @FXML
    private void onBtnAnularSeleccionClicked() {
        tablaEmpleados.getSelectionModel().clearSelection();
        txtDNI.setDisable(false);
        txtDNI.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtSueldoHora.setText("");
        txtFranquicia.setText("");
        unselect.setVisible(false);
        addEmpleado.setVisible(true);
        updateEmpleado.setVisible(false);
        deleteEmpleado.setVisible(false);
        deleteFranquicia.setVisible(false);
        updateFranquicia.setVisible(false);
        empleadoSeleccionado = null;
    }

    @FXML
    private void onBtnAgregarEmpleadoClicked() {
        viewModel.agregarEmpleado(txtDNI.getText(), txtNombre.getText(), txtApellido.getText(),
                Double.parseDouble(txtSueldoHora.getText()), txtFranquicia.getText());
    }

    @FXML
    private void onBtnActualizarEmpleadoClicked() {
        empleadoSeleccionado.setNombre(txtNombre.getText());
        empleadoSeleccionado.setApellido(txtApellido.getText());
        empleadoSeleccionado.setSueldoHora(Double.parseDouble(txtSueldoHora.getText()));
        viewModel.actualizarEmpleado(empleadoSeleccionado);
    }

    @FXML
    private void onBtnEliminarEmpleadoClicked() {
        viewModel.eliminarEmpleado(empleadoSeleccionado);
    }

    @FXML
    private void onBtnActualizarFranquiciaClicked() {
        viewModel.actualizarFranquicia(empleadoSeleccionado, txtFranquicia.getText());
    }

    @FXML
    private void onBtnQuitarFranquiciaClicked() {
        viewModel.quitarFranquicia(empleadoSeleccionado);
    }
}
