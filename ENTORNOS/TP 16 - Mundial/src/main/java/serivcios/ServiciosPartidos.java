package serivcios;

import dao.DaoPartidos;
import jakarta.inject.Inject;
import modelo.Partido;

import java.util.List;

public class ServiciosPartidos {

    private DaoPartidos daoPartidos;

    @Inject
    public ServiciosPartidos(DaoPartidos daoPartidos){
        this.daoPartidos = daoPartidos;
    }

    public List<Partido> getTodosLosPartidos(){
        return daoPartidos.getTodosLosPartidos();
    }

    public List<Partido> getTodosLosPartidosDeUnGrupo(int grupo){

        if (grupo > -1 && grupo < 8){
            return daoPartidos.getTodosLosPartidosDeUnGrupo(grupo);
        }
        return null;
    }


    public boolean guardarPartido(Partido partido) {
        if (partido != null){
            return daoPartidos.guardarPartido(partido);
        }
        return false;
    }
}
