package com.ctrempleados.domain.servicios.impl;

import com.ctrempleados.dao.DaoEmpleados;
import com.ctrempleados.dao.DaoRegistros;
import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Registro;
import com.ctrempleados.domain.servicios.ServiciosRegistros;
import com.ctrempleados.domain.servicios.common.ConstantesServicios;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ServiciosRegistrosImpl implements ServiciosRegistros {

    private final DaoRegistros daoRegistros;
    private final DaoEmpleados daoEmpleados;

    @Inject
    public ServiciosRegistrosImpl(DaoRegistros daoRegistros, DaoEmpleados daoEmpleados) {
        this.daoRegistros = daoRegistros;
        this.daoEmpleados = daoEmpleados;
    }

    @Override
    public Either<String, List<Registro>> getRegistros() {
        return daoRegistros.getRegistros();
    }

    @Override
    public Either<String, List<Registro>> getRegistrosEmpleado(Empleado empleado) {
        Either<String, List<Registro>> registros = daoRegistros.getRegistros();
        if (registros.isRight()) {
            List<Registro> registrosEmpleado = registros.get().stream()
                    .filter(r -> r.getDniEmpleado().equals(empleado.getDni()))
                    .collect(Collectors.toList());
            if (registrosEmpleado.isEmpty()) {
                return Either.left(ConstantesServicios.NO_HAY_REGISTROS_DEL_EMPLEADO);
            } else {
                return Either.right(registrosEmpleado);
            }
        } else {
            return registros;
        }
    }

    @Override
    public Either<String, Registro> getRegistro(int id) {
        return daoRegistros.getRegistro(id);
    }

    @Override
    public boolean iniciarRegistro(Empleado empleado) {
        boolean existeEmpleado = daoEmpleados.getEmpleado(empleado.getDni()).isRight();
        if (existeEmpleado) {
            Registro registro = new Registro(empleado.getDni(), empleado.getNombreFranquicia(), LocalDateTime.now());
            int id = daoRegistros.getRegistros().get().stream()
                    .mapToInt(Registro::getId)
                    .max().orElse(0) + 1;
            registro.setId(id);
            return daoRegistros.addRegistro(registro);
        } else {
            return false;
        }
    }

    @Override
    public boolean finalizarRegistro(Empleado empleado) {
        boolean existeEmpleado = daoEmpleados.getEmpleado(empleado.getDni()).isRight();
        Registro registro = daoRegistros.getRegistros().get().stream()
                .filter(r -> r.getDniEmpleado().equals(empleado.getDni()))
                .filter(registro1 -> registro1.getHoraSalida() == null)
                .findFirst().orElse(null);
        if (existeEmpleado && registro != null) {
            registro.setHoraSalida(LocalDateTime.now());
            registro.setComputoTiempo(Duration.between(registro.getHoraEntrada(), registro.getHoraSalida()));
            registro.setValidado(true);
            return daoRegistros.deleteRegistro(registro) && daoRegistros.addRegistro(registro);
        } else {
            return false;
        }
    }

    @Override
    public boolean modificarRegistro(Registro registro) {
        boolean existeEmpleado = daoEmpleados.getEmpleado(registro.getDniEmpleado()).isRight();
        boolean existeRegistro = daoRegistros.getRegistro(registro.getId()).isRight();
        if (existeEmpleado && existeRegistro) {
            registro.setValidado(false);
            registro.setComputoTiempo(Duration.between(registro.getHoraEntrada(), registro.getHoraSalida()));
            return daoRegistros.deleteRegistro(registro) && daoRegistros.addRegistro(registro);
        } else {
            return false;
        }
    }

    @Override
    public boolean validarRegistro(Registro registro) {
        boolean existeEmpleado = daoEmpleados.getEmpleado(registro.getDniEmpleado()).isRight();
        Registro registro1 = daoRegistros.getRegistros().get().stream()
                .filter(r -> r.getId() == registro.getId())
                .filter(registro2 -> !registro2.isValidado())
                .findFirst().orElse(null);
        if (existeEmpleado && registro1 != null) {
            registro1.setValidado(true);
            return daoRegistros.deleteRegistro(registro1) && daoRegistros.addRegistro(registro1);
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarRegistros(Empleado empleado) {
        return daoRegistros.getRegistros().get().stream()
                .filter(r -> r.getDniEmpleado().equals(empleado.getDni()))
                .peek(daoRegistros::deleteRegistro)
                .findAny().isPresent();
    }
}