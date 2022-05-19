package servicios.impl;

import dao.DaoSeriesPeliculas;
import jakarta.inject.Inject;
import modelo.Actor;
import modelo.Pelicula;
import modelo.Serie;
import servicios.ServiciosSeriesPeliculas;

import java.util.List;

public class ServiciosSeriesPeliculasImpl implements ServiciosSeriesPeliculas {

    private DaoSeriesPeliculas daoSeriesPeliculasImpl;

    @Inject
    public ServiciosSeriesPeliculasImpl(DaoSeriesPeliculas daoSeriesPeliculasImpl) {
        this.daoSeriesPeliculasImpl = daoSeriesPeliculasImpl;
    }

    @Override public boolean agregarPelicula(Pelicula nueva){
        if (nueva.getNombre().equals("")  || nueva.getActores().isEmpty()){
            return false;
        }
        return daoSeriesPeliculasImpl.agregarPeli(nueva);

    }
    @Override public boolean agregarSerie(Serie nueva){
        if (nueva.getNombre().equals("")  || nueva.getEpisodios().isEmpty()){
            return false;
        }
        return daoSeriesPeliculasImpl.agregarSerie(nueva);

    }

    @Override public List<Serie> getTodasLasSeries() {
        return daoSeriesPeliculasImpl.getTodasLasSeries();
    }

    @Override public List<Pelicula> getTodasLasPeliculas() {
        return daoSeriesPeliculasImpl.getTodasLasPeliculas();
    }

    @Override
    public List<Pelicula> getTodasLasPeliculasFiltroActor(Actor actor) {
        return daoSeriesPeliculasImpl.getTodasLasPeliculasFiltroActor(actor);
    }

    @Override
    public List<Serie> getTodasLasSeriesFiltroActor(Actor actor) {
        return daoSeriesPeliculasImpl.getTodasLasSeriesFiltroActor(actor);
    }
}
