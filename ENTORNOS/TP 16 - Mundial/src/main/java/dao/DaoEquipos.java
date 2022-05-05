package dao;

import jakarta.inject.Inject;
import modelo.Equipo;
import modelo.Partido;

import java.util.ArrayList;
import java.util.List;

public class DaoEquipos {

    private DataBase dataBase;

    @Inject
    public DaoEquipos(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public List<Equipo> getTodosLosEquipos(){
        List<Equipo> equipoList = dataBase.loadEquipos();
        if (equipoList == null){
            equipoList = new ArrayList<>();
        }
        return equipoList;
    }

    public void sumarEstadisticasEquipos(Partido partido){
        for (int i = 0; i < partido.getEquipos().length; i++) {

            Equipo equipo = partido.getEquipos()[i];

            int golesEquipo = partido.getGoles()[i];
            int golesRival = partido.getGoles()[(i+1)%2];

            equipo.setGolesFavor(equipo.getGolesFavor() + golesEquipo);

            if (golesRival == 0){
                equipo.setVallasInvictas((equipo.getVallasInvictas()) + 1);
            }

            if (golesEquipo > golesRival){
                equipo.setPuntos(equipo.getPuntos() + 3);
            }
            else if (golesEquipo == golesRival){
                equipo.setPuntos(equipo.getPuntos() + 1);
            }
        }

    }
}
