package servicios;

import dao.DaoTarjetas;
import modelo.Tarjeta;
import modelo.Usuario;

import java.util.List;

public class ServiciosTarjetas {


    public boolean agregarTarjeta(Tarjeta tarjNueva, Usuario user) {
        String nombre = tarjNueva.getNombre();
        DaoTarjetas dao = new DaoTarjetas();
        if (!laTarjetaExiste(nombre, user) && !(nombre.equals("") || tarjNueva.getSaldo()<0)) {
            dao.agregarusuario(tarjNueva, user);
            return true;
        }
        return false;
    }

    public boolean laTarjetaExiste(String nombreT, Usuario user) {
        DaoTarjetas daoTarjetas = new DaoTarjetas();
        return daoTarjetas.laTarjetaExiste(nombreT, user);
    }

    public List<Tarjeta> devolverLista(Usuario user){
        DaoTarjetas daoTarjetas = new DaoTarjetas();
        return daoTarjetas.devolverLista(user);
    }
}
