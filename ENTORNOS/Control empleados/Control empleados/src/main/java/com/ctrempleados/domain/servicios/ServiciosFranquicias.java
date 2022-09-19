package com.ctrempleados.domain.servicios;

import com.ctrempleados.domain.modelo.Franquicia;
import io.vavr.control.Either;

import java.util.List;

public interface ServiciosFranquicias {
    Either<String, Franquicia> getFranquicia(String nombre);

    Either<String, List<Franquicia>> getFranquicias();

    boolean crearFranquicia(String nombre, String ubicacion);

    boolean modificarFranquicia(Franquicia franquicia);

    boolean eliminarFranquicia(String nombre);
}
