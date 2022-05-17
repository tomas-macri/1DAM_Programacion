package modelo;

import lombok.Data;

import java.util.Objects;

@Data
public class Equipo {
    private String nombre;
    private int golesFavor;
    private int vallasInvictas;
    private int puntos;

    public Equipo(String nombre, int golesFavor, int vallasInvictas, int puntos) {
        this.nombre = nombre;
        this.golesFavor = golesFavor;
        this.vallasInvictas = vallasInvictas;
        this.puntos = puntos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombre, equipo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
