package com.ctrempleados.gui.pantallas.common;

import com.ctrempleados.gui.pantallas.principal.PrincipalController;


public class BasePantallaController {

    private PrincipalController principalController;

    public PrincipalController getPrincipalController() {
        return principalController;
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void principalCargado() {

    }
}
