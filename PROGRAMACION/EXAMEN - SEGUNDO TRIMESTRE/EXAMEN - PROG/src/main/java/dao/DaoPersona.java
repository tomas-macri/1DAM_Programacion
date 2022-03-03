package dao;

import modelo.Persona;

public class DaoPersona {

    public boolean laPersonaExiste(Persona personaAValidar){
        return BD.listaPersonas.contains(personaAValidar);
    }

    public Persona getPersona(Persona persona) {
        return BD.listaPersonas.get(BD.listaPersonas.indexOf(persona));
    }
}
