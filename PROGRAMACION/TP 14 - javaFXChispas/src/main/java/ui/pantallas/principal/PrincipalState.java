package ui.pantallas.principal;


import domain.modelo.Equipos;
import javafx.collections.ObservableList;
import lombok.Data;

@Data
public class PrincipalState {

    private final ObservableList<Equipos> equipos;

    private final String error;

}
