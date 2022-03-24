package config;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Log4j2
public class Configuracion {


    private String pathProductos;
    private String pathUsuarios;


    private Configuracion(){}

    private static Configuracion configuracion;

    public static synchronized Configuracion getInstance(){

        if (configuracion == null)
        {
            try {
                configuracion = new Configuracion();
                Properties p = new Properties();
                p.load(Configuracion.class.getClassLoader().getResourceAsStream("config.properties"));
                configuracion.pathProductos = p.getProperty("pathProductos");
                configuracion.pathUsuarios = p.getProperty("pathUsuarios");
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        return configuracion;
    }

    public String getPathProductos() {
        return pathProductos;
    }

    public void setPathProductos(String pathProductos) {
        this.pathProductos = pathProductos;
    }

    public String getPathUsuarios() {
        return pathUsuarios;
    }

    public void setPathUsuarios(String pathUsuarios) {
        this.pathUsuarios = pathUsuarios;
    }
}
