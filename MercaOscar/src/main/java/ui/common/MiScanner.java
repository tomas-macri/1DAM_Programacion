package ui.common;

import java.util.Scanner;

public class MiScanner {


    public int dameNumero(String mensaje)
    {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        int i=0;
        do {
            try {
                System.out.println(mensaje);
                i = Integer.parseInt(sc.nextLine());
                ok = true;
            } catch (NumberFormatException e) {
                System.out.println("error");
            }
        } while (!ok);

        return i;
    }



    public double dameDouble(String mensaje)
    {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        double i=0;
        do {
            try {
                System.out.println("dame un numero");
                i = Double.parseDouble(sc.nextLine());
                ok = true;
            } catch (NumberFormatException e) {
                System.out.println("error");
            }
        } while (!ok);

        return i;
    }
}
