package servicios.impl;

import dao.DaoTarjetas;
import jakarta.inject.Inject;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;
import servicios.ServiciosTarjetas;

import java.util.List;

public class ServiciosTarjetasImpl implements ServiciosTarjetas {

    private DaoTarjetas daoTarjetasImpl;

    @Inject
    public ServiciosTarjetasImpl(DaoTarjetas daoTarjetasImpl){
        this.daoTarjetasImpl = daoTarjetasImpl;
    }


    @Override public boolean agregarTarjeta(Tarjeta tarjNueva, Usuario user) {
        String nombre = tarjNueva.getNombre();
        if (!laTarjetaExiste(nombre, user) && !(nombre.equals("") || tarjNueva.getSaldo()<0)) {
            daoTarjetasImpl.agregarusuario(tarjNueva, user);
            return true;
        }
        return false;
    }

    @Override public boolean laTarjetaExiste(String nombreT, Usuario user) {
        return daoTarjetasImpl.laTarjetaExiste(nombreT, user);
    }

    @Override public List<Tarjeta> devolverLista(Usuario user){
        return daoTarjetasImpl.devolverLista(user);
    }

    @Override public Tarjeta getTarjeta(String nombreT, Usuario user){
        return daoTarjetasImpl.getTarjeta(nombreT, user);
    }

}
