package ui.pantallas.jornadaGrupos;

import jakarta.inject.Inject;
import javafx.fxml.Initializable;
import ui.pantallas.common.BasePantallaController;

import java.net.URL;
import java.util.ResourceBundle;

public class JornadaGruposController extends BasePantallaController implements Initializable {


    JornadaGruposViewModel jornadaGruposViewModel;

    @Inject
    public JornadaGruposController(JornadaGruposViewModel jornadaGruposViewModel) {
        this.jornadaGruposViewModel = jornadaGruposViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
