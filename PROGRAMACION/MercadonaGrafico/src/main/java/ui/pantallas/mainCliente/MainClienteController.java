package ui.pantallas.mainCliente;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import modelo.Ingrediente;
import modelo.Tarjeta;
import ui.pantallas.commonPantallas.BasePantallaController;

import java.util.Comparator;


public class MainClienteController extends BasePantallaController implements Initializable {

    @FXML
    private MFXTextField txtNombre;

    @FXML
    private MFXTextField txtSaldo;

    @FXML
    private Text txtDescuento;

    @FXML
    private MFXTableView tblTarjetas;

    @FXML
    private MFXTableView tblIngredientes;


    private MainClienteViewModel viewModel;




    @Inject
    public MainClienteController(MainClienteViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        txtDescuento.setText("Bienvenido cliente normal");
    }

    @Override
    public void principalCargado() {
        MFXTableColumn<Ingrediente> ingredienteMFXTableColumn = new MFXTableColumn<>("Ingrediente", true, Comparator.comparing(Ingrediente::getNombre));
        ingredienteMFXTableColumn.setRowCellFactory(ingrediente -> new MFXTableRowCell<>(Ingrediente::getNombre));
        tblIngredientes.getTableColumns().addAll(ingredienteMFXTableColumn);

        MFXTableColumn<Tarjeta> nombreTarjColumn = new MFXTableColumn<>("Nombre", true, Comparator.comparing(Tarjeta::getNombre));
        MFXTableColumn<Tarjeta> saldoTarjColumn = new MFXTableColumn<>("Saldo", true, Comparator.comparing(Tarjeta::getSaldo));
        nombreTarjColumn.setRowCellFactory(tarjeta -> new MFXTableRowCell<>(Tarjeta::getNombre));
        saldoTarjColumn.setRowCellFactory(tarjeta -> new MFXTableRowCell<>(Tarjeta::getSaldo));
        tblTarjetas.getTableColumns().addAll(nombreTarjColumn, saldoTarjColumn);

        viewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError()!=null){
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getIngredienteList() != null)
            {
                tblIngredientes.getItems().clear();
                tblIngredientes.getItems().addAll(listadoStateNew.getIngredienteList());
            }
            if (listadoStateNew.getTarjetaList() != null){
                tblTarjetas.getItems().clear();
                tblTarjetas.getItems().addAll(listadoStateNew.getTarjetaList());
            }
            if (listadoStateNew.getDescuento() > 0){
                txtDescuento.setText("Hola especial. Tienes un descuento del " + listadoStateNew.getDescuento() + "%");
            }


        });
        viewModel.loadTablas(getPrincipalController().getUsuarioLogueado());
    }


    public void logout(ActionEvent actionEvent) {
        getPrincipalController().logout();
    }

    public void nuevaCompra(ActionEvent actionEvent) {
        getPrincipalController().nuevaCompra();
    }

    public void nuevaTarjeta(ActionEvent actionEvent) {
        viewModel.nuevaTarjeta(getPrincipalController().getUsuarioLogueado(), new Tarjeta(txtNombre.getText(), Integer.parseInt(txtSaldo.getText())));
    }
}
