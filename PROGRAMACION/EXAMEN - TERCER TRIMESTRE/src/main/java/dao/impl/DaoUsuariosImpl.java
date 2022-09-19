package dao.impl;

import dao.DaoUsuarios;
import jakarta.inject.Inject;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


public class DaoUsuariosImpl implements DaoUsuarios {

    private DataBase dataBase;

    @Inject
    public DaoUsuariosImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override public boolean agregarUsuario(Usuario usuarioNuevo) {
        LinkedHashMap<String, Usuario> clientes = dataBase.loadUsuarios();
        if (clientes != null) {
            String dni = usuarioNuevo.getDni();
            if (!elUsuarioExiste(usuarioNuevo.getDni()) && !(usuarioNuevo.getNombre().equals("") || dni.equals(""))) {
                clientes.put(dni, usuarioNuevo);
                dataBase.saveUsuarios(clientes);
                return true;
            }

        }

        return false;
    }

     @Override public Usuario eliminarUsuario(String dni) {
        LinkedHashMap<String, Usuario> clientes = dataBase.loadUsuarios();
        if (clientes != null) {
            Usuario userEliminado = clientes.remove(dni);
            dataBase.saveUsuarios(clientes);
            return userEliminado;
        }
        return null;
    }

    @Override public boolean elUsuarioExiste(String key) {
        LinkedHashMap<String, Usuario> clientes = dataBase.loadUsuarios();
        if (clientes != null) {
            return clientes.get(key) != null;
        }
        return false;
    }

   @Override public boolean modificarUsuarioNombre(String dniOriginal, Usuario userViejo, Usuario userNuevo) {
        LinkedHashMap<String, Usuario> clientes = dataBase.loadUsuarios();
        if (clientes != null) {
            boolean seCambio = clientes.replace(dniOriginal, userViejo, userNuevo);
            dataBase.saveUsuarios(clientes);
            return seCambio;

        }
        return false;
    }


   @Override public Usuario getUsuario(String dni) {
        LinkedHashMap<String, Usuario> clientes = dataBase.loadUsuarios();
        if (clientes != null) {
            return clientes.get(dni);
        }
        return null;
    }


   @Override public List<Usuario> devolverLista() {
        LinkedHashMap<String, Usuario> clientes = dataBase.loadUsuarios();
        if (clientes != null) {
            return clientes.values().stream()
                    .collect(Collectors.toUnmodifiableList());
        }
        return new ArrayList<>();
    }
}
