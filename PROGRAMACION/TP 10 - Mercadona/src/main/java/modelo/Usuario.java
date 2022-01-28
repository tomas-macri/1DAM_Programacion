package modelo;

//import java.util.HashMap;
import java.util.Objects;

public class Usuario {

    String dni;
    String nombre;

//    HashMap<String, Usuario> mapa = new HashMap<>();

    public Usuario(String dni, String nombre, boolean esAdmin) {
        this.dni = dni;
        this.nombre = nombre;
    }

//    public void addUser(Usuario user){
//        mapa.put(user.getDni(), user);
//        mapa.remove(user.dni);
//        user.setNombre("hlogflflf");
//        mapa.replace(user.dni, user, user);
//
//    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
        Usuario usuario = (Usuario) o;
        return dni.equalsIgnoreCase(usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre);
    }

}
