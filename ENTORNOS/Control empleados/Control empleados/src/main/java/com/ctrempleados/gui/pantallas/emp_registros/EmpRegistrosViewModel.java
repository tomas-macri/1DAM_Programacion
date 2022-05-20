package com.ctrempleados.gui.pantallas.emp_registros;

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
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j2
public class EmpRegistrosViewModel {

    private final ServiciosRegistros serviciosRegistros;
    private final ServiciosEmpleados serviciosEmpleados;
    private final ObjectProperty<EmpRegistrosState> state;

    @Inject
    public EmpRegistrosViewModel(ServiciosRegistros serviciosRegistros, ServiciosEmpleados serviciosEmpleados) {
        this.serviciosRegistros = serviciosRegistros;
        this.serviciosEmpleados = serviciosEmpleados;
        this.state = new SimpleObjectProperty<>(new EmpRegistrosState(false, null, null));
    }

    public ReadOnlyObjectProperty<EmpRegistrosState> getState() {
        return state;
    }

    public List<Registro> getRegistrosEmpleado(int actualUserCode) {
        Empleado empleado = serviciosEmpleados.getEmpleadoCodigo(actualUserCode).get();
        Either<String, List<Registro>> registros = serviciosRegistros.getRegistrosEmpleado(empleado);
        if (registros.isRight()) {
            state.set(new EmpRegistrosState(false, registros.get(), null));
        } else {
            state.set(new EmpRegistrosState(false, null, registros.getLeft()));
        }
        return state.get().getRegistrosEmpleado();
    }

    public void modificarRegistro(Registro registroSeleccionado, String horaInicio, String horaFin) {
        try {
            LocalDateTime diaHoraInicio = LocalDateTime.parse(horaInicio, DateTimeFormatter.ofPattern(ConstantesPantallas.DD_MM_YYYY_HH_MM_SS));
            LocalDateTime diaHoraFin = LocalDateTime.parse(horaFin, DateTimeFormatter.ofPattern(ConstantesPantallas.DD_MM_YYYY_HH_MM_SS));
            registroSeleccionado.setHoraEntrada(diaHoraInicio);
            registroSeleccionado.setHoraSalida(diaHoraFin);
            if (serviciosRegistros.modificarRegistro(registroSeleccionado)) {
                state.set(new EmpRegistrosState(true, null, null));
            } else {
                state.set(new EmpRegistrosState(false, null, ConstantesPantallas.ERROR_AL_MODIFICAR_EL_REGISTRO));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            state.set(new EmpRegistrosState(false, null, ConstantesPantallas.FORMATO_DE_FECHA_INCORRECTO));
        }
    }
}
