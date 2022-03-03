package dao;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Profesor;

import java.util.ArrayList;
import java.util.List;

public class DaoProfesor {

    // EL METODO COTIZAR PODRIA SER COMUN EN LA CLASE EMPLEADO PERO ESTA ASI PARA USAR INTERFACES
    public String cotizarProfesor(Profesor profe){
        return profe.cotizar();
    }

    public List<Asignatura> getAsignaturasDeTodos() {
        List<Asignatura> asignaturasList = new ArrayList<>();
        BD.listaEmpleados.stream().forEach(empleado -> {
            if (empleado instanceof Profesor) {
                ((Profesor) empleado).getListaAsignaturas().forEach(
                        asignatura -> {
                            if (!asignaturasList.contains(asignatura)) {
                                asignaturasList.add(asignatura);
                            }
                        } );
            }
        });
        return asignaturasList;
    }

    public boolean matricular(Alumno alumnoAModificar, Asignatura asignatura) {
        alumnoAModificar.getListaAsignaturasNotas().put(asignatura, null);
        alumnoAModificar.setDinero(alumnoAModificar.getDinero() - asignatura.getPrecio());
        asignatura.setMaxAlumnos(asignatura.getMaxAlumnos()-1);
        return true;
    }

    public boolean ponerNota(Alumno alumnoAModificar, Asignatura asignatura, int notaAsignatura) {
        alumnoAModificar.getListaAsignaturasNotas().replace(asignatura, notaAsignatura);
        return true;
    }
}
