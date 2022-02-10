package dao;

import modelo.Producto;
import modelo.Tarjeta;
import modelo.Usuario;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class DaoTarjetas {
    public void agregarusuario(Tarjeta tarjNueva, Usuario cliente) {
        String nombreTarj = tarjNueva.getNombre();
        if (!laTarjetaExiste(tarjNueva.getNombre(), cliente) && !(tarjNueva.getNombre().equals("") || tarjNueva.getSaldo()<0)) {
            BD.listaUsuarios.get(cliente.getDni()).getListaTarjetas().add(tarjNueva);
        }
    }

    public boolean laTarjetaExiste(String nombTarjetaValidar, Usuario c) {
        AtomicBoolean existe = new AtomicBoolean(false);
        c.getListaTarjetas().forEach(tarjeta -> {
            if (tarjeta.getNombre().equalsIgnoreCase(nombTarjetaValidar)) {
                existe.set(true);
            }
        });
        return existe.get();
    }

    public List<Tarjeta> devolverLista(Usuario user) {
        return user.getListaTarjetas().stream()
                .map(tarjeta -> new Tarjeta(tarjeta.getNombre(), tarjeta.getSaldo()))
                .collect(Collectors.toUnmodifiableList());
    }
}
