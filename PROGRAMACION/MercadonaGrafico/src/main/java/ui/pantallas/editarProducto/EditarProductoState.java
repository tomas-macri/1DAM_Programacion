package ui.pantallas.editarProducto;

import lombok.Data;
import modelo.Ingrediente;

import java.util.List;

@Data
public class EditarProductoState {
    List<Ingrediente> ingredienteList;
    String error;

    public EditarProductoState(List<Ingrediente> ingredienteList, String error) {
        this.ingredienteList = ingredienteList;
        this.error = error;
    }
}
