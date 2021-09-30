import java.util.Scanner;

public class Ejercicio16 {

    public static void main(String[] args) {

        //Ejercicio 16
        //Dos vehículos viajan a diferentes velocidades (v1 y v2) y están distanciados por una
        //distancia d. El que está detrás viaja a una velocidad mayor. Se pide hacer un algoritmo para
        //ingresar la distancia entre los dos vehículos (km) y sus respectivas velocidades (km/h) y con
        //esto determinar y mostrar en que tiempo (minutos) alcanzará el vehículo más rápido al otro.

        int vel1;
        int vel2;
        double difVelocidades;
        double distancia;
        int tiempoFinal;
        Scanner sc = new Scanner(System.in);

        System.out.println("Velocidad 1: ");
        vel1 = sc.nextInt();
        System.out.println("Velocidad 2: ");
        vel2 = sc.nextInt();
        System.out.println("Distancia entre los coches: ");
        distancia = sc.nextInt();

        difVelocidades = vel1 - vel2;
         tiempoFinal = (int)((distancia / difVelocidades) * 60);
        System.out.println("El tiempo que tomara al primer coche alcanzar al segundo es de: " + tiempoFinal  + " minutos");

















    }

}
