package com.ctrempleados.domain.servicios;

import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Registro;
import io.vavr.control.Either;

import java.util.List;

public interface ServiciosRegistros {
    Either<String, List<Registro>> getRegistros();

    Either<String, List<Registro>> getRegistrosEmpleado(Empleado empleado);

    Either<String, Registro> getRegistro(int id);

    boolean iniciarRegistro(Empleado empleado);

    boolean finalizarRegistro(Empleado empleado);

    boolean modificarRegistro(Registro registro);

    boolean validarRegistro(Registro registro);

    boolean eliminarRegistros(Empleado empleado);
}
