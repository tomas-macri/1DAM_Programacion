package ui.pantallas.principal;


import domain.modelo.Equipo;
import javafx.collections.ObservableList;
import lombok.Data;

@Data
public class PrincipalStateBORRAR {

    private final ObservableList<Equipo> equipos;

    private final String error;

}
