package dao;

import modelo.Renting;

import java.util.HashMap;
import java.util.Map;

public class DaoAlquileres {


    // el nif como clave primaria
    private static final Map<String, Renting> alquileres = new HashMap<>();

    public boolean addAlquiler(Renting alquiler) {
        boolean alquilado = false;
        if (alquileres.get(alquiler.getSocio().getNif()) == null) {
            DaoAlquileres.alquileres.put(alquiler.getSocio().getNif(), alquiler);
            alquilado = true;
        }
        return alquilado;
    }

    public boolean borrarAlquiler(Renting alquiler) {
        return alquileres.remove(alquiler.getSocio().getNif()) != null;
    }

    public Renting alquilerSocio(String nif) {
        if (alquileres.size() == 0) {
            return null;
        } else {
            return alquileres.get(nif);
        }
    }
}
