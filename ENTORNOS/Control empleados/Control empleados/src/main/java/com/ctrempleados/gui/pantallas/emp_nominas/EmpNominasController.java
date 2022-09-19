package com.ctrempleados.gui.pantallas.emp_nominas;

import com.ctrempleados.domain.modelo.Nomina;
import com.ctrempleados.gui.pantallas.common.BasePantallaController;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.Comparator;

public class EmpNominasController extends BasePantallaController {

    private final EmpNominasViewModel viewModel;

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

    @Inject
    public EmpNominasController(EmpNominasViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void initialize() {

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

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getError() != null) {
                getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, newValue.getError());
            }
        });
    }

    @Override
    public void principalCargado() {
        tablaNominas.getItems().clear();
        tablaNominas.getItems().addAll(viewModel.getNominasEmpleado(getPrincipalController().getActualUserCode()));
    }
}
