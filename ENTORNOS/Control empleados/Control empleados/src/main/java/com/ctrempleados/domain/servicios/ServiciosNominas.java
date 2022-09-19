package com.ctrempleados.domain.servicios;

import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Franquicia;
import com.ctrempleados.domain.modelo.Nomina;
import io.vavr.control.Either;

import java.util.List;

public interface ServiciosNominas {
    Either<String, List<Nomina>> getNominas();

    Either<String, List<Nomina>> getNominasEmpleado(Empleado empleado);

    Either<String, Nomina> getNomina(Integer id);

    boolean crearNomina(Empleado empleado);

    boolean pagarNomina(Nomina nomina);

    boolean addFranquiciaNomina(Franquicia franquicia, Empleado empleado);

    boolean crearTodasNominas();

    boolean pagarTodasNominas();
}
