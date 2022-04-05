package domain;

import dao.DaoPersonas;
import domain.modelo.Persona;

import java.util.List;

public class ServiciosPersonas {

    private DaoPersonas daoPersonas;

    public ServiciosPersonas(DaoPersonas daoPersonas) {
        this.daoPersonas = daoPersonas;
    }

    public void addPersona(Persona p) {
        daoPersonas.addPersona(p);
    }

    // get listado personas
    public List<Persona> getPersonas() {
        return daoPersonas.getPersonas();
    }



}
