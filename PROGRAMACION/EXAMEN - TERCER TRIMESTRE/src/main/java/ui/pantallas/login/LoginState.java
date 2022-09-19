package ui.pantallas.login;

import lombok.Data;
import modelo.Usuario;

@Data
public class LoginState {
    private final Usuario userLogueado;
    private final String error;
}
