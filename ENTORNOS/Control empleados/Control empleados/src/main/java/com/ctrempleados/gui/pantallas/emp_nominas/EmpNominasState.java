package com.ctrempleados.gui.pantallas.emp_nominas;

import com.ctrempleados.domain.modelo.Nomina;
import lombok.Data;

import java.util.List;

@Data
public class EmpNominasState {

    private final List<Nomina> nominas;
    private final String error;
}
