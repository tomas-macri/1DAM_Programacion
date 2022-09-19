package com.ctrempleados.dao;

import com.ctrempleados.domain.modelo.Franquicia;
import io.vavr.control.Either;

import java.util.List;

public interface DaoFranquicias {
    Either<String, List<Franquicia>> getFranquicias();

    Either<String, Franquicia> getFranquicia(String nombre);

    boolean addFranquicia(Franquicia franquicia);

    boolean deleteFranquicia(Franquicia franquicia);
}
