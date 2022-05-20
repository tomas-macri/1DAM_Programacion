package com.ctrempleados.gui.pantallas.adm_franquicias;

import com.ctrempleados.domain.modelo.Franquicia;
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

public class AdmFranquiciasController extends BasePantallaController {

    private final AdmFranquiciasViewModel viewModel;
    private Franquicia franquiciaSeleccionada;
    @FXML
    private MFXTableView<Franquicia> tablaFranquicias;

    @FXML
    private MFXTableColumn<Franquicia> columnNombre;
    @FXML
    private MFXTableColumn<Franquicia> columnUbicacion;
    @FXML
    private MFXTableColumn<Franquicia> columnNumeroEmpleados;
    @FXML
    private MFXButton addFranquicia;

    @FXML
    private MFXButton unselect;
    @FXML
    private MFXButton updateFranquicia;
    @FXML
    private MFXButton deleteFranquicia;
    @FXML
    private MFXTextField txtNombre;

    @FXML
    private MFXTextField txtUbicacion;

    @Inject
    public AdmFranquiciasController(AdmFranquiciasViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void initialize() {
        unselect.setVisible(false);

        updateFranquicia.setVisible(false);
        deleteFranquicia.setVisible(false);
        addFranquicia.setVisible(true);

        columnNombre.setRowCellFactory(empleado -> new MFXTableRowCell<>(Franquicia::getNombre));
        columnUbicacion.setRowCellFactory(empleado -> new MFXTableRowCell<>(Franquicia::getUbicacion));
        columnNumeroEmpleados.setRowCellFactory(empleado -> new MFXTableRowCell<>(Franquicia::getNumeroEmpleados));

        columnNombre.setComparator(Comparator.comparing(Franquicia::getNombre));
        columnUbicacion.setComparator(Comparator.comparing(Franquicia::getUbicacion));
        columnNumeroEmpleados.setComparator(Comparator.comparing(Franquicia::getNumeroEmpleados));

        tablaFranquicias.getItems().clear();
        tablaFranquicias.getItems().addAll(viewModel.getFranquicias());

        tablaFranquicias.getSelectionModel().setAllowsMultipleSelection(false);
        tablaFranquicias.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.values().stream().findFirst().ifPresent(franquicia -> {
                    franquiciaSeleccionada = franquicia;
                    unselect.setVisible(true);
                    updateFranquicia.setVisible(true);
                    deleteFranquicia.setVisible(true);
                    addFranquicia.setVisible(false);
                    txtNombre.setText(franquicia.getNombre());
                    txtNombre.setDisable(true);
                    txtUbicacion.setText(franquicia.getUbicacion());
                });
            }
        });

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getError() != null) {
                getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, newValue.getError());
            }
            if (newValue.isFranquiciaAñadida()) {
                getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.INFORMACION, ConstantesPantallas.FRANQUICIA_AÑADIDA);
                onBtnAnularSeleccionClicked();
                tablaFranquicias.getItems().clear();
                tablaFranquicias.getItems().addAll(viewModel.getFranquicias());
            }
            if (newValue.isFranquiciaModificada()) {
                getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.INFORMACION, ConstantesPantallas.FRANQUICIA_ACTUALIZADA);
                onBtnAnularSeleccionClicked();
                tablaFranquicias.getItems().clear();
                tablaFranquicias.getItems().addAll(viewModel.getFranquicias());
            }
            if (newValue.isFranquiciaEliminada()) {
                getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.INFORMACION, ConstantesPantallas.FRANQUICIA_ELIMINADA);
                onBtnAnularSeleccionClicked();
                tablaFranquicias.getItems().clear();
                tablaFranquicias.getItems().addAll(viewModel.getFranquicias());
            }
        });
    }


    @FXML
    private void onBtnAnularSeleccionClicked() {
        tablaFranquicias.getSelectionModel().clearSelection();
        franquiciaSeleccionada = null;
        unselect.setVisible(false);
        updateFranquicia.setVisible(false);
        deleteFranquicia.setVisible(false);
        addFranquicia.setVisible(true);
        txtNombre.setText("");
        txtNombre.setDisable(false);
        txtUbicacion.setText("");
    }

    @FXML
    private void onBtnAgregarFranquiciaClicked() {
        viewModel.addFranquicia(txtNombre.getText(), txtUbicacion.getText());
    }

    @FXML
    private void onBtnActualizarFranquiciaClicked() {
        franquiciaSeleccionada.setUbicacion(txtUbicacion.getText());
        viewModel.updateFranquicia(franquiciaSeleccionada);
    }

    @FXML
    private void onBtnEliminarFranquiciaClicked() {
        viewModel.deleteFranquicia(franquiciaSeleccionada);
    }

}

