package com.ctrempleados.gui.pantallas.adm_nominas;

import com.ctrempleados.domain.modelo.Nomina;
import lombok.Data;

import java.util.List;

@Data
public class AdmNominasState {

    private final boolean pagarNominas;
    private final boolean crearNominas;
    private final List<Nomina> nominas;
    private final String error;

}
