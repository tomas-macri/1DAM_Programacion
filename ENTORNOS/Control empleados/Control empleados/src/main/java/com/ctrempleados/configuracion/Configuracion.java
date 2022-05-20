package com.ctrempleados.configuracion;

import com.ctrempleados.configuracion.common.Constantes;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Configuracion {

    private static Configuracion configuracion;
    private String pathJSONEmpleados;
    private String pathJSONFranquicias;
    private String pathJSONNominas;
    private String pathJSONRegistros;

    private Configuracion() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream(Constantes.CONFIG_PROPERTIES));
            pathJSONEmpleados = props.getProperty(Constantes.PATH_JSON_EMPLEADOS);
            pathJSONFranquicias = props.getProperty(Constantes.PATH_JSON_FRANQUICIAS);
            pathJSONNominas = props.getProperty(Constantes.PATH_JSON_NOMINAS);
            pathJSONRegistros = props.getProperty(Constantes.PATH_JSON_REGISTROS);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}

