package dao;

import domain.modelo.Persona;

import java.util.ArrayList;
import java.util.List;

public class DaoPersonas {


    private List<Persona> personas = new ArrayList<>();


    public void addPersona(Persona p) {
        personas.add(p);
    }

    public List<Persona> getPersonas() {
        return personas;
    }

}
