package com.ctrempleados.dao;

import com.ctrempleados.domain.modelo.Registro;
import io.vavr.control.Either;

import java.util.List;

public interface DaoRegistros {
    Either<String, List<Registro>> getRegistros();

    Either<String, Registro> getRegistro(int id);

    boolean addRegistro(Registro registro);

    boolean deleteRegistro(Registro registro);
}
