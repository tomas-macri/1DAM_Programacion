package cartas.examenBroncos;

public class MainBroncos2Opcion2 {

    public static void main(String[] args) {

        int numeros[] = {-10, -8, -5, 1, 2, 3, 4, 6, 7, 9};
        imprimirBaraja(numeros);

        int swap = 0;
        if (correcto(numeros)) {
            encriptar(numeros);

            imprimirBaraja(numeros);

            desencriptar(numeros);


            imprimirBaraja(numeros);
        }


    }

    public static void desencriptar(int[] numeros) {
        int swap = 0;
        for (int i = numeros.length - 1; i > 0; i--) {
            while (numeros[i] < numeros[i - 1]) {
                boolean salir = false;
                swap = numeros[i];
                int j = i - 1;
                for (; j >= 0 && !salir; j--) {

                    if (swap < numeros[j]) {
                        numeros[j + 1] = numeros[j];
                    } else {
                        salir = true;
                    }
                }
                if (!salir) j = 0;
                else j = j + 2;


                numeros[j] = swap;

            }
        }
    }

    public static void encriptar(int[] numeros) {
        int swap = 0;
        while (Math.abs(numeros[0]) > Math.abs(numeros[1])) {
            int i = 0;
            boolean salir = false;
            swap = numeros[i];
            int j = i + 1;
            for (; j < numeros.length && !salir; j++) {

                if (Math.abs(swap) > Math.abs(numeros[j])) {
                    numeros[j - 1] = numeros[j];
                } else {
                    salir = true;
                }
            }
            if (!salir) j = numeros.length - 1;
            else j = j - 2;


            numeros[j] = swap;
        }
    }

    public static void imprimirBaraja(int[] baraja) {
        System.out.print("{ ");
        for (int i = 0; i < baraja.length; i++) {
            if (i != 0)
                System.out.print(" ");
            System.out.print(baraja[i]);

        }
        System.out.println(" }");
    }

    public static boolean correcto(int[] numeros) {
        boolean ok = true;

        if (numeros[numeros.length - 1] <= 0)
            ok = false;
        else {
            for (int i = 0; i < numeros.length - 1 && ok; i++) {
                if (numeros[i] < -10 || numeros[i] > 10)
                    ok = false;
                else if (numeros[i] == 0)
                    ok = false;
                else if (numeros[i] > numeros[i + 1])
                    ok = false;
            }
        }


        return ok;
    }
}
