package ui.pantallas.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import lombok.extern.log4j.Log4j2;
import modelo.Usuarios.Usuario;
import ui.pantallas.commonPantallas.BasePantallaController;
import ui.pantallas.commonPantallas.ConstantesPantallas;
import ui.pantallas.commonPantallas.Pantallas;

import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PrincipalController {

    @FXML
    // objeto especial para DI
    Instance<Object> instance;

    @FXML
    private Stage primaryStage;

    private Usuario actualUser;

    public Usuario getActualUser() {
        return actualUser;
    }

    @FXML
    private BorderPane root;


    private Alert alert;

    private Pane pantallaBienvenida;


    @Inject
    public PrincipalController(Instance<Object> instance) {
       this.instance = instance;
       alert= new Alert(Alert.AlertType.NONE);
    }

    private void cargarPantalla(Pantallas pantalla) {

        switch (pantalla) {
            case MAINADMIN:

                cargarPantalla(pantalla.getRuta());
                break;
            case LOGIN:
                cargarPantalla(pantalla.getRuta());
                break;
            default:
                cargarPantalla(pantalla.getRuta());
                break;
        }
    }


    public void sacarAlertError(String mensaje)
    {
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
            log.error(e.getMessage(),e);
        }
        root.setCenter(panePantalla);
        return panePantalla;
    }





    public void logout() {
        actualUser = null;
        cargarPantalla(Pantallas.LOGIN);
    }



    public void initialize() {
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

    public void exit(ActionEvent actionEvent) {
//        primaryStage.close();
//        Platform.exit();
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    @FXML
    private void cambiarcss(ActionEvent actionEvent) {
        System.out.println(primaryStage.getScene().getRoot().getStylesheets().stream().findFirst().get());


        primaryStage.getScene().getRoot().getStylesheets().clear();



        primaryStage.getScene().getRoot().getStylesheets().add(getClass().getResource("/css/darkmode.css").toExternalForm());

    }



    public double getHeight() {
        return root.getScene().getWindow().getHeight();
    }

    public double getWidth()
    {
//        return 600;
        return root.getScene().getWindow().getWidth();
    }




    public void irAlLogin(ActionEvent actionEvent) {
        cargarPantalla(Pantallas.LOGIN);
    }

    //evento de otra pantalla
    public void onLoginHecho(Usuario usuario) {
        actualUser = usuario;
        Pantallas pantalla = Pantallas.PANTALLA1;
        if (actualUser.isAdmin()) {
            pantalla = Pantallas.MAINADMIN;

        }
        cargarPantalla(pantalla);
    }

    public void doRegistro(Pantallas pantalla) {
        cargarPantalla(pantalla);
    }


    public void cambiarPantalla(Pantallas pantalla1) {
    }


    public void listarClientes(ActionEvent actionEvent) {

    }
}
