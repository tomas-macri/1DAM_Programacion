package modelo;

import java.util.Objects;

public class Asignatura {

    private String nombre;
    private int maxAlumnos;
    private int precio;

    public Asignatura(String nombre, int maxAlumnos, int precio) {
        this.nombre = nombre;
        this.maxAlumnos = maxAlumnos;
        this.precio = precio;
    }

    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMaxAlumnos() {
        return maxAlumnos;
    }

    public void setMaxAlumnos(int maxAlumnos) {
        this.maxAlumnos = maxAlumnos;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "\n Asignatura{" +
                "nombre='" + nombre + '\'' +
                ", maxAlumnos=" + maxAlumnos +
                ", precio=" + precio +
                "}";
    }
}
