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
import lombok.extern.log4j.Log4j2;

import serivcios.ServiciosPartidos;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;
//import ui.pantallas.login.LoginController;

import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PrincipalController extends BasePantallaController {

    @FXML
    private Menu menuHelp;
    // objeto especial para DI
    Instance<Object> instance;

    @FXML
    private MenuBar menuPrincipal;
    private Stage primaryStage;

    private PrincipalViewModel principalViewModel;

//    private Usuario actualUser;
//    private Cromo cromoSeleccionado;
//
//    public Usuario getActualUser() {
//        return actualUser;
//    }
//    public Cromo getActualCromo(){return cromoSeleccionado;}

    @FXML
    private BorderPane root;


    private Alert alert;

    private Pane pantallaBienvenida;


    @Inject
    public PrincipalController(Instance<Object> instance, PrincipalViewModel principalViewModel) {
       this.instance = instance;
       this.principalViewModel = principalViewModel;
       alert= new Alert(Alert.AlertType.NONE);
    }




    public void sacarAlertError(String mensaje)
    {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
        alert.showAndWait();
    }

    public void sacarAlertConfirmation(String mensaje)
    {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
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
        cambioPantalla(panePantalla);
        return panePantalla;
    }





    public void logout() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }


    public void initialize() {
        //menuPrincipal.setVisible(false);
       cargarPantalla(Pantallas.HOMEGRUPOS);
    }

    private void cargarPantalla(Pantallas pantalla) {

        switch (pantalla) {
//            case LISTADO:
//                cambioPantalla(cargarPantalla(pantalla.getRuta()));
//                break;
//            case PANTALLA1:
//                if (pantallaBienvenida == null){
//                    pantallaBienvenida = cargarPantalla(pantalla.getRuta());
//                }
//
//                cambioPantalla(pantallaBienvenida);
//                break;
            default:
                cambioPantalla(cargarPantalla(pantalla.getRuta()));
                break;
        }
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
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }




    public double getHeight() {
        return root.getScene().getWindow().getHeight();
    }

    public double getWidth()
    {
//        return 600;
        return root.getScene().getWindow().getWidth();
    }


    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem)actionEvent.getSource()).getId())
        {
            case "menuItemPartidos":
                cargarPantalla(Pantallas.JORNADA_GRUPOS);
                break;
            case "menuItemGrupos":
                cargarPantalla(Pantallas.HOMEGRUPOS);
                break;
            case "menuItemPantallaNueva":
                cargarPantalla(Pantallas.PANTALLANUEVA);
                break;
            case "menuItemEstadisticas":
                cargarPantalla(Pantallas.ESTADISTICAS);
                break;
            case "menuItemReiniciar":
                if (principalViewModel.reiniciarTodo()){
                    sacarAlertConfirmation("SE HAN REINICIADO LOS DATOS");
                    cargarPantalla(Pantallas.HOMEGRUPOS);
                }
                else {
                    sacarAlertError("SE PRODUJO UN ERROR");
                }
                break;

        }
    }

    //evento de otra pantalla
//    public void onLoginHecho(Usuario usuario) {
//        actualUser = usuario;
//        menuPrincipal.setVisible(true);
//        if (actualUser.getNombre().equals("admin")) {
//            menuHelp.setVisible(false);
//        }
//
//        cargarPantalla(Pantallas.PANTALLA1);
//    }
//
//    public void onSeleccionCromo(Cromo p) {
//        this.cromoSeleccionado = p;
//        cargarPantalla(Pantallas.DETALLE);
//
//
//
//    }
}
