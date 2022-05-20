package com.ctrempleados.domain.servicios;

import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Franquicia;
import io.vavr.control.Either;

import java.util.List;

public interface ServiciosEmpleados {
    Either<String, Empleado> getEmpleadoDNI(String dni);

    Either<String, Empleado> getEmpleadoCodigo(int codigoAcceso);

    Either<String, List<Empleado>> getEmpleados();

    boolean crearEmpleado(String dni, String nombre, String apellido,
                          double sueldoHora, String nombreFranquicia);

    boolean modificarEmpleado(Empleado empleado);

    boolean cambiarFranquicia(Empleado empleado, Franquicia franquicia);

    boolean quitarFranquicia(Empleado empleado);

    boolean eliminarEmpleado(String dni);
}
