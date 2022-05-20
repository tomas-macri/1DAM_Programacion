package com.ctrempleados.gui.pantallas.principal;


import com.ctrempleados.gui.pantallas.common.BasePantallaController;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import com.ctrempleados.gui.pantallas.common.Pantallas;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

@Log4j2
public class PrincipalController {

    // objeto especial para DI
    final Instance<Object> instance;
    private final Alert alert;
    private Stage primaryStage;
    private int actualUserCodigo;
    private double xOffset;
    private double yOffset;

    @FXML
    private BorderPane root;
    @FXML
    private HBox windowHeader;
    @FXML
    private MFXFontIcon closeIcon;
    @FXML
    private MFXFontIcon minimizeIcon;
    @FXML
    private MFXFontIcon alwaysOnTopIcon;

    @FXML
    private MenuBar menuPrincipal;
    @FXML
    private Menu menuAdministracion;
    @FXML
    private Menu menuEmpleado;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }

    public void initialize() {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showAlertConfirmClose());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) root.getScene().getWindow()).setIconified(true));
        alwaysOnTopIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean newVal = !primaryStage.isAlwaysOnTop();
            alwaysOnTopIcon.pseudoClassStateChanged(PseudoClass
                    .getPseudoClass(ConstantesPantallas.ALWAYS_ON_TOP), newVal);
            primaryStage.setAlwaysOnTop(newVal);
        });

        windowHeader.setOnMousePressed(event -> {
            xOffset = primaryStage.getX() - event.getScreenX();
            yOffset = primaryStage.getY() - event.getScreenY();
        });
        windowHeader.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + xOffset);
            primaryStage.setY(event.getScreenY() + yOffset);
        });
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    public int getActualUserCode() {
        return actualUserCodigo;
    }

    public double getWidth() {
        return root.getScene().getWindow().getWidth();
    }

    private void showAlertConfirmClose() {
        Alert alertCerrar = new Alert(Alert.AlertType.WARNING);
        alertCerrar.getButtonTypes().remove(ButtonType.OK);
        alertCerrar.getButtonTypes().add(ButtonType.CANCEL);
        alertCerrar.getButtonTypes().add(ButtonType.YES);
        alertCerrar.setTitle(ConstantesPantallas.SALIR);
        alertCerrar.setContentText(ConstantesPantallas.SEGURO_QUE_QUIERE_CERRAR_LA_APLICACION);
        alertCerrar.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alertCerrar.showAndWait();
        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                Platform.exit();
            }
        });
    }

    public void showAlert(Alert.AlertType alertType, String titulo, String mensaje) {
        alert.setAlertType(alertType);
        alert.setTitle(titulo);
        alert.getDialogPane().setId("alert");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    private void cargarPantalla(Pantallas pantalla) {
        Pane panePantalla;
        ResourceBundle r = ResourceBundle.getBundle(ConstantesPantallas.I_18_N_TEXTOS_UI);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            fxmlLoader.setResources(r);
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(pantalla.getRuta()));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
            root.setCenter(panePantalla);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @FXML
    private void menuOnClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case ConstantesPantallas.MENU_ITEM_PANTALLA_INICIO:
                cargarPantalla(Pantallas.BIENVENIDA);
                break;
            case ConstantesPantallas.MENU_ITEM_EMPLEADOS_ADMIN:
                cargarPantalla(Pantallas.EMPLEADOS_ADMIN);
                break;
            case ConstantesPantallas.MENU_ITEM_FRANQUICIAS_ADMIN:
                cargarPantalla(Pantallas.FRANQUICIAS_ADMIN);
                break;
            case ConstantesPantallas.MENU_ITEM_REGISTROS_ADMIN:
                cargarPantalla(Pantallas.REGISTROS_ADMIN);
                break;
            case ConstantesPantallas.MENU_ITEM_NOMINAS_ADMIN:
                cargarPantalla(Pantallas.NOMINAS_ADMIN);
                break;
            case ConstantesPantallas.MENU_ITEM_FICHAR_EMPLEADO:
                cargarPantalla(Pantallas.FICHAR_EMPLEADO);
                break;
            case ConstantesPantallas.MENU_ITEM_REGISTROS_EMPLEADO:
                cargarPantalla(Pantallas.REGISTROS_EMPLEADO);
                break;
            case ConstantesPantallas.MENU_ITEM_NOMINAS_EMPLEADO:
                cargarPantalla(Pantallas.NOMINAS_EMPLEADO);
                break;
        }
    }

    @FXML
    private void cambiarcss() {
        if (primaryStage.getScene().getRoot().getStylesheets().stream().findFirst().isEmpty()
                || (primaryStage.getScene().getRoot().getStylesheets().stream().findFirst().isPresent()
                && primaryStage.getScene().getRoot().getStylesheets().stream().findFirst().get().contains(ConstantesPantallas.STYLE))) {
            try {
                primaryStage.getScene().getRoot().getStylesheets().clear();
                primaryStage.getScene().getRoot().getStylesheets().add(getClass().getResource(ConstantesPantallas.CSS_DARKMODE_CSS).toExternalForm());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } else {
            try {
                primaryStage.getScene().getRoot().getStylesheets().clear();
                primaryStage.getScene().getRoot().getStylesheets().add(getClass().getResource(ConstantesPantallas.CSS_STYLE_CSS).toExternalForm());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    @FXML
    private void acercaDe() {
        showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.ACERCA_DE, ConstantesPantallas.DATOS_AUTOR);
    }

    @FXML
    private void logout() {
        actualUserCodigo = -1;
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    @FXML
    private void exit() {
        showAlertConfirmClose();
    }

    public void onLoginHecho(int codigoAcceso) {
        actualUserCodigo = codigoAcceso;
        menuPrincipal.setVisible(true);
        menuAdministracion.setVisible(false);
        menuEmpleado.setVisible(true);
        if (actualUserCodigo == (ConstantesPantallas.CODIGO_ADMIN)) {
            menuAdministracion.setVisible(true);
            menuEmpleado.setVisible(false);
        }
        cargarPantalla(Pantallas.BIENVENIDA);
    }
}