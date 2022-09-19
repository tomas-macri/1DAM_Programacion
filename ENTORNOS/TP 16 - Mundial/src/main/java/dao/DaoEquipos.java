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

    public List<Equipo> getTodosLosEquipos() {
        List<Equipo> equipoList = dataBase.loadEquipos();
        if (equipoList == null) {
            equipoList = new ArrayList<>();
        }
        return equipoList;
    }

    public boolean resetEstadisticas() {
        List<Equipo> equipoList = dataBase.loadEquipos();
        if (equipoList != null) {
            for (Equipo e : equipoList) {
                e.setVallasInvictas(0);
                e.setGolesFavor(0);
                e.setPuntos(0);
            }
            return dataBase.saveEquipos(equipoList);
        }
        return false;
    }

    public void sumarEstadisticasEquipos(Partido partido) {
        for (int i = 0; i < partido.getEquipos().length; i++) {

            Equipo equipo = partido.getEquipos()[i];

            int golesEquipo = partido.getGoles()[i];
            int golesRival = partido.getGoles()[(i + 1) % 2];

            equipo.setGolesFavor(equipo.getGolesFavor() + golesEquipo);

            if (golesRival == 0) {
                equipo.setVallasInvictas((equipo.getVallasInvictas()) + 1);
            }

            if (golesEquipo > golesRival) {
                equipo.setPuntos(equipo.getPuntos() + 3);
            } else if (golesEquipo == golesRival) {
                equipo.setPuntos(equipo.getPuntos() + 1);
            }
        }

    }

    public Equipo getEquipo(String nombre) {
        List<Equipo> equipoList = dataBase.loadEquipos();
        Equipo equipo = null;
        if (equipoList != null) {
            equipo = equipoList.get(equipoList.indexOf(new Equipo(nombre, 0, 0, 0)));
        }
        return equipo;
    }

    public boolean agregarEstadisticas(Equipo equipo1, Equipo equipo2, int goles1, int goles2) {
        equipo1.setGolesFavor(equipo1.getGolesFavor() + goles1);
        equipo2.setGolesFavor(equipo2.getGolesFavor() + goles2);

        if (goles1 > goles2) {
            equipo1.setPuntos(equipo1.getPuntos() + 3);
        } else if (goles2 > goles1) {
            equipo2.setPuntos(equipo2.getPuntos() + 3);
        } else {
            equipo1.setPuntos(equipo1.getPuntos() + 1);
            equipo2.setPuntos(equipo2.getPuntos() + 1);
        }


        if (goles1 == 0) {
            equipo2.setVallasInvictas(equipo2.getVallasInvictas() + 1);
        }
        if (goles2 == 0) {
            equipo1.setVallasInvictas(equipo1.getVallasInvictas() + 1);
        }
        List<Equipo> equipoList = dataBase.loadEquipos();
        if (equipoList != null) {
            equipoList.set(equipoList.indexOf(equipo1), equipo1);
            equipoList.set(equipoList.indexOf(equipo2), equipo2);
            return dataBase.saveEquipos(equipoList);
        }
        return false;
    }
}
