import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//
//          int numero1, numero2;
//          Scanner sc = new Scanner(System.in);
//
//        System.out.print("Primer numero: ");
//        numero1 = sc.nextInt();
//        System.out.print("Segundo numero: ");
//        numero2 = sc.nextInt();
//
//        System.out.println("La suma es: " + (numero1+numero2));

        int numero1, numero2;
        String nombre;
        Scanner sc = new Scanner(System.in);
        //Scanner nb = new Scanner(System.in);



        System.out.print("Primer numero: ");
        numero1 = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Segundo número: ");
        numero2 = sc.nextInt();

        System.out.println("Hola " + nombre + ". La suma es: " + (numero1+numero2));


//        double numero1, numero2;
//        Scanner sc = new Scanner(System.in);
//        //Scanner nb = new Scanner(System.in);
//
//
//
//        System.out.print("Primer numero: ");
//        numero1 = sc.nextDouble();
//        sc.nextLine();
//        System.out.print("Segundo numero: ");
//        numero2 = sc.nextDouble();
//        System.out.println("La suma es: " + (numero1/numero2));





    }


}

