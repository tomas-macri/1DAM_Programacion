package videoclub.dao;

import videoclub.dao.modelo.Socio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaoSocios {

    private static Map<String, Socio> socios = new HashMap<>();

    // devuelve si se metio o no
    public boolean addSocio(Socio socio) {
        boolean insertado = false;
        if (socios.get(socio.getNif()) == null) {
            DaoSocios.socios.put(socio.getNif(), socio);
            insertado = true;
        }

        return insertado;
    }

    public List<Socio> getTodosSocios() {
        return socios.values().stream().collect(Collectors.toList());
    }


    public Socio getSocioPorNif(String nif) {
        return socios.get(nif);
    }

    public boolean deleteSocio(Socio socio) {
        boolean borrado = false;
        if (socios.containsKey(socio.getNif())) {
            DaoSocios.socios.remove(socio.getNif());
            borrado = true;
        }
        return borrado;
    }

}
