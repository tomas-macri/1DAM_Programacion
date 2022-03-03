package servicios;

import dao.DaoPersona;
import modelo.Persona;

public class ServiciosPersona {

    public boolean laPersonaSeRegistro(Persona personaAVer){
        DaoPersona daoPersona = new DaoPersona();
        return daoPersona.laPersonaExiste(personaAVer);
    }

    public Persona getPersona(Persona persona) {
        DaoPersona daoPersona = new DaoPersona();
        return daoPersona.getPersona(persona);
    }


    // EL METODO COTIZAR PODRIA SER COMUN EN LA CLASE EMPLEADO PERO ESTA ASI PARA USAR INTERFACES

}
