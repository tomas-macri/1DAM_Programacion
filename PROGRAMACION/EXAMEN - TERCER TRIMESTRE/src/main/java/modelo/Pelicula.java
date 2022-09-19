package modelo;

import lombok.Data;

import java.util.List;

@Data
public class Pelicula {

    String nombre;
    List<Actor> actores;

    public Pelicula(String nombre, List<Actor> actores) {
        this.nombre = nombre;
        this.actores = actores;
    }
}
