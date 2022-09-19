package servicios.impl;

import dao.DaoActores;
import jakarta.inject.Inject;
import modelo.Actor;
import servicios.ServiciosActores;

import java.util.List;

public class ServiciosActoresImpl implements ServiciosActores {

    private DaoActores daoActores;

    @Inject
    public ServiciosActoresImpl(DaoActores daoActores) {
        this.daoActores = daoActores;
    }

    @Override public List<Actor> getTodosLosActores() {
        return daoActores.getTodosLosActores();
    }
}
