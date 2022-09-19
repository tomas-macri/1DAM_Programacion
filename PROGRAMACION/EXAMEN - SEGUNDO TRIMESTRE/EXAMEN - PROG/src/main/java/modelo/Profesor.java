package modelo;

import java.util.List;

public class Profesor extends Empleado implements Cotizable{

    private List<Asignatura> listaAsignaturas;
    private int experiencia;

    public Profesor(String dni, String nombre, int sueldo, List<Asignatura> listaAsignaturas, int experiencia) {
        super(dni, nombre, sueldo);
        this.listaAsignaturas = listaAsignaturas;
        this.experiencia = experiencia;
    }

    public List<Asignatura> getListaAsignaturas() {
        return listaAsignaturas;
    }

    public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String cotizar() {
        return (this.getNombre() + " ha cotizado!");
    }

    @Override
    public String toString() {
        return super.toString() + "Profesor{" +
                "listaAsignaturas=" + listaAsignaturas +
                ", experiencia=" + experiencia +
                "} \n \n";
    }
}
