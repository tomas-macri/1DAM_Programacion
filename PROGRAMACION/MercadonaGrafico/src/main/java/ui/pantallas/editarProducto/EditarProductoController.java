package ui.pantallas.editarProducto;

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
import modelo.Ingrediente;
import modelo.Productos.Producto;
import modelo.Productos.ProductoCaducable;
import modelo.Productos.ProductoNormal;
import ui.pantallas.commonPantallas.BasePantallaController;
import ui.pantallas.commonPantallas.Pantallas;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditarProductoController extends BasePantallaController implements Initializable {

    @FXML
    private MFXDatePicker fecha;

    @FXML
    private Text txtCaduca;

    @FXML
    private MFXButton btnAgregar;

    @FXML
    private MFXTableView tblIngredientes;

    @FXML
    private MFXButton btnEditar;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;





    private EditarProductoViewModel editarProductoViewModel;

    @Inject
    public EditarProductoController(EditarProductoViewModel editarProductoViewModel) {
        this.editarProductoViewModel = editarProductoViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void principalCargado() {
        Producto prod = getPrincipalController().getProdEditar();
        boolean editar = getPrincipalController().getIdBTN().equals("btnEditar");
        if (editar) {

            btnAgregar.setVisible(false);
            btnEditar.setVisible(true);
        }
        else {
            btnAgregar.setVisible(true);
            btnEditar.setVisible(false);
        }
        if (prod instanceof ProductoCaducable || !editar) {
            fecha.setDisable(false);
            txtCaduca.setVisible(true);
            fecha.setVisible(true);
            fecha.setValue(LocalDate.now());
        }
        else{
            fecha.setVisible(false);
            txtCaduca.setVisible(false);
        }
        if (prod != null){
        txtNombre.setText(prod.getNombre());
        txtPrecio.setText(prod.getPrecio() + "");
        txtStock.setText(prod.getStock() + "");
        }
        MFXTableColumn<Ingrediente> ingredienteMFXTableColumn = new MFXTableColumn<>("Ingrediente", true, Comparator.comparing(Ingrediente::getNombre));
        ingredienteMFXTableColumn.setRowCellFactory(ingrediente -> new MFXTableRowCell<>(Ingrediente::getNombre));
        tblIngredientes.getTableColumns().addAll(ingredienteMFXTableColumn);

        editarProductoViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError()!=null){
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getIngredienteList() != null)
            {
                tblIngredientes.getItems().clear();
                tblIngredientes.getItems().addAll(listadoStateNew.getIngredienteList());
            }
            if (listadoStateNew.getCaducidad() != null){
                fecha.setDisable(false);
                fecha.setValue(listadoStateNew.getCaducidad());
            }

        });
        editarProductoViewModel.loadIngredientes(prod);
    }


    public void editar(ActionEvent actionEvent) {
        editarProductoViewModel.updateProduct(new ProductoNormal(txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()), getPrincipalController().getProdEditar().getListaIngredientes()), getPrincipalController().getProdEditar().getNombre());
        getPrincipalController().finEditar(Pantallas.MAINADMIN);
    }

    public void volver(ActionEvent actionEvent) {
        getPrincipalController().finEditar(Pantallas.MAINADMIN);
    }

    public void agregar(ActionEvent actionEvent) {
//        ERROR
//        if (!Objects.equals(fecha.getValue(), LocalDate.now().atStartOfDay())){
//            editarProductoViewModel.agregarProducto(new ProductoCaducable(txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()), new ArrayList<>(tblIngredientes.getItems()), fecha.getValue().atStartOfDay()));
//        }
        editarProductoViewModel.agregarProducto(new ProductoNormal(txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()), new ArrayList<>(tblIngredientes.getItems())));
        getPrincipalController().finEditar(Pantallas.MAINADMIN);
    }
}
