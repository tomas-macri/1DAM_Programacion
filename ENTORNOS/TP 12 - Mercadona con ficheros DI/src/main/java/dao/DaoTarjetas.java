package dao;

import modelo.Tarjeta;
import modelo.Usuarios.Usuario;

import java.util.List;

public interface DaoTarjetas {
    void agregarTarjeta(Tarjeta tarjNueva, Usuario cliente);

    boolean laTarjetaExiste(String nombTarjetaValidar, Usuario c);

    Tarjeta getTarjeta(String nombreTarj, Usuario user);

    List<Tarjeta> devolverLista(Usuario user);
}
