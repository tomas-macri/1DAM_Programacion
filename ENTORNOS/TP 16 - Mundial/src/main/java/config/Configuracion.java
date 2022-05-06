package config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Getter
@Log4j2
@Singleton
public class Configuracion {


    private String pathPartidos;
    private String pathEquipos;

    public Configuracion(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        try {
            JsonNode node = mapper.readTree(
                    Configuracion.class.getClassLoader().getResourceAsStream("config/config.yml"));
            this.pathPartidos = node.get("pathPartidos").asText();
            this.pathEquipos = node.get("pathEquipos").asText();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }
}
