package ui.pantallas.estadisticas;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Equipo;
import serivcios.ServiciosEquipos;
import ui.pantallas.jornadaGrupos.JornadaGruposState;

import java.util.List;

public class EstadisticasViewModel {

    ServiciosEquipos serviciosEquipos;

    @Inject
    public EstadisticasViewModel(ServiciosEquipos serviciosEquipos) {
        this.serviciosEquipos = serviciosEquipos;
        _state = new SimpleObjectProperty<>(new EstadisticasState(null,null));
    }

    private final ObjectProperty<EstadisticasState> _state;
    public ReadOnlyObjectProperty<EstadisticasState> getState() {
        return _state;
    }

    public void cargarEquipos(){
        EstadisticasState es;
        List<Equipo> list = serviciosEquipos.getTodosLosEquipos();
        if (!list.isEmpty()){
            es = new EstadisticasState(null, list);
        }
        else {
            es = new EstadisticasState("NO SE HAN PODIDO CARGAR LOS EQUIPOS", null);
        }
        _state.setValue(es);
    }


}
