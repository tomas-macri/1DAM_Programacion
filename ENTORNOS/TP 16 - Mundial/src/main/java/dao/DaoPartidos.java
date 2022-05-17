package dao;

import jakarta.inject.Inject;
import modelo.Equipo;
import modelo.Grupo;
import modelo.Partido;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoPartidos {

    DataBase dataBase;

    @Inject
    public DaoPartidos(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean crearPartidos(List<Grupo> gruposList) {
        List<Partido> partidos = new ArrayList<>();
        for (int i = 0; i < gruposList.size(); i++) {
            List<Equipo> equiposGrupo = gruposList.get(i).getEquipos();
            for (int j = 0; j < equiposGrupo.size(); j++) {

                for (int k = j + 1; k < 4; k++) {
                    Equipo[] equiposPartido = new Equipo[2];
                    equiposPartido[0] = equiposGrupo.get(j);
                    equiposPartido[1] = equiposGrupo.get(k);

                    int[] goles = new int[2];
                    goles[0] = -1;
                    goles[1] = -1;
                    Partido partido = new Partido(equiposPartido, goles, i);
                    partidos.add(partido);
                }
            }
        }
        return dataBase.savePartidos(partidos);
    }

    public List<Partido> getTodosLosPartidos() {
        return dataBase.loadPartidos();
    }

    public List<Partido> getTodosLosPartidosDeUnGrupo(int grupo) {
        List<Partido> partidos = dataBase.loadPartidos();
        if (partidos == null) {
            return new ArrayList<>();
        }
        return partidos.stream().filter(partido -> partido.getGrupo() == grupo).collect(Collectors.toList());
    }

    public Partido getPartido(Equipo[] equipos) {
        List<Partido> partidos = dataBase.loadPartidos();
        if (partidos != null)
            return partidos.get(partidos.indexOf(new Partido(equipos)));
        return null;
    }


    public boolean guardarPartido(Partido partido) {
        List<Partido> partidos = dataBase.loadPartidos();
        if (partidos != null) {
            partidos.set(partidos.indexOf(partido), partido);
            return dataBase.savePartidos(partidos);
        }
        return false;
    }
}
