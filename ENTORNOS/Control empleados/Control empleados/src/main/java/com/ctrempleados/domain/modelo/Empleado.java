package com.ctrempleados.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Empleado {

    private String dni;
    private String nombre;
    private String apellido;
    private int codigoAcceso;
    private double sueldoHora;
    private String nombreFranquicia;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return dni.equalsIgnoreCase(empleado.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
