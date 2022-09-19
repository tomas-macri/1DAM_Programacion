package com.ctrempleados.dao.impl;


import com.ctrempleados.dao.DaoNominas;
import com.ctrempleados.dao.Database;
import com.ctrempleados.dao.common.ConstantesDao;
import com.ctrempleados.domain.modelo.Nomina;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class DaoNominasImpl implements DaoNominas {

    private final Database database;

    @Inject
    public DaoNominasImpl(Database database) {
        this.database = database;
    }

    @Override
    public Either<String, List<Nomina>> getNominas() {
        return database.readJSONNominas();
    }

    @Override
    public Either<String, Nomina> getNomina(int id) {
        Either<String, List<Nomina>> nominas = database.readJSONNominas();
        if (nominas.isRight()) {
            Optional<Nomina> nomina = nominas.get().stream()
                    .filter(r -> r.getId() == id).findFirst();
            return nomina.<Either<String, Nomina>>map(Either::right)
                    .orElseGet(() -> Either.left(ConstantesDao.NO_EXISTE_LA_NOMINA_CON_ID + id));
        } else {
            return Either.left(nominas.getLeft());
        }
    }

    @Override
    public boolean addNomina(Nomina nomina) {
        List<Nomina> nominas = database.readJSONNominas().get();
        nominas.add(nomina);
        return database.writeJSONNominas(nominas);
    }

    @Override
    public boolean deleteNomina(Nomina nomina) {
        List<Nomina> nominas = database.readJSONNominas().get();
        nominas.remove(nomina);
        return database.writeJSONNominas(nominas);
    }
}