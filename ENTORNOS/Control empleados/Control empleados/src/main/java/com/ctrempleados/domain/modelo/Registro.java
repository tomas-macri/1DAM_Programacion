package com.ctrempleados.domain.modelo;


import com.ctrempleados.domain.modelo.common.ConstantesModelo;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class Registro {

    private int id;
    private String dniEmpleado;
    private String nombreFranquicia;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private Duration computoTiempo;
    private boolean validado;

    public Registro(String dni, String franquicia, LocalDateTime horaEntrada) {
        this.dniEmpleado = dni;
        this.nombreFranquicia = franquicia;
        this.horaEntrada = horaEntrada;
        this.horaSalida = null;
        this.computoTiempo = null;
        this.validado = false;
    }

    public String imprimirHoraEntrada() {
        return horaEntrada.format(DateTimeFormatter.ofPattern(ConstantesModelo.DD_MM_YYYY_HH_MM_SS));
    }

    public String imprimirHoraSalida() {
        if (horaSalida != null) {
            return horaSalida.format(DateTimeFormatter.ofPattern(ConstantesModelo.DD_MM_YYYY_HH_MM_SS));
        } else {
            return "";
        }
    }

    public String imprimirComputoTiempo() {
        if (computoTiempo != null) {
            return computoTiempo.toHours() + ":"
                    + (computoTiempo.toMinutes() - (computoTiempo.toHours() * 60)) + ConstantesModelo.DOS_PUNTOS
                    + (computoTiempo.getSeconds() - (computoTiempo.toMinutes() * 60));
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registro registro = (Registro) o;
        return id == registro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
