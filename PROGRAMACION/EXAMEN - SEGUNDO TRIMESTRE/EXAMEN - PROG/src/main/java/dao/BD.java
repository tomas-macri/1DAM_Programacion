package dao;

import modelo.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BD {

    public static List<Persona> listaPersonas = new ArrayList<>();
    public static List<Empleado> listaEmpleados = new ArrayList<>();
    public static List<Asignatura> listaAsignaturas = new ArrayList<>();


    static {

        // CARGA ASIGNATURAS

//        listaAsignaturas.add(new Asignatura("as1", 10, 10));
//        listaAsignaturas.add(new Asignatura("as2", 20, 20));
//        listaAsignaturas.add(new Asignatura("as3", 30, 30));
//        listaAsignaturas.add(new Asignatura("as4", 40, 40));
//        listaAsignaturas.add(new Asignatura("as5", 50, 50));
//        listaAsignaturas.add(new Asignatura("as6", 60, 60));
//        listaAsignaturas.add(new Asignatura("as7", 70, 70));
//        listaAsignaturas.add(new Asignatura("as8", 80, 80));
//        listaAsignaturas.add(new Asignatura("as9", 90, 90));
//        listaAsignaturas.add(new Asignatura("as10", 100, 100));


        // CARGA ALUMNOS

        LinkedHashMap<Asignatura, Integer> listaAsignaturaNota = new LinkedHashMap<>();

        listaAsignaturaNota.put(new Asignatura("as4", 40, 40), 4);
        listaAsignaturaNota.put(new Asignatura("as5", 50, 50), 5);
        listaAsignaturaNota.put(new Asignatura("as6", 60, 60), 6);


        listaPersonas.add(new Alumno("a1", "alumno1", listaAsignaturaNota, 200));

        listaAsignaturaNota = new LinkedHashMap<>();

        listaAsignaturaNota.put(new Asignatura("as7", 70, 70), 7);
        listaAsignaturaNota.put(new Asignatura("as8", 80, 80), 8);
        listaAsignaturaNota.put(new Asignatura("as9", 90, 90), 9);

        listaPersonas.add(new Alumno("a2", "alumno2", listaAsignaturaNota, 300));

        listaAsignaturaNota = new LinkedHashMap<>();

        listaAsignaturaNota.put(new Asignatura("as1", 10, 10), 1);
        listaAsignaturaNota.put(new Asignatura("as3", 30, 30), 3);
        listaAsignaturaNota.put(new Asignatura("as10", 100, 100), 10);

        listaPersonas.add(new Alumno("a3", "alumno3", listaAsignaturaNota, 400));


        // FIN CARGA ALUMNOS


        //CARGA EMPLEADOS

        // CARGA PROFESORES

        List<Asignatura> asignaturasImpartidas = new ArrayList<>();

        asignaturasImpartidas.add(new Asignatura("as1", 10, 10));
        asignaturasImpartidas.add(new Asignatura("as2", 1, 20));
        asignaturasImpartidas.add(new Asignatura("as3", 30, 30));
        asignaturasImpartidas.add(new Asignatura("as4", 40, 40));



        Profesor unProfe = new Profesor("p1", "profesor1", 1000, asignaturasImpartidas, 10);
        listaPersonas.add(unProfe);
        listaEmpleados.add(unProfe);

        asignaturasImpartidas = new ArrayList<>();

        asignaturasImpartidas.add(new Asignatura("as5", 50, 50));
        asignaturasImpartidas.add(new Asignatura("as6", 60, 60));
        asignaturasImpartidas.add(new Asignatura("as7", 70, 70));


        unProfe = new Profesor("p2", "profesor2", 2000, asignaturasImpartidas, 20);
        listaPersonas.add(unProfe);
        listaEmpleados.add(unProfe);

        asignaturasImpartidas = new ArrayList<>();

        asignaturasImpartidas.add(new Asignatura("as8", 80, 80));
        asignaturasImpartidas.add(new Asignatura("as9", 90, 90));
        asignaturasImpartidas.add(new Asignatura("as10", 100, 100));
        asignaturasImpartidas.add(new Asignatura("as7", 70, 70));
        asignaturasImpartidas.add(new Asignatura("as4", 40, 40));


        unProfe = new Profesor("p3", "profesor3", 3000, asignaturasImpartidas, 30);
        listaPersonas.add(unProfe);
        listaEmpleados.add(unProfe);

        //FIN CARGA PROFESORES

        //CARGA ADMINISTRATIVOS
        Administrativo unAdmin = new Administrativo("ad1", "administrativo1", 1010, "t1");
        listaPersonas.add(unAdmin);
        listaEmpleados.add(unAdmin);

        unAdmin = new Administrativo("ad2", "administrativo2", 2020, "t2");
        listaPersonas.add(unAdmin);
        listaEmpleados.add(unAdmin);

        unAdmin = new Administrativo("ad3", "administrativo3", 3030, "t2");
        listaPersonas.add(unAdmin);
        listaEmpleados.add(unAdmin);

        //FIN CARGA ADMINISTRATIVOS

        //FIN CARGA EMPLEADOS
    }

}
