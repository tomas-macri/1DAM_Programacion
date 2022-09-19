package modelo;

import lombok.Data;

@Data
public class Usuario {

    private String dni;
    private String nombre;
    private boolean admin;


    public Usuario(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.admin = false;
    }
}
