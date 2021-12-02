package cartas.examenBroncos;

import cartas.servicios.ServiciosCartas;

public class MainBroncos1 {

    public static void main(String[] args) {
        ServiciosCartas sc = new ServiciosCartas();

        int baraja1[] = sc.generarBaraja(40, 10);
        int baraja2[] = sc.generarBaraja(40, 10);


        imprimirBaraja(baraja1);
        imprimirBaraja(baraja2);

        int sumaPosiciones1[] = new int[10];
        int sumaPosiciones2[] = new int[10];

        for (int i = 0; i < baraja1.length; i++) {
            sumaPosiciones1[baraja1[i] - 1] += i + 1;
            sumaPosiciones2[baraja2[i] - 1] += i + 1;
        }

        imprimirBaraja(sumaPosiciones1);
        imprimirBaraja(sumaPosiciones2);

        int puntos1 = 0;
        int puntos2 = 0;
        for (int i = 0; i < sumaPosiciones1.length; i++) {
            if (sumaPosiciones1[i] > sumaPosiciones2[i])
                puntos1++;
            else if (sumaPosiciones1[i] < sumaPosiciones2[i])
                puntos2++;
        }

        System.out.println("jugador 1 "+puntos1);
        System.out.println("jugador 2 "+puntos2);

    }

    public static void imprimirBaraja(int[] baraja) {
        System.out.print("{ ");
        for (int i = 0; i < baraja.length; i++) {
            if (i != 0)
                System.out.print("-");
            System.out.print(baraja[i]);

        }
        System.out.println(" }");
    }
}
