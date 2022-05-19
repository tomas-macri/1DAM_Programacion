package ui.pantallas.nuevaPelicula;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modelo.Actor;
import modelo.Pelicula;
import ui.pantallas.commonPantallas.BasePantallaController;
import ui.pantallas.commonPantallas.Pantallas;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class NuevaPeliculaController extends BasePantallaController implements Initializable {

    @FXML
    private MFXTableView tblActors;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNombreActor;




    private NuevaPeliculaViewModel nuevaPeliculaViewModel;

    @Inject
    public NuevaPeliculaController(NuevaPeliculaViewModel nuevaPeliculaViewModel) {
        this.nuevaPeliculaViewModel = nuevaPeliculaViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void principalCargado() {
        MFXTableColumn<Actor> actorMFXTableColumn = new MFXTableColumn<>("Actor", true, Comparator.comparing(Actor::getNombre));
        actorMFXTableColumn.setRowCellFactory(actor -> new MFXTableRowCell<>(Actor::getNombre));
        tblActors.getTableColumns().addAll(actorMFXTableColumn);

        nuevaPeliculaViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError()!=null){
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getConfirmacion() != null){
                getPrincipalController().sacarAlertConfirmation(listadoStateNew.getConfirmacion());
            }
        });
    }

    public void agregar(ActionEvent actionEvent) {
        nuevaPeliculaViewModel.agregarPelicula(new Pelicula(txtNombre.getText(), new ArrayList<>(tblActors.getItems())));
        txtNombre.clear();
        tblActors.getItems().clear();
    }

    public void addActor(ActionEvent actionEvent) {
        tblActors.getItems().add(new Actor(txtNombreActor.getText()));
        txtNombreActor.clear();
    }
}
