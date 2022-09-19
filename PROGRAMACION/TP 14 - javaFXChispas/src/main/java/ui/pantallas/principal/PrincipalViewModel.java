package ui.pantallas.principal;

import domain.ServiciosEquipos;
import domain.modelo.Equipo;
import javafx.collections.ObservableList;
import lombok.Getter;

@Getter
public class PrincipalViewModel {

    private ServiciosEquipos serviciosEquipos;



    public PrincipalViewModel(ServiciosEquipos serviciosPersonas) {
        this.serviciosEquipos = serviciosPersonas;
    }



    public Equipo addEquipo(Equipo equipo){
        return serviciosEquipos.addEquipo(equipo);
    }

    public Equipo updateEquipo(Equipo equipo, Equipo equipoNuevo) {
       return serviciosEquipos.updateEquipo(equipo, equipoNuevo);
    }

    public Equipo deleteEquipo(Equipo equipo) {
        return serviciosEquipos.deleteEquipo(equipo);
    }

    public ObservableList<Equipo> getEquipos(){
        return serviciosEquipos.getEquipos();
    }

}
