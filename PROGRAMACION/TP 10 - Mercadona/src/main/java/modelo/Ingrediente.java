package modelo;

import java.util.Objects;

public class Ingrediente implements Clonable<Ingrediente> {

    private String nombre;

    public Ingrediente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public Ingrediente clonar() {
        return new Ingrediente(this.nombre);
    }

    /* @Override
    public Producto clonar() {
        return new Ingrediente(this.nombre);
    }*/
}