package servicios;

import dao.BD;
import dao.DaoAlumno;
import modelo.Alumno;
import modelo.Persona;

import java.util.LinkedHashMap;

public class ServiciosAlumno {

    public boolean elAlumnoExiste(Persona persona){
        DaoAlumno daoAlumno = new DaoAlumno();
        return daoAlumno.elAlumnoExiste(persona);
    }

    public Alumno getAlumno(Persona persona) {
        if (BD.listaPersonas.contains(persona)){
            DaoAlumno daoAlumno = new DaoAlumno();
            return daoAlumno.getAlumno(persona);
        }
        return new Alumno("error", "error", new LinkedHashMap<>(), 0);
    }

    public double calcularMedia(Alumno alumnoAVerMedia) {
        if (alumnoAVerMedia!=null){
            DaoAlumno daoAlumno = new DaoAlumno();
            return daoAlumno.calcularMedia(alumnoAVerMedia);
        }
        return 0;

    }
}
