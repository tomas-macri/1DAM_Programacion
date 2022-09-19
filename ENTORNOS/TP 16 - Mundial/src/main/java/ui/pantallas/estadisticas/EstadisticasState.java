package ui.pantallas.estadisticas;

import lombok.Data;
import modelo.Equipo;

import java.util.List;

@Data
public class EstadisticasState {

    String error;
    List<Equipo> equipoList;


    public EstadisticasState(String error, List<Equipo> equipoList) {
        this.error = error;
        this.equipoList = equipoList;
    }

}
