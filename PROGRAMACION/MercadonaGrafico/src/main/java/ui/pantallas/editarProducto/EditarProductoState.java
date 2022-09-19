package ui.pantallas.editarProducto;

import lombok.Data;
import modelo.Ingrediente;

import java.time.LocalDate;
import java.util.List;

@Data
public class EditarProductoState {
    List<Ingrediente> ingredienteList;
    LocalDate caducidad;
    String error;

    public EditarProductoState(List<Ingrediente> ingredienteList, LocalDate fecha, String error) {
        this.ingredienteList = ingredienteList;
        this.caducidad = fecha;
        this.error = error;
    }
}
