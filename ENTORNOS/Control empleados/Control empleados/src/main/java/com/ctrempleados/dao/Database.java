package com.ctrempleados.dao;

import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Franquicia;
import com.ctrempleados.domain.modelo.Nomina;
import com.ctrempleados.domain.modelo.Registro;
import io.vavr.control.Either;

import java.util.List;

public interface Database {
    Either<String, List<Empleado>> readJSONEmpleados();

    boolean writeJSONEmpleados(List<Empleado> empleados);

    Either<String, List<Franquicia>> readJSONFranquicias();

    boolean writeJSONFranquicias(List<Franquicia> franquicias);

    Either<String, List<Nomina>> readJSONNominas();

    boolean writeJSONNominas(List<Nomina> nominas);

    Either<String, List<Registro>> readJSONRegistros();

    boolean writeJSONRegistros(List<Registro> registros);
}
