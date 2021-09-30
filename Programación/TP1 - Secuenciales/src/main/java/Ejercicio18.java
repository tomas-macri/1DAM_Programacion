import java.util.Scanner;

public class Ejercicio18 {

    public static void main(String[] args) {

//        Ejercicio 18
//        Pedir el nombre y los dos apellidos de una persona y mostrar las iniciales.



        Scanner sc = new Scanner(System.in);

        String nombre;
        String apellido1;
        String apellido2;

        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Primer apellido: ");
        apellido1 = sc.nextLine();
        System.out.print("Segundo apellido: ");
        apellido2 = sc.nextLine();

        System.out.println("Sus iniciales son: " + nombre.toUpperCase().charAt(0) + apellido1.toUpperCase().charAt(0) + apellido2.toUpperCase().charAt(0));




    }

}
