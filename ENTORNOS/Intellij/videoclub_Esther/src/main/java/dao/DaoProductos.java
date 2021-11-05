package dao;

import modelo.Documentary;
import modelo.Movie;
import modelo.Product;
import modelo.Videogame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoProductos {


    private static final List<Product> productos = new ArrayList<>();

    public boolean addProducto(Product producto) {
        boolean productoAnadido = false;
        if (!productos.contains(producto)) {
            productos.add(producto);
            productoAnadido = true;
        }
        return productoAnadido;
    }

    public boolean borrarProducto(Product producto) {
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
