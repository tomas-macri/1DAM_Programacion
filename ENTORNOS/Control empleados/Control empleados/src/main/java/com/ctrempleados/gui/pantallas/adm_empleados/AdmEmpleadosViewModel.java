package com.ctrempleados.gui.pantallas.adm_empleados;

import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Franquicia;
import com.ctrempleados.domain.servicios.ServiciosEmpleados;
import com.ctrempleados.domain.servicios.ServiciosFranquicias;
import com.ctrempleados.domain.servicios.ServiciosNominas;
import com.ctrempleados.gui.pantallas.common.ConstantesPantallas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class AdmEmpleadosViewModel {

    private final ServiciosEmpleados serviciosEmpleados;
    private final ServiciosFranquicias serviciosFranquicias;
    private final ServiciosNominas serviciosNominas;
    private final ObjectProperty<AdmEmpleadosState> state;

    @Inject
    public AdmEmpleadosViewModel(ServiciosEmpleados serviciosEmpleados, ServiciosFranquicias serviciosFranquicias,
                                 ServiciosNominas serviciosNominas) {
        this.serviciosEmpleados = serviciosEmpleados;
        this.serviciosFranquicias = serviciosFranquicias;
        this.serviciosNominas = serviciosNominas;
        state = new SimpleObjectProperty<>(new AdmEmpleadosState(false,
                false, false, null, null));
    }

    public ReadOnlyObjectProperty<AdmEmpleadosState> getState() {
        return state;
    }

    public List<Empleado> getEmpleados() {
        Either<String, List<Empleado>> empleados = serviciosEmpleados.getEmpleados();
        if (empleados.isRight()) {
            state.set(new AdmEmpleadosState(false, false,
                    false, empleados.get(), null));
        } else {
            state.set(new AdmEmpleadosState(false, false,
                    false, null, empleados.getLeft()));
        }
        return state.get().getEmpleados();
    }

    public void agregarEmpleado(String dniText, String nombreText, String apellidoText,
                                double sueldoHora, String franquiciaText) {
        Either<String, Franquicia> franquiciaEither = serviciosFranquicias.getFranquicia(franquiciaText);
        if (franquiciaEither.isRight()) {
            Franquicia franquicia = franquiciaEither.get();
            franquicia.setNumeroEmpleados(franquicia.getNumeroEmpleados() + 1);
            serviciosFranquicias.modificarFranquicia(franquicia);
            if (serviciosEmpleados.crearEmpleado(dniText, nombreText, apellidoText, sueldoHora, franquiciaText)
                    && serviciosNominas.crearNomina(serviciosEmpleados.getEmpleadoDNI(dniText).get())) {
                state.set(new AdmEmpleadosState(true, false, false, null, null));
            } else {
                state.set(new AdmEmpleadosState(false, false, false, null, ConstantesPantallas.NO_SE_HA_PODIDO_AÑADIR_EL_EMPLEADO));
            }
        } else {
            state.set(new AdmEmpleadosState(false, false, false, null, franquiciaEither.getLeft()));
        }
    }

    public void actualizarEmpleado(Empleado empleadoSeleccionado) {
        if (serviciosEmpleados.modificarEmpleado(empleadoSeleccionado)) {
            state.set(new AdmEmpleadosState(false, true, false, null, null));
        } else {
            state.set(new AdmEmpleadosState(false, false, false, null, ConstantesPantallas.NO_SE_HA_PODIDO_MODIFICAR_EL_EMPLEADO));
        }
    }

    public void eliminarEmpleado(Empleado empleadoSeleccionado) {
        if (serviciosEmpleados.eliminarEmpleado(empleadoSeleccionado.getDni())) {
            Either<String, Franquicia> franquiciaPrevia = serviciosFranquicias.getFranquicia(empleadoSeleccionado.getNombreFranquicia());
            if (franquiciaPrevia.isRight()) {
                Franquicia franquicia = franquiciaPrevia.get();
                franquicia.setNumeroEmpleados(franquicia.getNumeroEmpleados() - 1);
                serviciosFranquicias.modificarFranquicia(franquicia);
            }
            state.set(new AdmEmpleadosState(false, false, true, null, null));
        } else {
            state.set(new AdmEmpleadosState(false, false, false, null, ConstantesPantallas.NO_SE_HA_PODIDO_ELIMINAR_EL_EMPLEADO));
        }
    }

    public void actualizarFranquicia(Empleado empleadoSeleccionado, String franquiciaText) {
        Either<String, Franquicia> franquiciaEither = serviciosFranquicias.getFranquicia(franquiciaText);
        if (franquiciaEither.isRight()) {
            Either<String, Franquicia> franquiciaPrevia = serviciosFranquicias.getFranquicia(empleadoSeleccionado.getNombreFranquicia());
            if (franquiciaPrevia.isRight()) {
                Franquicia franquicia = franquiciaPrevia.get();
                franquicia.setNumeroEmpleados(franquicia.getNumeroEmpleados() - 1);
                serviciosFranquicias.modificarFranquicia(franquicia);
            }
            Franquicia franquiciaNueva = franquiciaEither.get();
            franquiciaNueva.setNumeroEmpleados(franquiciaNueva.getNumeroEmpleados() + 1);
            if (serviciosEmpleados.cambiarFranquicia(empleadoSeleccionado, franquiciaNueva)
                    && serviciosFranquicias.modificarFranquicia(franquiciaNueva)) {
                serviciosNominas.addFranquiciaNomina(franquiciaNueva, empleadoSeleccionado);
                state.set(new AdmEmpleadosState(false, true, false, null, null));
            } else {
                state.set(new AdmEmpleadosState(false, false, false, null, ConstantesPantallas.NO_SE_HA_PODIDO_ASIGNAR_EL_EMPLEADO_A_LA_FRANQUICIA));
            }
        } else {
            state.set(new AdmEmpleadosState(false, false, false, null, franquiciaEither.getLeft()));
        }
    }

    public void quitarFranquicia(Empleado empleadoSeleccionado) {
        Either<String, Franquicia> franquiciaPrevia = serviciosFranquicias.getFranquicia(empleadoSeleccionado.getNombreFranquicia());
        if (franquiciaPrevia.isRight()) {
            Franquicia franquicia = franquiciaPrevia.get();
            franquicia.setNumeroEmpleados(franquicia.getNumeroEmpleados() - 1);
            serviciosFranquicias.modificarFranquicia(franquicia);
        }
        if (serviciosEmpleados.quitarFranquicia(empleadoSeleccionado)) {
            state.set(new AdmEmpleadosState(false, true, false, null, null));
        } else {
            state.set(new AdmEmpleadosState(false, false, false, null, ConstantesPantallas.NO_SE_HA_PODIDO_DESVINCULAR_EL_EMPLEADO_DE_LA_FRANQUICIA));
        }
    }
}
