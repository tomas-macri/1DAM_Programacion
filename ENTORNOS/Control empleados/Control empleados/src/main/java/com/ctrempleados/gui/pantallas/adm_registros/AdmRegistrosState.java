package com.ctrempleados.gui.pantallas.adm_registros;

import com.ctrempleados.domain.modelo.Registro;
import lombok.Data;

import java.util.List;

@Data
public class AdmRegistrosState {

    private final boolean registroValdado;
    private final List<Registro> registros;
    private final String error;
}
