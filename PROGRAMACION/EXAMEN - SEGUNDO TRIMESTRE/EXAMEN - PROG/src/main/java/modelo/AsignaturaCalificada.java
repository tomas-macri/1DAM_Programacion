package modelo;

public class AsignaturaCalificada {

    private Asignatura asignatura;
    private int nota;

    public AsignaturaCalificada(Asignatura asignatura, int nota) {
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
