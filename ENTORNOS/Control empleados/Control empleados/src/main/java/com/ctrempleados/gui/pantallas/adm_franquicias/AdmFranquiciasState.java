package com.ctrempleados.gui.pantallas.adm_franquicias;

import com.ctrempleados.domain.modelo.Franquicia;
import lombok.Data;

import java.util.List;

@Data
public class AdmFranquiciasState {

    private final boolean franquiciaAñadida;
    private final boolean franquiciaModificada;
    private final boolean franquiciaEliminada;
    private final List<Franquicia> franquicias;
    private final String error;
}
