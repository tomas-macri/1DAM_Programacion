package dao;

import modelo.Tarjeta;
import modelo.Usuario;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class DaoTarjetas {
    public void agregarusuario(Tarjeta tarjNueva, Usuario cliente) {
        String nombreTarj = tarjNueva.getNombre();
        if (!laTarjetaExiste(nombreTarj, cliente) && !(nombreTarj.equals("") || tarjNueva.getSaldo()<0)) {
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

    public Tarjeta getTarjeta(String nombreTarj, Usuario user){

        AtomicReference<Tarjeta> tarjADevolver = new AtomicReference<>();
        tarjADevolver.set(new Tarjeta("error", 0));

        user.getListaTarjetas().forEach(tarjeta -> {
            if (tarjeta.getNombre().equalsIgnoreCase(nombreTarj)) {
                tarjADevolver.set(tarjeta);
            }
        });
        return tarjADevolver.get();
    }

    public List<Tarjeta> devolverLista(Usuario user) {
        return user.getListaTarjetas().stream()
                .map(tarjeta -> new Tarjeta(tarjeta.getNombre(), tarjeta.getSaldo()))
                .collect(Collectors.toUnmodifiableList());
    }
}
