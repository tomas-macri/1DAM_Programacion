package serivcios;

import dao.DaoEquipos;
import jakarta.inject.Inject;
import modelo.Equipo;
import modelo.Partido;

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
    public Equipo getEquipo(String nombre){
        if (nombre.equals(""))
            return null;

        return daoEquipos.getEquipo(nombre);
    }

    public boolean agregarEstadisticas(Partido partido){
        Equipo equipo1 = partido.getEquipos()[0];
        Equipo equipo2 = partido.getEquipos()[1];
        int goles1 = partido.getGoles()[0];
        int goles2 = partido.getGoles()[1];

        if (equipo1 != null && equipo2 != null){
            return daoEquipos.agregarEstadisticas(equipo1,equipo2,goles1,goles2);
        }
        return false;
    }

    public boolean resetEstadisticas(){
        return daoEquipos.resetEstadisticas();
    }
}
