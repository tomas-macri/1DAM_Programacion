package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        ResourceBundle r = ResourceBundle.getBundle("textosI18N", Locale.getDefault());

        FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/fxml/principal.fxml"),r);

        AnchorPane root = loaderMenu.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle(r.getString("app.title"));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(true);

    }
}
