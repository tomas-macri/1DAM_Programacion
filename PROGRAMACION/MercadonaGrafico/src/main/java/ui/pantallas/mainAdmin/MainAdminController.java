package ui.pantallas.mainAdmin;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;
import ui.pantallas.commonPantallas.BasePantallaController;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainAdminController extends BasePantallaController implements Initializable {

    @FXML
    private Text txtText;

    @FXML
    private MFXTableView<Usuario> tablaUsuarios;

    @FXML
    private MFXTableView<Producto> tablaProductos;

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
    }


    public void listClientes(ActionEvent actionEvent) {
        tablaProductos.setVisible(false);
        tablaUsuarios.setVisible(true);
    }

    public void listProductos(ActionEvent actionEvent) {
        tablaUsuarios.setVisible(false);
        tablaProductos.setVisible(true);
    }
}
