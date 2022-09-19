package di;

import com.google.gson.*;
import gsonutils.RuntimeTypeAdapterFactory;
import jakarta.enterprise.inject.Produces;
import modelo.Equipo;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class GsonProducer {


    @Produces
    public Gson getGson() {

        return new GsonBuilder().create();
    }

}
