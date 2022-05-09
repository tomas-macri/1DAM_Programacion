package serivcios;

import dao.DaoEquipos;
import jakarta.inject.Inject;
import modelo.Equipo;

import java.util.List;

public class ServiciosEquipos {

    private DaoEquipos daoEquipos;

    @Inject
    public ServiciosEquipos(DaoEquipos daoEquipos) {
        this.daoEquipos = daoEquipos;
    }

    public List<Equipo> getTodosLosEquipos(){
        return daoEquipos.getTodosLosEquipos();
    }
}
