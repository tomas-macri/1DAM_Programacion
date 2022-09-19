package com.ctrempleados.dao;

import com.ctrempleados.domain.modelo.Nomina;
import io.vavr.control.Either;

import java.util.List;

public interface DaoNominas {
    Either<String, List<Nomina>> getNominas();

    Either<String, Nomina> getNomina(int id);

    boolean addNomina(Nomina nomina);

    boolean deleteNomina(Nomina nomina);
}
