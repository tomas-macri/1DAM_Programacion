package dao;

import modelo.Ingrediente;
import modelo.Producto;
import modelo.ProductoCaducable;
import modelo.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BD {
    public static LinkedHashMap<String, Usuario> listaUsuarios = new LinkedHashMap<>();
    public static ArrayList<Producto> listaProductos = new ArrayList<>();

    static {
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();

        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));
        ingredienteArrayList.add(new Ingrediente("ing4"));



        listaProductos.add(new Producto("prod1", 11,11, ingredienteArrayList));

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing5"));
        ingredienteArrayList.add(new Ingrediente("ing6"));
        ingredienteArrayList.add(new Ingrediente("ing7"));
        ingredienteArrayList.add(new Ingrediente("ing8"));


        listaProductos.add(new Producto("prod2", 12,22, ingredienteArrayList));


        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing4"));
        ingredienteArrayList.add(new Ingrediente("ing5"));

        listaProductos.add(new Producto("prod3", 13,33, ingredienteArrayList));

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing3"));
        ingredienteArrayList.add(new Ingrediente("ing6"));
        ingredienteArrayList.add(new Ingrediente("ing7"));
        ingredienteArrayList.add(new Ingrediente("ing8"));

        listaProductos.add(new Producto("prod4", 14,1, ingredienteArrayList));

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));
        ingredienteArrayList.add(new Ingrediente("ing4"));

        listaProductos.add(new ProductoCaducable("cad1", 15, 22, ingredienteArrayList, LocalDateTime.now().plusMinutes(15)));

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing5"));
        ingredienteArrayList.add(new Ingrediente("ing6"));
        ingredienteArrayList.add(new Ingrediente("ing7"));
        ingredienteArrayList.add(new Ingrediente("ing8"));

        listaProductos.add(new ProductoCaducable("cad2", 15, 22, ingredienteArrayList, LocalDateTime.now().minusMinutes(15)));


        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));
        ingredienteArrayList.add(new Ingrediente("ing4"));

        listaUsuarios.put("u1", new Usuario("u1", "cliente1", ingredienteArrayList));

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing5"));
        ingredienteArrayList.add(new Ingrediente("ing6"));
        ingredienteArrayList.add(new Ingrediente("ing7"));
        ingredienteArrayList.add(new Ingrediente("ing8"));

        listaUsuarios.put("u2", new Usuario("u2", "cliente2", ingredienteArrayList));

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing4"));
        ingredienteArrayList.add(new Ingrediente("ing5"));

        listaUsuarios.put("u3", new Usuario("u3", "cliente3", ingredienteArrayList));

        ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing3"));
        ingredienteArrayList.add(new Ingrediente("ing6"));
        ingredienteArrayList.add(new Ingrediente("ing7"));
        ingredienteArrayList.add(new Ingrediente("ing8"));

        listaUsuarios.put("u4", new Usuario("u4", "cliente4", ingredienteArrayList));

        listaUsuarios.put("a1", new Usuario("a1", "admin1", true));

    }

}
