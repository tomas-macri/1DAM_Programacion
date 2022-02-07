package dao;

import modelo.Producto;
import modelo.Usuario;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


public class DaoUsuarios {

    private static final LinkedHashMap<String, Usuario> listaUsuarios = new LinkedHashMap<>();



    public void agregarusuario(Usuario usuarioNuevo) {
        String dni = usuarioNuevo.getDni();
        if (!elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getNombre().equals("") || dni.equals(""))) {
            listaUsuarios.put(dni, usuarioNuevo);
        }
    }

    public Usuario eliminarUsuario(String dni) {
        return listaUsuarios.remove(dni);
    }

    public boolean elUsuarioExiste(String key) {
        return listaUsuarios.get(key) != null;
    }

    public boolean modificarUsuarioNombre(String dniOriginal, Usuario userViejo, Usuario userNuevo) {
        return listaUsuarios.replace(dniOriginal, userViejo, userNuevo);
    }


    public Usuario getUsuario(String dni) {
        return listaUsuarios.get(dni);
    }



    public List<Usuario> devolverLista() {
        // tuve que dejar este metodo en vez de devolver una unmodifiable list porque los maps no tienen un metodo .stream()
        return listaUsuarios.values().stream()
                .map(usuario -> new Usuario(usuario.getDni(), usuario.getNombre()))
                .collect(Collectors.toUnmodifiableList());

    }

}
