package ui.pantallas.nuevaPelicula;


import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Pelicula;
import servicios.ServiciosSeriesPeliculas;

public class NuevaPeliculaViewModel {

    private ServiciosSeriesPeliculas serviciosSeriesPeliculasImpl;

    @Inject
    public NuevaPeliculaViewModel(ServiciosSeriesPeliculas serviciosProductos) {
        this.serviciosSeriesPeliculasImpl = serviciosProductos;
        state = new SimpleObjectProperty<>(new NuevaPeliculaState( null, null));

    }
    private final ObjectProperty<NuevaPeliculaState> state;
    public ReadOnlyObjectProperty<NuevaPeliculaState> getState() {
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

    public void agregarPelicula(Pelicula peli) {
        NuevaPeliculaState nuevaPeliculaState = null;
        if (serviciosSeriesPeliculasImpl.agregarPelicula(peli)) {
            nuevaPeliculaState = new NuevaPeliculaState(null, "PELICULA AGREGADA");
        }
        else {
            nuevaPeliculaState = new NuevaPeliculaState("NO SE HA PODIDO CREAR LA PELICULA", null);
        }
        state.setValue(nuevaPeliculaState);
    }
}
