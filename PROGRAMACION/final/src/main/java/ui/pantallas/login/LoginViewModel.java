package ui.pantallas.login;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Usuario;
import servicios.ServiciosUsuarios;

public class LoginViewModel {

    private ServiciosUsuarios serviciosUsuariosImpl;

    @Inject
    public LoginViewModel(ServiciosUsuarios serviciosUsuariosImpl) {
        this.serviciosUsuariosImpl = serviciosUsuariosImpl;
        state = new SimpleObjectProperty<>(null,null);
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
            state.setValue(new LoginState(null, "NO EXISTE EL DNI"));
        }
    }
}




