package modelo;

import lombok.Data;

import java.util.Objects;

@Data
public class Actor {

    private String nombre;

    public Actor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(nombre, actor.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
