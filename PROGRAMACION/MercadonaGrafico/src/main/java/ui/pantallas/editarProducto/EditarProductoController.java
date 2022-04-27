package ui.pantallas.editarProducto;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import modelo.Ingrediente;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;
import ui.pantallas.commonPantallas.BasePantallaController;
import ui.pantallas.commonPantallas.Pantallas;
import ui.pantallas.mainAdmin.MainAdminViewModel;

import java.awt.*;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class EditarProductoController extends BasePantallaController implements Initializable {
    @FXML
    public MFXTableView aaa;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;



    @FXML
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
        txtNombre.setText(prod.getNombre());
        txtPrecio.setText(prod.getPrecio() + "");
        txtStock.setText(prod.getStock() + "");
        MFXTableColumn<Ingrediente> ingredienteMFXTableColumn = new MFXTableColumn<>("Ingrediente", true, Comparator.comparing(Ingrediente::getNombre));
        ingredienteMFXTableColumn.setRowCellFactory(ingrediente -> new MFXTableRowCell<>(Ingrediente::getNombre));
        aaa.getTableColumns().addAll(ingredienteMFXTableColumn);

        editarProductoViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError()!=null){
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getIngredienteList() != null)
            {
                aaa.getItems().clear();
                aaa.getItems().addAll(listadoStateNew.getIngredienteList());
            }


        });
        editarProductoViewModel.loadIngredientes(prod);
    }


    public void volver(ActionEvent actionEvent) {
        //editarProductoViewModel.updateProduct(new Producto(txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()), getPrincipalController().getProdEditar().getListaIngredientes()), getPrincipalController().getProdEditar().getNombre());
        getPrincipalController().finEditar(Pantallas.MAINADMIN);
    }
}
