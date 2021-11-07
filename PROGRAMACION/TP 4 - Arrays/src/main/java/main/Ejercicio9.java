package main;


public class Ejercicio9 {
    public int[] ejercicio() {
//        9. Crear un programa que lea por teclado una tabla de 10 números enteros y la desplace
//        una posición hacia abajo (el último pasa a ser el primero).
        final int arrayLength = 10;
        Main claseMain = new Main();
        int[] arrayNumeros = claseMain.llenarArray(arrayLength);
        int[] arrayNumerosDesplazados = new int[arrayLength];
        for (int i = 0; i < arrayNumeros.length; i++) {
            arrayNumerosDesplazados[((i+1)%arrayNumerosDesplazados.length)] = arrayNumeros[i];
        }
        return arrayNumerosDesplazados;
    }
}
