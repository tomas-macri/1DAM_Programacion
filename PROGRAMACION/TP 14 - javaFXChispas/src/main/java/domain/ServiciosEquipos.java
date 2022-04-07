package domain;

import dao.DaoEquipos;
import domain.modelo.Equipos;

import java.util.List;

public class ServiciosEquipos {

    private DaoEquipos daoEquipos;

    public ServiciosEquipos(DaoEquipos daoEquipos) {
        this.daoEquipos = daoEquipos;
    }

    public void addPersona(Equipos p) {
        daoEquipos.addEquipo(p);
    }

    // get listado personas
    public List<Equipos> getPersonas() {
        return daoEquipos.getPersonas();
    }



}
