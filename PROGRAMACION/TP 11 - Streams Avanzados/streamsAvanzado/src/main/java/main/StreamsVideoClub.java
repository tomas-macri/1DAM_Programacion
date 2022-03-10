package main;

import videoclub.dao.modelo.Pelicula;
import videoclub.dao.modelo.Producto;
import videoclub.dao.modelo.Socio;
import videoclub.dao.modelo.Videojuego;
import videoclub.servicios.ServiciosVideoclub;

import java.util.List;

public class StreamsVideoClub {
    ServiciosVideoclub sv = new ServiciosVideoclub();
    List<Socio> socios = sv.getTodosLosSocios();
    List<Producto> productos = sv.getTodosProductos();
    List<Pelicula> peliculas = sv.getTodasPeliculas();
    List<Videojuego> videojuegos = sv.getTodosVideoJuegos();

    public void numeroSociosSancionados() {

    }

    public void mediaEdadDeSociosSancionados() {

    }

    /**
     * DIFICIL
     **/
    public void listaDiezProductosMasAlquilados() {

    }

    // numero de Peliculas Alquiladas, de Documentales y Videojuegos.
    public void numeroProductosAlquiladosPorTipo() {

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
