package ui.pantallas.principal;

import domain.ServiciosEquipos;
import domain.modelo.Equipos;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import lombok.Getter;

@Getter
public class PrincipalViewModel {

    private ServiciosEquipos serviciosEquipos;

    public PrincipalViewModel() {
        _state = new SimpleObjectProperty<>(new PrincipalState(FXCollections.observableArrayList(),null));
    }


    public PrincipalViewModel(ServiciosEquipos serviciosPersonas) {
        this();
        this.serviciosEquipos = serviciosPersonas;
    }

    private ObjectProperty<PrincipalState> _state;
    public ReadOnlyObjectProperty<PrincipalState> getState() {
        return _state;
    }

    public void addPersona(Equipos equipos){
        serviciosEquipos.addPersona(equipos);
        _state.get().getEquipos().clear();
        _state.get().getEquipos().addAll(serviciosEquipos.getPersonas());

    }

    public void updatePersona(Equipos equipos) {
        _state.get().getEquipos().remove(equipos);
        _state.get().getEquipos().add(equipos);
    }

}
