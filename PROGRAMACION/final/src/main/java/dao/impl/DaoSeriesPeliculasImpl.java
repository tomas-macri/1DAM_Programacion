package dao.impl;

import dao.DaoSeriesPeliculas;
import jakarta.inject.Inject;
import modelo.Actor;
import modelo.Pelicula;
import modelo.Serie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoSeriesPeliculasImpl implements DaoSeriesPeliculas {
    private DataBase dataBase;

    @Inject
    public DaoSeriesPeliculasImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    @Override
    public boolean agregarPeli(Pelicula nueva) {
        List<Pelicula> peliculaList = dataBase.loadPeliculas();
        if (peliculaList == null) {
            peliculaList = new ArrayList<>();
        }

        peliculaList.add(nueva);

        return dataBase.savePeliculas(peliculaList);
    }

    @Override
    public boolean agregarSerie(Serie nueva) {
        List<Serie> serieList = dataBase.loadSeries();
        if (serieList == null) {
            serieList = new ArrayList<>();
        }

        serieList.add(nueva);

        return dataBase.saveSeries(serieList);
    }

    @Override
    public List<Serie> getTodasLasSeries() {
        List<Serie> serieList = dataBase.loadSeries();
        if (serieList == null) {
            serieList = new ArrayList<>();
        }
        return serieList;
    }

    @Override
    public List<Pelicula> getTodasLasPeliculas() {
        List<Pelicula> peliculaList = dataBase.loadPeliculas();
        if (peliculaList == null) {
            peliculaList = new ArrayList<>();
        }
        return peliculaList;
    }


    @Override
    public List<Pelicula> getTodasLasPeliculasFiltroActor(Actor actor) {
        List<Pelicula> peliculaList = dataBase.loadPeliculas();
        if (peliculaList == null) {
            peliculaList = new ArrayList<>();
        } else {
            return peliculaList.stream().filter(pelicula -> pelicula.getActores().contains(actor)).collect(Collectors.toList());
        }
        return peliculaList;
    }

    @Override
    public List<Serie> getTodasLasSeriesFiltroActor(Actor actor) {
        List<Serie> serieList = dataBase.loadSeries();
        if (serieList == null) {
            serieList = new ArrayList<>();
        } else {
            return serieList.stream().filter
                    (serie -> serie.getEpisodios().stream()
                            .flatMap(episodio -> episodio.getActores().stream())
                            .anyMatch(actor1 -> actor1.equals(actor)))
                    .collect(Collectors.toList());
        }
        return serieList;
    }
}
