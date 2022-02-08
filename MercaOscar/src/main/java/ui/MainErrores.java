package ui;

import ui.common.MiScanner;

public class MainErrores {

    public static void main(String[] args) {

        MiScanner sc = new MiScanner();
        double d = 0;
        boolean ok = false;

        d= sc.dameNumero("dame un numero");

        double f = sc.dameDouble("dame un precio");

        System.out.println(d);
        System.out.println(f);



    }



}
