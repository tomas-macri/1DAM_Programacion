package dao;

import modelo.Actor;
import modelo.Pelicula;
import modelo.Serie;

import java.util.List;

public interface DaoSeriesPeliculas {
    boolean agregarPeli(Pelicula nueva);

    boolean agregarSerie(Serie nueva);

    List<Serie> getTodasLasSeries();

    List<Pelicula> getTodasLasPeliculas();

    List<Pelicula> getTodasLasPeliculasFiltroActor(Actor actor);

    List<Serie> getTodasLasSeriesFiltroActor(Actor actor);
}
