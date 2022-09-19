package com.ctrempleados.gui.pantallas.emp_nominas;

import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Nomina;
import com.ctrempleados.domain.servicios.ServiciosEmpleados;
import com.ctrempleados.domain.servicios.ServiciosNominas;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class EmpNominasViewModel {

    private final ServiciosNominas serviciosNominas;
    private final ServiciosEmpleados serviciosEmpleados;
    private final ObjectProperty<EmpNominasState> state;

    @Inject
    public EmpNominasViewModel(ServiciosNominas serviciosNominas, ServiciosEmpleados serviciosEmpleados) {
        this.serviciosNominas = serviciosNominas;
        this.serviciosEmpleados = serviciosEmpleados;
        this.state = new SimpleObjectProperty<>(new EmpNominasState(null, null));
    }

    public ReadOnlyObjectProperty<EmpNominasState> getState() {
        return state;
    }

    public List<Nomina> getNominasEmpleado(int actualUserCode) {
        Empleado empleado = serviciosEmpleados.getEmpleadoCodigo(actualUserCode).get();
        Either<String, List<Nomina>> either = serviciosNominas.getNominasEmpleado(empleado);
        if (either.isRight()) {
            if (either.get().isEmpty()) {
                state.set(new EmpNominasState(null, ConstantesPantallas.NO_HAY_NOMINAS_PARA_ESTE_EMPLEADO));
            } else {
                state.set(new EmpNominasState(either.get(), null));
            }
        } else {
            state.set(new EmpNominasState(null, either.getLeft()));
        }
        return state.get().getNominas();
    }
}
