package ui.pantallas.commonPantallas;

public enum Pantallas {


    LOGIN (ConstantesPantallas.FXML_LOGIN_FXML),
    REGISTRO(ConstantesPantallas.FXML_REGISTRO_FXML),
    MAINADMIN(ConstantesPantallas.FXML_MAIN_ADMIN_FXML),
    NUEVAPELI(ConstantesPantallas.FXML_NUEVA_PELI_FXML),
    EDITARUSUARIO(ConstantesPantallas.FXML_EDITAR_USUARIO_FXML),
    MAINCLIENTE(ConstantesPantallas.FXML_MAIN_CLIENTE_FXML),
    COMPRA(ConstantesPantallas.FXML_COMPRAS_FXML),
    LISTADO ("/fxml/listado.fxml"),
    DETALLE ("/fxml/detalle.fxml"),
    NUEVASERIE(ConstantesPantallas.FXML_NUEVA_SERIE_FXML),
    PANTALLANUEVA (ConstantesPantallas.FXML_PANTALLA_NUEVA_FXML);


    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}

}
