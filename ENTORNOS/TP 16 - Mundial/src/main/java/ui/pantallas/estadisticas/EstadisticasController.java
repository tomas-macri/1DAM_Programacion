package ui.pantallas.estadisticas;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.enums.SortState;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import modelo.Equipo;
import ui.pantallas.common.BasePantallaController;

import java.util.Comparator;

public class EstadisticasController extends BasePantallaController {

    @FXML
    private MFXTableView<Equipo> tblEquiposGoleadores;

    @FXML
    private MFXTableView<Equipo> tblEquiposInvictos;

    EstadisticasViewModel estadisticasViewModel;

    @Inject
    public EstadisticasController(EstadisticasViewModel estadisticasViewModel) {
        this.estadisticasViewModel = estadisticasViewModel;
    }

    @Override
    public void principalCargado() {
        MFXTableColumn<Equipo> paisColumn = new MFXTableColumn<>("Equipo", true, Comparator.comparing(Equipo::getNombre));
        MFXTableColumn<Equipo> paisColumn2 = new MFXTableColumn<>("Equipo", true, Comparator.comparing(Equipo::getNombre));
        MFXTableColumn<Equipo> golesColumn = new MFXTableColumn<>("GF", true, Comparator.comparing(Equipo::getGolesFavor));
        MFXTableColumn<Equipo> invictoColumn = new MFXTableColumn<>("Porterías a 0", true, Comparator.comparing(Equipo::getVallasInvictas));

        paisColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getNombre));
        golesColumn.setSortState(SortState.DESCENDING);
        invictoColumn.setSortState(SortState.DESCENDING);

        golesColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getGolesFavor));

        paisColumn2.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getNombre));
        invictoColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getVallasInvictas));

        tblEquiposGoleadores.getTableColumns().addAll(paisColumn, golesColumn);
        tblEquiposInvictos.getTableColumns().addAll(paisColumn2, invictoColumn);

        addListener();
        estadisticasViewModel.cargarEquipos();
    }

    private void addListener() {
        estadisticasViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError() != null) {
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getEquipoList() != null) {

                tblEquiposGoleadores.getItems().clear();
                tblEquiposGoleadores.getItems().addAll(listadoStateNew.getEquipoList());


                tblEquiposInvictos.getItems().clear();
                tblEquiposInvictos.getItems().addAll(listadoStateNew.getEquipoList());
            }

        });
    }
}
