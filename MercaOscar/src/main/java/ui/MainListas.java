package ui;

import modelo.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainListas {

    public static void main(String[] args) {

        ArrayList<Producto> lista = new ArrayList<>();
        List<String> lista2 = List.of("kk", "de", "la", "...");

        if (lista2.contains("de")) {
            System.out.println("SI LA TIENE STRINGSSSS");
        }


        lista.add(new Producto(9, "pantene miel limon y ciruelas",8));
        lista.add(new Producto(7, "H&S",9));
        lista.add(new Producto(9, "kkk",0));
        lista.add(new Producto(90, "Cream Loreal Antiarrugas",7));

        if (lista.contains(new Producto("nombre"))) {
            System.out.println("Si Lo contiene");
        } else
            System.out.println("NO LO TIENE");

        ArrayList<Producto> productosPrecio10 = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals("H%S")) {
                lista.get(i).setPrecio(10);
                break;
            }
        }



        List<Producto> lista3 = lista.stream().collect(Collectors.toUnmodifiableList());
        lista3 = Collections.unmodifiableList(lista);
;
        lista3.get(0).setPrecio(999999);

        lista.forEach(System.out::println);


        String nombreaBorrar;


        //lista.remove(new Producto("H&S"));
//
//        for (int j = 0; j < lista.size(); j++) {
//                Producto s = lista.get(j);
//                System.out.println(s);
//        }
//        int indice = lista.indexOf(new Producto("H&S"));
//
//        lista.set(indice,new Producto(22,"H&S Fresh"));
//
//        for (Producto p : lista) {
//            System.out.println(p);
//        }


    }
}
