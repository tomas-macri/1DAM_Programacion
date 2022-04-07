package servicios;

import dao.DaoUsuarios;
import jakarta.inject.Inject;
import modelo.Ingrediente;
import modelo.Usuario;

import java.util.List;


public class ServiciosUsuarios {


    DaoUsuarios daoUsuarios;
    
    @Inject
    public ServiciosUsuarios(DaoUsuarios daoUsuarios){
        this.daoUsuarios = daoUsuarios;
    }
    
    
    public boolean agregarUsuario(Usuario usuarioNuevo) {
        String dni = usuarioNuevo.getDni();
        if (!elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getNombre().equals("") || dni.equals(""))) {
            return daoUsuarios.agregarusuario(usuarioNuevo);
        }
        return false;
    }

    public Usuario eliminarUsuario(String dni) {
        Usuario userBorrado = null;
        if (elUsuarioExiste(dni)) {
            userBorrado = daoUsuarios.eliminarUsuario(dni);
        }
        return userBorrado;
    }

    public boolean elUsuarioExiste(String key) {
        return daoUsuarios.getUsuario(key) != null;
    }

    //
    public boolean modificarUsuario(Usuario usuarioNuevo, String dniOriginal) {
        boolean exito = false;
        Usuario usuarioViejo = daoUsuarios.getUsuario(dniOriginal);
        if (usuarioViejo != null && !elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getDni().equals("") || usuarioNuevo.getNombre().equals(""))) {
            daoUsuarios.eliminarUsuario(dniOriginal);
            daoUsuarios.agregarusuario(usuarioNuevo);
            exito = true;
        }
        return exito;
    }

    //
    public boolean modificarUsuarioNombre(String dniOriginal, String nombNuevo, List<Ingrediente> list) {
        Usuario usuarioViejo = daoUsuarios.getUsuario(dniOriginal);
        if (usuarioViejo != null && !(nombNuevo.equals(""))) {
            return daoUsuarios.modificarUsuarioNombre(dniOriginal, usuarioViejo, new Usuario(dniOriginal, nombNuevo, list));
        }
        return false;
    }

    public boolean modificarUsuarioDNI(String dniOriginal, String dniNuevo) {
        boolean exito = false;
        Usuario usuarioViejo = daoUsuarios.getUsuario(dniOriginal);
        if (usuarioViejo != null && !elUsuarioExiste(dniNuevo) && !(dniNuevo.equals(""))) {
            daoUsuarios.eliminarUsuario(dniOriginal);
            daoUsuarios.agregarusuario(new Usuario(dniNuevo, usuarioViejo.getNombre(), usuarioViejo.getIngredienteList()));
            exito = true;
        }
        return exito;
    }

    public String getUsuarioString(String dni) {
        Usuario user = daoUsuarios.getUsuario(dni);
        if (user != null) {
            return user.toString();
        }
        return "error";
    }


    public Usuario getUsuario(String dni) {
        return daoUsuarios.getUsuario(dni);
    }


    public List<Usuario> getLista() {
        return daoUsuarios.devolverLista();
    }




}
