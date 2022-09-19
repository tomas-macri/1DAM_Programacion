package com.ctrempleados.di;

import com.google.gson.*;
import jakarta.enterprise.inject.Produces;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class ProducerGson {

    @Produces
    public Gson getGson() {

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
                .registerTypeAdapter(Period.class,
                        (JsonDeserializer<Period>) (json, type, jsonDeserializationContext) ->
                                Period.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(Period.class,
                        (JsonSerializer<Period>) (period, type, jsonSerializationContext) ->
                                new JsonPrimitive(period.toString()))
                .registerTypeAdapter(Duration.class,
                        (JsonDeserializer<Duration>) (json, type, jsonDeserializationContext) ->
                                Duration.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(Duration.class,
                        (JsonSerializer<Duration>) (duration, type, jsonSerializationContext) ->
                                new JsonPrimitive(duration.toString()))
                .create();
    }
}
