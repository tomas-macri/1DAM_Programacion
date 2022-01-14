package main;


public class Ejercicio2 {
    public void ejercicio() {
        final int lengthArray = 5;
        Main claseMain = new Main();
        int[] arrayNumeros = claseMain.llenarArray(lengthArray);
        for (int i = arrayNumeros.length-1; i >= 0; i--) {
            System.out.println(arrayNumeros[i]);
        }
    }
}
