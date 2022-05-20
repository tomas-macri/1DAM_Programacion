package com.ctrempleados.gui.pantallas.adm_nominas;

import com.ctrempleados.domain.modelo.Nomina;
import com.ctrempleados.domain.servicios.ServiciosNominas;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class AdmNominasViewModel {

    private final ServiciosNominas serviciosNominas;
    private final ObjectProperty<AdmNominasState> state;

    @Inject
    public AdmNominasViewModel(ServiciosNominas serviciosNominas) {
        this.serviciosNominas = serviciosNominas;
        this.state = new SimpleObjectProperty<>(new AdmNominasState(false, false, null, null));
    }

    public ReadOnlyObjectProperty<AdmNominasState> getState() {
        return state;
    }

    public List<Nomina> getNominas() {
        Either<String, List<Nomina>> nominas = serviciosNominas.getNominas();
        if (nominas.isRight()) {
            state.set(new AdmNominasState(false, false, nominas.get(), null));
        } else {
            state.set(new AdmNominasState(false, false, null, nominas.getLeft()));
        }
        return state.get().getNominas();
    }

    public void pagarNomina(Nomina nominaSeleccionada) {
        if (serviciosNominas.pagarNomina(nominaSeleccionada)) {
            state.set(new AdmNominasState(true, false, null, null));
        } else {
            state.set(new AdmNominasState(false, false, null, ConstantesPantallas.ERROR_AL_PAGAR_LA_NOMINA));
        }
    }

    public void pagarTodasNominas() {
        if (serviciosNominas.pagarTodasNominas()) {
            state.set(new AdmNominasState(true, false, null, null));
        } else {
            state.set(new AdmNominasState(false, false, null, ConstantesPantallas.ERROR_AL_PAGAR_LAS_NOMINAS));
        }
    }

    public void crearTodasNominas() {
        if (serviciosNominas.crearTodasNominas()) {
            state.set(new AdmNominasState(false, true, null, null));
        } else {
            state.set(new AdmNominasState(false, false, null, ConstantesPantallas.ERROR_AL_CREAR_LAS_NOMINAS));
        }
    }
}