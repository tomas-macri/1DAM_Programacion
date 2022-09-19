package servicios;

import modelo.Tarjeta;
import modelo.Usuario;

import java.util.List;

public interface ServiciosTarjetas {
    boolean agregarTarjeta(Tarjeta tarjNueva, Usuario user);

    boolean laTarjetaExiste(String nombreT, Usuario user);

    List<Tarjeta> devolverLista(Usuario user);

    Tarjeta getTarjeta(String nombTarjeta, Usuario userLogueado);
}
