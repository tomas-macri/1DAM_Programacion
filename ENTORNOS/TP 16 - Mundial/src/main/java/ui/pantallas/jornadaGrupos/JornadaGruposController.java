package ui.pantallas.jornadaGrupos;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.enums.SortState;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import modelo.Equipo;
import modelo.Partido;
import ui.pantallas.common.BasePantallaController;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class JornadaGruposController extends BasePantallaController implements Initializable {

    @FXML
    private MFXTextField txtGoles2;

    @FXML
    private MFXTextField txtGoles1;

    @FXML
    private Text txtEquipo2;

    @FXML
    Text txtEquipo1;

    @FXML
    private ComboBox cmbGrupos;

    @FXML
    private MFXButton btnGuardar;

    @FXML
    private MFXTableView tblPartido;
    @FXML
    private MFXTableView tblPosiciones;


    JornadaGruposViewModel jornadaGruposViewModel;



    @Inject
    public JornadaGruposController(JornadaGruposViewModel jornadaGruposViewModel) {
        this.jornadaGruposViewModel = jornadaGruposViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtEquipo1.setText("");
        txtEquipo2.setText("");

        MFXTableColumn<Equipo> paisColumn = new MFXTableColumn<>("Equipo", true, Comparator.comparing(Equipo::getNombre));
        MFXTableColumn<Equipo> puntosColumns = new MFXTableColumn<>("Puntos", true, Comparator.comparing(Equipo::getPuntos));
        MFXTableColumn<Equipo> golesColumn = new MFXTableColumn<>("GF", true, Comparator.comparing(Equipo::getGolesFavor));
        puntosColumns.setSortState(SortState.DESCENDING);
        paisColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getNombre));
        puntosColumns.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getPuntos));
        golesColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getGolesFavor));
        tblPosiciones.getTableColumns().addAll(paisColumn, puntosColumns, golesColumn);
        tblPosiciones.setFooterVisible(false);


        MFXTableColumn<Partido> equipo1 = new MFXTableColumn<>("Equipo", true);
        MFXTableColumn<Partido> goles1 = new MFXTableColumn<>("Goles", true);
        MFXTableColumn<Partido> goles2 = new MFXTableColumn<>("Goles", true);
        MFXTableColumn<Partido> equipo2 = new MFXTableColumn<>("Equipo", true);
        equipo1.setRowCellFactory(equipo -> new MFXTableRowCell<>(partido -> partido.getEquipos()[0].getNombre()));
        goles1.setRowCellFactory(equipo -> new MFXTableRowCell<>(partido -> partido.getGoles()[0]));
        equipo2.setRowCellFactory(equipo -> new MFXTableRowCell<>(partido -> partido.getEquipos()[1].getNombre()));
        goles2.setRowCellFactory(equipo -> new MFXTableRowCell<>(partido -> partido.getGoles()[1]));
        tblPartido.getTableColumns().addAll(equipo1, goles1, goles2, equipo2);
        tblPartido.setFooterVisible(false);



        cmbGrupos.getItems().addAll("GRUPO A", "GRUPO B", "GRUPO C" , "GRUPO D", "GRUPO E", "GRUPO F", "GRUPO G", "GRUPO H");
        cmbGrupos.setValue("GRUPO A");
        //txtEquipo1.setText(cmbGrupos.getSelectionModel().getSelectedItem() + "");

        cmbGrupos.getSelectionModel().selectFirst();


        cambioEstadoCmb();

        }

    private void cambioEstadoCmb() {
        jornadaGruposViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError()!=null){
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getConfirnacion() != null){
                getPrincipalController().sacarAlertConfirmation(listadoStateNew.getConfirnacion());
            }
            if (listadoStateNew.getEquipoList() != null)
            {

                tblPosiciones.getItems().clear();
                tblPosiciones.getItems().addAll(listadoStateNew.getEquipoList());
            }
            if (listadoStateNew.getPartidoList() != listadoState.getPartidoList()){
                txtEquipo1.setText("");
                txtEquipo2.setText("");
                txtGoles1.clear();
                txtGoles2.clear();
                btnGuardar.setDisable(true);
            }
            if (listadoStateNew.getPartidoList() != null){
                tblPartido.getItems().clear();
                tblPartido.getItems().addAll(listadoStateNew.getPartidoList());
            }


        });
    }


    public void grupoSelected(ActionEvent actionEvent) {
        String opcion = cmbGrupos.getSelectionModel().getSelectedItem().toString().toLowerCase();
        char cara = opcion.charAt(opcion.length()-1);
        jornadaGruposViewModel.getEquiposPartidosGrupo(cara - 97);

    }

    public void verDetallePartido(ActionEvent actionEvent) {

        if (tblPartido.getSelectionModel().getSelection() != null) {
            if (tblPartido.getSelectionModel().getSelection().values().size() != 1) {
                getPrincipalController().sacarAlertError("SELECCIONE SOLO UN PARTIDO");
            } else {
                Partido partido = (Partido) tblPartido.getSelectionModel().getSelection().values().stream().findFirst().orElse(null);
                txtEquipo1.setText(partido.getEquipos()[0].getNombre());
                txtEquipo2.setText(partido.getEquipos()[1].getNombre());
                txtGoles1.setText(partido.getGoles()[0] + "");
                txtGoles2.setText(partido.getGoles()[1] + "");
                btnGuardar.setDisable(false);
            }
        }
        else getPrincipalController().sacarAlertError("SELECCIONE UN PARTIDO");

    }

    public void guardarPartido(ActionEvent actionEvent) {
        getPrincipalController().sacarAlertError("LLEGO");
        int goles1 = Integer.parseInt(txtGoles1.getText());
        int goles2 = Integer.parseInt(txtGoles2.getText());
        if (goles1 >= 0 && goles1 < 10 && goles2 >= 0 && goles2 < 10) {
            getPrincipalController().sacarAlertError("GOLES VALUDOA");
            String opcion = cmbGrupos.getSelectionModel().getSelectedItem().toString().toLowerCase();
            char cara = opcion.charAt(opcion.length() - 1);
            jornadaGruposViewModel.guardarPartido(txtEquipo1.getText(), goles1, txtEquipo2.getText(), goles2, cara - 97);
        } else getPrincipalController().sacarAlertError("LOS EQUIPOS NO PUEDEN RECIBIR MÁS DE 9 GOLES");
    }

}

