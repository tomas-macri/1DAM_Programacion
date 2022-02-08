package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cliente {

    private String dni;
    private String nombre;
    private Set<Monedero> monederos;
    private List<LineaCompra> carrito;
    private List<List<LineaCompra>> comprasAntiguas;

    public List<LineaCompra> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<LineaCompra> carrito) {
        this.carrito = carrito;
    }

    public List<List<LineaCompra>> getComprasAntiguas() {
        return comprasAntiguas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private Cliente() {
        monederos = new HashSet<>();
        carrito = new ArrayList<>();
        comprasAntiguas = new ArrayList<>();


    }

    public Cliente(String dni) {
        this();
        this.dni = dni;
    }

    public Cliente(String dni, String nombre) {
        this(dni);
        this.nombre = nombre;

    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    public Set<Monedero> getMonederos() {
        return monederos;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' + nombre +
                '}';
    }
}
