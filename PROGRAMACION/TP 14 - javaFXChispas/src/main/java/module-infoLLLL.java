module javafx {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires jakarta.jakartaee.web.api;
    exports ui;

    opens domain.modelo to javafx.base;
    exports ui.pantallas.principal;
    exports common to javafx.fxml;
    opens ui.pantallas.principal to javafx.fxml;

}
