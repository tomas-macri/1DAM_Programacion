package com.ctrempleados.gui.pantallas.common;

public enum Pantallas {

    BIENVENIDA(ConstantesPantallas.FXML_PANTALLA_INICIO_FXML),
    LOGIN(ConstantesPantallas.FXML_PANTALLA_LOGIN_FXML),
    EMPLEADOS_ADMIN(ConstantesPantallas.FXML_PANTALLA_EMPLEADOS_ADMIN_FXML),
    FRANQUICIAS_ADMIN(ConstantesPantallas.FXML_PANTALLA_FRANQUICIAS_ADMIN_FXML),
    REGISTROS_ADMIN(ConstantesPantallas.FXML_PANTALLA_REGISTROS_ADMIN_FXML),
    NOMINAS_ADMIN(ConstantesPantallas.FXML_PANTALLA_NOMINAS_ADMIN_FXML),
    FICHAR_EMPLEADO(ConstantesPantallas.FXML_PANTALLA_FICHAR_EMPLEADO_FXML),
    REGISTROS_EMPLEADO(ConstantesPantallas.FXML_PANTALLA_REGISTROS_EMPLEADO_FXML),
    NOMINAS_EMPLEADO(ConstantesPantallas.FXML_PANTALLA_NOMINAS_EMPLEADO_FXML);

    private final String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }


}
