package main;

public class Ejercicio4 {
    public int[] ejercicio() {
//        4. Leer 10 números enteros. Debemos mostrarlos en el siguiente orden: el primero, el
//        último, el segundo, el penúltimo, el tercero, etc.

        final int lengthArray = 10;
        Main claseMain = new Main();
        int[] arrayNumeros = claseMain.llenarArray(lengthArray);
        int[] arrayResultado = new int[lengthArray];

        for (int i = 0, j = arrayNumeros.length-1, contArray = 0; i < j; i++, j--, contArray+=2) {
            arrayResultado[contArray] = arrayNumeros[i];
            arrayResultado[(contArray+1)] = arrayNumeros[j];
        }
        return arrayResultado;
    }
}
