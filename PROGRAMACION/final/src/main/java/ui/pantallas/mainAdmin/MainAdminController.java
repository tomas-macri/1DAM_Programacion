package ui.pantallas.mainAdmin;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import ui.pantallas.commonPantallas.BasePantallaController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainAdminController extends BasePantallaController implements Initializable {

    @FXML
    private Text txtText;

//    @FXML
//    private MFXTableView<Usuario> tablaSeries;
//
//    @FXML
//    private MFXTableView<Producto> tablaPeliculas;

//    private MainAdminViewModel mainAdminViewModel;
//
//    @Inject
//    public MainAdminController(MainAdminViewModel mainAdminViewModel) {
//        this.mainAdminViewModel = mainAdminViewModel;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtText.setText("Bienvenido Administrador");
    }
}
