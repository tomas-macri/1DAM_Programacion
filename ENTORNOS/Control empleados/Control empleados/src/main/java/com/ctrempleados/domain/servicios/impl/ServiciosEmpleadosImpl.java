package com.ctrempleados.domain.servicios.impl;

import com.ctrempleados.dao.DaoEmpleados;
import com.ctrempleados.dao.DaoFranquicias;
import com.ctrempleados.dao.DaoNominas;
import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Franquicia;
import com.ctrempleados.domain.servicios.ServiciosEmpleados;
import com.ctrempleados.domain.servicios.common.ConstantesServicios;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class ServiciosEmpleadosImpl implements ServiciosEmpleados {

    private final DaoEmpleados daoEmpleados;
    private final DaoFranquicias daoFranquicias;

    @Inject
    public ServiciosEmpleadosImpl(DaoEmpleados daoEmpleados, DaoFranquicias daoFranquicias) {
        this.daoEmpleados = daoEmpleados;
        this.daoFranquicias = daoFranquicias;
    }

    @Override
    public Either<String, Empleado> getEmpleadoDNI(String dni) {
        return daoEmpleados.getEmpleado(dni);
    }

    @Override
    public Either<String, Empleado> getEmpleadoCodigo(int codigoAcceso) {
        Either<String, List<Empleado>> empleados = daoEmpleados.getEmpleados();
        if (empleados.isRight()) {
            Optional<Empleado> empleado = empleados.get().stream()
                    .filter(e -> e.getCodigoAcceso() == codigoAcceso)
                    .findFirst();
            return empleado.<Either<String, Empleado>>map(Either::right)
                    .orElseGet(() -> Either.left(ConstantesServicios.NO_EXISTE_UN_EMPLEADO_CON_ESE_CÓDIGO_DE_ACCESO));
        } else {
            return Either.left(empleados.getLeft());
        }
    }

    @Override
    public Either<String, List<Empleado>> getEmpleados() {
        return daoEmpleados.getEmpleados();
    }

    @Override
    public boolean crearEmpleado(String dni, String nombre, String apellido,
                                 double sueldoHora, String nombreFranquicia) {
        boolean existeEmpleado = daoEmpleados.getEmpleado(dni).isRight();
        boolean existeFranquicia = daoFranquicias.getFranquicia(nombreFranquicia).isRight();
        boolean dniValido = dni.length() >= 8;
        boolean nombreValido = nombre.length() > 0;
        boolean apellidoValido = apellido.length() > 0;
        int codigoAcceso = daoEmpleados.getEmpleados().get().stream()
                .mapToInt(Empleado::getCodigoAcceso).max().orElse(1000) + 1;
        boolean sueldoHoraValido = sueldoHora > 10 && sueldoHora < 100;
        if (!existeEmpleado && existeFranquicia && dniValido
                && sueldoHoraValido && nombreValido && apellidoValido) {
            Empleado empleado = new Empleado(dni, nombre, apellido, codigoAcceso, sueldoHora, nombreFranquicia);
            return daoEmpleados.addEmpleado(empleado);
        } else {
            return false;
        }
    }

    @Override
    public boolean modificarEmpleado(Empleado empleado) {
        Either<String, Empleado> empleadoBD = daoEmpleados.getEmpleado(empleado.getDni());
        boolean nombreValido = empleado.getNombre().length() > 0;
        boolean apellidoValido = empleado.getApellido().length() > 0;
        boolean sueldoHoraValido = empleado.getSueldoHora() > 10;
        if (empleadoBD.isRight() && nombreValido && apellidoValido && sueldoHoraValido) {
            empleadoBD.get().setNombre(empleado.getNombre());
            empleadoBD.get().setApellido(empleado.getApellido());
            empleadoBD.get().setSueldoHora(empleado.getSueldoHora());
            return daoEmpleados.deleteEmpleado(empleado) && daoEmpleados.addEmpleado(empleadoBD.get());
        } else {
            return false;
        }
    }

    @Override
    public boolean cambiarFranquicia(Empleado empleado, Franquicia franquicia) {
        Either<String, Empleado> empleadoBD = daoEmpleados.getEmpleado(empleado.getDni());
        Franquicia franquiciaBD = daoFranquicias.getFranquicia(franquicia.getNombre()).get();
        if (empleadoBD.isRight() && franquiciaBD != null) {
            empleadoBD.get().setNombreFranquicia(franquiciaBD.getNombre());
            return daoEmpleados.deleteEmpleado(empleado) && daoEmpleados.addEmpleado(empleadoBD.get());
        } else {
            return false;
        }
    }

    @Override
    public boolean quitarFranquicia(Empleado empleado) {
        Either<String, Empleado> empleadoBD = daoEmpleados.getEmpleado(empleado.getDni());
        if (empleadoBD.isRight()) {
            empleadoBD.get().setNombreFranquicia(null);
            return daoEmpleados.deleteEmpleado(empleado) && daoEmpleados.addEmpleado(empleadoBD.get());
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarEmpleado(String dni) {
        if (daoEmpleados.getEmpleado(dni).isRight()) {
            Empleado empleado = daoEmpleados.getEmpleado(dni).get();
            return daoEmpleados.deleteEmpleado(empleado);
        } else {
            return false;
        }
    }
}
