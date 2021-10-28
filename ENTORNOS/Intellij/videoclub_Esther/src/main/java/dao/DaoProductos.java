package dao;

import modelo.Documentary;
import modelo.Movie;
import modelo.Producto;
import modelo.Videogame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoProductos {


    private static List<Producto> productos = new ArrayList<>();

    public boolean addProducto(Producto producto) {
        boolean productoAñadido = false;
        if (!productos.contains(producto)) {
            productos.add(producto);
            productoAñadido = true;
        }
        return productoAñadido;
    }

    public boolean borrarProducto(Producto producto) {
        boolean productoBorrado = false;
        return productoBorrado = productos.remove(producto);
    }


    public List<Movie> getTodasPeliculas() {
        return productos.stream()
                .filter(producto -> producto instanceof Movie)
                .map(producto -> (Movie) producto)
                .collect(Collectors.toList());
    }

    public List<Videogame> getTodosVideojuegos() {
        return productos.stream()
                .filter(producto -> producto instanceof Videogame)
                .map(producto -> (Videogame) producto)
                .collect(Collectors.toList());
    }

    public List<Documentary> getTodosDocumentales() {
        return productos.stream()
                .filter(producto -> !(producto instanceof Movie)
                        && (producto instanceof Documentary))
                .map(producto -> (Documentary) producto)
                .collect(Collectors.toList());
    }


}
