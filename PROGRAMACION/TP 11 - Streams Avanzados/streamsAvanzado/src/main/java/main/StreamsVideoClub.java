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

    public void mostrarListas(){
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
        System.out.println( "NUMERO DE SOCIOS SANCIONADOS: " +
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
        System.out.println( "MEDIA DE EDAD DE SOCIOS SANCIONADOS: " +
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
        System.out.println( "LISTA DIEZ PRODUCTOS MAS ALQUILADOS: " +
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
                                .collect(Collectors.groupingBy(Producto::getClass,Collectors.counting()))



        );
    }

    public void todosLosActoresDistintosDeTodasLasPeliculas() {

    }

    public void peliculaConMasActores() {

    }


    //el listado de productos ordenados de mayor a menor según su valoración media.
    public void productoConSuValoracionMediaOrdenadosDeMayoraMenor() {

    }

    public void las10PeliculasMejorValoradas() {

    }

    public void los10VideoJuegosMejorValoradas() {

    }


    // el numero de DVD y Videos que hay.
    public void numeroDocumentalesyPeliculasSegunSuFormato() {

    }

    // conseguir un String con todos los faricantes distintos de videojuegos separados por ,
    public void todosLosFabricantesDistintosDeVideoJuegosEnUnSoloString() {

    }
}
