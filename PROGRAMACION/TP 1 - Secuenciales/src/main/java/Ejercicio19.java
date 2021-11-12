import java.util.Scanner;

public class Ejercicio19 {

    public static void main(String[] args) {

//        Ejercicio 19
//        Escribir un algoritmo para calcular la nota final de un estudiante, considerando que: por cada
//        respuesta correcta 5 puntos, por una incorrecta -1 y por respuestas en blanco 0. Imprime el
//        resultado obtenido por el estudiante.


        int puntosTotales;
        int cantCorrectas;
        int cantIncorrectas;
        // No se crea una variable para las respuestas en blanco ya que no afectan la puntuación final.
        Scanner sc = new Scanner(System.in);




        System.out.println("Ingrese la cantidad de respuestas correctas: ");
        cantCorrectas = sc.nextInt();
        System.out.println("Ingrese la cantidad de respuestas incorrectas: ");
        cantIncorrectas = sc.nextInt();
        System.out.println("Ingrese la cantidad de respuestas en blanco: ");
        sc.nextInt();
        puntosTotales = cantCorrectas*5 - cantIncorrectas;
        System.out.println("La puntuación del estudiante es de " + puntosTotales + " puntos.");
    }
}
