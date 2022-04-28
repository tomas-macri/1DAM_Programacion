package ui.pantallas.mainCliente;

import lombok.Data;
import modelo.Ingrediente;
import modelo.Tarjeta;

import java.util.List;

@Data
public class MainClienteState {

    List<Ingrediente> ingredienteList;
    List<Tarjeta> tarjetaList;
    int descuento;
    String error;

    public MainClienteState(List<Ingrediente> ingredienteList, List<Tarjeta> tarjetaList, int descuento, String error) {
        this.ingredienteList = ingredienteList;
        this.tarjetaList = tarjetaList;
        this.descuento = descuento;
        this.error = error;
    }
}
