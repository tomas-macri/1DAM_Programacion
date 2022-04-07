package servicios;

import dao.BD;
import dao.DaoTarjetas;
import jakarta.inject.Inject;
import modelo.Tarjeta;
import modelo.Usuario;

import java.util.List;

public class ServiciosTarjetas {

    private DaoTarjetas daoTarjetas;

    @Inject
    public ServiciosTarjetas(DaoTarjetas daoTarjetas){
        this.daoTarjetas = daoTarjetas;
    }

    public boolean agregarTarjeta(Tarjeta tarjNueva, Usuario user) {
        String nombre = tarjNueva.getNombre();
        if (!laTarjetaExiste(nombre, user) && !(nombre.equals("") || tarjNueva.getSaldo()<0)) {
            daoTarjetas.agregarTarjeta(tarjNueva, user);
            return true;
        }
        return false;
    }

    public boolean laTarjetaExiste(String nombreT, Usuario user) {
        return daoTarjetas.laTarjetaExiste(nombreT, user);
    }

    public List<Tarjeta> devolverLista(Usuario user){
        return daoTarjetas.devolverLista(user);
    }

    public Tarjeta getTarjeta(String nombTarjeta, Usuario userLogueado) {
        if (laTarjetaExiste(nombTarjeta, userLogueado)){
            return daoTarjetas.getTarjeta(nombTarjeta, userLogueado);
        }
        return new Tarjeta("error", 0);

    }
}
