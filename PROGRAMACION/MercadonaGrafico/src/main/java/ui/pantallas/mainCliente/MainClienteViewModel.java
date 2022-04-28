package ui.pantallas.mainCliente;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Ingrediente;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioEspecial;
import servicios.impl.ServiciosEstadisticasImpl;
import servicios.impl.ServiciosTarjetasImpl;
import servicios.impl.ServiciosUsuariosImpl;

import java.util.ArrayList;
import java.util.List;

public class MainClienteViewModel {

    private ServiciosUsuariosImpl serviciosUsuarios;
    private ServiciosTarjetasImpl serviciosTarjetas;

    @Inject
    public MainClienteViewModel(ServiciosUsuariosImpl serviciosUsuarios, ServiciosTarjetasImpl serviciosTarjetas) {
        this.serviciosUsuarios = serviciosUsuarios;
        this.serviciosTarjetas = serviciosTarjetas;
        state = new SimpleObjectProperty<>(new MainClienteState(null, null, 0, null));

    }
    private final ObjectProperty<MainClienteState> state;
    public ReadOnlyObjectProperty<MainClienteState> getState() {
        return state;
    }

    public void loadTablas(Usuario user) {
        MainClienteState mainClienteState;
        List<Ingrediente> ingredienteList = null;
        List<Tarjeta> tarjetasList = null;
        int descuento = 0;
        if (serviciosUsuarios.getUsuario(user.getDni()) != null) {
            if (user instanceof UsuarioEspecial) {
                descuento = ((UsuarioEspecial) user).getPorcentajeDescuento();
            }
            ingredienteList = serviciosUsuarios.getUsuario(user.getDni()).getIngredienteList();
            tarjetasList = new ArrayList<>(serviciosUsuarios.getUsuario(user.getDni()).getListaTarjetas());
        }
        if (ingredienteList==null && tarjetasList==null)
            mainClienteState = new MainClienteState(null, null,descuento, "no se han podido cargar algunas cosas");
        else
            mainClienteState = new MainClienteState(ingredienteList, tarjetasList, descuento,null);
        state.setValue(mainClienteState);
    }

    public void nuevaTarjeta(Usuario usuarioLogueado, Tarjeta tarjeta) {
        if (serviciosTarjetas.agregarTarjeta(tarjeta, usuarioLogueado)) {
            loadTablas(usuarioLogueado);
        }
        else{
            state.setValue(new MainClienteState(null, null, 0, "no se ha podido agregar la tarjeta"));
        }

    }
}
