package ui.pantallas.jornadaGrupos;

import lombok.Data;
import modelo.Equipo;
import modelo.Partido;

import java.util.List;

@Data
public class JornadaGruposState {

    private String error;
    private List<Equipo> equipoList;
    private List<Partido> partidoList;
    private String confirnacion;

    public JornadaGruposState(String error, List<Equipo> equipoList, List<Partido> partidoList, String confirmacion) {
        this.error = error;
        this.equipoList = equipoList;
        this.partidoList = partidoList;
        this.confirnacion = confirmacion;
    }
}
