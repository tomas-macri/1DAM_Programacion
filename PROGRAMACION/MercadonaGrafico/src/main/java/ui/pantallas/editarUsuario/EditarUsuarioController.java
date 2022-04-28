package ui.pantallas.editarUsuario;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
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
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioEspecial;
import ui.pantallas.commonPantallas.BasePantallaController;
import ui.pantallas.commonPantallas.Pantallas;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class EditarUsuarioController extends BasePantallaController implements Initializable {

    @FXML
    private MFXSlider descuento;

    @FXML
    private Text txtCaduca;

    @FXML
    private MFXButton btnAgregar;

    @FXML
    private MFXTableView tblIngredientes;

    @FXML
    private MFXButton btnEditar;

    @FXML
    private TextField txtDNI;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtStock;



    @FXML
    private EditarUsuarioViewModel editarUsuarioViewModel;

    @Inject
    public EditarUsuarioController(EditarUsuarioViewModel editarUsuarioViewModel) {
        this.editarUsuarioViewModel = editarUsuarioViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void principalCargado() {
        Usuario userEditar = getPrincipalController().getUserEditar();
        Boolean editar = getPrincipalController().getIdBTN().equals("btnEditar")? true : false;
        if (editar) {
            btnAgregar.setVisible(false);
            btnEditar.setVisible(true);
        }
        else {
            btnAgregar.setVisible(true);
            btnEditar.setVisible(false);
        }
        if (userEditar instanceof UsuarioEspecial || !editar) {
            descuento.setDisable(false);
        }
        txtDNI.setText(userEditar.getDni());
        txtNombre.setText(userEditar.getNombre() + "");
        MFXTableColumn<Ingrediente> ingredienteMFXTableColumn = new MFXTableColumn<>("Ingrediente", true, Comparator.comparing(Ingrediente::getNombre));
        ingredienteMFXTableColumn.setRowCellFactory(ingrediente -> new MFXTableRowCell<>(Ingrediente::getNombre));
        tblIngredientes.getTableColumns().addAll(ingredienteMFXTableColumn);

        editarUsuarioViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError()!=null){
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getIngredienteList() != null)
            {
                tblIngredientes.getItems().clear();
                tblIngredientes.getItems().addAll(listadoStateNew.getIngredienteList());
            }
            descuento.setValue(0);
            descuento.setDisable(true);
            if (listadoStateNew.getDescuento() != 0){
                descuento.setDisable(false);
                descuento.setMax(100);
            }



        });
        editarUsuarioViewModel.loadIngredientes(userEditar);
    }


    public void editar(ActionEvent actionEvent) {
        //editarProductoViewModel.updateProduct(new Producto(txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()), getPrincipalController().getProdEditar().getListaIngredientes()), getPrincipalController().getProdEditar().getNombre());
        getPrincipalController().finEditar(Pantallas.MAINADMIN);
    }

    public void volver(ActionEvent actionEvent) {
        getPrincipalController().finEditar(Pantallas.MAINADMIN);
    }

    public void agregar(ActionEvent actionEvent) {
        //editarProductoViewModel.agregarProducto(new Producto(txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()), tblIngredientes.getItems()));
        getPrincipalController().finEditar(Pantallas.MAINADMIN);
    }
}
