package modelo;

import java.util.*;

public class Usuario {

    private String dni;
    private String nombre;
    private boolean admin;
    private Set<Tarjeta> listaTarjetas;
    private List<ProductoComprado> carrito;


    private Usuario(){
        listaTarjetas = new HashSet<>();
        carrito = new ArrayList<>();
    }



    public Usuario(String dni, String nombre) {
        this();
        this.dni = dni;
        this.nombre = nombre;
        this.admin = false;
    }


    public Usuario(String dni, String nombre, boolean esAdmin) {
        this();
        this.dni = dni;
        this.nombre = nombre;
        this.admin = esAdmin;
    }


    public boolean isAdmin() {
        return admin;
    }


    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }


    public Set<Tarjeta> getListaTarjetas() {
        return listaTarjetas;
    }

    public void setListaTarjetas(Set<Tarjeta> listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }

    public List<ProductoComprado> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<ProductoComprado> carrito) {
        this.carrito = carrito;
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
