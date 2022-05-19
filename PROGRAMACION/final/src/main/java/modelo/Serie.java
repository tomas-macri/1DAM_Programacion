package modelo;

import lombok.Data;

import java.util.List;

@Data
public class Serie {


    private String nombre;
    private List<Episodio> episodios;

    public Serie(String nombre, List<Episodio> episodios) {
        this.nombre = nombre;
        this.episodios = episodios;
    }
}
