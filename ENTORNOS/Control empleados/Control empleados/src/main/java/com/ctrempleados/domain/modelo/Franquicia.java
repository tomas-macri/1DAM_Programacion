package com.ctrempleados.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Franquicia {

    private final String nombre;
    private String ubicacion;
    private int numeroEmpleados;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Franquicia that = (Franquicia) o;
        return nombre.equalsIgnoreCase(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
