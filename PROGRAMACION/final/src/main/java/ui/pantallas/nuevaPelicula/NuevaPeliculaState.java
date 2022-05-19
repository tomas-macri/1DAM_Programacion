package ui.pantallas.nuevaPelicula;

import lombok.Data;
import modelo.Actor;

import java.time.LocalDate;
import java.util.List;

@Data
public class NuevaPeliculaState {
    String error;
    String confirmacion;

    public NuevaPeliculaState(String error, String confirmacion) {
        this.error = error;
        this.confirmacion = confirmacion;
    }
}
