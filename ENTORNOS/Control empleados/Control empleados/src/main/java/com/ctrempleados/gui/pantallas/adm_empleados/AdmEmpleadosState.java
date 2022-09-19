package com.ctrempleados.gui.pantallas.adm_empleados;

import com.ctrempleados.domain.modelo.Empleado;
import lombok.Data;

import java.util.List;

@Data
public class AdmEmpleadosState {
    private final boolean EmpleadoNuevo;
    private final boolean EmpleadoModificado;
    private final boolean EmpleadoEliminado;
    private final List<Empleado> empleados;
    private final String error;
}
