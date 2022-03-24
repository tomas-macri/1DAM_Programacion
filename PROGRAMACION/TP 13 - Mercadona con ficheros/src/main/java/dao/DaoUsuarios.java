package dao;

import modelo.Usuarios.Usuario;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


public class DaoUsuarios {
    private DataBase usuariosDataBase;

    public DaoUsuarios() {
        this.usuariosDataBase = new DataBase();
    }

    public boolean agregarUsuario(Usuario usuarioNuevo) {
        LinkedHashMap<String, Usuario> clientes = usuariosDataBase.loadClientes();
        if (clientes != null) {
            String dni = usuarioNuevo.getDni();
            if (!elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getNombre().equals("") || dni.equals(""))) {
                clientes.put(dni, usuarioNuevo);
                usuariosDataBase.saveClientes(clientes);
                return true;
            }

        }

        return false;
    }

    public Usuario eliminarUsuario(String dni) {
        LinkedHashMap<String, Usuario> clientes = usuariosDataBase.loadClientes();
        if (clientes != null) {
            Usuario userEliminado = clientes.remove(dni);
            usuariosDataBase.saveClientes(clientes);
            return userEliminado;
        }
        return null;
    }

    public boolean elUsuarioExiste(String key) {
        LinkedHashMap<String, Usuario> clientes = usuariosDataBase.loadClientes();
        if (clientes != null) {
            return clientes.get(key) != null;
        }
        return false;
    }

    public boolean modificarUsuarioNombre(String dniOriginal, Usuario userViejo, Usuario userNuevo) {
        LinkedHashMap<String, Usuario> clientes = usuariosDataBase.loadClientes();
        if (clientes != null) {
            boolean seCambio = clientes.replace(dniOriginal, userViejo, userNuevo);
            usuariosDataBase.saveClientes(clientes);
            return seCambio;

        }
        return false;
    }


    public Usuario getUsuario(String dni) {
        LinkedHashMap<String, Usuario> clientes = usuariosDataBase.loadClientes();
        if (clientes != null) {
            return clientes.get(dni);
        }
        return null;
    }


    public List<Usuario> devolverLista() {
        LinkedHashMap<String, Usuario> clientes = usuariosDataBase.loadClientes();
        if (clientes != null) {
            return clientes.values().stream()
                    .map(Usuario::clonar).collect(Collectors.toUnmodifiableList());
        }
        return new ArrayList<>();
    }
}
