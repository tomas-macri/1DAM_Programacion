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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class HomeGruposController extends BasePantallaController implements Initializable {


    private HomeGruposViewModel gruposViewModel;

    @FXML
    private MFXTableView mfxGrupo1;

    @FXML
    private MFXTableView mfxGrupo2;

    @FXML
    private MFXTableView mfxGrupo3;

    @FXML
    private MFXTableView mfxGrupo4;

    @FXML
    private MFXTableView mfxGrupo5;

    @FXML
    private MFXTableView mfxGrupo6;

    @FXML
    private MFXTableView mfxGrupo7;

    @FXML
    private MFXTableView mfxGrupo8;


    private List<MFXTableView> listGrupos;

    @Inject
    public HomeGruposController(HomeGruposViewModel gruposViewModel) {this.gruposViewModel = gruposViewModel;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listGrupos = new ArrayList<>();
        listGrupos.add(mfxGrupo1);
        listGrupos.add(mfxGrupo2);
        listGrupos.add(mfxGrupo3);
        listGrupos.add(mfxGrupo4);
        listGrupos.add(mfxGrupo5);
        listGrupos.add(mfxGrupo6);
        listGrupos.add(mfxGrupo7);
        listGrupos.add(mfxGrupo8);


        for (int i = 0; i <listGrupos.size(); i++){
            MFXTableColumn<Equipo> paisColumn = new MFXTableColumn<>("Equipo", true, Comparator.comparing(Equipo::getNombre));
            MFXTableColumn<Equipo> puntosColumns = new MFXTableColumn<>("Puntos", true, Comparator.comparing(Equipo::getPuntos));
            MFXTableColumn<Equipo> golesColumn = new MFXTableColumn<>("GF", true, Comparator.comparing(Equipo::getGolesFavor));
            paisColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getNombre));
            paisColumn.setPrefWidth(paisColumn.getPrefWidth()*2);
            puntosColumns.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getPuntos));
            golesColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getGolesFavor));


            listGrupos.get(i).getTableColumns().addAll(paisColumn, puntosColumns, golesColumn);

        }

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
                List<Equipo> equipoList = listadoStateNew.getEquipos();

                for (int i = 0; i < listGrupos.size(); i++){
                    listGrupos.get(i).getItems().clear();
                    for (int j = 0; j < 4; j++) {
                        listGrupos.get(i).getItems().add(equipoList.get((i*4)+j));
                    }
                }


            }


        });
    }
}
