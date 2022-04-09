package servicios.impl;

import dao.DaoUsuarios;
import jakarta.inject.Inject;
import modelo.Ingrediente;
import modelo.Usuario;
import servicios.ServiciosUsuarios;

import java.util.List;


public class ServiciosUsuariosImpl implements ServiciosUsuarios {


    DaoUsuarios daoUsuariosImpl;
    
    @Inject
    public ServiciosUsuariosImpl(DaoUsuarios daoUsuariosImpl){
        this.daoUsuariosImpl = daoUsuariosImpl;
    }
    
    
    @Override public boolean agregarUsuario(Usuario usuarioNuevo) {
        String dni = usuarioNuevo.getDni();
        if (!elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getNombre().equals("") || dni.equals(""))) {
            return daoUsuariosImpl.agregarusuario(usuarioNuevo);
        }
        return false;
    }

    @Override public Usuario eliminarUsuario(String dni) {
        Usuario userBorrado = null;
        if (elUsuarioExiste(dni)) {
            userBorrado = daoUsuariosImpl.eliminarUsuario(dni);
        }
        return userBorrado;
    }

    @Override public boolean elUsuarioExiste(String key) {
        return daoUsuariosImpl.getUsuario(key) != null;
    }

    //
    @Override public boolean modificarUsuario(Usuario usuarioNuevo, String dniOriginal) {
        boolean exito = false;
        Usuario usuarioViejo = daoUsuariosImpl.getUsuario(dniOriginal);
        if (usuarioViejo != null && !elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getDni().equals("") || usuarioNuevo.getNombre().equals(""))) {
            daoUsuariosImpl.eliminarUsuario(dniOriginal);
            daoUsuariosImpl.agregarusuario(usuarioNuevo);
            exito = true;
        }
        return exito;
    }

    //
    @Override public boolean modificarUsuarioNombre(String dniOriginal, String nombNuevo, List<Ingrediente> list) {
        Usuario usuarioViejo = daoUsuariosImpl.getUsuario(dniOriginal);
        if (usuarioViejo != null && !(nombNuevo.equals(""))) {
            return daoUsuariosImpl.modificarUsuarioNombre(dniOriginal, usuarioViejo, new Usuario(dniOriginal, nombNuevo, list));
        }
        return false;
    }

    @Override public boolean modificarUsuarioDNI(String dniOriginal, String dniNuevo) {
        boolean exito = false;
        Usuario usuarioViejo = daoUsuariosImpl.getUsuario(dniOriginal);
        if (usuarioViejo != null && !elUsuarioExiste(dniNuevo) && !(dniNuevo.equals(""))) {
            daoUsuariosImpl.eliminarUsuario(dniOriginal);
            daoUsuariosImpl.agregarusuario(new Usuario(dniNuevo, usuarioViejo.getNombre(), usuarioViejo.getIngredienteList()));
            exito = true;
        }
        return exito;
    }

    @Override public String getUsuarioString(String dni) {
        Usuario user = daoUsuariosImpl.getUsuario(dni);
        if (user != null) {
            return user.toString();
        }
        return "error";
    }


    @Override public Usuario getUsuario(String dni) {
        return daoUsuariosImpl.getUsuario(dni);
    }


    @Override public List<Usuario> getLista() {
        return daoUsuariosImpl.devolverLista();
    }




}
