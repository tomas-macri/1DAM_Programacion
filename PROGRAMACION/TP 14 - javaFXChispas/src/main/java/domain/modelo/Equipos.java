package domain.modelo;


import lombok.Data;

import java.util.Objects;

@Data
public class Equipos {

    private final String nombre;
    private final Integer champions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipos equipos = (Equipos) o;
        return Objects.equals(nombre, equipos.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
