package ui;

import modelo.Cliente;

import java.util.LinkedHashMap;

public class MainMap {


    public static void main(String[] args) {

        LinkedHashMap<String, Cliente> clientes = new LinkedHashMap<>();

        clientes.put("9",new Cliente("9"));

        Cliente juan = new Cliente("34");
        juan.setNombre("juan");
        clientes.put(juan.getDni(),juan);

        Cliente juanito = new Cliente("34");
        juanito.setNombre("juanito");

        Cliente c = clientes.put("34",juanito);
        if ( c!= null)
        {
            System.out.println("has sacado a alguien");
            System.out.println(c);
        }

        System.out.println(clientes.get("34"));

        //clientes.remove("9");

        clientes.size();

        clientes.keySet().forEach(System.out::println);

        clientes.values().forEach(System.out::println);

        clientes.keySet()
                .forEach(dni -> System.out.println(dni + " " +clientes.get(dni)));











    }
}
