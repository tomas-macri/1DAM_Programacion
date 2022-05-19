package ui.pantallas.nuevaSerie;


import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Actor;
import modelo.Serie;
import servicios.ServiciosSeriesPeliculas;

import java.util.ArrayList;

public class NuevaSerieViewModel {

    private ServiciosSeriesPeliculas serviciosSeriesPeliculasImpl;

    @Inject
    public NuevaSerieViewModel(ServiciosSeriesPeliculas serviciosProductos) {
        this.serviciosSeriesPeliculasImpl = serviciosProductos;
        state = new SimpleObjectProperty<>(new NuevaSerieState( null, null));

    }
    private final ObjectProperty<NuevaSerieState> state;
    public ReadOnlyObjectProperty<NuevaSerieState> getState() {
        return state;
    }


//    public void loadActores(Producto prod) {
//            NuevaPeliculaState nuevaPeliculaState;
//            LocalDate fecha = null;
//           List<Actor> actorList = null;
//            if (serviciosPeliculas.getProducto(prod.getNombre()) != null) {
//                if (prod instanceof ProductoCaducable) {
//                    fecha = ((ProductoCaducable) prod).getCaducidad().toLocalDate();
//                }
//                actorList = serviciosPeliculas.getProducto(prod.getNombre()).getListaIngredientes();
//            }
//            if (actorList != null) {
//                nuevaPeliculaState = new NuevaPeliculaState(actorList, fecha, null);
//                state.setValue(nuevaPeliculaState);
//            }
//    }

//    public void updateProduct(Producto prod, String nomProdActual) {
//        NuevaPeliculaState nuevaPeliculaState = null;
//        try {
//            if (serviciosPeliculas.modificarProducto(prod, nomProdActual)){
//                nuevaPeliculaState = new NuevaPeliculaState(prod.getListaIngredientes(), prod instanceof ProductoCaducable ? ((ProductoCaducable)prod).getCaducidad().toLocalDate() : null , null);
//            }
//            else {
//                nuevaPeliculaState = new NuevaPeliculaState(null, null, "Producto actualizado");
//            }
//        } catch (Exception e) {
//            nuevaPeliculaState = new NuevaPeliculaState(null, null, e.getMessage());
//        }
//        state.setValue(nuevaPeliculaState);
//    }

    public void agregarSerie(Serie serie) {
        NuevaSerieState nuevaSerieState = null;
        if (serviciosSeriesPeliculasImpl.agregarSerie(serie)) {
            nuevaSerieState = new NuevaSerieState(null, "SERIE AGREGADA");
        }
        else {
            nuevaSerieState = new NuevaSerieState("NO SE HA PODIDO CREAR LA SERIE", null);
        }
        state.setValue(nuevaSerieState);
    }

    public boolean validEp(String nombre, ArrayList<Actor> actors) {
        NuevaSerieState nuevaSerieState = null;
        boolean valido = true;
        if (nombre.isEmpty() || actors.isEmpty()) {
            nuevaSerieState = new NuevaSerieState("EPISODIO NO VALIDO", null);
            valido = false;
        }
        else {
            nuevaSerieState = new NuevaSerieState(null, "EPISODIO " + nombre + "AGREGADO");
        }
        state.setValue(nuevaSerieState);
        return valido;
    }

}
