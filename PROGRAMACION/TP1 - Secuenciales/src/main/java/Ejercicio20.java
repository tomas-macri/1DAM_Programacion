import java.util.Scanner;

public class Ejercicio20 {

    public static void main(String[] args) {

//        Ejercicio 20
//        Diseñar un algoritmo que nos diga el dinero que tenemos (en euros y céntimos) después de
//        pedirnos cuantas monedas tenemos (de 2€, 1€, 50 céntimos, 20 céntimos o 10 céntimos).

        double eurosTotales;
        int centimosTotales;
        int mon2;
        int mon1;
        int mon50;
        int mon20;
        int mon10;

        Scanner sc = new Scanner(System.in);
        System.out.println("Cantidad de monedas de 2€ que tiene: ");
        mon2 = sc.nextInt();
        System.out.println("Cantidad de monedas de 1€ que tiene: ");
        mon1 = sc.nextInt();
        System.out.println("Cantidad de monedas de 50 céntimos que tiene: ");
        mon50 = sc.nextInt();
        System.out.println("Cantidad de monedas de 20 céntimos que tiene: ");
        mon20 = sc.nextInt();
        System.out.println("Cantidad de monedas de 10 céntimos que tiene: ");
        mon10 = sc.nextInt();

        centimosTotales = mon2 * 200 + mon1 * 100 + mon50 * 50 + mon20 * 20 + mon10 * 10;
        eurosTotales = centimosTotales * 0.01;
        System.out.println("Usted tiene un total de " + eurosTotales + " euros");


//      eurosTotales = centimosTotales / 100;
//      centimosTotales = centimosTotales % 100;
//      System.out.println("Usted tiene un total de " + eurosTotales + " euros y " + centimosTotales + " céntimos");


    }


}
