package com.ctrempleados.gui.pantallas.emp_fichar;

import lombok.Data;

@Data
public class EmpFicharState {

    private final boolean entradaRegistrada;
    private final boolean salidaRegistrada;
    private final String error;
}
