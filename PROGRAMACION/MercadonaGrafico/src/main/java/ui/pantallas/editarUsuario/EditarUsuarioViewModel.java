package ui.pantallas.editarUsuario;


import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Ingrediente;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioEspecial;
import servicios.impl.ServiciosProductosImpl;
import servicios.impl.ServiciosUsuariosImpl;

import java.util.List;

public class EditarUsuarioViewModel {

    private ServiciosUsuariosImpl serviciosUsuarios;

    @Inject
    public EditarUsuarioViewModel(ServiciosUsuariosImpl serviciosUsuarios) {
        this.serviciosUsuarios = serviciosUsuarios;
        state = new SimpleObjectProperty<>(new EditarUsuarioState(null,0, null));

    }
    private final ObjectProperty<EditarUsuarioState> state;
    public ReadOnlyObjectProperty<EditarUsuarioState> getState() {
        return state;
    }


    public void loadIngredientes(Usuario user) {
            EditarUsuarioState editarUsuarioState;
            int descuento = 0;
           List<Ingrediente> ingredienteList = null;
            if (serviciosUsuarios.getUsuario(user.getDni()) != null) {
                if (user instanceof UsuarioEspecial) {
                    descuento = ((UsuarioEspecial) user).getPorcentajeDescuento();
                }
                ingredienteList = serviciosUsuarios.getUsuario(user.getDni()).getIngredienteList();
            }
            if (ingredienteList==null)
                editarUsuarioState = new EditarUsuarioState(null, descuento, "no se han podido cargar cromos");
            else
                editarUsuarioState = new EditarUsuarioState(ingredienteList,descuento, null);
            state.setValue(editarUsuarioState);
    }

    public void updateProduct(Usuario user, String nomProdActual) {
        EditarUsuarioState editarUsuarioState = null;
        try {
            serviciosUsuarios.modificarUsuario(user, nomProdActual);
            editarUsuarioState = new EditarUsuarioState(null, 0, "Producto actualizado");
        } catch (Exception e) {
            editarUsuarioState = new EditarUsuarioState(null, 0, e.getMessage());
        }
        state.setValue(editarUsuarioState);
    }

    public void agregarProducto(Usuario usuario) {
        EditarUsuarioState editarUsuarioState = null;
        try {
            serviciosUsuarios.agregarUsuario(usuario);
            editarUsuarioState = new EditarUsuarioState(null, 0, "Producto agregado");
        } catch (Exception e) {
            editarUsuarioState = new EditarUsuarioState(null, 0, e.getMessage());
        }
        state.setValue(editarUsuarioState);
    }
}
