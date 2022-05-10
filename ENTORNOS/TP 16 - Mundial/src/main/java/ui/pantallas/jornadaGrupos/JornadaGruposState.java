package ui.pantallas.jornadaGrupos;

import lombok.Data;
import modelo.Equipo;

import java.util.List;

@Data
public class JornadaGruposState {

    private String error;
    private List<Equipo> equipoList;

    public JornadaGruposState(String error, List<Equipo> equipoList) {
        this.error = error;
        this.equipoList = equipoList;
    }
}
