package servicios;

import dao.DaoUsuarios;
import modelo.Ingrediente;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;

import java.util.List;


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
        if (elUsuarioExiste(dni)) {
            userBorrado = dao.eliminarUsuario(dni);
        }
        return userBorrado;
    }

    public boolean elUsuarioExiste(String key) {
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        return daoUsuarios.getUsuario(key) != null;
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
    public boolean modificarUsuarioNombre(String dniOriginal, String nombNuevo, List<Ingrediente> list) {
        DaoUsuarios dao = new DaoUsuarios();
        Usuario usuarioViejo = dao.getUsuario(dniOriginal);
        if (usuarioViejo != null && !(nombNuevo.equals(""))) {
            return dao.modificarUsuarioNombre(dniOriginal, usuarioViejo, new UsuarioNormal(dniOriginal, nombNuevo, list));
        }
        return false;
    }

    public boolean modificarUsuarioDNI(String dniOriginal, String dniNuevo) {
        boolean exito = false;
        DaoUsuarios dao = new DaoUsuarios();
        Usuario usuarioViejo = dao.getUsuario(dniOriginal);
        if (usuarioViejo != null && !elUsuarioExiste(dniNuevo) && !(dniNuevo.equals(""))) {
            dao.eliminarUsuario(dniOriginal);
            dao.agregarusuario(new UsuarioNormal(dniNuevo, usuarioViejo.getNombre(), usuarioViejo.getIngredienteList()));
            exito = true;
        }
        return exito;
    }

    public String getUsuarioString(String dni) {
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        Usuario user = daoUsuarios.getUsuario(dni);
        if (user != null) {
            return user.toString();
        }
        return "error";
    }


    public Usuario getUsuario(String dni) {
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        return daoUsuarios.getUsuario(dni);
    }


    public List<Usuario> getLista() {
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        return daoUsuarios.devolverLista();
    }




}
