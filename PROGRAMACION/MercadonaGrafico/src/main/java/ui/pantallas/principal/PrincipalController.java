package ui.pantallas.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;
import ui.pantallas.commonPantallas.BasePantallaController;
import ui.pantallas.commonPantallas.Pantallas;

import java.io.IOException;
import java.util.Optional;

@Log4j2
@Data
public class PrincipalController {

    public MenuItem menuItemExit;
    @FXML
    private MenuBar menuPrincipal;
    @FXML
    // objeto especial para DI
    Instance<Object> instance;

    @FXML
    private Stage primaryStage;

    private Usuario userEditar;
    private Producto prodEditar;
    private Usuario usuarioLogueado;


    @FXML
    private BorderPane root;


    private Alert alert;

    private Pane pantallaBienvenida;
    private String idBTN;


    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void cargarPantalla(Pantallas pantalla) {

        switch (pantalla) {
            case MAINADMIN:

                cargarPantalla(pantalla.getRuta());
                break;
            case LOGIN:
                menuPrincipal.setVisible(false);
                cargarPantalla(pantalla.getRuta());
                break;
            default:
                cargarPantalla(pantalla.getRuta());
                break;
        }
    }


    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();


        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        root.setCenter(panePantalla);
        return panePantalla;
    }


    public void logout() {
        usuarioLogueado = null;
        cargarPantalla(Pantallas.LOGIN);
    }


    public void initialize() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);

    }

    private void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Quit application");
        alert.setContentText("Close without saving?");
        alert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();


        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.CANCEL) {
                event.consume();
            }
        });
    }

    public void help(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText("Ayuda");
        alert.setContentText("Este es un mensaje de ayuda");
        alert.showAndWait();
    }


    public void setStage(Stage stage) {
        primaryStage = stage;
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    public void irAlLogin(ActionEvent actionEvent) {
        cargarPantalla(Pantallas.LOGIN);
    }

    //evento de otra pantalla
    public void onLoginHecho(Usuario usuario) {
        usuarioLogueado = usuario;
        menuPrincipal.setVisible(true);
        Pantallas pantalla = Pantallas.MAINCLIENTE;
        if (usuario.isAdmin()) {
            menuItemExit.setVisible(true);
            menuItemExit.setDisable(false);
            pantalla = Pantallas.MAINADMIN;
        }
        cargarPantalla(pantalla);
    }

    public void doRegistro(Pantallas pantalla) {
        cargarPantalla(pantalla);
    }

    public void finEditar(Pantallas pantalla) {
        cargarPantalla(pantalla);
    }


    public void cambiarPantalla(Pantallas pantalla1) {
    }


    public void listarClientes(ActionEvent actionEvent) {

    }

    public void editarProd(Producto producto, String idBTNEditarNuevo) {
            idBTN = idBTNEditarNuevo;
            prodEditar = producto;
            cargarPantalla(Pantallas.EDITARPRODUCTO);
        }

    public void editarUser(Usuario usuario, String id) {
        idBTN = id;
        userEditar = usuario;
        cargarPantalla(Pantallas.EDITARUSUARIO);
    }

}


