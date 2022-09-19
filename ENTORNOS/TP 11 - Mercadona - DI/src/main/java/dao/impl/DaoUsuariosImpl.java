package dao.impl;

import dao.BD;
import dao.DaoUsuarios;
import jakarta.inject.Inject;
import modelo.Usuario;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


public class DaoUsuariosImpl implements DaoUsuarios {

    private LinkedHashMap<String, Usuario> bdUsuarios;

    @Inject
    public DaoUsuariosImpl(BD bd){this.bdUsuarios = bd.listaUsuarios;}


    @Override public boolean agregarusuario(Usuario usuarioNuevo) {
        String dni = usuarioNuevo.getDni();
        if (!elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getNombre().equals("") || dni.equals(""))) {
            bdUsuarios.put(dni, usuarioNuevo);
            return true;
        }
        return false;
    }

    @Override public Usuario eliminarUsuario(String dni) {
        return bdUsuarios.remove(dni);
    }

    @Override public boolean elUsuarioExiste(String key) {
        return bdUsuarios.get(key) != null;
    }

    @Override public boolean modificarUsuarioNombre(String dniOriginal, Usuario userViejo, Usuario userNuevo) {
        return bdUsuarios.replace(dniOriginal, userViejo, userNuevo);
    }

    @Override public Usuario getUsuario(String dni) {
        return bdUsuarios.get(dni);
    }

    @Override public List<Usuario> devolverLista() {
        return bdUsuarios.values().stream()
                .map(Usuario::clonar).collect(Collectors.toUnmodifiableList());
    }
}
