package modelo;

import lombok.Data;

import java.util.List;

@Data
public class Grupo {

    private List<Equipo> equipos;

    public Grupo(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}
