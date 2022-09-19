package servicios;

import dao.DaoAdministrativo;
import modelo.Administrativo;
import modelo.Empleado;
import modelo.Profesor;

import java.util.List;

public class ServiciosAdministrativo {

    // EL METODO COTIZAR PODRIA SER COMUN EN LA CLASE EMPLEADO PERO ESTA ASI PARA USAR INTERFACES
    public String cotizarAdministrativo(Administrativo admin){
        DaoAdministrativo daoAdministrativo = new DaoAdministrativo();
        return daoAdministrativo.cotizarAdministrativo(admin);
    }

    public List<Profesor> profesoresPorCantidadAsignaturas(){
        DaoAdministrativo daoAdministrativo = new DaoAdministrativo();
        return daoAdministrativo.listaProfesoresPorAsignaturas();
    }
}
