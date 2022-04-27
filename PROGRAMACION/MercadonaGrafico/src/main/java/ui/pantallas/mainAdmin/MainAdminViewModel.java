package ui.pantallas.mainAdmin;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;
import servicios.impl.ServiciosProductosImpl;
import servicios.impl.ServiciosUsuariosImpl;
import ui.pantallas.login.LoginState;

import java.util.LinkedHashMap;
import java.util.List;

public class MainAdminViewModel {

    private ServiciosUsuariosImpl serviciosUsuarios;
    private ServiciosProductosImpl serviciosProductos;

    @Inject
    public MainAdminViewModel(ServiciosUsuariosImpl serviciosUsuarios, ServiciosProductosImpl serviciosProductos) {
        this.serviciosUsuarios = serviciosUsuarios;
        this.serviciosProductos = serviciosProductos;


        MainAdminState mas = null;
        List<Usuario> usuarioList = serviciosUsuarios.getLista();
        List<Producto> productosList = serviciosProductos.getLista();
        if (usuarioList==null && productosList==null)
        {
            mas = new MainAdminState(null, null, "no se han podido cargar usuarios");
        }
        else if (usuarioList==null){
            mas = new MainAdminState(null, productosList, null);
        }
        else {
            mas = new MainAdminState(usuarioList, null, null);

        }

        state = new SimpleObjectProperty<>(new MainAdminState(usuarioList, productosList, ""));

    }
    private final ObjectProperty<MainAdminState> state;
    public ReadOnlyObjectProperty<MainAdminState> getState() {
        return state;
    }

    public void getUsuarios() {
        MainAdminState mas = null;
        List<Usuario> usuarios = serviciosUsuarios.getLista();
        if (usuarios==null)
            mas = new MainAdminState(null, null, "no se han podido cargar usuarios");
        else
            mas = new MainAdminState(usuarios, null ,null);
        state.setValue(mas);
    }

    public void getProductos() {
        MainAdminState mas = null;
        List<Producto> productos = serviciosProductos.getLista();
        if (productos==null)
            mas = new MainAdminState(null, null, "no se han podido cargar productos");
        else
            mas = new MainAdminState(null, productos ,null);
        state.setValue(mas);
    }
}
