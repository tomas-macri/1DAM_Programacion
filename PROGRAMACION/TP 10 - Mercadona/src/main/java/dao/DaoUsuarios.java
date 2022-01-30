package dao;

import modelo.Usuario;

import java.util.LinkedHashMap;

public class DaoUsuarios {

    private final LinkedHashMap<String, Usuario> listaUsuarios;


    public DaoUsuarios() {
        listaUsuarios = new LinkedHashMap<>();
    }


    public boolean agregarusuario(Usuario usuarioNuevo) {
        String dni = usuarioNuevo.getDni();
        if (!elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getNombre().equals("") || dni.equals(""))) {
            listaUsuarios.put(dni, usuarioNuevo);
            return true;
        }
        return false;
    }

    public Usuario eliminarUsuario(String dni) {
        Usuario userBorrado = null;
        if (elUsuarioExiste(dni)){
            userBorrado = listaUsuarios.remove(dni);
        }
        return userBorrado;
    }

    public boolean elUsuarioExiste(String key) {
        return listaUsuarios.containsKey(key);
    }
//
    public boolean modificarUsuario(Usuario usuarioNuevo, String dniOriginal) {
        boolean exito = false;
        Usuario usuarioViejo = listaUsuarios.get(dniOriginal);
        if (usuarioViejo != null && !elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getDni().equals("") || usuarioNuevo.getNombre().equals(""))) {
            listaUsuarios.remove(dniOriginal);
            listaUsuarios.put(usuarioNuevo.getDni(), usuarioNuevo);
            exito = true;
        }
        return exito;
    }

//
    public boolean modificarUsuarioNombre(String dniOriginal, String nombNuevo) {
        Usuario usuarioViejo = listaUsuarios.get(dniOriginal);
        if (usuarioViejo != null && !(nombNuevo.equals(""))) {
            return listaUsuarios.replace(dniOriginal, usuarioViejo, new Usuario(dniOriginal, nombNuevo));
        }
        return false;
    }

    public boolean modificarUsuarioDNI(String dniOriginal, String dniNuevo) {
        boolean exito = false;
        Usuario usuarioViejo = listaUsuarios.get(dniOriginal);
        if (usuarioViejo != null && !elUsuarioExiste(dniNuevo) && !(dniNuevo.equals(""))) {
            listaUsuarios.remove(dniOriginal);
            listaUsuarios.put(dniNuevo, new Usuario(dniNuevo, usuarioViejo.getNombre()));
            exito = true;
        }
        return exito;
    }

    public String getUsuario(String dni) {
        String usuario = "error";
        Usuario user = listaUsuarios.get(dni);
        if (user != null) {
            usuario = user.toString();
        }
        return usuario;
    }

    @Override
    public String toString() {
        return "Lista de usuarios = " + listaUsuarios;
    }

}
