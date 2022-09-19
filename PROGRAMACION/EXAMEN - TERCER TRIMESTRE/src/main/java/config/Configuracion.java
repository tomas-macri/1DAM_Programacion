package config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.ejb.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Getter
@Log4j2
@Singleton
public class Configuracion {


    private String pathPeliculas;
    private String pathUsuarios;
    private String pathSeries;

    public Configuracion(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

            try {
//                configuracion = new Configuracion();
//                Properties p = new Properties();
//                p.load(Configuracion.class.getClassLoader().getResourceAsStream("common.config.properties"));
//                configuracion.pathProductos = p.getProperty("pathProductos");
//                configuracion.pathUsuarios = p.getProperty("pathUsuarios");
                JsonNode node = mapper.readTree(
                        Configuracion.class.getClassLoader().getResourceAsStream("config.yml"));
                this.pathPeliculas = node.get("pathPeliculas").asText();
                this.pathSeries = node.get("pathSeries").asText();
                this.pathUsuarios = node.get("pathUsuarios").asText();
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
    }
}
