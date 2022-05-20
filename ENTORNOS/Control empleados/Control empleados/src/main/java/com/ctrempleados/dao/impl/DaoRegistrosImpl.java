package com.ctrempleados.dao.impl;


import com.ctrempleados.dao.DaoRegistros;
import com.ctrempleados.dao.Database;
import com.ctrempleados.dao.common.ConstantesDao;
import com.ctrempleados.domain.modelo.Registro;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class DaoRegistrosImpl implements DaoRegistros {

    private final Database database;

    @Inject
    public DaoRegistrosImpl(Database database) {
        this.database = database;
    }

    @Override
    public Either<String, List<Registro>> getRegistros() {
        return database.readJSONRegistros();
    }

    @Override
    public Either<String, Registro> getRegistro(int id) {
        Either<String, List<Registro>> registros = database.readJSONRegistros();
        if (registros.isRight()) {
            Optional<Registro> registro = registros.get().stream()
                    .filter(r -> r.getId() == id).findFirst();
            return registro.<Either<String, Registro>>map(Either::right)
                    .orElseGet(() -> Either.left(ConstantesDao.NO_EXISTE_EL_REGISTRO_CON_ID + id));
        } else {
            return Either.left(registros.getLeft());
        }
    }

    @Override
    public boolean addRegistro(Registro registro) {
        List<Registro> registros = database.readJSONRegistros().get();
        registros.add(registro);
        return database.writeJSONRegistros(registros);
    }

    @Override
    public boolean deleteRegistro(Registro registro) {
        List<Registro> registros = database.readJSONRegistros().get();
        registros.remove(registro);
        return database.writeJSONRegistros(registros);
    }
}
