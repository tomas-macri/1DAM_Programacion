package domain.modelo;


import lombok.Data;

import java.util.Objects;

@Data
public class Equipo {

    private final String nombre;
    private final Integer champions;

    public Equipo(String nombre, Integer champions){
        this.nombre = nombre;
        this.champions = champions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipos = (Equipo) o;
        return Objects.equals(nombre, equipos.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

}
