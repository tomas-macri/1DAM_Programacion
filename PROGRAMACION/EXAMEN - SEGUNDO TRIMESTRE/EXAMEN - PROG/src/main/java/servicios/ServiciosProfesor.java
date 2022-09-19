package servicios;

import dao.DaoProfesor;
import modelo.Alumno;
import modelo.Asignatura;
import modelo.Profesor;

import java.util.List;

public class ServiciosProfesor {

    // EL METODO COTIZAR PODRIA SER COMUN EN LA CLASE EMPLEADO PERO ESTA ASI PARA USAR INTERFACES
    public String cotizarProfesor(Profesor profe) {
        DaoProfesor daoProfesor = new DaoProfesor();
        return daoProfesor.cotizarProfesor(profe);
    }

    public List<Asignatura> getAsignaturas() {
        DaoProfesor daoProfesor = new DaoProfesor();
        return daoProfesor.getAsignaturasDeTodos();
    }

    public boolean matricular(Alumno alumnoAModificar, List<Asignatura> asignaturaList, Asignatura asignatura) {
        if (alumnoAModificar != null && asignaturaList != null && !asignatura.getNombre().equalsIgnoreCase("")) {
            int index = asignaturaList.indexOf(asignatura);
            if (index != -1) {
                asignatura = asignaturaList.get(index);

                if (asignatura.getPrecio() < alumnoAModificar.getDinero() && asignatura.getMaxAlumnos() > 0 && !alumnoAModificar.getListaAsignaturasNotas().containsKey(asignatura)) {
                    DaoProfesor daoProfesor = new DaoProfesor();
                    return daoProfesor.matricular(alumnoAModificar, asignaturaList.get(index));
                }
            }
        }
        return false;
    }

    public boolean ponerNota(Alumno alumnoAModificar, List<Asignatura> asignaturaList, Asignatura asignatura, int notaAsignatura) {
        if (alumnoAModificar != null && asignaturaList != null && notaAsignatura < 10 && notaAsignatura > 0) {
            int index = asignaturaList.indexOf(asignatura);
            if (index != -1) {
                DaoProfesor daoProfesor = new DaoProfesor();
                return daoProfesor.ponerNota(alumnoAModificar, asignaturaList.get(index), notaAsignatura);
            }
        }
        return false;
    }
}
