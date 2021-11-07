package main;

public class Ejercicio5 {
    public int[] ejercicio() {
        //5. Leer por teclado dos tablas de 10 números enteros y mezclarlas en una tercera de la
        //forma: el 1º de A, el 1º de B, el 2º de A, el 2º de B, etc.
        final int lengthArray = 10;
        Main claseMain = new Main();
        int[] tabla1 = claseMain.llenarArray(lengthArray);
        int[] tabla2 = claseMain.llenarArray(lengthArray);
        int[] tablaCombinada = new int[lengthArray*2];
        int contTabla1 = 0;
        int contTabla2 = 0;
        for (int i = 0; i < tablaCombinada.length; i++) {
            if (i%2==0){
                tablaCombinada[i] = tabla1[contTabla1];
                contTabla1++;
            }
            else {
                tablaCombinada[i] = tabla2[contTabla2];
                contTabla2++;
            }
        }
        return tablaCombinada;
    }
}
