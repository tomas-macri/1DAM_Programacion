package ui.pantallas.jornadaGrupos;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
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
        paisColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getNombre));
        paisColumn.setPrefWidth(paisColumn.getPrefWidth()*2);
        puntosColumns.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getPuntos));
        golesColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getGolesFavor));
        tblPosiciones.getTableColumns().addAll(paisColumn, puntosColumns, golesColumn);



        MFXTableColumn<Partido> equipo1 = new MFXTableColumn<>("Equipo", true);
        MFXTableColumn<Partido> goles1 = new MFXTableColumn<>("Goles", true);
        MFXTableColumn<Partido> goles2 = new MFXTableColumn<>("Goles", true);
        MFXTableColumn<Partido> equipo2 = new MFXTableColumn<>("Equipo", true);
        equipo1.setRowCellFactory(equipo -> new MFXTableRowCell<>(partido -> partido.getEquipos()[0]));
        goles1.setRowCellFactory(equipo -> new MFXTableRowCell<>(partido -> partido.getGoles()[0]));
        equipo2.setRowCellFactory(equipo -> new MFXTableRowCell<>(partido -> partido.getEquipos()[1]));
        goles2.setRowCellFactory(equipo -> new MFXTableRowCell<>(partido -> partido.getGoles()[1]));
        tblPartido.getTableColumns().addAll(equipo1, goles1, goles2, equipo2);


        cmbGrupos.getItems().addAll("GRUPO A", "GRUPO B", "GRUPO C" , "GRUPO D", "GRUPO E", "GRUPO F", "GRUPO G", "GRUPO H");
        cmbGrupos.setValue(1);
        txtEquipo1.setText(cmbGrupos.getSelectionModel().getSelectedItem() + "");




    }
}
