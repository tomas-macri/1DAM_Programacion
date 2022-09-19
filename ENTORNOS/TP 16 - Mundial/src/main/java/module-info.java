module Mundial {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;
    requires org.apache.logging.log4j;

    requires jakarta.inject;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
    requires jakarta.cdi;
    //requires jakarta.jakartaee.web.api;

    exports ui.main to javafx.graphics;
    exports ui.pantallas.homeGrupos;
    exports ui.pantallas.jornadaGrupos;
    exports ui.pantallas.estadisticas;
    exports modelo;
    exports dao;
    exports serivcios;
    exports di;

    opens ui.pantallas.common;
    opens ui.pantallas.principal;
    opens ui.pantallas.homeGrupos;
    opens ui.pantallas.jornadaGrupos;
    opens ui.pantallas.estadisticas;
    opens ui.main;
    opens config;
    opens fxml;
    opens modelo to com.google.gson;

//    exports ui;
//    opens domain.modelo to javafx.base;
//    exports ui.pantallas.principal;
//
//    opens ui.pantallas.principal to javafx.fxml;

}
