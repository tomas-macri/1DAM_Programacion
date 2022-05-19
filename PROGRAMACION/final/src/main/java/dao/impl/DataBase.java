package dao.impl;

import  com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import config.Configuracion;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import modelo.Pelicula;
import modelo.Serie;
import modelo.Usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Log4j2
public class DataBase {
    private Gson gson;
    private Configuracion configuracion;

    /*public DataBase() {

        RuntimeTypeAdapterFactory<Usuario> usuarioAdapter =
                RuntimeTypeAdapterFactory
                        .of(Usuario.class, "type", true)
                        .registerSubtype(UsuarioEspecial.class)
                        .registerSubtype(UsuarioNormal.class);

        RuntimeTypeAdapterFactory<Pelicula> productosAdapter =
                RuntimeTypeAdapterFactory
                        .of(Pelicula.class, "type", true)
                        .registerSubtype(PeliculaCaducable.class)
                        .registerSubtype(PeliculaNormal.class);


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
                .registerTypeAdapterFactory(usuarioAdapter)
                .registerTypeAdapterFactory(productosAdapter)
                .create();


        this.configuracion = new Configuracion();
    }*/

    @Inject
    public DataBase(Gson gson, Configuracion configuracion) {
        this.gson = gson;
        this.configuracion = configuracion;
    }

    public LinkedHashMap<String, Usuario> loadUsuarios() {
        Type userListType = new TypeToken<LinkedHashMap<String, Usuario>>() {
        }.getType();

        LinkedHashMap<String, Usuario> clientes = null;
        try {
            clientes = gson.fromJson(
                    new FileReader(configuracion.getPathUsuarios()),
                    userListType);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        if (clientes == null){
            clientes = new LinkedHashMap<>();
        }
        return clientes;
    }


    public boolean saveUsuarios(LinkedHashMap<String, Usuario> clientes) {

        try (FileWriter w = new FileWriter(configuracion.getPathUsuarios())) {
            gson.toJson(clientes, w);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public List<Pelicula> loadPeliculas() {
        Type userListType = new TypeToken<List<Pelicula>>() {
        }.getType();

        List<Pelicula> peliculas = new ArrayList<>();
        try (FileReader w = new FileReader(configuracion.getPathPeliculas())) {
            peliculas = gson.fromJson(
                   w,
                    userListType);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        if (peliculas == null){
            peliculas = new ArrayList<>();
        }
        return peliculas;
    }

    public boolean savePeliculas(List<Pelicula> peliculas) {

        try (FileWriter w = new FileWriter(configuracion.getPathPeliculas())) {
            gson.toJson(peliculas, w);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public List<Serie> loadSeries() {
        Type userListType = new TypeToken<List<Serie>>() {
        }.getType();

        List<Serie> series = new ArrayList<>();
        try (FileReader w = new FileReader(configuracion.getPathSeries())) {
            series = gson.fromJson(
                    w,
                    userListType);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        if (series == null){
            series = new ArrayList<>();
        }
        return series;
    }

    public boolean saveSeries(List<Serie> series) {

        try (FileWriter w = new FileWriter(configuracion.getPathSeries())) {
            gson.toJson(series, w);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
