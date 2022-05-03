package dao;

import modelo.Usuario;

import java.util.List;

public interface DaoUsuarios {
    boolean agregarusuario(Usuario usuarioNuevo);

    Usuario eliminarUsuario(String dni);

    boolean elUsuarioExiste(String key);

    boolean modificarUsuarioNombre(String dniOriginal, Usuario userViejo, Usuario userNuevo);

    Usuario getUsuario(String dni);

    List<Usuario> devolverLista();
}
