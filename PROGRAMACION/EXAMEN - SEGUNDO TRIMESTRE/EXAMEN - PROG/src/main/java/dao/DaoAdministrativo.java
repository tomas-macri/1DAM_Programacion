package dao;

import modelo.Administrativo;
import modelo.Empleado;
import modelo.Profesor;

import java.util.List;
import java.util.stream.Collectors;

public class DaoAdministrativo {

    // EL METODO COTIZAR PODRIA SER COMUN EN LA CLASE EMPLEADO PERO ESTA ASI PARA USAR INTERFACES
    public String cotizarAdministrativo(Administrativo administrativo) {
        return administrativo.cotizar();
    }

    public List<Profesor> listaProfesoresPorAsignaturas() {
        return BD.listaEmpleados.stream()
                .filter(empleado -> empleado instanceof Profesor)
                .map(empleado -> new Profesor(empleado.getDni(), empleado.getNombre(), empleado.getSueldo(), ((Profesor) empleado).getListaAsignaturas(), ((Profesor) empleado).getExperiencia()))
                .sorted((o1, o2) -> Integer.compare(o2.getListaAsignaturas().size(),o1.getListaAsignaturas().size()))
                .collect(Collectors.toList());
    }
}
