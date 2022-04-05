package ui.pantallas.principal;

import domain.ServiciosPersonas;
import domain.modelo.Persona;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

@Getter
public class PrincipalViewModel {

    private ServiciosPersonas serviciosPersonas;

    public PrincipalViewModel() {
        _state = new SimpleObjectProperty<>(new PrincipalState(FXCollections.observableArrayList(),null));
    }


    public PrincipalViewModel(ServiciosPersonas serviciosPersonas) {
        this();
        this.serviciosPersonas = serviciosPersonas;
    }

    private ObjectProperty<PrincipalState> _state;
    public ReadOnlyObjectProperty<PrincipalState> getState() {
        return _state;
    }

    public void addPersona(Persona persona){
        serviciosPersonas.addPersona(persona);
        _state.get().getPersonas().clear();
        _state.get().getPersonas().addAll(serviciosPersonas.getPersonas());

    }

    public void updatePersona(Persona persona) {
        _state.get().getPersonas().remove(persona);
        _state.get().getPersonas().add(persona);
    }

}
