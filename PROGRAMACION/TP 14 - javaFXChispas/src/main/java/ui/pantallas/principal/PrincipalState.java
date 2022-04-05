package ui.pantallas.principal;


import domain.modelo.Persona;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import lombok.Data;

import java.util.List;

@Data
public class PrincipalState {

    private final ObservableList<Persona> personas;

    private final String error;

}
