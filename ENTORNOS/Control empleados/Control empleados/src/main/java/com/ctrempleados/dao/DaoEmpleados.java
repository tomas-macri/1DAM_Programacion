package com.ctrempleados.dao;

import com.ctrempleados.domain.modelo.Empleado;
import io.vavr.control.Either;

import java.util.List;

public interface DaoEmpleados {
    Either<String, List<Empleado>> getEmpleados();

    Either<String, Empleado> getEmpleado(String dni);

    boolean addEmpleado(Empleado empleado);

    boolean deleteEmpleado(Empleado empleado);
}
