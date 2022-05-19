package ui.pantallas.nuevaSerie;

import lombok.Data;

@Data
public class NuevaSerieState {
    String error;
    String confirmacion;

    public NuevaSerieState(String error, String confirmacion) {
        this.error = error;
        this.confirmacion = confirmacion;
    }
}
