package ui.pantallas.homeGrupos;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.Equipo;
import ui.pantallas.common.BasePantallaController;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class HomeGruposController extends BasePantallaController implements Initializable {

    private HomeGruposViewModel gruposViewModel;

    @FXML
    MFXTableView mfxGrupo1;


    @Inject
    public HomeGruposController(HomeGruposViewModel gruposViewModel) {
        this.gruposViewModel = gruposViewModel;
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MFXTableColumn<Equipo> paisColumn = new MFXTableColumn<>("Equipo", true, Comparator.comparing(Equipo::getNombre));
        MFXTableColumn<Equipo> puntosColumns = new MFXTableColumn<>("Puntos", true, Comparator.comparing(Equipo::getPuntos));
        MFXTableColumn<Equipo> golesColumn = new MFXTableColumn<>("GF", true, Comparator.comparing(Equipo::getGolesFavor));
        paisColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getNombre));
        puntosColumns.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getPuntos));
        golesColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getGolesFavor));


        mfxGrupo1.getTableColumns().addAll(paisColumn, puntosColumns, golesColumn);

        cambiosEstado();
        gruposViewModel.loadGrupos();

    }

    private void cambiosEstado() {
        gruposViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError()!=null){
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getEquipos() != null)
            {
                mfxGrupo1.getItems().clear();
                mfxGrupo1.getItems().addAll(listadoStateNew.getEquipos());
            }


        });
    }
}
