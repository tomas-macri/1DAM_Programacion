package com.ctrempleados.domain.servicios.impl;

import com.ctrempleados.dao.DaoFranquicias;
import com.ctrempleados.domain.modelo.Franquicia;
import com.ctrempleados.domain.servicios.ServiciosEmpleados;
import com.ctrempleados.domain.servicios.ServiciosFranquicias;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class ServiciosFranquiciasImpl implements ServiciosFranquicias {

    private final DaoFranquicias daoFranquicias;
    private final ServiciosEmpleados serviciosEmpleados;

    @Inject
    public ServiciosFranquiciasImpl(DaoFranquicias daoFranquicias, ServiciosEmpleados serviciosEmpleados) {
        this.daoFranquicias = daoFranquicias;
        this.serviciosEmpleados = serviciosEmpleados;
    }

    @Override
    public Either<String, Franquicia> getFranquicia(String nombre) {
        return daoFranquicias.getFranquicia(nombre);
    }

    @Override
    public Either<String, List<Franquicia>> getFranquicias() {
        return daoFranquicias.getFranquicias();
    }

    @Override
    public boolean crearFranquicia(String nombre, String ubicacion) {
        if (daoFranquicias.getFranquicia(nombre).isLeft()) {
            Franquicia franquicia = new Franquicia(nombre, ubicacion, 0);
            return daoFranquicias.addFranquicia(franquicia);
        } else {
            return false;
        }
    }

    @Override
    public boolean modificarFranquicia(Franquicia franquicia) {
        Either<String, Franquicia> franquiciaBD = daoFranquicias.getFranquicia(franquicia.getNombre());
        if (franquiciaBD.isRight()) {
            franquiciaBD.get().setUbicacion(franquicia.getUbicacion());
            franquiciaBD.get().setNumeroEmpleados(franquicia.getNumeroEmpleados());
            return daoFranquicias.deleteFranquicia(franquicia) && daoFranquicias.addFranquicia(franquiciaBD.get());
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarFranquicia(String nombre) {
        Either<String, Franquicia> franquicia = daoFranquicias.getFranquicia(nombre);
        if (franquicia.isRight() && daoFranquicias.deleteFranquicia(franquicia.get())) {
            serviciosEmpleados.getEmpleados().get()
                    .stream().filter(empleado -> empleado.getNombreFranquicia().equals(franquicia.get().getNombre()))
                    .forEach(serviciosEmpleados::quitarFranquicia);
            return true;
        } else {
            return false;
        }
    }

}
