package modelo.Usuarios;

import jakarta.inject.Inject;
import modelo.Clonable;
import modelo.Ingrediente;
import modelo.ProductoComprado;
import modelo.Tarjeta;

import java.util.*;

public abstract class Usuario implements Clonable<Usuario> {

    public String type;
    private String dni;
    private String nombre;
    private boolean admin;
    private Set<Tarjeta> listaTarjetas;
    private List<ProductoComprado> carrito;
    private List<List<ProductoComprado>> comprasPrevias;
    private List<Ingrediente> ingredienteList;


    private Usuario(){
        listaTarjetas = new HashSet<>();
        carrito = new ArrayList<>();
        comprasPrevias = new ArrayList<>();
    }

    @Inject
    public Usuario(String dni, String nombre, List<Ingrediente> ingredienteList) {
        this();
        this.dni = dni;
        this.nombre = nombre;
        this.admin = false;
        this.ingredienteList = ingredienteList;
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

    public List<List<ProductoComprado>> getComprasPrevias() {
        return comprasPrevias;
    }

    public void setComprasPrevias(List<List<ProductoComprado>> comprasPrevias) {
        this.comprasPrevias = comprasPrevias;
    }

    public List<Ingrediente> getIngredienteList() {
        return ingredienteList;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dni='" + dni + '\'' +
                "tipo='" + type + '\'' +
                ", nombre='" + nombre + '\'' +
                ", admin=" + admin +
                ", listaTarjetas=" + listaTarjetas +
                ", carrito=" + carrito +
                ", comprasPrevias=" + comprasPrevias +
                ", ingredienteList=" + ingredienteList +
                "} \n";
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


    @Override
    public Usuario clonar() {
        return new UsuarioNormal(this.dni, this.nombre, this.ingredienteList);
    }
}
