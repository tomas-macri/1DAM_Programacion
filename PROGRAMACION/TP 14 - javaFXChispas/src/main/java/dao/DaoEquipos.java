package dao;

import domain.modelo.Equipo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class DaoEquipos {

    private DataBase bd;

    public DaoEquipos(DataBase bd){
           this.bd = bd;
    }


    public Equipo addEquipo(Equipo equipoNuevo) {
        ObservableList<Equipo> equipos = FXCollections.observableArrayList(bd.loadEquipos());
        if (equipos == null) {
            equipos = FXCollections.observableArrayList();
        }
        equipos.add(equipoNuevo);
        bd.saveEquipos(equipos);
        return equipoNuevo;
    }

    public ObservableList<Equipo> getEquipos() {
        ObservableList<Equipo> equipos = FXCollections.observableArrayList(bd.loadEquipos());
        if (equipos == null) {
            equipos = FXCollections.observableArrayList();
        }
        return equipos;
    }

    public Equipo updateEquipo(Equipo equipo, Equipo equipoNuevo) {
        ObservableList<Equipo> equipos = FXCollections.observableArrayList(bd.loadEquipos());
        if (equipos == null) {
            equipos = FXCollections.observableArrayList();
        }
        int indexEquipoViejo = equipos.indexOf(equipo);
        if (indexEquipoViejo >= 0) {
            equipos.set(indexEquipoViejo, equipoNuevo);
        }
        bd.saveEquipos(equipos);
        return equipo;
    }

    public Equipo deleteEquipo(Equipo equipo) {
        ObservableList<Equipo> equipos = FXCollections.observableArrayList(bd.loadEquipos());
        if (equipos == null) {
            equipos = FXCollections.observableArrayList();
        }
        boolean equipoEliminado = equipos.remove(equipo);
        bd.saveEquipos(equipos);

        return equipoEliminado ? equipo : null;
    }
}
