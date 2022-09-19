package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import config.Configuracion;
import jakarta.inject.Inject;
import modelo.Equipo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import modelo.Partido;


@Log4j2
public class DataBase {
    private Gson gson;
    private Configuracion configuracion;

    @Inject
    public DataBase(Gson gson, Configuracion configuracion) {
        this.gson = gson;
        this.configuracion = configuracion;
    }

    public List<Equipo> loadEquipos() {
        Type userListType = new TypeToken<List<Equipo>>() {
        }.getType();

        List<Equipo> equipos = null;
        try {
            equipos = gson.fromJson(
                    new FileReader(configuracion.getPathEquipos()),
                    userListType);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        if (equipos == null){
            equipos = new ArrayList<>();
        }
        return equipos;
    }


    public boolean saveEquipos(List<Equipo> equipos) {

        try (FileWriter w = new FileWriter(configuracion.getPathEquipos())) {
            gson.toJson(equipos, w);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public List<Partido> loadPartidos() {
        Type userListType = new TypeToken<List<Partido>>() {
        }.getType();

        List<Partido> partidos = new ArrayList<>();
        try (FileReader w = new FileReader(configuracion.getPathPartidos())) {
            partidos = gson.fromJson(
                    w,
                    userListType);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        if (partidos == null){
            partidos = new ArrayList<>() {
            };
        }
        return partidos;
    }

    public boolean savePartidos(List<Partido> partidos) {

        try (FileWriter w = new FileWriter(configuracion.getPathPartidos())) {
            gson.toJson(partidos, w);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
