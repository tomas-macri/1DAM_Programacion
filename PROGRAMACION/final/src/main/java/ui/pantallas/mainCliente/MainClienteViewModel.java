package ui.pantallas.mainCliente;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Actor;
import modelo.Pelicula;
import modelo.Serie;
import servicios.ServiciosActores;
import servicios.ServiciosSeriesPeliculas;

import java.util.List;

public class MainClienteViewModel {

    private ServiciosSeriesPeliculas serviciosSeriesPeliculasImpl;
    private ServiciosActores serviciosActoresImpl;

    @Inject
    public MainClienteViewModel(ServiciosSeriesPeliculas serviciosSeriesPeliculasImpl, ServiciosActores serviciosActoresImpl) {
        this.serviciosSeriesPeliculasImpl = serviciosSeriesPeliculasImpl;
        this.serviciosActoresImpl = serviciosActoresImpl;
        state = new SimpleObjectProperty<>(new MainClienteState(null, null, null, null));

    }
    private final ObjectProperty<MainClienteState> state;
    public ReadOnlyObjectProperty<MainClienteState> getState() {
        return state;
    }

    public void loadTablas() {
        MainClienteState mainClienteState;
        List<Actor> actorList = null;
        List<Serie> seriesList = null;
        List<Pelicula> peliculaList = null;

        actorList = serviciosActoresImpl.getTodosLosActores();
        seriesList = serviciosSeriesPeliculasImpl.getTodasLasSeries();
        peliculaList = serviciosSeriesPeliculasImpl.getTodasLasPeliculas();
        if (peliculaList==null || seriesList==null || actorList == null)
            mainClienteState = new MainClienteState(null, null, null, "no se han podido cargar algunas cosas");
        else
            mainClienteState = new MainClienteState(actorList, seriesList, peliculaList,null);
        state.setValue(mainClienteState);
    }

    public void loadTablasFiltadasActor(Actor actor) {
        MainClienteState mainClienteState;
        List<Serie> seriesList = null;
        List<Pelicula> peliculaList = null;
        if (actor != null){
            seriesList = serviciosSeriesPeliculasImpl.getTodasLasSeriesFiltroActor(actor);
            peliculaList = serviciosSeriesPeliculasImpl.getTodasLasPeliculasFiltroActor(actor);
            mainClienteState = new MainClienteState(List.of(actor), seriesList, peliculaList, null);
        }
        else mainClienteState = new MainClienteState(null, null, null, "ERROR FILTRANDO POR ACTOR");
        state.setValue(mainClienteState);
    }
}
