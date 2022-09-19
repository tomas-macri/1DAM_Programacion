package dao;

import modelo.Alumno;
import modelo.Persona;

public class DaoAlumno {
    public boolean elAlumnoExiste(Persona persona) {
        if (BD.listaPersonas.contains(persona)){
            if (BD.listaPersonas.get(BD.listaPersonas.indexOf(persona)) instanceof Alumno){
                return true;
            }
        }
        return false;
    }

    public Alumno getAlumno(Persona persona) {
        return (Alumno) BD.listaPersonas.get(BD.listaPersonas.indexOf(persona));
    }

    public double calcularMedia(Alumno alumnoAVerMedia) {
        //double notaMedia;
//        AtomicInteger acumNotas = new AtomicInteger();
//        AtomicInteger contNotas = new AtomicInteger();
//        alumnoAVerMedia.getListaAsignaturasNotas().forEach((asignatura, integer) -> {
//            if (integer != null) {
//                acumNotas.addAndGet(integer);
//                contNotas.getAndIncrement();
//            }
//        });
//        return acumNotas.get()/contNotas.get();
        //alumnoAVerMedia.getListaAsignaturasNotas().values().stream()
        return alumnoAVerMedia.getListaAsignaturasNotas().entrySet().stream()
                .filter(asignaturaIntegerEntry -> asignaturaIntegerEntry.getValue()!=null)
                .mapToDouble(asignaturaIntegerEntry -> asignaturaIntegerEntry.getValue()).average().getAsDouble();
    }
}
