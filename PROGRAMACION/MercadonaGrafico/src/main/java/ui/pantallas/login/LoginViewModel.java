package ui.pantallas.login;

import common.Constantes;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Usuarios.Usuario;
import servicios.impl.ServiciosUsuariosImpl;

public class LoginViewModel {

    private ServiciosUsuariosImpl serviciosUsuariosImpl;

    @Inject
    public LoginViewModel(ServiciosUsuariosImpl serviciosUsuariosImpl) {
        this.serviciosUsuariosImpl = serviciosUsuariosImpl;
        state = new SimpleObjectProperty<>(new LoginState(null,null));
    }

    private final ObjectProperty<LoginState> state;
    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }


    public void doLogin(String dni) {
        Usuario userLogueado = serviciosUsuariosImpl.getUsuario(dni);
        if (userLogueado != null) {
            state.setValue(new LoginState(userLogueado,null));
        }
        else
        {
            state.setValue(new LoginState(null, Constantes.DNI_INEXISTENTE));
        }
    }
}




