package modelo;

import java.util.Objects;

public class Usuario {

    String dni;
    String nombre;


    public Usuario(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }


    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return dni.equalsIgnoreCase(usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre);
    }

}
