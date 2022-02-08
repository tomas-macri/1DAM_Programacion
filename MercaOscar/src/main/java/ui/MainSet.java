package ui;

import modelo.Producto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Consumer;

public class MainSet {

    public static void main(String[] args) {
        HashSet<Producto> productos = new HashSet<>();

        System.out.println(productos.add(new Producto("donut")));

        System.out.println(productos.add(new Producto("Donut")));

        System.out.println(productos.size());

        Iterator<Producto> it = productos.iterator();
        while (it.hasNext())
        {
            Producto p = it.next();
            System.out.println(p);
        }

        productos.forEach(System.out::println);

        productos.forEach(producto -> System.out.println(producto));

        productos.forEach(anihabl -> {
            if(anihabl.getNombre().equalsIgnoreCase("DONUT"))
                anihabl.setPrecio(100000);
        });

    }



    class Test implements Consumer<Producto> {

        @Override
        public void accept(Producto anihabl) {
            if(anihabl.getNombre().equalsIgnoreCase("DONUT"))
                anihabl.setPrecio(100000);
        }
    }

}
