package ui;

import modelo.Administrativo;
import servicios.ServiciosAdministrativo;
import servicios.ServiciosAlumno;
import servicios.ServiciosProfesor;

import java.util.Scanner;

public class MainAdministrativo {

    public void mainAdministrativo(Administrativo adminLogueado){
        Scanner sc = new Scanner(System.in);
        ServiciosAdministrativo serviciosAdministrativo = new ServiciosAdministrativo();
        ServiciosAlumno serviciosAlumno = new ServiciosAlumno();


        int opcion;
        do {
            System.out.println("BIENVENIDO " + adminLogueado.getNombre());
            do {

                System.out.println("Que quiere hacer?");
                System.out.println("1 - Cotizar\n" +
                        "2- Ver listado de profesores por cantidad de asignaturas\n" +
                        "3 - Salir\n");
                opcion = sc.nextInt();
                sc.nextLine();
            } while (opcion < 1 || opcion > 5);
            switch (opcion) {
                case 1:
                    //cotizar
                    System.out.println(serviciosAdministrativo.cotizarAdministrativo(adminLogueado));
                    break;
                case 2:
                    //ver listado de profesores
                    System.out.println(serviciosAdministrativo.profesoresPorCantidadAsignaturas());
                    break;
                case 3:
                    //salir
                    break;
                default:
                    break;
            }
        } while (opcion != 3);


    }

}
