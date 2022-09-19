package com.ctrempleados.gui.pantallas.adm_registros;

import com.ctrempleados.domain.modelo.Registro;
import com.ctrempleados.domain.servicios.ServiciosRegistros;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class AdmRegistrosViewModel {

    private final ServiciosRegistros serviciosRegistros;
    private final ObjectProperty<AdmRegistrosState> state;

    @Inject
    public AdmRegistrosViewModel(ServiciosRegistros serviciosRegistros) {
        this.serviciosRegistros = serviciosRegistros;
        this.state = new SimpleObjectProperty<>(new AdmRegistrosState(false, null, null));
    }

    public ReadOnlyObjectProperty<AdmRegistrosState> getState() {
        return state;
    }

    public List<Registro> getRegistros() {
        Either<String, List<Registro>> registros = serviciosRegistros.getRegistros();
        if (registros.isRight()) {
            state.set(new AdmRegistrosState(false, registros.get(), null));
        } else {
            state.set(new AdmRegistrosState(false, null, registros.getLeft()));
        }
        return state.get().getRegistros();
    }

    public void validarRegistro(Registro registroSeleccionado) {
        if (serviciosRegistros.validarRegistro(registroSeleccionado)) {
            state.set(new AdmRegistrosState(true, null, null));
        } else {
            state.set(new AdmRegistrosState(false, null, ConstantesPantallas.REGISTRO_NO_VALIDADO));
        }
    }
}
