package modelo.Usuarios;

import modelo.Ingrediente;

import java.util.List;

public class UsuarioNormal extends Usuario{

    public UsuarioNormal(String dni, String nombre, List<Ingrediente> ingredienteList) {
        super(dni, nombre, ingredienteList);
        type = "UsuarioNormal";

    }

    public UsuarioNormal(String dni, String nombre, boolean esAdmin) {
        super(dni, nombre, esAdmin);
    }

}
