package dao;

import modelo.Producto;
import modelo.Usuario;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DaoUsuarios {

    private static final LinkedHashMap<String, Usuario> listaUsuarios = new LinkedHashMap<>();


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
        return listaUsuarios.get(key)!=null;
    }
//
//    public boolean modificarUsuario(Usuario usuarioNuevo, String dniOriginal) {
//        boolean exito = false;
//        Usuario usuarioViejo = listaUsuarios.get(dniOriginal);
//        if (usuarioViejo != null && !elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getDni().equals("") || usuarioNuevo.getNombre().equals(""))) {
//            listaUsuarios.remove(dniOriginal);
//            listaUsuarios.put(usuarioNuevo.getDni(), usuarioNuevo);
//            exito = true;
//        }
//        return exito;
//    }

//
    public boolean modificarUsuarioNombre(String dniOriginal, Usuario userViejo, Usuario userNuevo) {
        return listaUsuarios.replace(dniOriginal, userViejo, userNuevo);
    }

//    public boolean modificarUsuarioDNI(String dniOriginal, String dniNuevo) {
//        boolean exito = false;
//        Usuario usuarioViejo = listaUsuarios.get(dniOriginal);
//        if (usuarioViejo != null && !elUsuarioExiste(dniNuevo) && !(dniNuevo.equals(""))) {
//            listaUsuarios.remove(dniOriginal);
//            listaUsuarios.put(dniNuevo, new Usuario(dniNuevo, usuarioViejo.getNombre()));
//            exito = true;
//        }
//        return exito;
//    }

    public Usuario getUsuario(String dni) {
        Usuario usuario = null;
        Usuario user = listaUsuarios.get(dni);
        if (user != null) {
            usuario = user;
        }
        return usuario;
    }

//    public List<Producto> devolverLista() {
//        return listaUsuarios()
//                .map(producto -> new Producto(producto.getNombre(), producto.getPrecio(), producto.getStock()))
//                .collect(Collectors.toUnmodifiableList());
//    }

    @Override
    public String toString() {
        return "Lista de usuarios = " + listaUsuarios;
    }

}
