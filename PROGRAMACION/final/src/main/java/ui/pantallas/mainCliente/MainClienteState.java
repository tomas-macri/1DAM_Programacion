package ui.pantallas.mainCliente;

import lombok.Data;
import modelo.Actor;
import modelo.Pelicula;
import modelo.Serie;

import javax.swing.text.PlainDocument;
import java.util.List;

@Data
public class MainClienteState {

    List<Actor> actors;
    List<Serie> series;
    List<Pelicula> pelis;
    String error;

    public MainClienteState(List<Actor> actors, List<Serie> series, List<Pelicula> pelis, String error) {
        this.actors = actors;
        this.series = series;
        this.pelis = pelis;
        this.error = error;
    }
}
