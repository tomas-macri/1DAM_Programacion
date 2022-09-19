package modelo;

import lombok.Data;

import java.util.List;

@Data
public class Episodio {

    private String nombre;
    private List<Actor> actores;


    public Episodio(String nombre, List<Actor> actores) {
        this.nombre = nombre;
        this.actores = actores;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
