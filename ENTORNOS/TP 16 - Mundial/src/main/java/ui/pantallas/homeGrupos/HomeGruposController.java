package ui.pantallas.homeGrupos;

import config.Configuracion;
import dao.DaoPartidos;
import dao.DataBase;
import di.GsonProducer;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.enums.SortState;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.Equipo;
import modelo.Grupo;
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


    private List<MFXTableView> listTablasGrupos;

    @Inject
    public HomeGruposController(HomeGruposViewModel gruposViewModel) {this.gruposViewModel = gruposViewModel;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listTablasGrupos = new ArrayList<>();
        listTablasGrupos.add(mfxGrupo1);
        listTablasGrupos.add(mfxGrupo2);
        listTablasGrupos.add(mfxGrupo3);
        listTablasGrupos.add(mfxGrupo4);
        listTablasGrupos.add(mfxGrupo5);
        listTablasGrupos.add(mfxGrupo6);
        listTablasGrupos.add(mfxGrupo7);
        listTablasGrupos.add(mfxGrupo8);


        for (int i = 0; i < listTablasGrupos.size(); i++){
            MFXTableColumn<Equipo> paisColumn = new MFXTableColumn<>("Equipo", true, Comparator.comparing(Equipo::getNombre));
            MFXTableColumn<Equipo> puntosColumns = new MFXTableColumn<>("Puntos", true, Comparator.comparing(Equipo::getPuntos));
            MFXTableColumn<Equipo> golesColumn = new MFXTableColumn<>("GF", true, Comparator.comparing(Equipo::getGolesFavor));
            paisColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getNombre));
            puntosColumns.setSortState(SortState.DESCENDING);
            paisColumn.setPrefWidth(paisColumn.getPrefWidth()*2);
            puntosColumns.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getPuntos));
            golesColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getGolesFavor));


            listTablasGrupos.get(i).getTableColumns().addAll(paisColumn, puntosColumns, golesColumn);

        }

        cambiosEstado();
        gruposViewModel.loadGrupos();

    }

    private void cambiosEstado() {
        gruposViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            List<Grupo> todosLosGrupos = new ArrayList<>();
            if (listadoStateNew.getError()!=null){
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getEquipos() != null)
            {
                List<Equipo> equipoList = listadoStateNew.getEquipos();

                for (int i = 0; i < listTablasGrupos.size(); i++){
                    listTablasGrupos.get(i).setFooterVisible(false);
                    listTablasGrupos.get(i).getItems().clear();
                    List<Equipo> equiposGrupos = new ArrayList<>();
                    for (int j = 0; j < 4; j++) {
                        equiposGrupos.add(equipoList.get((i*4)+j));
                        listTablasGrupos.get(i).getItems().add(equipoList.get((i*4)+j));
                    }
                    todosLosGrupos.add(new Grupo(equiposGrupos));
                }


            }

        });
    }
}
