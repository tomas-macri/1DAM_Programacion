package com.ctrempleados.dao.impl;


import com.ctrempleados.dao.DaoFranquicias;
import com.ctrempleados.dao.Database;
import com.ctrempleados.dao.common.ConstantesDao;
import com.ctrempleados.domain.modelo.Franquicia;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class DaoFranquiciasImpl implements DaoFranquicias {

    private final Database database;

    @Inject
    public DaoFranquiciasImpl(Database database) {
        this.database = database;
    }

    @Override
    public Either<String, List<Franquicia>> getFranquicias() {
        return database.readJSONFranquicias();
    }

    @Override
    public Either<String, Franquicia> getFranquicia(String nombre) {
        Either<String, List<Franquicia>> franquicias = database.readJSONFranquicias();
        if (franquicias.isRight()) {
            Optional<Franquicia> franquicia = franquicias.get().stream()
                    .filter(r -> r.getNombre().equalsIgnoreCase(nombre))
                    .findFirst();
            return franquicia.<Either<String, Franquicia>>map(Either::right)
                    .orElseGet(() -> Either.left(ConstantesDao.NO_EXISTE_LA_FRANQUICIA + nombre));
        } else {
            return Either.left(franquicias.getLeft());
        }
    }

    @Override
    public boolean addFranquicia(Franquicia franquicia) {
        List<Franquicia> franquicias = database.readJSONFranquicias().get();
        franquicias.add(franquicia);
        return database.writeJSONFranquicias(franquicias);
    }

    @Override
    public boolean deleteFranquicia(Franquicia franquicia) {
        List<Franquicia> franquicias = database.readJSONFranquicias().get();
        franquicias.remove(franquicia);
        return database.writeJSONFranquicias(franquicias);
    }
}