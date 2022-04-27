module Mercadona.Grafico{

        requires javafx.graphics;
        requires javafx.controls;
        requires javafx.fxml;
        requires MaterialFX;

        requires lombok;
        requires org.apache.logging.log4j;

        //requires jakarta.inject;
        //requires jakarta.cdi;
        requires com.fasterxml.jackson.databind;
        requires com.fasterxml.jackson.dataformat.yaml;
        requires jakarta.jakartaee.web.api;
        requires com.google.gson;
        //requires jakarta.jakartaee.web.api;

        exports ui.pantallas.editarProducto;
        exports ui.pantallas.principal;
        exports ui.pantallas.registro;
        exports ui.pantallas.login;
        exports ui.pantallas.mainAdmin;
        exports ui.main to javafx.graphics;
        exports common.config;
        exports common;
        exports dao.impl;
        exports servicios.impl;
        exports modelo;

    opens ui.pantallas.editarProducto to javafx.fxml;
    opens ui.pantallas.registro to javafx.fxml;
    opens ui.pantallas.principal to javafx.fxml;
//    opens ui.pantallas.pantalla1 to javafx.fxml;
//    opens ui.pantallas.pantallaNueva to javafx.fxml;
//    opens ui.pantallas.listado to javafx.fxml;
    opens ui.pantallas.login to  javafx.fxml;
//    opens ui.pantallas.detalle to  javafx.fxml;
//        opens ui.pantallas.login;
        opens modelo to com.google.gson;
        opens modelo.Productos to com.google.gson;
        opens ui.pantallas.mainAdmin to javafx.fxml;
        opens ui.main;
        opens servicios;
        opens gsonutils;
        opens di;
        opens modelo.Usuarios;
        opens common;
        opens dao;
        opens uiGRAFICO;
//    opens css;
        opens fxml;
        opens ui.pantallas.commonPantallas;

//    exports ui;
//    opens domain.modelo to javafx.base;
//    exports ui.pantallas.principal;
//
//    opens ui.pantallas.principal to javafx.fxml;
        }