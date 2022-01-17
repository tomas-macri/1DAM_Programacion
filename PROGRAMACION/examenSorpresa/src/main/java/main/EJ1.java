package main;

import servicios.ServiciosBaraja;

public class EJ1 {
    public static void main(String[] args) throws InterruptedException {
        ServiciosBaraja sb = new ServiciosBaraja();
        int[] baraja1 = sb.inicializarBaraja(40,10);
        int[] baraja2 = sb.inicializarBaraja(40,10);
        EJ1 ej = new EJ1();

        System.out.println("La baraja 1 mezclada quedó así: ");
        ej.mostrarBarajaMezclada(baraja1);
        int cantPuntosBaraja1 = 0;
        int cantPuntosBaraja2 = 0;
        int acumPosiciones1;
        int acumPosiciones2;
        // todas los valores de una carta
        for (int i = 1; i <= 10; i++) {
            acumPosiciones1 = 0;
            acumPosiciones2 = 0;

            //recorro barajas
            for (int j = 0; j < baraja1.length; j++) {
                if (i==baraja1[j]){
                    acumPosiciones1+=j;
                }
                if (i == baraja2[j]){
                    acumPosiciones2+=j;
                }
            }
            if (acumPosiciones1>acumPosiciones2){
                cantPuntosBaraja1++;
            }
            else if (acumPosiciones2>acumPosiciones1){
                cantPuntosBaraja2++;
            }
            ej.mostrarResulRonda(acumPosiciones1, acumPosiciones2, i);
            Thread.sleep(2000);
        }
        String mensajeFinal = "Es un empate a " + cantPuntosBaraja1 + " entre los jugadores";
        if (cantPuntosBaraja1 > cantPuntosBaraja2)
        {
            mensajeFinal = "El jugador 1 gana por " + cantPuntosBaraja1 + " a " + cantPuntosBaraja2;
        }
        else if (cantPuntosBaraja2 > cantPuntosBaraja1){
            mensajeFinal = "El jugador 2 gana por " + cantPuntosBaraja2 + " a " + cantPuntosBaraja1;
        }
        System.out.println(mensajeFinal);

    }

    private void mostrarBarajaMezclada(int[] baraja1) {
        for (int i = 0; i < baraja1.length; i++) {
            if (i != baraja1.length -1){
                System.out.print(baraja1[i] + " - ");
            }
            else{
                System.out.print(baraja1[i]);
                System.out.println();
            }
        }
    }


    private void mostrarResulRonda(int acumPosiciones1, int acumPosiciones2, int i) {
        System.out.println("Las posiciones de los " + i + " de la baraja 1 dan un total de " + acumPosiciones1);
        System.out.println("Las posiciones de los " + i + " de la baraja 2 dan un total de " + acumPosiciones2);
    }

}
