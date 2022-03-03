package modelo;

import java.util.LinkedHashMap;

public class Alumno extends Persona{

    private LinkedHashMap<Asignatura, Integer> listaAsignaturasNotas;
    private int dinero;

    public Alumno(String dni, String nombre, LinkedHashMap<Asignatura, Integer> listaAsignaturasNotas, int dinero) {
        super(dni, nombre);
        this.listaAsignaturasNotas = listaAsignaturasNotas;
        this.dinero = dinero;
    }

    public LinkedHashMap<Asignatura, Integer> getListaAsignaturasNotas() {
        return listaAsignaturasNotas;
    }

    public void setListaAsignaturasNotas(LinkedHashMap<Asignatura, Integer> listaAsignaturasNotas) {
        this.listaAsignaturasNotas = listaAsignaturasNotas;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }


}
