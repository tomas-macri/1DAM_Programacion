package ui.pantallas.commonPantallas;

public enum Pantallas {


    LOGIN (ConstantesPantallas.FXML_LOGIN_FXML),
    REGISTRO(ConstantesPantallas.FXML_REGISTRO_FXML),
    MAINADMIN(ConstantesPantallas.FXML_MAIN_ADMIN_FXML),
    EDITAR(ConstantesPantallas.FXML_EDITAR_FXML),
    LISTADO ("/fxml/listado.fxml"),
    DETALLE ("/fxml/detalle.fxml"),
    PANTALLA1 ("/fxml/pantalla1.fxml"),
    PANTALLANUEVA (ConstantesPantallas.FXML_PANTALLA_NUEVA_FXML);

    public static final String FXML_EDITAR_FXML = "/fxml/editar.fxml";



    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}

}
