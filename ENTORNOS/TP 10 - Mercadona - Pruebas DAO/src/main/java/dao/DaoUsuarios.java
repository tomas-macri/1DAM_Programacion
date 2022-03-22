package dao;

import modelo.Usuario;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


public class DaoUsuarios {

    private LinkedHashMap<String, Usuario> bdUsuarios;

    public DaoUsuarios(LinkedHashMap<String, Usuario> bdUsuarios){this.bdUsuarios = bdUsuarios;}


    public boolean agregarusuario(Usuario usuarioNuevo) {
        String dni = usuarioNuevo.getDni();
        if (!elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getNombre().equals("") || dni.equals(""))) {
            bdUsuarios.put(dni, usuarioNuevo);
            return true;
        }
        return false;
    }

    public Usuario eliminarUsuario(String dni) {
        return bdUsuarios.remove(dni);
    }

    public boolean elUsuarioExiste(String key) {
        return bdUsuarios.get(key) != null;
    }

    public boolean modificarUsuarioNombre(String dniOriginal, Usuario userViejo, Usuario userNuevo) {
        return bdUsuarios.replace(dniOriginal, userViejo, userNuevo);
    }

    public Usuario getUsuario(String dni) {
        return bdUsuarios.get(dni);
    }

    public List<Usuario> devolverLista() {
        return bdUsuarios.values().stream()
                .map(Usuario::clonar).collect(Collectors.toUnmodifiableList());
    }
}
