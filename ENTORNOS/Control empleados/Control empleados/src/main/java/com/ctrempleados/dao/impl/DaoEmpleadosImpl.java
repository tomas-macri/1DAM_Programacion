package com.ctrempleados.dao.impl;

import com.ctrempleados.dao.DaoEmpleados;
import com.ctrempleados.dao.Database;
import com.ctrempleados.dao.common.ConstantesDao;
import com.ctrempleados.domain.modelo.Empleado;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class DaoEmpleadosImpl implements DaoEmpleados {

    private final Database database;

    @Inject
    public DaoEmpleadosImpl(Database database) {
        this.database = database;
    }

    @Override
    public Either<String, List<Empleado>> getEmpleados() {
        return database.readJSONEmpleados();
    }

    @Override
    public Either<String, Empleado> getEmpleado(String dni) {
        Either<String, List<Empleado>> empleados = database.readJSONEmpleados();
        if (empleados.isRight()) {
            Optional<Empleado> empleado = empleados.get().stream()
                    .filter(r -> r.getDni().equalsIgnoreCase(dni))
                    .findFirst();
            return empleado.<Either<String, Empleado>>map(Either::right)
                    .orElseGet(() -> Either.left(ConstantesDao.NO_EXISTE_EL_EMPLEADO_CON_DNI + dni));
        } else {
            return Either.left(empleados.getLeft());
        }
    }

    @Override
    public boolean addEmpleado(Empleado empleado) {
        List<Empleado> empleados = database.readJSONEmpleados().get();
        empleados.add(empleado);
        return database.writeJSONEmpleados(empleados);
    }

    @Override
    public boolean deleteEmpleado(Empleado empleado) {
        List<Empleado> empleados = database.readJSONEmpleados().get();
        empleados.remove(empleado);
        return database.writeJSONEmpleados(empleados);
    }
}
