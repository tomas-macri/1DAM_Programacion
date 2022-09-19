package servicios;

import modelo.Ingrediente;
import modelo.Usuario;

import java.util.List;

public interface ServiciosUsuarios {
    boolean agregarUsuario(Usuario usuarioNuevo);

    Usuario eliminarUsuario(String dni);

    boolean elUsuarioExiste(String key);

    //
    boolean modificarUsuario(Usuario usuarioNuevo, String dniOriginal);

    //
    boolean modificarUsuarioNombre(String dniOriginal, String nombNuevo, List<Ingrediente> list);

    boolean modificarUsuarioDNI(String dniOriginal, String dniNuevo);

    String getUsuarioString(String dni);

    Usuario getUsuario(String dni);

    List<Usuario> getLista();
}
