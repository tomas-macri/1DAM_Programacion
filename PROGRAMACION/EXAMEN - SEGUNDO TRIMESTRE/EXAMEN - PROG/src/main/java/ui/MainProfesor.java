package ui;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Persona;
import modelo.Profesor;
import servicios.ServiciosAlumno;
import servicios.ServiciosProfesor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainProfesor {

    public void MainProfesor(Profesor profeLogueado) {
        Scanner sc = new Scanner(System.in);
        ServiciosProfesor serviciosProfesor = new ServiciosProfesor();
        ServiciosAlumno serviciosAlumno = new ServiciosAlumno();


        int opcion;
        do {
            System.out.println("BIENVENIDO " + profeLogueado.getNombre());
            do {

                System.out.println("Que quiere hacer?");
                System.out.println("1 - Cotizar\n" +
                        "2- Matricular alumno\n" +
                        "3 - Poner nota a un alumno\n" +
                        "4 - Ver nota media del alumno en todas sus asignaturas\n" +
                        "5 - Salir");
                opcion = sc.nextInt();
                sc.nextLine();
            } while (opcion < 1 || opcion > 5);
            switch (opcion) {
                case 1:
                    //cotizar
                    System.out.println(serviciosProfesor.cotizarProfesor(profeLogueado));
                    break;
                case 2:
                    //matricular
                    matricular(sc, serviciosProfesor, serviciosAlumno);
                    break;
                case 3:
                    //poner nota
                    ponerNota(sc, serviciosProfesor, serviciosAlumno);
                    break;
                case 4:
                    //filtro
                    filtrarMediaAlumno(sc, serviciosProfesor,serviciosAlumno);
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }



    private void matricular(Scanner sc, ServiciosProfesor serviciosProfesor, ServiciosAlumno serviciosAlumno) {
        String dniAlumno;
        do {
            dniAlumno = dniValido(sc, serviciosAlumno);
            if (!dniAlumno.equalsIgnoreCase("0")) {
                Alumno alumnoAModificar = serviciosAlumno.getAlumno(new Persona(dniAlumno));

            }
            if (!dniAlumno.equalsIgnoreCase("0")) {
                Alumno alumnoAModificar = serviciosAlumno.getAlumno(new Persona(dniAlumno));
                System.out.println("LISTA DE ASIGNATURAS TOTALES: ");
                List<Asignatura> asignaturaList = serviciosProfesor.getAsignaturas();
                System.out.println(asignaturaList);
                String nombreAsignatura;
                System.out.println("Ingrese la asignatura de la que quiere matricular a " + alumnoAModificar.toString());
                nombreAsignatura = sc.nextLine();


                if (serviciosProfesor.matricular(alumnoAModificar, asignaturaList, new Asignatura(nombreAsignatura))) {
                    System.out.println("Se matriculo al alumno. Sus asignaturas quedaron asi:");
                    System.out.println(alumnoAModificar.getListaAsignaturasNotas());
                } else {
                    System.out.println("ERROR");
                }
            }
        } while (!dniAlumno.equalsIgnoreCase("0"));
    }


    public void ponerNota(Scanner sc, ServiciosProfesor serviciosProfesor, ServiciosAlumno serviciosAlumno ) {
        String dniAlumno;
        do {
            dniAlumno = dniValido(sc, serviciosAlumno);

            if (!dniAlumno.equalsIgnoreCase("0")) {
                Alumno alumnoAModificar = serviciosAlumno.getAlumno(new Persona(dniAlumno));
                System.out.println("LISTA DE ASIGNATURAS DE " + alumnoAModificar.getNombre().toUpperCase() + ": ");
                List<Asignatura> asignaturaList = new ArrayList<>(alumnoAModificar.getListaAsignaturasNotas().keySet());
                System.out.println(asignaturaList);
                String nombreAsignatura;
                int notaAsignatura;
                System.out.println("Ingrese la asignatura de la que quiere poner nota a " + alumnoAModificar.getNombre() + ": ");
                nombreAsignatura = sc.nextLine();
                System.out.println("Ingrese la nota que saco en esa asignatura: ");
                notaAsignatura = sc.nextInt();
                sc.nextLine();

                if (serviciosProfesor.ponerNota(alumnoAModificar, asignaturaList, new Asignatura(nombreAsignatura), notaAsignatura)) {
                    System.out.println("Se agrego la nota al alumno. Sus asignaturas quedaron asi:");
                    System.out.println(alumnoAModificar.getListaAsignaturasNotas());
                } else {
                    System.out.println("ERROR");
                }
            }
        } while (!dniAlumno.equalsIgnoreCase("0"));
    }

    private void filtrarMediaAlumno(Scanner sc, ServiciosProfesor serviciosProfesor, ServiciosAlumno serviciosAlumno) {
        String dniAlumno;
        do {
            dniAlumno = dniValido(sc, serviciosAlumno);
            if (!dniAlumno.equalsIgnoreCase("0")) {
                 Alumno alumnoAVerMedia = serviciosAlumno.getAlumno(new Persona(dniAlumno));
                 double mediaAlumno = serviciosAlumno.calcularMedia(alumnoAVerMedia);
                 System.out.println(mediaAlumno);
            }
        }while (!dniAlumno.equalsIgnoreCase("0"));
    }



    private String dniValido(Scanner sc, ServiciosAlumno serviciosAlumno) {
        String dniAlumno;
        do {
            System.out.println("Ingrese un dni de un alumno o 0 para salir: ");
            dniAlumno = sc.nextLine();
        } while (!serviciosAlumno.elAlumnoExiste(new Persona(dniAlumno)) && !dniAlumno.equalsIgnoreCase("0"));
        return dniAlumno;
    }
}
