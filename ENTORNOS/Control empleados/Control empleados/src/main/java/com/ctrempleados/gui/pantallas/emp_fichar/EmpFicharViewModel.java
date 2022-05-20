package com.ctrempleados.gui.pantallas.emp_fichar;

import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Registro;
import com.ctrempleados.domain.servicios.ServiciosEmpleados;
import com.ctrempleados.domain.servicios.ServiciosRegistros;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;
import java.util.List;

public class EmpFicharViewModel {

    private final ServiciosRegistros serviciosRegistros;
    private final ServiciosEmpleados serviciosEmpleados;
    private final ObjectProperty<EmpFicharState> state;

    @Inject
    public EmpFicharViewModel(ServiciosRegistros serviciosRegistros, ServiciosEmpleados serviciosEmpleados) {
        this.serviciosRegistros = serviciosRegistros;
        this.serviciosEmpleados = serviciosEmpleados;
        this.state = new SimpleObjectProperty<>(new EmpFicharState(false, false, null));
    }

    public ReadOnlyObjectProperty<EmpFicharState> getState() {
        return state;
    }

    public Empleado getEmpleadoLogueado(int actualUserCode) {
        return serviciosEmpleados.getEmpleadoCodigo(actualUserCode).get();
    }

    public boolean isEntradaRegistrada(Empleado empleadoLogueado) {
        Either<String, List<Registro>> either = serviciosRegistros.getRegistrosEmpleado(empleadoLogueado);
        if (either.isRight()) {
            return either.get().stream()
                    .anyMatch(registro -> registro.getHoraSalida() == null);
        } else {
            return false;
        }
    }

    public void registrarEntrada(Empleado empleadoLogueado) {
        if (serviciosRegistros.iniciarRegistro(empleadoLogueado)) {
            state.set(new EmpFicharState(true, false, null));
        } else {
            state.set(new EmpFicharState(false, false, ConstantesPantallas.NO_SE_PUDO_INICIAR_EL_REGISTRO));
        }
    }

    public void registrarSalida(Empleado empleadoLogueado) {
        if (serviciosRegistros.finalizarRegistro(empleadoLogueado)) {
            state.set(new EmpFicharState(false, true, null));
        } else {
            state.set(new EmpFicharState(false, false, ConstantesPantallas.NO_SE_PUDO_FINALIZAR_EL_REGISTRO));
        }
    }

    public LocalDateTime getHoraEntrada(Empleado empleadoLogueado) {
        Either<String, List<Registro>> either = serviciosRegistros.getRegistrosEmpleado(empleadoLogueado);
        if (either.isRight()) {
            return either.get().stream()
                    .filter(registro -> registro.getHoraSalida() == null)
                    .map(Registro::getHoraEntrada)
                    .findFirst().orElse(null);
        } else {
            return null;
        }
    }
}
