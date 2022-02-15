package dao;

import modelo.Usuario;
import java.util.List;
import java.util.stream.Collectors;


public class DaoUsuarios {


    public void agregarusuario(Usuario usuarioNuevo) {
        String dni = usuarioNuevo.getDni();
        if (!elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getNombre().equals("") || dni.equals(""))) {
            BD.listaUsuarios.put(dni, usuarioNuevo);
        }
    }

    public Usuario eliminarUsuario(String dni) {
        return BD.listaUsuarios.remove(dni);
    }

    public boolean elUsuarioExiste(String key) {
        return BD.listaUsuarios.get(key) != null;
    }

    public boolean modificarUsuarioNombre(String dniOriginal, Usuario userViejo, Usuario userNuevo) {
        return BD.listaUsuarios.replace(dniOriginal, userViejo, userNuevo);
    }


    public Usuario getUsuario(String dni) {
        return BD.listaUsuarios.get(dni);
    }



    public List<Usuario> devolverLista() {
        return BD.listaUsuarios.values().stream()
                .map(usuario -> new Usuario(usuario.getDni(), usuario.getNombre()))
                .collect(Collectors.toUnmodifiableList());

    }

}
