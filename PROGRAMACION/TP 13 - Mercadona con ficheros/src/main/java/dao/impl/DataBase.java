package dao.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import config.Configuracion;
import gsonutils.RuntimeTypeAdapterFactory;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import modelo.Productos.Producto;
import modelo.Productos.ProductoCaducable;
import modelo.Productos.ProductoNormal;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioEspecial;
import modelo.Usuarios.UsuarioNormal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        RuntimeTypeAdapterFactory<Producto> productosAdapter =
                RuntimeTypeAdapterFactory
                        .of(Producto.class, "type", true)
                        .registerSubtype(ProductoCaducable.class)
                        .registerSubtype(ProductoNormal.class);


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

    public List<Producto> loadProductos() {
        Type userListType = new TypeToken<List<Producto>>() {
        }.getType();

        List<Producto> productos = new ArrayList<>();
        try (FileReader w = new FileReader(configuracion.getPathProductos())) {
            productos = gson.fromJson(
                   w,
                    userListType);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        if (productos == null){
            productos = new ArrayList<>() {
            };
        }
        return productos;
    }

    public boolean saveProductos(List<Producto> productos) {

        try (FileWriter w = new FileWriter(configuracion.getPathProductos())) {
            gson.toJson(productos, w);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
