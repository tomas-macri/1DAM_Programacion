package servicios;

import dao.DaoUsuarios;
import modelo.Usuario;

import java.util.LinkedHashMap;

public class ServiciosUsuarios {


    public boolean agregarusuario(Usuario usuarioNuevo) {
        String dni = usuarioNuevo.getDni();
        DaoUsuarios dao = new DaoUsuarios();
        if (!elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getNombre().equals("") || dni.equals(""))) {
            dao.agregarusuario(usuarioNuevo);
            return true;
        }
        return false;
    }

    public Usuario eliminarUsuario(String dni) {
        DaoUsuarios dao = new DaoUsuarios();
        Usuario userBorrado = null;
        if (elUsuarioExiste(dni)){
            userBorrado = dao.eliminarUsuario(dni);
        }
        return userBorrado;
    }

    public boolean elUsuarioExiste(String key) {
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        return daoUsuarios.getUsuario(key)!=null;
    }
    //
    public boolean modificarUsuario(Usuario usuarioNuevo, String dniOriginal) {
        boolean exito = false;
        DaoUsuarios dao = new DaoUsuarios();
        Usuario usuarioViejo = dao.getUsuario(dniOriginal);
        if (usuarioViejo != null && !elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getDni().equals("") || usuarioNuevo.getNombre().equals(""))) {
            dao.eliminarUsuario(dniOriginal);
            dao.agregarusuario(usuarioNuevo);
            exito = true;
        }
        return exito;
    }

    //
    public boolean modificarUsuarioNombre(String dniOriginal, String nombNuevo) {
        DaoUsuarios dao = new DaoUsuarios();
        Usuario usuarioViejo = dao.getUsuario(dniOriginal);
        if (usuarioViejo != null && !(nombNuevo.equals(""))) {
            return dao.modificarUsuarioNombre(dniOriginal, usuarioViejo, new Usuario(dniOriginal, nombNuevo);
        }
        return false;
    }

    public boolean modificarUsuarioDNI(String dniOriginal, String dniNuevo) {
        boolean exito = false;
        DaoUsuarios dao = new DaoUsuarios();
        Usuario usuarioViejo = dao.getUsuario(dniOriginal);
        if (usuarioViejo != null && !elUsuarioExiste(dniNuevo) && !(dniNuevo.equals(""))) {
            dao.eliminarUsuario(dniOriginal);
            dao.agregarusuario(new Usuario(dniNuevo, usuarioViejo.getNombre()));
            exito = true;
        }
        return exito;
    }

    public String getUsuario(String dni) {
        String usuario = "error";
        DaoUsuarios dao = new DaoUsuarios();
        Usuario user = dao.get(dni);
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
