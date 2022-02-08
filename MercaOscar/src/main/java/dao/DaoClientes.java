package dao;

import modelo.Cliente;
import modelo.Producto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DaoClientes {




    public DaoClientes() {
        BD.clientes = new LinkedHashMap<>();
    }




    public List<Cliente> getClientes()
    {
        return BD.clientes.values().stream()
                .map(cliente -> new Cliente(cliente.getDni(),cliente.getNombre()))
                .collect(Collectors.toUnmodifiableList());
    }



}
