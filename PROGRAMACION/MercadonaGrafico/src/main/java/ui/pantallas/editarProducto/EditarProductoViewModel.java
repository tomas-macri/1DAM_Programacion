package ui.pantallas.editarProducto;


import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Ingrediente;
import modelo.Productos.Producto;
import servicios.impl.ServiciosProductosImpl;
import ui.pantallas.mainAdmin.MainAdminState;

import java.util.List;

public class EditarProductoViewModel {

    private ServiciosProductosImpl serviciosProductos;

    @Inject
    public EditarProductoViewModel(ServiciosProductosImpl serviciosProductos) {
        this.serviciosProductos = serviciosProductos;
        EditarProductoState eps = null;
        state = new SimpleObjectProperty<>(new EditarProductoState(null, null));

    }
    private final ObjectProperty<EditarProductoState> state;
    public ReadOnlyObjectProperty<EditarProductoState> getState() {
        return state;
    }


    public void loadIngredientes(Producto prod) {
            EditarProductoState editarProductoState = null;
            List<Ingrediente> ingredienteList = serviciosProductos.getProducto(prod.getNombre()).getListaIngredientes();
            if (ingredienteList==null)
                editarProductoState = new EditarProductoState(null,"no se han podido cargar cromos");
            else
                editarProductoState = new EditarProductoState(ingredienteList,null);
            state.setValue(editarProductoState);
    }
}
