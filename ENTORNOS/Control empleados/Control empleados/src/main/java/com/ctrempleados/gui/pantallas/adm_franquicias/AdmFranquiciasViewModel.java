package com.ctrempleados.gui.pantallas.adm_franquicias;

import com.ctrempleados.domain.modelo.Franquicia;
import com.ctrempleados.domain.servicios.ServiciosFranquicias;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class AdmFranquiciasViewModel {

    private final ServiciosFranquicias serviciosFranquicias;
    private final ObjectProperty<AdmFranquiciasState> state;

    @Inject
    public AdmFranquiciasViewModel(ServiciosFranquicias serviciosFranquicias) {
        this.serviciosFranquicias = serviciosFranquicias;
        state = new SimpleObjectProperty<>(new AdmFranquiciasState(false,
                false, false, null, null));
    }

    public ReadOnlyObjectProperty<AdmFranquiciasState> getState() {
        return state;
    }

    public List<Franquicia> getFranquicias() {
        Either<String, List<Franquicia>> franquicias = serviciosFranquicias.getFranquicias();
        if (franquicias.isRight()) {
            state.set(new AdmFranquiciasState(false, false,
                    false, franquicias.get(), null));
        } else {
            state.set(new AdmFranquiciasState(false, false,
                    false, null, franquicias.getLeft()));
        }
        return state.get().getFranquicias();
    }

    public void addFranquicia(String nombre, String ubicacion) {
        if (serviciosFranquicias.crearFranquicia(nombre, ubicacion)) {
            state.set(new AdmFranquiciasState(true, false, false, null, null));
        } else {
            state.set(new AdmFranquiciasState(false, false, false, null, ConstantesPantallas.ERROR_AL_CREAR_LA_FRANQUICIA));
        }
    }

    public void updateFranquicia(Franquicia franquiciaSeleccionada) {
        if (serviciosFranquicias.modificarFranquicia(franquiciaSeleccionada)) {
            state.set(new AdmFranquiciasState(false, true, false, null, null));
        } else {
            state.set(new AdmFranquiciasState(false, false, false, null, ConstantesPantallas.ERROR_AL_MODIFICAR_LA_FRANQUICIA));
        }
    }

    public void deleteFranquicia(Franquicia franquiciaSeleccionada) {
        if (serviciosFranquicias.eliminarFranquicia(franquiciaSeleccionada.getNombre())) {
            state.set(new AdmFranquiciasState(false, false, true, null, null));
        } else {
            state.set(new AdmFranquiciasState(false, false, false, null, ConstantesPantallas.ERROR_AL_ELIMINAR_LA_FRANQUICIA));
        }
    }
}
