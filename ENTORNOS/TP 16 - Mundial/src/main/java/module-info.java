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
    exports modelo;
    exports dao;
    exports di;

    opens ui.pantallas.common;
    opens ui.pantallas.principal;
    opens ui.main;
    opens config;
    opens fxml;

//    exports ui;
//    opens domain.modelo to javafx.base;
//    exports ui.pantallas.principal;
//
//    opens ui.pantallas.principal to javafx.fxml;

}
