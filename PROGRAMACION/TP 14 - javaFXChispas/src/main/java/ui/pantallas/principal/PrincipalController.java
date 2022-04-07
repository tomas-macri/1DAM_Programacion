package ui.pantallas.principal;

import common.Constantes;
import dao.DaoEquipos;
import dao.DataBase;
import domain.ServiciosEquipos;
import domain.modelo.Equipos;
import io.github.palexdev.materialfx.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {


    @FXML
    private Constantes common;

    private final String nombreDefecto = "Alejandro";
    @FXML
    private MFXTextField nombre;
    @FXML
    private MFXTextField edad;
    @FXML
    private MFXButton borrar;
    @FXML
    private MFXComboBox combo2;
    @FXML
    private MFXDatePicker fecha;
    @FXML
    private MFXListView<Equipos> listado;
    @FXML
    private MFXComboBox<String> combo;

    private PrincipalViewModel viewModel;

    @FXML
    private TableColumn<Equipos, String> columnNombre;
    @FXML
    private TableColumn<Equipos, Integer> columnEdad;

    @FXML
    private TableView<Equipos> table;

    @FXML
    private TextField txtNombre;

    public PrincipalController() {
        viewModel = new PrincipalViewModel(new ServiciosEquipos(new DaoEquipos(new DataBase())));
    }

    @FXML
    private void saludar() {
        viewModel.addPersona(new Equipos(nombreDefecto, 10));

        String nombre = !txtNombre.getText().isBlank()
                ? txtNombre.getText() : nombreDefecto;
        String text = "hola " + nombre;
        fecha.setValue(LocalDate.of(2019, 1, 1));

        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(text);
        a.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        table.setItems(viewModel.getState().get().getEquipos());
        combo.getItems().addAll("Alejandro", "Pedro", "Juan");
        List<Equipos> equipos = new ArrayList<>();

        equipos.add(new Equipos("Alejandro", 10));
        equipos.add(new Equipos("Pedro", 20));
        equipos.add(new Equipos("Juan", 30));

        listado.getItems().addAll(equipos);

        listado.getSelectionModel().setAllowsMultipleSelection(false);

        fecha.setValue(LocalDate.of(2019, 1, 1));

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getError() != null) {
                //sacar un alert
            }
        });

    }

    @FXML
    private void verSeleccionTabla(ActionEvent actionEvent) {

        Equipos p = table.getSelectionModel().getSelectedItem();
        if (p != null) {
            viewModel.updatePersona(new Equipos(p.getNombre(), p.getChampions() + 1));
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(p.getNombre());
            a.showAndWait();
        }
    }

    @FXML
    private void selection(ActionEvent actionEvent) {
        String nombre = combo.getSelectionModel().getSelectedItem();
        if (nombre != null) {

            combo2.getItems().clear();
            switch (nombre) {
                case "Alejandro":
                    combo2.getItems().addAll("Moreno", "malote", "guappo");
                    break;
                case "Pedro":
                    combo2.getItems().addAll("pollavieja", "marichulo", "gordo");
                    break;
                case "Juan":
                    combo2.getItems().addAll("papagayo", "papagayo", "papagayo");
                    break;
            }
        }
    }


    @FXML
    private void borrarElemento(ActionEvent actionEvent) {
        Equipos p = listado.getSelectionModel().getSelection().values().stream().findFirst().orElse(null);
        if (p != null) {
            listado.getItems().remove(p);
        }
    }


    @FXML
    private void update(MouseEvent mouseEvent) {
        Equipos p = listado.getSelectionModel().getSelection().values().stream().findFirst().orElse(null);
        if (p != null) {
            nombre.setText(p.getNombre());
            edad.setText(p.getChampions().toString());
        }

    }
}
