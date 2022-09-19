package ui.pantallas.mainCliente;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import modelo.Actor;
import modelo.Pelicula;
import modelo.Serie;
import ui.pantallas.commonPantallas.BasePantallaController;

import java.util.Comparator;


public class MainClienteController extends BasePantallaController implements Initializable {

    @FXML
    private MFXTextField txtNombre;

    @FXML
    private MFXTextField txtSaldo;

    @FXML
    private Text txtWelcome;

    @FXML
    private MFXTableView<Serie> tblSeries;

    @FXML
    private MFXTableView<Pelicula> tblPeliculas;

    @FXML
    private MFXTableView<Actor> tblActores;


    private MainClienteViewModel viewModel;




    @Inject
    public MainClienteController(MainClienteViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        txtWelcome.setText("Bienvenido cliente");
    }

    @Override
    public void principalCargado() {
        MFXTableColumn<Actor> actorMFXTableColumn = new MFXTableColumn<>("Actor", true, Comparator.comparing(Actor::getNombre));
        actorMFXTableColumn.setRowCellFactory(actor -> new MFXTableRowCell<>(Actor::getNombre));
        tblActores.getTableColumns().addAll(actorMFXTableColumn);

        MFXTableColumn<Serie> nombreSerieCol = new MFXTableColumn<>("Nombre", true, Comparator.comparing(Serie::getNombre));
        nombreSerieCol.setRowCellFactory(serie -> new MFXTableRowCell<>(Serie::getNombre));
        tblSeries.getTableColumns().addAll(nombreSerieCol);

        MFXTableColumn<Pelicula> nombrePeliCol = new MFXTableColumn<>("Nombre", true, Comparator.comparing(Pelicula::getNombre));
        nombrePeliCol.setRowCellFactory(peli -> new MFXTableRowCell<>(Pelicula::getNombre));
        tblPeliculas.getTableColumns().addAll(nombrePeliCol);

        viewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError()!=null){
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getActors() != null)
            {
                tblActores.getItems().clear();
                tblActores.getItems().addAll(listadoStateNew.getActors());
            }
            if (listadoStateNew.getSeries() != null){
                tblSeries.getItems().clear();
                tblSeries.getItems().addAll(listadoStateNew.getSeries());
            }
           if (listadoStateNew.getPelis() != null){
               tblPeliculas.getItems().clear();
               tblPeliculas.getItems().addAll(listadoStateNew.getPelis());
           }

        });
        viewModel.loadTablas();
    }

    public void filtrarActor(ActionEvent actionEvent) {
        if (tblActores.getSelectionModel().getSelection().values().size() == 0){
            getPrincipalController().sacarAlertError("SELECCIONE UN ACTOR");
        }
        else {
            tblActores.getSelectionModel().getSelection().values().stream().findFirst()
                    .ifPresent(actor -> viewModel.loadTablasFiltadasActor(actor));
        }
    }

    public void listarTodo(ActionEvent actionEvent) {
        viewModel.loadTablas();
    }
}
