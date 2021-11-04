package main;

import java.util.Scanner;

public class Ejercicio16 {
    public static void main(String[] args) {
        //16. Queremos desarrollar una aplicación que nos ayude a gestionar las notas de un centro
        //educativo. Cada grupo (o clase) está compuesto por 5 alumnos. Se pide leer las notas del
        //primer, segundo y tercer trimestre de un grupo. Debemos mostrar al final: la nota media del
        //grupo en cada trimestre, y la media del alumno que se encuentra en la posición N (N se lee
        //por teclado).
    }

    public int[] ejercicio(Scanner sc) {

        //cantGrupos = 1
        final int cantAlumnosPorGrupo = 2;
        final int cantTrimestres = 3;
        // for grupos

        String[] arrayNombreAlumnos = new String[cantAlumnosPorGrupo];
        double[] arrayMediaAlumnos = new double[cantAlumnosPorGrupo];
        for (int i = 0; i < arrayNombreAlumnos.length; i++) {
            System.out.println("Ingrese un nombre del alumno ");
            arrayNombreAlumnos[i] = sc.nextLine();
        }

        double mediaAlumno;
        double mediaPrimerTrimestre = 0;
        double mediaSegundoTrimestre = 0;
        double mediaTercerTrimestre = 0;
        double notaIngresada;
        for (int i = 0; i < arrayNombreAlumnos.length; i++) {
            System.out.println(arrayNombreAlumnos[i]);
            mediaAlumno = 0;
            for (int j = 0; j < cantTrimestres; j++) {
                System.out.println("Ingrese la nota de " + arrayNombreAlumnos[i] + " en el trimestre " + (j + 1));
                notaIngresada = sc.nextDouble();
                mediaAlumno+= notaIngresada;
                switch (j+1){
                    case 1:
                        mediaPrimerTrimestre+= notaIngresada;
                        break;
                    case 2:
                        mediaSegundoTrimestre += notaIngresada;
                        break;
                    case 3:
                        mediaTercerTrimestre += notaIngresada;
                        break;
                    default:
                        break;
                }
            }
            mediaAlumno/=cantTrimestres;
            arrayMediaAlumnos[i] = mediaAlumno;
        }
        mediaPrimerTrimestre/=cantAlumnosPorGrupo;
        mediaSegundoTrimestre/=cantAlumnosPorGrupo;
        mediaTercerTrimestre/=cantAlumnosPorGrupo;
        double[] arrayMediaGrupoTrimestre = new double[]{mediaPrimerTrimestre, mediaSegundoTrimestre, mediaTercerTrimestre};
        for (int i = 0; i < arrayMediaGrupoTrimestre.length; i++) {
            System.out.println("La media de los " + cantAlumnosPorGrupo + " del grupo en el trimestre " + (i+1) + " es de: " + arrayMediaGrupoTrimestre[i]);
        }

        int alumnoIngresado;
        do {
            System.out.println("La media de que alumno desea saber (del 1 al " + cantAlumnosPorGrupo + ") : ");
            alumnoIngresado = sc.nextInt();
        }while (alumnoIngresado < 1 || alumnoIngresado > cantAlumnosPorGrupo);
            if ((alumnoIngresado-1) >= 0 && (alumnoIngresado-1) < cantAlumnosPorGrupo) {
                System.out.println("La nota de promedio de " + arrayNombreAlumnos[alumnoIngresado-1] + " es de: " + arrayMediaAlumnos[alumnoIngresado-1]);
            }
            else{
                System.out.println("Alumno inexistente");
            }





















        return new int[0];
    }
}
