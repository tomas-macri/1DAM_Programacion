package dao;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import config.Configuracion;
import domain.modelo.Equipo;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private Gson gson;

    private Configuracion configuracion;

    public DataBase() {

        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                                LocalDateTime.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) ->
                                new JsonPrimitive(localDateTime.toString()))
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) ->
                                LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (localDateTime, type, jsonSerializationContext) ->
                                new JsonPrimitive(localDateTime.toString()))
                //.registerTypeAdapter(Equipo.classadapter)
                .create();


        this.configuracion = new Configuracion();
    }

    public List<Equipo> loadEquipos() {
        Type userListType = new TypeToken<List<Equipo>>() {
        }.getType();

        List<Equipo> productos = new ArrayList<>();
        try (FileReader w = new FileReader(configuracion.getPathEquipos())) {
            productos = gson.fromJson(
                   w,
                    userListType);
        } catch (IOException e) {
            System.out.println(e);
        }
        if (productos == null){
            productos = new ArrayList<>() {
            };
        }
        return productos;
    }

    public boolean saveEquipos(List<Equipo> equipos) {

        try (FileWriter w = new FileWriter(configuracion.getPathEquipos())) {
            gson.toJson(equipos, w);
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
