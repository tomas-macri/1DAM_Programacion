package otro;

import servicios.ServiciosBaraja;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        //OPCION 1 ARRAYS

        int[] valoresIniciales = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] vecesPorValor = new int[]{1, 1, 1, 1, 1, 2, 2, 1, 1, 1};
        int longitudArray = 0;
        for (int i = 0; i < vecesPorValor.length; i++) {
            longitudArray += vecesPorValor[i];
        }

        int[] arrayResultado = new int[longitudArray];
        int contadorValores = 0;
        int contArrayResultado = 0;

        while (contArrayResultado < arrayResultado.length){
            for (int j = 0; j < vecesPorValor[contadorValores]; j++) {
                    arrayResultado[contArrayResultado] = valoresIniciales[contadorValores];
                    contArrayResultado++;
            }
            contadorValores++;
        }

        System.out.println("OPCION 1 - ARRAYS");
        for (int i = 0; i < arrayResultado.length; i++) {
            System.out.print(arrayResultado[i] + " ");
        }

        System.out.println();
        System.out.println();
        // BARAJA OPCIONES 1 y 2
        System.out.println("OPCIONES 1 Y 2 - BARAJAS");
        ServiciosBaraja sb = new ServiciosBaraja();
        int[] baraja = sb.inicializarBaraja(40,10);
        // tengo baraja mezclada
        int[] primerasPosiciones = new int[10];
        int[] ultimasPosiciones = new int[10];
        boolean encontrado;
        System.out.println();
        System.out.println("BARAJA: ");
        for (int i = 0; i < baraja.length; i++) {
            System.out.print(baraja[i] + " - ");
        }
        for (int i = 0; i < primerasPosiciones.length; i++) {
            encontrado = false;
            for (int j = 0; j < baraja.length; j++) {
                if (baraja[j] == i+1){
                    if (!encontrado){
                        primerasPosiciones[i] = j+1; // de 1 a 40
                        encontrado = true;
                    }
                    ultimasPosiciones[i] = j+1;
                }
            }
        }
        System.out.println();
        for (int i = 0; i < primerasPosiciones.length; i++) {
            System.out.println("primera carta " + (i+1) + ": " + primerasPosiciones[i]);
            System.out.println("ultima carta " + (i+1) + ": " + ultimasPosiciones[i]);

        }
        System.out.println();
        // OPCION 2 ARRAYS
        System.out.println("OPCION 2 - ARRAYS");
        int[] numeros = new int[] {1,2,1,4,5,6,7,8,9,10};
        int[] operaciones = new int[]{1,4,3,1,2,2,1,1,2};

        String msjError = "";
        int acumResultado = numeros[0];
        for (int i = 1; i < numeros.length && msjError.equals(""); i++) {
            switch (operaciones[i-1]){
                case 1:
                    acumResultado+=numeros[i];
                    break;
                case 2:
                    acumResultado-=numeros[i];
                    break;
                case 3:
                    acumResultado*=numeros[i];
                    break;
                case 4:
                    if (numeros[i] != 0){
                        acumResultado/=numeros[i];
                    }
                    else {
                        msjError = "No se puede dividir por 0";
                    }
                    break;
                default:
                    msjError = "Operacion inexistente";
                    break;
            }
        }
        if (msjError.equals("")){
            System.out.println("El calculo da un resultado de: " + acumResultado);
        }
        else {
            System.out.println(msjError);
        }


    }

}
