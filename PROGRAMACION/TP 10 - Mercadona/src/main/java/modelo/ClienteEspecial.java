package modelo;

import java.util.List;

public class ClienteEspecial extends Usuario {

    private int porcentajeDescuento;

    public ClienteEspecial(String dni, String nombre, List<Ingrediente> ingredienteList, int porcentajeDescuento) {
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
    public Ingrediente clonar() {
        return new ClienteEspecial(this.getDni(), this.getNombre(), this.getIngredienteList(), porcentajeDescuento);
    }
}
