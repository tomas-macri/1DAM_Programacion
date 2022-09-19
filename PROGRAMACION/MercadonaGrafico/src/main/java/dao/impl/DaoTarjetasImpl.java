package dao.impl;

import dao.DaoTarjetas;
import jakarta.inject.Inject;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class DaoTarjetasImpl implements DaoTarjetas {
    private DataBase dataBase;

    @Inject
    public DaoTarjetasImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override public void agregarTarjeta(Tarjeta tarjNueva, Usuario cliente) {
        String nombreTarj = tarjNueva.getNombre();
        if (!laTarjetaExiste(nombreTarj, cliente) && !(nombreTarj.equals("") || tarjNueva.getSaldo()<0)) {
            LinkedHashMap<String, Usuario> usuarios = dataBase.loadUsuarios();
            usuarios.get(cliente.getDni()).getListaTarjetas().add(tarjNueva);
            dataBase.saveUsuarios(usuarios);
        }
    }

    @Override public boolean laTarjetaExiste(String nombTarjetaValidar, Usuario c) {
        AtomicBoolean existe = new AtomicBoolean(false);
        c.getListaTarjetas().forEach(tarjeta -> {
            if (tarjeta.getNombre().equalsIgnoreCase(nombTarjetaValidar)) {
                existe.set(true);
            }
        });
        return existe.get();
    }

    @Override public Tarjeta getTarjeta(String nombreTarj, Usuario user){

        AtomicReference<Tarjeta> tarjADevolver = new AtomicReference<>();
        tarjADevolver.set(new Tarjeta("error", 0));

        user.getListaTarjetas().forEach(tarjeta -> {
            if (tarjeta.getNombre().equalsIgnoreCase(nombreTarj)) {
                tarjADevolver.set(tarjeta);
            }
        });
        return tarjADevolver.get();
    }

    @Override public List<Tarjeta> devolverLista(Usuario user) {
        return user.getListaTarjetas().stream()
                .map(Tarjeta::clonar)
                .collect(Collectors.toUnmodifiableList());
    }
}
