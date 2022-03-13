package main;

import videoclub.dao.modelo.*;
import videoclub.servicios.ServiciosVideoclub;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsVideoClub {
    ServiciosVideoclub sv = new ServiciosVideoclub();
    List<Socio> socios;
    List<Producto> productos;
    List<Pelicula> peliculas;
    List<Videojuego> videojuegos;
    List<Alquiler> alquileres;

    public void mostrarListas() {
        socios = sv.getTodosLosSocios();
        productos = sv.getTodosProductos();
        peliculas = sv.getTodasPeliculas();
        videojuegos = sv.getTodosVideoJuegos();
        alquileres = sv.getTodosAlquileres();

        System.out.println(socios);
        System.out.println();
        System.out.println();

        System.out.println(productos);
        System.out.println();
        System.out.println();

        System.out.println(peliculas);
        System.out.println();
        System.out.println();

        System.out.println(videojuegos);
        System.out.println();
        System.out.println();

        System.out.println(alquileres);
        System.out.println();
        System.out.println();
    }

    public void numeroSociosSancionados() {
        System.out.println();
        System.out.println();
        System.out.println("NUMERO DE SOCIOS SANCIONADOS: " +
                socios.stream()
                        .filter(Socio::isSancionado)
                        .count()
        );
        System.out.println();
        System.out.println();


    }

    public void mediaEdadDeSociosSancionados() {
        System.out.println();
        System.out.println();
        System.out.println("MEDIA DE EDAD DE SOCIOS SANCIONADOS: " +
                socios.stream()
                        .filter(Socio::isSancionado)
                        .mapToInt(socioSancionado -> socioSancionado.getEdad())
                        .average().orElse(0.0)
        );
        System.out.println();
        System.out.println();

    }

    /**
     * DIFICIL
     **/
    public void listaDiezProductosMasAlquilados() {
        productos = sv.getTodosProductos();
        System.out.println();
        System.out.println();
        System.out.println("LISTA DIEZ PRODUCTOS MAS ALQUILADOS: " +
                productos.stream().sorted(Comparator.comparingInt(Producto::getCantidadAlquilada).reversed())
                        .limit(10)
                        .collect(Collectors.toList())
        );
        System.out.println();
        System.out.println();


    }

    // numero de Peliculas Alquiladas, de Documentales y Videojuegos.
    public void numeroProductosAlquiladosPorTipo() {
        System.out.println();
        System.out.println();

        System.out.println(
                "NUMERO DE PELICULAS, DOCUMENTALES Y VIDEOJUEGOS: " +
                        productos.stream()
                                .collect(Collectors.groupingBy(producto -> producto.getClass(), Collectors.counting()))
        );


    }


    public void todosLosActoresDistintosDeTodasLasPeliculas() {
        System.out.println(
                peliculas.stream()
                        .map(pelicula -> pelicula.getActores())
                        .distinct()
                        .collect(Collectors.toList())

        );
    }

    public void peliculaConMasActores() {
        System.out.println(
                peliculas.stream()
                        .reduce((pelicula, pelicula2) -> pelicula.getActores().size() > pelicula2.getActores().size() ? pelicula : pelicula2)
                        .orElse(new Pelicula("error", 0, "error", FormatoPelicula.DVD, "error", "error"))
        );
    }


    //el listado de productos ordenados de mayor a menor según su valoración media.
    public void productoConSuValoracionMediaOrdenadosDeMayoraMenor() {
        System.out.println(
                productos.stream()
                        .sorted((o1, o2) -> Double.compare(o2.getValoracionMedia(), o1.getValoracionMedia()))
                        .collect(Collectors.toList())
        );
    }

    public void las10PeliculasMejorValoradas() {
        System.out.println(
                productos.stream()
                        .filter(producto -> producto instanceof Pelicula)
                        .sorted((o1, o2) -> Double.compare(o2.getValoracionMedia(), o1.getValoracionMedia()))
                        .limit(10)
                        .collect(Collectors.toList())
        );
    }

    public void los10VideoJuegosMejorValoradas() {
        System.out.println(
                productos.stream()
                        .filter(producto -> producto instanceof Videojuego)
                        .sorted((o1, o2) -> Double.compare(o2.getValoracionMedia(), o1.getValoracionMedia()))
                        .limit(10)
                        .collect(Collectors.toList())
        );
    }


    // el numero de DVD y Videos que hay.
    public void numeroDocumentalesyPeliculasSegunSuFormato() {
        peliculas.stream()
                .collect(Collectors.groupingBy(pelicula -> pelicula.getFormato(), Collectors.counting()));

    }

    // conseguir un String con todos los faricantes distintos de videojuegos separados por ,
    public void todosLosFabricantesDistintosDeVideoJuegosEnUnSoloString() {
        StringBuilder sb = new StringBuilder();
        productos.stream()
                .filter(producto -> producto instanceof Videojuego)
                .map(producto -> ((Videojuego) producto).getFabricante())
                .forEach(s -> sb.append(s).append(", "));

        System.out.println(sb);
    }
}
