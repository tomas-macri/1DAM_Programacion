package dao;

import domain.modelo.Equipos;

import java.util.List;

public class DaoEquipos {

    private DataBase bd;

    public DaoEquipos(DataBase bd){
           this.bd = bd;
    }


    public void addEquipo(Equipos equipoNuevo) {
        List<Equipos> equipos = bd.loadEquipos();
        equipos.add(equipoNuevo);
        bd.saveEquipos(equipos);
    }

    public List<Equipos> getPersonas() {
        return equipos;
    }

}
