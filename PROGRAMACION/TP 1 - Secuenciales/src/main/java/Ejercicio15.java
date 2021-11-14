import java.util.Scanner;

public class Ejercicio15 {


    public static void main(String[] args) {

//        Ejercicio 15
//        Dadas dos variables numéricas A y B, que el usuario debe teclear, se pide realizar un
//        algoritmo que intercambie los valores de ambas variables y muestre cuanto valen al final las
//        dos variables.




        Scanner sc = new Scanner(System.in);
        int numA;
        int numB;
        int c;

        System.out.println("Dame el número A: ");
        numA = sc.nextInt();
        System.out.println("Dame el número B: ");
        numB = sc.nextInt();
        c = numA;
        numA = numB;
        numB = c;
        System.out.println("Intercambiando los números, la primera variable vale " + numA + ". La segunda variable vale " + numB );

    }

}
