package dao;

import modelo.Usuarios.Usuario;

import java.util.List;

public interface DaoUsuarios {
    boolean agregarUsuario(Usuario usuarioNuevo);

    Usuario eliminarUsuario(String dni);

    boolean elUsuarioExiste(String key);

    boolean modificarUsuarioNombre(String dniOriginal, Usuario userViejo, Usuario userNuevo);

    Usuario getUsuario(String dni);

    List<Usuario> devolverLista();
}
