package ui.pantallas.compras;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import ui.pantallas.commonPantallas.BasePantallaController;

import java.net.URL;
import java.util.ResourceBundle;

public class ComprasController extends BasePantallaController implements Initializable {


    @FXML
    private MFXTableView tblProdDisponibles;

    @FXML
    private MFXTableView tblCarrito;

    @FXML
    private MFXButton btnPagar;

    @FXML
    private MFXButton btnEliminar;

    @FXML
    private MFXButton btnVaciar;

    @FXML
    private MFXTextField txtProducto;

    @FXML
    private MFXTextField txtCantidad;

    @FXML
    private MFXButton btnAgregar;

    private ComprasViewModel comprasViewModel;

    @Inject
    public ComprasController(ComprasViewModel comprasViewModel) {
        this.comprasViewModel = comprasViewModel;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void principalCargado() {
    }
}
