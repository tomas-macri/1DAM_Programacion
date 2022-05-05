package modelo;

import lombok.Data;

@Data
public class Partido {
    private Equipo[] equipos;
    private int[] goles;

    public Partido(Equipo[] equipos, int[] goles) {
        this.equipos = equipos;
        this.goles = goles;
    }
}
