package ui.pantallas.principal;

import common.Constantes;
import dao.DaoEquipos;
import dao.DataBase;
import domain.ServiciosEquipos;
import domain.modelo.Equipo;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    private MFXTableView<Equipo> mfxTable;
    @FXML
    private MFXTextField txtNombre;
    @FXML
    private MFXTextField txtChampions;

    private PrincipalViewModel viewModel;



    public PrincipalController() {
        viewModel = new PrincipalViewModel(new ServiciosEquipos(new DaoEquipos(new DataBase())));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MFXTableColumn<Equipo> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Equipo::getNombre));
        MFXTableColumn<Equipo> championsColumn = new MFXTableColumn<>("Champions", true, Comparator.comparing(Equipo::getChampions));
        nameColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getNombre));
        championsColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Equipo::getChampions));
        championsColumn.setAlignment(Pos.CENTER_RIGHT);

        mfxTable.getTableColumns().addAll(nameColumn, championsColumn);
        mfxTable.getFilters().addAll(
                new StringFilter<>("Name", Equipo::getNombre),
                new IntegerFilter<>("Champions", Equipo::getChampions)
        );
        mfxTable.setItems(viewModel.getEquipos());
        mfxTable.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.values().stream().findFirst().ifPresent(equipo -> {
                    txtNombre.setText(equipo.getNombre());
                    txtChampions.setText(equipo.getChampions().toString());
                });
            }
        });


    }
    public void clickAgregar(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            Equipo e = new Equipo(txtNombre.getText(), Integer.parseInt(txtChampions.getText()));
            if (viewModel.addEquipo(e) == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("El equipo " + e.getNombre() + " no tiene un nombre valido o tiene menos de 0 champions.");
                a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("SE AGREGÓ " + e.getNombre() + " A LA LISTA.");
                a.showAndWait();
            }
            mfxTable.getItems().removeAll();
            mfxTable.setItems(FXCollections.observableArrayList(viewModel.getEquipos()));
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("HAGA CLICK CON EL BOTON IZQUIERDO");
            a.showAndWait();
        }
    }

    public void clickUpdatear(MouseEvent mouseEvent) {
        Equipo equipo = new Equipo(txtNombre.getText(), Integer.parseInt(txtChampions.getText()));
        Equipo equipoViejo = mfxTable.getSelectionModel().getSelection().values().stream().findFirst().orElse(null);
        if (equipoViejo != null) {
            if (viewModel.updateEquipo(equipoViejo, equipo) == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("El equipo " + equipo.getNombre() + " no tiene un nombre valido o tiene menos de 0 champions.");
                a.showAndWait();
            }
            else {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("SE ACTUALIZÓ " + equipo.getNombre() + " EN LA LISTA.");
                a.showAndWait();
                mfxTable.getItems().removeAll();
                mfxTable.setItems(FXCollections.observableArrayList(viewModel.getEquipos()));
            }
        }
        else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("SELECCIONE UN ELEMENTO DE LA LISTA.");
            a.showAndWait();
        }
    }

    public void clickEliminar(MouseEvent mouseEvent) {
        Equipo equipo = mfxTable.getSelectionModel().getSelection().values().stream().findFirst().orElse(null);
        if (equipo != null) {
            if (viewModel.deleteEquipo(equipo) == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(equipo.getNombre());
                a.showAndWait();

            } else {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("SE ELIMINÓ " + equipo.getNombre() + " DE LA LISTA.");
                a.showAndWait();
                mfxTable.getItems().removeAll();
                mfxTable.setItems(FXCollections.observableArrayList(viewModel.getEquipos()));
            }
        }
    }
}
