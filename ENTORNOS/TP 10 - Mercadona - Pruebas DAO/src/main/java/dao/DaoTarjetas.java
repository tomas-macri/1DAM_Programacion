package dao;

import jakarta.inject.Inject;
import modelo.Tarjeta;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class DaoTarjetas {

    private LinkedHashMap<String, Usuario> bdUsuarios;

    @Inject
    public DaoTarjetas(BD bd){
        this.bdUsuarios = bd.listaUsuarios;
    }

    public boolean agregarTarjeta(Tarjeta tarjNueva, Usuario cliente) {
        String nombreTarj = tarjNueva.getNombre();
        if (!laTarjetaExiste(nombreTarj, cliente) && !(nombreTarj.equals("") || tarjNueva.getSaldo()<0)) {
            bdUsuarios.get(cliente.getDni()).getListaTarjetas().add(tarjNueva);
            return true;
        }
        return false;
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
        if (tarjADevolver.get().getNombre().equalsIgnoreCase("error")){
            return null;
        }
        return tarjADevolver.get();
    }

    public List<Tarjeta> devolverLista(Usuario user) {
        return user.getListaTarjetas().stream()
                .map(Tarjeta::clonar)
                .collect(Collectors.toUnmodifiableList());
    }
}
