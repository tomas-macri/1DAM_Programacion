package ui.pantallas.homeGrupos;

import lombok.Data;
import modelo.Equipo;

import java.util.List;

@Data
public class HomeGruposState {

    private List<Equipo> equipos;
    private String error;

    public HomeGruposState(List<Equipo> equipos, String error) {
        this.equipos = equipos;
        this.error = error;
    }
}
