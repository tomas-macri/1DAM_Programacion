package ui.pantallas.jornadaGrupos;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import serivcios.ServiciosEquipos;
import ui.pantallas.homeGrupos.HomeGruposState;

public class JornadaGruposViewModel {

    ServiciosEquipos serviciosEquipos;

    @Inject
    public JornadaGruposViewModel(ServiciosEquipos serviciosEquipos) {
        this.serviciosEquipos = serviciosEquipos;
        _state = new SimpleObjectProperty<>(new JornadaGruposState(null,null));
    }

    private final ObjectProperty<JornadaGruposState> _state;
    public ReadOnlyObjectProperty<JornadaGruposState> getState() {
        return _state;
    }



}
