package cartas.examenBroncos;

import java.util.Arrays;

public class MainBroncos2Opcion3 {

    public static void main(String[] args) {
        int[] numeros = new int[]{-10, -6, -5, -3, -2, -1, 4, 6, 7, 9};

        System.out.println(Arrays.toString(numeros));

        boolean arrayValido = true;

        //ENCRIPTAR
        encriptar(numeros);
        System.out.println(Arrays.toString(numeros));

        //DESENCRIPTAR
        desencriptar(numeros);
        System.out.println(Arrays.toString(numeros));

    }


    public static void desencriptar(int[] numeros) {
        int swap=0;
        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros.length - 1; j++) {
                if (numeros[j] > numeros[j + 1]) {
                    swap = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = swap;
                }
            }
        }
    }

    public static void encriptar(int[] numeros) {
        int swap;

        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros.length - 1; j++) {
                if (Math.abs(numeros[j]) > Math.abs(numeros[j + 1])) {
                    swap = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = swap;
                }
            }
        }
    }


}
