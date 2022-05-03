package ui.pantallas.compras;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import modelo.Ingrediente;
import modelo.ProductoComprado;
import modelo.Productos.Producto;
import ui.pantallas.commonPantallas.BasePantallaController;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
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
        MFXTableColumn<Producto> nombProdMFXTableColumn = new MFXTableColumn<>("Nombre", true, Comparator.comparing(Producto::getNombre));
        nombProdMFXTableColumn.setRowCellFactory(nombre -> new MFXTableRowCell<>(Producto::getNombre));
        MFXTableColumn<Producto> precioProdMFXTableColumn = new MFXTableColumn<>("Precio", true, Comparator.comparing(Producto::getPrecio));
        precioProdMFXTableColumn.setRowCellFactory(precio -> new MFXTableRowCell<>(Producto::getPrecio));

        MFXTableColumn<ProductoComprado> nombProdCompMFXTableColumn = new MFXTableColumn<>("Nombre", true, Comparator.comparing(productoComprado -> productoComprado.getProducto().getNombre()));
        nombProdCompMFXTableColumn.setRowCellFactory(nombre -> new MFXTableRowCell<>(producto -> producto.getProducto().getNombre()));
        MFXTableColumn<ProductoComprado> precioProdCompMFXTableColumn = new MFXTableColumn<>("Precio", true, Comparator.comparing(productoComprado -> productoComprado.getProducto().getPrecio()));
        precioProdCompMFXTableColumn.setRowCellFactory(precio -> new MFXTableRowCell<>(productoComprado -> productoComprado.getProducto().getPrecio()));
        MFXTableColumn<ProductoComprado> cantProdCompMFXTableColumn = new MFXTableColumn<>("Cantidad", true, Comparator.comparing(ProductoComprado::getCantidad));
        cantProdCompMFXTableColumn.setRowCellFactory(precio -> new MFXTableRowCell<>(ProductoComprado::getCantidad));


        tblProdDisponibles.getTableColumns().addAll(nombProdMFXTableColumn, precioProdMFXTableColumn);
        tblCarrito.getTableColumns().addAll(nombProdCompMFXTableColumn, precioProdCompMFXTableColumn, cantProdCompMFXTableColumn);
        comprasViewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.error != null) {
                getPrincipalController().sacarAlertError(newValue.error);
            }
            else if (newValue.productosList != null && newValue.productosComprados != null) {
                tblProdDisponibles.getItems().clear();
                tblProdDisponibles.getItems().addAll(newValue.productosList);

                tblCarrito.getItems().clear();
                tblCarrito.getItems().addAll(newValue.productosComprados);
            }
        });
        comprasViewModel.getProductosDisponibles(getPrincipalController().getUsuarioLogueado(), comprasViewModel.getProductos());
    }
}
