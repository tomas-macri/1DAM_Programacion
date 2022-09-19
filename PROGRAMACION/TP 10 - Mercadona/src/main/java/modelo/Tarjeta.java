package modelo;

public class Tarjeta implements Clonable<Tarjeta> {

    String nombre;
    double saldo;

    public Tarjeta(String nombre, double saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public Tarjeta clonar() {
        return new Tarjeta(nombre,saldo);
    }
}
