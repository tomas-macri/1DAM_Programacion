package ui;

import modelo.Administrativo;
import modelo.Empleado;
import modelo.Persona;
import modelo.Profesor;
import servicios.ServiciosPersona;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ServiciosPersona serviciosPersona = new ServiciosPersona();
        Scanner sc = new Scanner(System.in);


        String dni;

        do {
            System.out.println("BIENVENIDO AL LUGAR ESTE");
            System.out.println("DNI's para ver cosas: \n" +
                    "ad1 - administrativo: cotiza y ve el listado de profesores filtrado\n" +
                    "p1 - profesor: cotiza, matricula, pone nota y ve nota media del alumno\n" +
                    "a1 - alumno: no hace nada");

            do {
                System.out.println("INGRESE SU DNI. TIENE QUE ESTAR REGISTRADO. O 0 PARA SALIR");
                dni = sc.nextLine();
            } while (!serviciosPersona.laPersonaSeRegistro(new Persona(dni)) && !dni.equalsIgnoreCase("0"));

            if (!dni.equalsIgnoreCase("0")) {

                Persona unaPersona = serviciosPersona.getPersona(new Persona(dni));


                if (unaPersona instanceof Profesor) {
                    // el dni es el de un profesor
                    MainProfesor mainProfesor = new MainProfesor();
                    mainProfesor.MainProfesor((Profesor) unaPersona);

                } else if (unaPersona instanceof Administrativo) {
                    MainAdministrativo mainAdministrativo = new MainAdministrativo();
                    mainAdministrativo.mainAdministrativo((Administrativo) unaPersona);
                    System.out.println("hola admin");
                } else {
                    //el dni es el de un alumno
                    System.out.println("hola alumno");
                }
            }
        } while (!dni.equalsIgnoreCase("0"));
    }
}
