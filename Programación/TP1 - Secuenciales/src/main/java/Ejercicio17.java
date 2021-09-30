import java.util.Scanner;

public class Ejercicio17 {

    public static void main(String[] args) {

//        Ejercicio 17
//        Un ciclista parte de una ciudad A a las HH horas, MM minutos y SS segundos. El tiempo de
//        viaje hasta llegar a otra ciudad B es de T segundos. Escribir un algoritmo que determine la
//        hora de llegada a la ciudad B.
        Scanner sc = new Scanner(System.in);
        int horas;
        int minutos;
        int segundos_iniciales;
        int tiempo;
        int segstotales;

        System.out.println("Dame hora de salida: ");
        horas = sc.nextInt();
        System.out.println("Dame minuto de salida: ");
        minutos = sc.nextInt();
        System.out.println("Dame segundo de salida: ");
        segundos_iniciales = sc.nextInt();

        System.out.println("Dame el tiempo que tarda  en segundos: ");
        tiempo = sc.nextInt();

        segstotales = horas*3600;
        segstotales = segstotales + minutos*60;
        segstotales = segstotales + segundos_iniciales;
        segstotales = segstotales + tiempo;

        horas = segstotales / 3600;
        segstotales = segstotales % 3600;
        minutos = segstotales / 60;
        segstotales = segstotales % 60;
        segundos_iniciales = segstotales;

        System.out.println("Dame hora de llegada " + horas + ":" + minutos + ":" + segundos_iniciales);
    }

}
