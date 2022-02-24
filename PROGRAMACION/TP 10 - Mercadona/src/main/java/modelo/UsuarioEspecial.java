package modelo;

import java.util.List;

public class UsuarioEspecial extends Usuario {

    private int porcentajeDescuento;

    public UsuarioEspecial(String dni, String nombre, List<Ingrediente> ingredienteList, int porcentajeDescuento) {
        super(dni, nombre, ingredienteList);
        this.porcentajeDescuento = porcentajeDescuento;
    }


    public int getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    @Override
    public String toString() {
        return "ClienteEspecial{" +
                "porcentajeDescuento=" + porcentajeDescuento +
                '}';
    }

    @Override
    public Usuario clonar() {
        return new UsuarioEspecial(this.getDni(), this.getNombre(), this.getIngredienteList(), porcentajeDescuento);
    }
}
