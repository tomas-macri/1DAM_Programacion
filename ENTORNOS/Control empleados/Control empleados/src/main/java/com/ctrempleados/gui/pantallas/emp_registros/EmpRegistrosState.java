package com.ctrempleados.gui.pantallas.emp_registros;

import com.ctrempleados.domain.modelo.Registro;
import lombok.Data;

import java.util.List;

@Data
public class EmpRegistrosState {

    private final boolean registroModificado;
    private final List<Registro> registrosEmpleado;
    private final String error;

}
