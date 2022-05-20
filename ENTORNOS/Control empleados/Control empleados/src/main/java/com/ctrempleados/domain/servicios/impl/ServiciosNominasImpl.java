package com.ctrempleados.domain.servicios.impl;

import com.ctrempleados.dao.DaoEmpleados;
import com.ctrempleados.dao.DaoFranquicias;
import com.ctrempleados.dao.DaoNominas;
import com.ctrempleados.dao.DaoRegistros;
import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Franquicia;
import com.ctrempleados.domain.modelo.Nomina;
import com.ctrempleados.domain.modelo.Registro;
import com.ctrempleados.domain.servicios.ServiciosNominas;
import com.ctrempleados.domain.servicios.common.ConstantesServicios;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


public class ServiciosNominasImpl implements ServiciosNominas {

    private final DaoNominas daoNominas;
    private final DaoEmpleados daoEmpleados;
    private final DaoRegistros daoRegistros;
    private final DaoFranquicias daoFranquicias;

    @Inject
    public ServiciosNominasImpl(DaoNominas daoNominas, DaoEmpleados daoEmpleados,
                                DaoRegistros daoRegistros, DaoFranquicias daoFranquicias) {
        this.daoNominas = daoNominas;
        this.daoEmpleados = daoEmpleados;
        this.daoRegistros = daoRegistros;
        this.daoFranquicias = daoFranquicias;
    }

    @Override
    public Either<String, List<Nomina>> getNominas() {
        Either<String, List<Nomina>> nominasEither = daoNominas.getNominas();
        if (nominasEither.isRight()) {
            List<Nomina> nominas = daoNominas.getNominas().get();
            if (nominas.isEmpty()) {
                return Either.left(ConstantesServicios.NO_HAY_NOMINAS);
            } else {
                return Either.right(nominas);
            }
        } else {
            return Either.left(daoNominas.getNominas().getLeft());
        }
    }

    @Override
    public Either<String, List<Nomina>> getNominasEmpleado(Empleado empleado) {
        Either<String, List<Nomina>> nominasEither = daoNominas.getNominas();
        if (nominasEither.isRight()) {
            List<Nomina> nominas = daoNominas.getNominas().get().stream()
                    .filter(nomina -> nomina.getDniEmpleado().equals(empleado.getDni()))
                    .filter(nomina -> !nomina.isPendienteDeAbonar())
                    .collect(Collectors.toList());
            if (nominas.isEmpty()) {
                return Either.left(ConstantesServicios.NO_HAY_NOMINAS);
            } else {
                return Either.right(nominas);
            }
        } else {
            return Either.left(daoNominas.getNominas().getLeft());
        }
    }

    @Override
    public Either<String, Nomina> getNomina(Integer id) {
        return daoNominas.getNomina(id);
    }

    @Override
    public boolean crearNomina(Empleado empleado) {
        Either<String,Empleado> empleadoDB = daoEmpleados.getEmpleado(empleado.getDni());
        boolean empleadoValido = empleadoDB.isRight() && empleadoDB.get().getNombreFranquicia() != null;
        boolean existeNominaAbierta = empleadoDB.isRight() && daoNominas.getNominas().get().stream()
                .filter(Nomina::isPendienteDeAbonar)
                .anyMatch(n -> n.getDniEmpleado().equals(empleadoDB.get().getDni()));
        if (!existeNominaAbierta && empleadoValido) {
            int id = daoNominas.getNominas().get().stream()
                    .mapToInt(Nomina::getId)
                    .max().orElse(0) + 1;
            List<String> franquicias = new ArrayList<>(List.of(empleadoDB.get().getNombreFranquicia()));
            Nomina nomina = new Nomina(id, empleado.getDni(), empleado.getSueldoHora(), franquicias, LocalDate.now());
            return daoNominas.addNomina(nomina);
        } else {
            return false;
        }
    }

    @Override
    public boolean pagarNomina(Nomina nomina) {
        Either<String, Nomina> nominaBD = daoNominas.getNomina(nomina.getId());
        if (nominaBD.isRight()
                && nominaBD.get().isPendienteDeAbonar()) {
            nominaBD.get().setFechaFin(LocalDate.now());
            nominaBD.get().setComputoDias(Period.between(nominaBD.get().getFechaInicio(), nominaBD.get().getFechaFin()).plusDays(1));
            AtomicReference<Duration> tiempoTrabajado = new AtomicReference<>(Duration.ZERO);
            daoRegistros.getRegistros().get().stream()
                    .filter(registro -> registro.getDniEmpleado().equals(nominaBD.get().getDniEmpleado()))
                    .filter(Registro::isValidado)
                    .filter(registro -> registro.getHoraEntrada().isAfter(nominaBD.get().getFechaInicio().atStartOfDay())
                            && registro.getHoraSalida() != null
                            && registro.getHoraSalida().isBefore(nominaBD.get().getFechaFin().atStartOfDay()))
                    .map(Registro::getComputoTiempo)
                    .forEach(duration -> tiempoTrabajado.set(tiempoTrabajado.get().plus(duration)));
            nominaBD.get().setTiempoTrabajado(tiempoTrabajado.get());
            nominaBD.get().setSueldo(nominaBD.get().getTiempoTrabajado().toMinutes() * (nominaBD.get().getSueldoHora() / 60));
            nominaBD.get().setPendienteDeAbonar(false);
            return daoNominas.deleteNomina(nomina) && daoNominas.addNomina(nominaBD.get());
        }
        return false;
    }

    @Override
    public boolean addFranquiciaNomina(Franquicia franquicia, Empleado empleado) {
        Either<String, Franquicia> franquiciaBD = daoFranquicias.getFranquicia(franquicia.getNombre());
        if (franquiciaBD.isRight()) {
            return daoNominas.getNominas().get().stream()
                    .filter(nomina -> nomina.getDniEmpleado().equals(empleado.getDni()))
                    .filter(Nomina::isPendienteDeAbonar)
                    .peek(nomina -> {
                        List<String> franquicias = nomina.getLugaresDeTrabajo();
                        if (!franquicias.contains(franquicia.getNombre())) {
                            nomina.getLugaresDeTrabajo().add(franquicia.getNombre());
                            daoNominas.deleteNomina(nomina);
                            daoNominas.addNomina(nomina);
                        }
                    }).findAny().isPresent();
        } else {
            return false;
        }
    }

    @Override
    public boolean crearTodasNominas() {
        Set<Empleado> empleados = daoNominas.getNominas().get().stream()
                .filter(nomina -> !nomina.isPendienteDeAbonar())
                .map(nomina -> daoEmpleados.getEmpleado(nomina.getDniEmpleado()))
                .filter(Either::isRight)
                .map(Either::get)
                .filter(empleado -> empleado.getNombreFranquicia() != null)
                .collect(Collectors.toSet());
        empleados.forEach(this::crearNomina);
        return !empleados.isEmpty();
    }

    @Override
    public boolean pagarTodasNominas() {
        Set<Nomina> nominas = daoNominas.getNominas().get().stream()
                .filter(Nomina::isPendienteDeAbonar)
                .collect(Collectors.toSet());
        nominas.forEach(this::pagarNomina);
        return !nominas.isEmpty();
    }
}