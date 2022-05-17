package ui.pantallas.jornadaGrupos;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.text.Text;
import modelo.Equipo;
import modelo.Partido;
import serivcios.ServiciosEquipos;
import serivcios.ServiciosPartidos;
import ui.pantallas.homeGrupos.HomeGruposState;

import java.util.List;

public class JornadaGruposViewModel {

    ServiciosEquipos serviciosEquipos;
    ServiciosPartidos serviciosPartidos;

    @Inject
    public JornadaGruposViewModel(ServiciosEquipos serviciosEquipos, ServiciosPartidos serviciosPartidos) {
        this.serviciosEquipos = serviciosEquipos;
        this.serviciosPartidos = serviciosPartidos;
        _state = new SimpleObjectProperty<>(new JornadaGruposState(null,null, null, null));
    }

    private final ObjectProperty<JornadaGruposState> _state;
    public ReadOnlyObjectProperty<JornadaGruposState> getState() {
        return _state;
    }

    public void getEquiposPartidosGrupo(int grupo){

        List<Partido> partidosGrupo = serviciosPartidos.getTodosLosPartidosDeUnGrupo(grupo);
        List<Equipo> equipoList = serviciosEquipos.getTodosLosEquipos().subList(grupo*4, grupo*4+4);
        JornadaGruposState jn = new JornadaGruposState(null, equipoList, partidosGrupo, null);
        _state.setValue(jn);

    }

    public void guardarPartido(String  txtEquipo1, int goles1, String txtEquipo2, int goles2, int grupo) {
        Equipo[] equipos = new Equipo[2];
        int[] goles = new int[2];

        equipos[0] = serviciosEquipos.getEquipo(txtEquipo1);
        equipos[1] = serviciosEquipos.getEquipo(txtEquipo2);
        goles[0] = goles1;
        goles[1] = goles2;
        Partido partido = new Partido(equipos, goles, grupo);
        if (serviciosPartidos.guardarPartido(partido)){
            if (serviciosEquipos.agregarEstadisticas(partido)){
                List<Partido> partidosGrupo = serviciosPartidos.getTodosLosPartidosDeUnGrupo(grupo);
                List<Equipo> equipoList = serviciosEquipos.getTodosLosEquipos().subList(grupo*4, grupo*4+4);
                JornadaGruposState jn = new JornadaGruposState(null, equipoList, partidosGrupo, "Se ha actualizado el partido " + txtEquipo1 + " vs " + txtEquipo2);
                _state.setValue(jn);
            }
        }

    }
}
