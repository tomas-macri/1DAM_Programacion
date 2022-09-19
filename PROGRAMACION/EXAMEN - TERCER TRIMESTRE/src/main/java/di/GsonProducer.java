package di;

import com.google.gson.*;
//import gsonutils.RuntimeTypeAdapterFactory;
import jakarta.enterprise.inject.Produces;
import modelo.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class GsonProducer {


    @Produces
    public Gson getGson() {
//        RuntimeTypeAdapterFactory<Usuario> usuarioAdapter =
//                RuntimeTypeAdapterFactory
//                        .of(Usuario.class, "type", true)
//                        .registerSubtype(UsuarioEspecial.class)
//                        .registerSubtype(UsuarioNormal.class);
//
//        RuntimeTypeAdapterFactory<Producto> productosAdapter =
//                RuntimeTypeAdapterFactory
//                        .of(Producto.class, "type", true)
//                        .registerSubtype(ProductoCaducable.class)
//                        .registerSubtype(ProductoNormal.class);


        return new GsonBuilder()
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
//                .registerTypeAdapterFactory(usuarioAdapter)
//                .registerTypeAdapterFactory(productosAdapter)
                .create();
    }

}
