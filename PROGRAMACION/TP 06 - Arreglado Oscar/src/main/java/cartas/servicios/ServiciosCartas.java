package cartas.servicios;

import java.util.Random;

public class ServiciosCartas {

    public int[] generarBaraja(int numeroCartas,int numeroCartasPalo)
    {
        int cartas[] = new int[numeroCartas];
        for (int i = 0; i <numeroCartas; i++) {
            cartas[i] = (i%numeroCartasPalo)+1;
        }
        barajar(cartas);
        return cartas;
    }

    private void barajar(int []cartas)
    {
        //swap
        Random r = new Random();

        int indice1= 0;
        int indice2 = 0;
        int swap=0;

        for (int i = 0; i < 1000; i++) {
            indice1 = r.nextInt(39);
            indice2 = r.nextInt(39);
            swap = cartas[indice1];
            cartas[indice1] = cartas[indice2];
            cartas[indice2] = swap;
        }
    }


}
