package ui.pantallas.homeGrupos;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Equipo;
import serivcios.ServiciosEquipos;
import ui.pantallas.common.ConstantesPantallas;

import java.util.List;


public class HomeGruposViewModel {

    ServiciosEquipos serviciosEquipos;



    @Inject
    public HomeGruposViewModel(ServiciosEquipos serviciosEquipos) {
        this.serviciosEquipos = serviciosEquipos;
        _state = new SimpleObjectProperty<>(new HomeGruposState(null,null));
    }


    private final ObjectProperty<HomeGruposState> _state;
    public ReadOnlyObjectProperty<HomeGruposState> getState() {
        return _state;
    }


    public void loadGrupos() {
        HomeGruposState hg = null;
        List<Equipo> equipos = serviciosEquipos.getTodosLosEquipos();
        if (equipos==null)
            hg = new HomeGruposState(null, ConstantesPantallas.ERROR_NO_SE_HAN_PODIDO_CARGAR_LOS_EQUIPOS);
        else
            hg = new HomeGruposState(equipos,null);
        _state.setValue(hg);
    }
}
