package modelo;

import lombok.Data;

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

    public Equipo getEquipo1(){
        return equipos[0];
    }

    public Equipo getEquipo2(){
        return equipos[1];
    }
}
