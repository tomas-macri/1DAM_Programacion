package ui.pantallas.principal;

import jakarta.inject.Inject;
import serivcios.ServiciosEquipos;
import serivcios.ServiciosPartidos;

public class PrincipalViewModel {

    ServiciosEquipos serviciosEquipos;
    ServiciosPartidos serviciosPartidos;

    @Inject
    public PrincipalViewModel(ServiciosEquipos serviciosEquipos, ServiciosPartidos serviciosPartidos) {
        this.serviciosEquipos = serviciosEquipos;
        this.serviciosPartidos = serviciosPartidos;
    }

    public boolean reiniciarTodo(){
        return serviciosPartidos.resetPartidos() && serviciosEquipos.resetEstadisticas();
    }
}
