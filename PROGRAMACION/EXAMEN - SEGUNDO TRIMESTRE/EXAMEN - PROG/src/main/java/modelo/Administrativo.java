package modelo;

public class Administrativo extends Empleado implements Cotizable{

    private String tarea;

    public Administrativo(String dni, String nombre, int sueldo, String tarea) {
        super(dni, nombre, sueldo);
        this.tarea = tarea;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    @Override
    public String cotizar() {
        return (this.getNombre() + " ha cotizado!");
    }
}
