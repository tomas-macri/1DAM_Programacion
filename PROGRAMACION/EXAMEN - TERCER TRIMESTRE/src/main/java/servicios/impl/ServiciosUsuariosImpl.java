package servicios.impl;

import dao.DaoUsuarios;
import jakarta.inject.Inject;
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
            return daoUsuariosImpl.agregarUsuario(usuarioNuevo);
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
        if (usuarioViejo != null && !(usuarioNuevo.getDni().equals("") || usuarioNuevo.getNombre().equals(""))) {
            daoUsuariosImpl.eliminarUsuario(dniOriginal);
            daoUsuariosImpl.agregarUsuario(usuarioNuevo);
            exito = true;
        }
        return exito;
    }

    //
    @Override public boolean modificarUsuarioNombre(String dniOriginal, String nombNuevo) {
        Usuario usuarioViejo = daoUsuariosImpl.getUsuario(dniOriginal);
        if (usuarioViejo != null && !(nombNuevo.equals(""))) {
            return daoUsuariosImpl.modificarUsuarioNombre(dniOriginal, usuarioViejo, new Usuario(dniOriginal, nombNuevo));
        }
        return false;
    }

    @Override public boolean modificarUsuarioDNI(String dniOriginal, String dniNuevo) {
        boolean exito = false;
        Usuario usuarioViejo = daoUsuariosImpl.getUsuario(dniOriginal);
        if (usuarioViejo != null && !elUsuarioExiste(dniNuevo) && !(dniNuevo.equals(""))) {
            daoUsuariosImpl.eliminarUsuario(dniOriginal);
            daoUsuariosImpl.agregarUsuario(new Usuario(dniNuevo, usuarioViejo.getNombre()));
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
