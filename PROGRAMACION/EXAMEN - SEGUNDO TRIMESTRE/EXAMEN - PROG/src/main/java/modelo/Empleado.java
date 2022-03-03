package modelo;

public class Empleado extends Persona{

    private int sueldo;

    public Empleado(String dni, String nombre, int sueldo) {
        super(dni, nombre);
        this.sueldo = sueldo;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }
}
