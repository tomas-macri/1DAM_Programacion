module com.example.ctrempleados {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;
    requires org.apache.logging.log4j;
    requires io.vavr;
    requires com.google.gson;
    requires jakarta.inject;
    requires jakarta.cdi;

    exports com.ctrempleados.configuracion;
    exports com.ctrempleados.gui.main;
    exports com.ctrempleados.gui.main.common;
    exports com.ctrempleados.domain.modelo.common;
    exports com.ctrempleados.domain.servicios.common;
    exports com.ctrempleados.gui.pantallas.principal;
    exports com.ctrempleados.gui.pantallas.common;
    exports com.ctrempleados.gui.pantallas.login;
    exports com.ctrempleados.gui.pantallas.inicio;
    exports com.ctrempleados.gui.pantallas.adm_empleados;
    exports com.ctrempleados.gui.pantallas.adm_franquicias;
    exports com.ctrempleados.gui.pantallas.adm_registros;
    exports com.ctrempleados.gui.pantallas.adm_nominas;
    exports com.ctrempleados.gui.pantallas.emp_fichar;
    exports com.ctrempleados.gui.pantallas.emp_registros;
    exports com.ctrempleados.gui.pantallas.emp_nominas;
    exports com.ctrempleados.configuracion.common;
    exports com.ctrempleados.dao;
    exports com.ctrempleados.domain.servicios;
    exports com.ctrempleados.domain.modelo;
    exports com.ctrempleados.di;

    opens com.ctrempleados.domain.modelo;
    opens com.ctrempleados.domain.modelo.common;
    opens com.ctrempleados.domain.servicios;
    opens com.ctrempleados.domain.servicios.common;
    opens com.ctrempleados.configuracion.common;
    opens com.ctrempleados.configuracion;

    opens com.ctrempleados.gui.main;
    opens com.ctrempleados.gui.main.common;
    opens com.ctrempleados.dao.common;

    opens com.ctrempleados.gui.pantallas.common;
    opens com.ctrempleados.gui.pantallas.login;
    opens com.ctrempleados.gui.pantallas.principal;
    opens com.ctrempleados.gui.pantallas.inicio;
    opens com.ctrempleados.gui.pantallas.adm_empleados;
    opens com.ctrempleados.gui.pantallas.adm_franquicias;
    opens com.ctrempleados.gui.pantallas.adm_registros;
    opens com.ctrempleados.gui.pantallas.adm_nominas;
    opens com.ctrempleados.gui.pantallas.emp_fichar;
    opens com.ctrempleados.gui.pantallas.emp_registros;
    opens com.ctrempleados.gui.pantallas.emp_nominas;

    opens css;
    opens fxml;
    opens media;
    exports com.ctrempleados.dao.impl;
    exports com.ctrempleados.domain.servicios.impl;
    opens com.ctrempleados.domain.servicios.impl;

}