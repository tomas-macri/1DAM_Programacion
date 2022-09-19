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
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;
import ui.pantallas.commonPantallas.BasePantallaController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainAdminController extends BasePantallaController implements Initializable {

    @FXML
    private Text txtText;

    @FXML
    private MFXTableView<Usuario> tablaUsuarios;

    @FXML
    private MFXTableView<Producto> tablaProductos;

    private MainAdminViewModel mainAdminViewModel;

    @Inject
    public MainAdminController(MainAdminViewModel mainAdminViewModel) {
        this.mainAdminViewModel = mainAdminViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtText.setText("Bienvenido Administrador");
        tablaProductos.setVisible(false);
        MFXTableColumn<Producto> nameColumn = new MFXTableColumn<>("Producto", true, Comparator.comparing(Producto::getNombre));
        MFXTableColumn<Producto> precioColumn = new MFXTableColumn<>("Precio", true, Comparator.comparing(Producto::getPrecio));
        MFXTableColumn<Producto> stockColumn = new MFXTableColumn<>("Stock", true, Comparator.comparing(Producto::getStock));
        nameColumn.setRowCellFactory(producto -> new MFXTableRowCell<>(Producto::getNombre));
        precioColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Producto::getPrecio));
        stockColumn.setRowCellFactory(equipo -> new MFXTableRowCell<>(Producto::getStock));

        tablaProductos.getTableColumns().addAll(nameColumn, precioColumn, stockColumn);
        tablaProductos.getFilters().addAll(
                new StringFilter<>("Name", Producto::getNombre),
                new DoubleFilter<>("Precio", Producto::getPrecio),
                new IntegerFilter<>("Stock", Producto::getStock)
        );

        tablaUsuarios.setVisible(false);
        MFXTableColumn<Usuario> dni = new MFXTableColumn<>("DNI", true, Comparator.comparing(Usuario::getDni));
        MFXTableColumn<Usuario> nombre = new MFXTableColumn<>("Nombre", true, Comparator.comparing(Usuario::getNombre));
        dni.setRowCellFactory(usuario -> new MFXTableRowCell<>(Usuario::getDni));
        nombre.setRowCellFactory(usuario -> new MFXTableRowCell<>(Usuario::getNombre));
        tablaUsuarios.getTableColumns().addAll(dni, nombre);
        tablaUsuarios.getFilters().addAll(
                new StringFilter<>("DNI", Usuario::getDni),
                new StringFilter<>("Nombre", Usuario::getNombre)
        );

        mainAdminViewModel.getState().addListener((observable, oldValue, newValue) -> {

            if (newValue.getError() != null) {
                getPrincipalController().sacarAlertError(newValue.getError());
            }
            if (newValue.getListaProductos() != null) {

                tablaProductos.getItems().clear();
                tablaProductos.getItems().addAll(newValue.getListaProductos());
                tablaUsuarios.setVisible(false);
                tablaProductos.setVisible(true);
            } else if (newValue.getListaUsuarios() != null) {
                tablaUsuarios.getItems().clear();
                tablaUsuarios.getItems().addAll(newValue.getListaUsuarios());
                tablaProductos.setVisible(false);
                tablaUsuarios.setVisible(true);
            }
        });
    }


    public void listClientes(ActionEvent actionEvent) {
        tablaProductos.setVisible(false);
        tablaUsuarios.setVisible(true);
        mainAdminViewModel.getUsuarios();
    }

    public void listProductos(ActionEvent actionEvent) {
        tablaUsuarios.setVisible(false);
        tablaProductos.setVisible(true);
        mainAdminViewModel.getProductos();
    }

    public void editar(ActionEvent actionEvent) {
        if (tablaProductos.getSelectionModel().getSelection().values().stream().findFirst().orElse(null) != null) {
            Producto producto = tablaProductos.getSelectionModel().getSelection().values().stream().findFirst().orElse(null);
            if (producto != null) {
                getPrincipalController().editarProd(producto, ((MFXButton) actionEvent.getSource()).getId());
            }
        }
        else if (tablaUsuarios.getSelectionModel().getSelection().values().stream().findFirst().orElse(null) != null) {
            Usuario usuario = tablaUsuarios.getSelectionModel().getSelection().values().stream().findFirst().orElse(null);
            getPrincipalController().editarUser((usuario), ((MFXButton) actionEvent.getSource()).getId());
        }
        else {
            getPrincipalController().sacarAlertError("No se ha seleccionado ninguna fila");
        }
    }

    public void crear(ActionEvent actionEvent) {
        if (tablaProductos.isVisible()){
            getPrincipalController().editarProd(new Producto("",0,0, new ArrayList<>()), ((MFXButton)actionEvent.getSource()).getId());
        }
        else if (tablaUsuarios.isVisible()){
            getPrincipalController().editarUser(new Usuario("","", new ArrayList<>()), ((MFXButton)actionEvent.getSource()).getId());
        }
        else {
            getPrincipalController().sacarAlertError("Seleccione una tabla");
        }
    }


    public void eliminar(ActionEvent actionEvent) {

        if (tablaProductos.isVisible() || tablaUsuarios.isVisible()){
            if (tablaProductos.getSelectionModel().getSelection().values().stream().findFirst().orElse(null) != null) {
                Producto producto = tablaProductos.getSelectionModel().getSelection().values().stream().findFirst().orElse(null);
                if (producto != null) {
                    mainAdminViewModel.eliminarProducto(producto);
                }
            }
            else if (tablaUsuarios.getSelectionModel().getSelection().values().stream().findFirst().orElse(null) != null) {
                Usuario usuario = tablaUsuarios.getSelectionModel().getSelection().values().stream().findFirst().orElse(null);
                if (usuario != null) {
                    mainAdminViewModel.eliminarUsuario(usuario);
                }
            }
            else {
                getPrincipalController().sacarAlertError("No se ha seleccionado ninguna fila");
            }
        }
        else {
            getPrincipalController().sacarAlertError("Seleccione una tabla");
        }
    }
}
