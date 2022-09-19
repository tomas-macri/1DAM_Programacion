package dao;

import modelo.Cliente;
import modelo.Producto;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BD {

    static LinkedHashMap<String, Cliente> clientes = new LinkedHashMap<>();
    static ArrayList<Producto> productos = new ArrayList<>();

    static {
        productos.add(new Producto(12,"",12));

    }

}
