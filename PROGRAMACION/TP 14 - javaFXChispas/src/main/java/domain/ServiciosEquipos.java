package domain;

import dao.DaoEquipos;
import domain.modelo.Equipo;
import javafx.collections.ObservableList;

public class ServiciosEquipos {

    private DaoEquipos daoEquipos;

    public ServiciosEquipos(DaoEquipos daoEquipos) {
        this.daoEquipos = daoEquipos;
    }

    public Equipo addEquipo(Equipo equipo) {
        if (!equipo.getNombre().equalsIgnoreCase("") && equipo.getChampions() >= 0) {
            return daoEquipos.addEquipo(equipo);
        }
        return null;
    }

    // get listado equipos
    public ObservableList<Equipo> getEquipos() {
        return daoEquipos.getEquipos();
    }

    public Equipo updateEquipo(Equipo equipo, Equipo nuevoEquipo) {
        if (!nuevoEquipo.getNombre().equalsIgnoreCase("") && nuevoEquipo.getChampions() >= 0) {
            return daoEquipos.updateEquipo(equipo, nuevoEquipo);
        }
        return null;
    }

    public Equipo deleteEquipo(Equipo equipo) {
        return daoEquipos.deleteEquipo(equipo);
    }
}
