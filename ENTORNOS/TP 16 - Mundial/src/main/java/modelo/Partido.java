package modelo;

import lombok.Data;

import java.util.Arrays;

@Data
public class Partido {
    private Equipo[] equipos;
    private int[] goles;
    private int grupo;

    public Partido(Equipo[] equipos, int[] goles, int grupo) {
        this.equipos = equipos;
        this.goles = goles;
        this.grupo = grupo;
    }


    public Partido(Equipo[] equipos) {
        this.equipos = equipos;
    }

    public Equipo getEquipo1(){
        return equipos[0];
    }

    public Equipo getEquipo2(){
        return equipos[1];
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partido partido = (Partido) o;
        return Arrays.equals(equipos, partido.equipos);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(equipos);
    }
}
