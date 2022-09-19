package ui.pantallas.nuevaSerie;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modelo.Actor;
import modelo.Episodio;
import modelo.Pelicula;
import modelo.Serie;
import ui.pantallas.commonPantallas.BasePantallaController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class NuevaSerieController extends BasePantallaController implements Initializable {

    @FXML
    private MFXTableView<Actor> tblActors;

    @FXML
    private MFXTableView<Episodio> tblEp;



    @FXML
    private TextField txtNombreEp;

    @FXML
    private TextField txtNombreActor;

    @FXML
    private TextField txtNombreSerie;




    private NuevaSerieViewModel nuevaSerieViewModel;

    @Inject
    public NuevaSerieController(NuevaSerieViewModel nuevaSerieViewModel) {
        this.nuevaSerieViewModel = nuevaSerieViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void principalCargado() {
        MFXTableColumn<Episodio> nombreEp = new MFXTableColumn<>("Nombre", true, Comparator.comparing(Episodio::getNombre));
        nombreEp.setRowCellFactory(episodio -> new MFXTableRowCell<>(Episodio::getNombre));
//        MFXTableColumn<Episodio> actorsEp = new MFXTableColumn<>("Actores", true, Comparator.comparing(episodio -> episodio.getActores().toString()));
//        nombreEp.setRowCellFactory(actor -> new MFXTableRowCell<>(Episodio::getActores));
        tblEp.getTableColumns().addAll(nombreEp);

        MFXTableColumn<Actor> actorMFXTableColumn = new MFXTableColumn<>("Actor", true, Comparator.comparing(Actor::getNombre));
        actorMFXTableColumn.setRowCellFactory(actor -> new MFXTableRowCell<>(Actor::getNombre));
        tblActors.getTableColumns().addAll(actorMFXTableColumn);
        nuevaSerieViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError()!=null){
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getConfirmacion() != null){
                getPrincipalController().sacarAlertConfirmation(listadoStateNew.getConfirmacion());
            }
        });
    }



    public void addActor(ActionEvent actionEvent) {
        tblActors.getItems().add(new Actor(txtNombreActor.getText()));
        txtNombreActor.clear();
    }

    public void addEp(ActionEvent actionEvent) {
        if (nuevaSerieViewModel.validEp(txtNombreEp.getText(), new ArrayList<>(tblActors.getItems()))){
            Episodio ep = new Episodio(txtNombreEp.getText(), new ArrayList<>(tblActors.getItems()));
            tblEp.getItems().add(ep);
            txtNombreEp.clear();
            tblActors.getItems().clear();
        }
    }

    public void addSerie(ActionEvent actionEvent) {
        nuevaSerieViewModel.agregarSerie(new Serie(txtNombreSerie.getText(), new ArrayList<>(tblEp.getItems())));
        txtNombreSerie.clear();
        tblActors.getItems().clear();
        tblEp.getItems().clear();
    }
}
