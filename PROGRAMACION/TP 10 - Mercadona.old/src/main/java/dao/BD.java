package dao;

import modelo.Producto;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BD {
    public static LinkedHashMap<String, Usuario> listaUsuarios = new LinkedHashMap<>();
    public static ArrayList<Producto> listaProductos = new ArrayList<>();

    static {
        listaProductos.add(new Producto("prod1", 11,11));
        listaProductos.add(new Producto("prod2", 12,22));
        listaProductos.add(new Producto("prod3", 13,33));
        listaProductos.add(new Producto("prod4", 14,1));

        listaUsuarios.put("u1", new Usuario("u1", "cliente1"));
        listaUsuarios.put("u2", new Usuario("u2", "cliente2"));
        listaUsuarios.put("u3", new Usuario("u3", "cliente3"));
        listaUsuarios.put("u4", new Usuario("u4", "cliente4"));

        listaUsuarios.put("a1", new Usuario("a1", "admin1", true));

    }

}
