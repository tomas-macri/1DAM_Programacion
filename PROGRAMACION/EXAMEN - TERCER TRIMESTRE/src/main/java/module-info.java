module finalProg{
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;
    requires org.apache.logging.log4j;

  // requires jakarta.inject;
  //  requires jakarta.cdi;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires jakarta.jakartaee.web.api;
    requires com.google.gson;
    //requires jakarta.jakartaee.web.api;

//    exports ui.pantallas.compras;
//    exports ui.pantallas.editarUsuario;
//    exports ui.pantallas.editarProducto;
    exports ui.pantallas.principal;
//    exports ui.pantallas.registro;
    exports ui.pantallas.login;
//    exports ui.pantallas.mainAdmin;
//    exports ui.pantallas.mainCliente;
//    exports ui.main to javafx.graphics;
//    exports common.config;
//    exports common;
//    exports dao.impl;
//    exports servicios.impl;
    exports modelo;
//    exports modelo.Productos;
    //tests
    exports ui.common;
    exports ui.pantallas.nuevaPelicula;
    exports ui.pantallas.mainAdmin;
    exports ui.pantallas.nuevaSerie;
    exports ui.pantallas.mainCliente;
    exports servicios.impl;


//    opens ui.pantallas.compras to javafx.fxml;
//    opens ui.pantallas.editarUsuario to javafx.fxml;
//    opens ui.pantallas.editarProducto to javafx.fxml;
//    opens ui.pantallas.registro to javafx.fxml;
    opens ui.pantallas.principal to javafx.fxml;
//    opens ui.pantallas.mainCliente to javafx.fxml;
    //    opens ui.pantallas.pantalla1 to javafx.fxml;
    //    opens ui.pantallas.pantallaNueva to javafx.fxml;
    //    opens ui.pantallas.listado to javafx.fxml;
    opens ui.pantallas.login to  javafx.fxml;
    //    opens ui.pantallas.detalle to  javafx.fxml;
//        opens ui.pantallas.login;
    opens modelo to com.google.gson;
//    opens modelo.Productos to com.google.gson;
    //opens modelo.Productos;
//    opens ui.pantallas.mainAdmin to javafx.fxml;
    //opens ui.main;
    opens servicios;
    //opens gsonutils;
    opens di;

//    opens modelo.Usuarios;
    //opens common;
    opens config;
    opens dao;
   // opens uiGRAFICO;
//    opens css;
    opens fxml;
    opens ui.pantallas.commonPantallas;

    opens ui.common;
    opens ui.pantallas.nuevaPelicula;
    opens ui.pantallas.mainAdmin;
    opens ui.pantallas.nuevaSerie;
    opens ui.pantallas.mainCliente;
    opens dao.impl;
    opens servicios.impl;
}