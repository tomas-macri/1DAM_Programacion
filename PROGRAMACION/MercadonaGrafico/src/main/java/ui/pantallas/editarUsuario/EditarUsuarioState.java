package ui.pantallas.editarUsuario;

import lombok.Data;
import modelo.Ingrediente;

import java.time.LocalDate;
import java.util.List;

@Data
public class EditarUsuarioState {
    List<Ingrediente> ingredienteList;
    int descuento;
    String error;

    public EditarUsuarioState(List<Ingrediente> ingredienteList, int descuento, String error) {
        this.ingredienteList = ingredienteList;
        this.descuento = descuento;
        this.error = error;
    }
}
