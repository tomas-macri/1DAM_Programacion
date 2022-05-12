package modelo;

import lombok.Data;

@Data
public class Equipo {
    private String nombre;
    private int golesFavor;
    private int vallasInvictas;
    private int puntos;

    public Equipo(String nombre, int golesFavor, int vallasInvictas, int puntos) {
        this.nombre = nombre;
        this.golesFavor = golesFavor;
        this.vallasInvictas = vallasInvictas;
        this.puntos = puntos;
    }



}
