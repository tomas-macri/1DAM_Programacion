package com.ctrempleados.gui.pantallas.login;

import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.servicios.ServiciosEmpleados;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LoginViewModel {


    private final ServiciosEmpleados serviciosEmpleados;
    private final ObjectProperty<LoginState> state;

    @Inject
    public LoginViewModel(ServiciosEmpleados serviciosEmpleados) {
        this.serviciosEmpleados = serviciosEmpleados;
        state = new SimpleObjectProperty<>(new LoginState(false, null));
    }

    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }

    public void doLoginCode(String code) {
        int codigo = 0;
        try {
            codigo = Integer.parseInt(code);
            Either<String, Empleado> empleado = serviciosEmpleados.getEmpleadoCodigo(codigo);
            if (empleado.isRight()) {
                state.setValue(new LoginState(true, null));
            } else {
                state.setValue(new LoginState(false, empleado.getLeft()));
            }
        } catch (NumberFormatException e) {
            state.set(new LoginState(false, ConstantesPantallas.EL_CODIGO_DE_ACCESO_DEBE_SER_UN_NUMERO));
        }
    }

    public String getAccessCode(String dni) {
        Either<String, Empleado> empleado = serviciosEmpleados.getEmpleadoDNI(dni);
        if (empleado.isRight()) {
            return String.valueOf(empleado.get().getCodigoAcceso());
        } else {
            state.setValue(new LoginState(false, empleado.getLeft()));
            return null;
        }
    }
}
