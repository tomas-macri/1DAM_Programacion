package com.ctrempleados.domain.modelo;

import com.ctrempleados.domain.modelo.common.ConstantesModelo;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Data
public class Nomina {

    private final int id;
    private final String dniEmpleado;
    private final List<String> lugaresDeTrabajo;
    private final LocalDate fechaInicio;
    private double sueldoHora;
    private LocalDate fechaFin;
    private Period computoDias;
    private Duration tiempoTrabajado;
    private double sueldo;
    private boolean pendienteDeAbonar;

    public Nomina(int id, String dni, double sueldoHora, List<String> lugaresDeTrabajo, LocalDate fechaInicio) {
        this.id = id;
        this.dniEmpleado = dni;
        this.sueldoHora = sueldoHora;
        this.lugaresDeTrabajo = lugaresDeTrabajo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = null;
        this.computoDias = null;
        this.tiempoTrabajado = null;
        this.sueldo = 0;
        this.pendienteDeAbonar = true;
    }

    public String imprimirComputoDias() {
        if (computoDias == null) {
            return "";
        } else {
            return computoDias.getDays() + ConstantesModelo.D + computoDias.getMonths() + ConstantesModelo.M;
        }
    }

    public String imprimirTiempoTrabajado() {
        if (tiempoTrabajado == null) {
            return "";
        } else {
            return tiempoTrabajado.toHours() + ConstantesModelo.DOS_PUNTOS + (tiempoTrabajado.toMinutes() - (tiempoTrabajado.toHours() * 60));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nomina nomina = (Nomina) o;
        return id == nomina.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
