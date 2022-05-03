package ui.pantallas.compras;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;
import servicios.impl.ServiciosComprasImpl;
import servicios.impl.ServiciosProductosImpl;
import servicios.impl.ServiciosUsuariosImpl;
import ui.pantallas.editarUsuario.EditarUsuarioState;

import javax.swing.*;
import java.util.List;

public class ComprasViewModel {
    private ServiciosComprasImpl serviciosCompras;
    private ServiciosProductosImpl serviciosProductos;
    private ServiciosUsuariosImpl serviciosUsuarios;

    @Inject
    public ComprasViewModel(ServiciosComprasImpl serviciosCompras, ServiciosProductosImpl serviciosProductos, ServiciosUsuariosImpl serviciosUsuarios) {
        this.serviciosCompras = serviciosCompras;
        this.serviciosProductos = serviciosProductos;
        this.serviciosUsuarios = serviciosUsuarios;

        state = new SimpleObjectProperty<>(new ComprasState(null,null, null));
    }
    private final ObjectProperty<ComprasState> state;
    public ReadOnlyObjectProperty<ComprasState> getState() {
        return state;
    }


    public void getProductosDisponibles(Usuario userLogueado, List<Producto> productos) {
        List<Producto> productoList = serviciosCompras.getProductosSinAlergia(userLogueado, serviciosProductos.getLista());
        if ( productoList != null) {
            state.setValue(new ComprasState(productoList, userLogueado.getCarrito(), null));
        }
        else {
            state.setValue(new ComprasState(null, null, "No hay productos disponibles"))    ;
        }
    }

    public List<Producto> getProductos() {
        return serviciosProductos.getLista();
    }
}
