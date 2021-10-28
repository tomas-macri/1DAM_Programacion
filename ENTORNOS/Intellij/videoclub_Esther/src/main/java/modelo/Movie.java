package modelo;

import java.util.List;

public class Movie extends Documentary {

  private List<String> actores;

  public Movie(String titulo, int cantidad, String genero, MovieFormat formato, String director, String duracion) {
    super(titulo, cantidad, genero, formato, director, duracion);
    this.actores = actores;
  }

  public List<String> getActores() {
    return actores;
  }

  @Override
  public String toString() {
    return "Movie: (" + super.toString() + ")";
  }
}
