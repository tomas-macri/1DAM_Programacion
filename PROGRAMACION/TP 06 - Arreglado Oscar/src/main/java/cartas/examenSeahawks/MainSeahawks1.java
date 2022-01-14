package cartas.examenSeahawks;

import cartas.servicios.ServiciosCartas;

public class MainSeahawks1 {

    public static void main(String[] args) {
        ServiciosCartas sc = new ServiciosCartas();

        int baraja1[] = sc.generarBaraja(40, 10);
        int baraja2[] = sc.generarBaraja(40, 10);


        imprimirBaraja(baraja1);
        imprimirBaraja(baraja2);

        boolean pierde1 = false;
        boolean pierde2 = false;
        int valorAMirar = 0;

        int indiceComienzoCollejas = 0;
        for (; indiceComienzoCollejas < 80 && !pierde2 && !pierde1; indiceComienzoCollejas++) {
            valorAMirar = (indiceComienzoCollejas % 10) + 1;
            if (baraja1[indiceComienzoCollejas/2] == valorAMirar) {
                pierde1 = true;
            }
            indiceComienzoCollejas++;
            valorAMirar = (indiceComienzoCollejas % 10) + 1;
            if (baraja2[indiceComienzoCollejas/2] == valorAMirar) {
                pierde2 = true;
            }


        }

        //calcular collejas
        int sumaCollejas = 0;
        if (pierde1) {

            for (int i = indiceComienzoCollejas/2; i < baraja1.length; i++) {
                sumaCollejas += baraja1[i];
            }
            System.out.println("jugador 1 recibe "+sumaCollejas);
        }
        sumaCollejas = 0;
        if (pierde2){
            for (int i = indiceComienzoCollejas/2; i < baraja2.length; i++) {
                sumaCollejas += baraja2[i];
            }
            System.out.println("jugador 2 recibe "+sumaCollejas);

        }
        if (!pierde1 && !pierde2)
        {
            System.out.println("todos a casa en paz");
        }



    }

    public static void imprimirBaraja(int[] baraja) {
        System.out.print("{ ");
        for (int i = 0; i < baraja.length; i++) {
            if (i != 0)
                System.out.print("*");
            System.out.print(baraja[i]);

        }
        System.out.println(" }");
    }
}
