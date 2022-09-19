package dao.impl;

import dao.DaoActores;
import jakarta.inject.Inject;
import modelo.Actor;
import modelo.Pelicula;
import modelo.Serie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoActoresImpl implements DaoActores {

    private DataBase dataBase;

    @Inject
    public DaoActoresImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    @Override
    public List<Actor> getTodosLosActores() {
        List<Serie> series = dataBase.loadSeries();
        List<Pelicula> peliculas = dataBase.loadPeliculas();
        List<Actor> todosLosActores = new ArrayList<>();
        if (series!=null){
            todosLosActores.addAll(
                    series.stream()
                            .flatMap(serie -> serie.getEpisodios()
                                    .stream()).
                    flatMap(episodio -> episodio.getActores()
                            .stream())
                            .collect(Collectors.toList()));//.filter(actor -> todosLosActores.contains(actor))collect(Collectors.toList());
        }
        if (peliculas != null){
            todosLosActores.addAll(peliculas.stream().flatMap(pelicula -> pelicula.getActores().stream()).collect(Collectors.toList()));
        }

//        todosLosActores.stream().filter
//                (actor -> todosLosActores.stream().
//                        filter(actor1 -> actor1.equals(actor))
//                        .count() > 1)
//
//                .collect(Collectors.toList());
        return todosLosActores;
    }
}
