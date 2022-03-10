package main;

import com.github.javafaker.Faker;
import videoclub.config.Configuration;
import videoclub.dao.modelo.*;
import videoclub.servicios.ServiciosVideoclub;

import java.time.LocalDateTime;
import java.util.Random;

public class MainVideoclubPractica {

  public static void main(String[] args) {
    setupSocioSocios();
    setupProductos();
    setAlquileres();

    ServiciosVideoclub sv = new ServiciosVideoclub();

    System.out.println(sv.getTodosLosSocios().toString());
    System.out.println(sv.getTodosProductos().toString());
    System.out.println(sv.getTodosAlquileres().toString());

    StreamsProductos sp = new StreamsProductos();
    sp.mediaPrecioTodosLosProductos();

  }

  private static void setupSocioSocios() {

    Faker f = new Faker();
    ServiciosVideoclub sv = new ServiciosVideoclub();

    for (int i = 0; i < 100; i++) {
      Socio socio = new Socio(f.phoneNumber().extension(), f.name().firstName(), f.gameOfThrones().city(), f.phoneNumber().subscriberNumber(), f.number().numberBetween(1, 99));
      sv.addSocio(socio);
    }
  }

  private static void setupProductos() {

    Faker f = new Faker();
    ServiciosVideoclub sv = new ServiciosVideoclub();

    Random r = new Random();

    for (int i = 0; i < 100; i++) {

      Producto pelicula = new Pelicula(f.name().title(), r.nextInt(10), f.music().genre(), FormatoPelicula.DVD, f.name().lastName(), "120");
      int numeroActores = r.nextInt(50);
      for (int j = 0; j < numeroActores; j++) {
        ((Pelicula) pelicula).addActor(f.leagueOfLegends().champion());
      }
      sv.addProducto(pelicula);
    }
    for (int i = 0; i < 100; i++) {

      Producto videoJuego = new Videojuego(f.name().title(), r.nextInt(10), f.music().genre(), f.name().lastName());
      sv.addProducto(videoJuego);
    }
    for (int i = 0; i < 100; i++) {

      Producto documental = new Documental(f.animal().name(), r.nextInt(10), f.music().genre(), FormatoPelicula.VIDEO, f.name().lastName(), "120");
      sv.addProducto(documental);
    }
  }


  private static void setAlquileres() {

    Faker f = new Faker();
    Random r = new Random();
    ServiciosVideoclub sv = new ServiciosVideoclub();
    for (int i = 0; i < 100; i++) {
      Socio s = sv.getTodosLosSocios().get(r.nextInt(sv.getTodosLosSocios().size()));
      Producto producto = sv.getTodosProductos().get(r.nextInt(sv.getTodosProductos().size()));
      sv.alquilarProducto(producto, s.getNif());
      Alquiler alquiler = new Alquiler(LocalDateTime.now().minusSeconds(r.nextInt(Configuration.getDiasAlquilerPeliculas() * 2)), s, producto);
      Encuesta e = new Encuesta(r.nextInt(5) + 1, true);
      sv.devolverProducto(s.getNif(), e);
    }
  }
}
