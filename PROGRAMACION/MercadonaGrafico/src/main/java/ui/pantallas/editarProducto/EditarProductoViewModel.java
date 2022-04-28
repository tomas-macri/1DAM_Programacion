package ui.pantallas.editarProducto;


import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Ingrediente;
import modelo.Productos.Producto;
import modelo.Productos.ProductoCaducable;
import servicios.impl.ServiciosProductosImpl;
import ui.pantallas.mainAdmin.MainAdminState;

import java.time.LocalDate;
import java.util.List;

public class EditarProductoViewModel {

    private ServiciosProductosImpl serviciosProductos;

    @Inject
    public EditarProductoViewModel(ServiciosProductosImpl serviciosProductos) {
        this.serviciosProductos = serviciosProductos;
        state = new SimpleObjectProperty<>(new EditarProductoState(null,null, null));

    }
    private final ObjectProperty<EditarProductoState> state;
    public ReadOnlyObjectProperty<EditarProductoState> getState() {
        return state;
    }


    public void loadIngredientes(Producto prod) {
            EditarProductoState editarProductoState;
            LocalDate fecha = null;
           List<Ingrediente> ingredienteList = null;
            if (serviciosProductos.getProducto(prod.getNombre()) != null) {
                if (prod instanceof ProductoCaducable) {
                    fecha = ((ProductoCaducable) prod).getCaducidad().toLocalDate();
                }
                ingredienteList = serviciosProductos.getProducto(prod.getNombre()).getListaIngredientes();
            }
            if (ingredienteList==null)
                editarProductoState = new EditarProductoState(null, fecha, "no se han podido cargar cromos");
            else
                editarProductoState = new EditarProductoState(ingredienteList,fecha, null);
            state.setValue(editarProductoState);
    }

    public void updateProduct(Producto prod, String nomProdActual) {
        EditarProductoState editarProductoState = null;
        try {
            if (serviciosProductos.modificarProducto(prod, nomProdActual)){
                editarProductoState = new EditarProductoState(prod.getListaIngredientes(), prod instanceof ProductoCaducable ? ((ProductoCaducable)prod).getCaducidad().toLocalDate() : null , null);
            }
            else {
                editarProductoState = new EditarProductoState(null, null, "Producto actualizado");
            }
        } catch (Exception e) {
            editarProductoState = new EditarProductoState(null, null, e.getMessage());
        }
        state.setValue(editarProductoState);
    }

    public void agregarProducto(Producto producto) {
        EditarProductoState editarProductoState = null;
        try {
            serviciosProductos.agregarProducto(producto);
            editarProductoState = new EditarProductoState(null, null, "Producto agregado");
        } catch (Exception e) {
            editarProductoState = new EditarProductoState(null, null, e.getMessage());
        }
        state.setValue(editarProductoState);
    }
}
