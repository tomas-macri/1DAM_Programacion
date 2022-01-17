package cartas.examenSeahawks;


public class MainSeahawks2 {


    public static void main(String[] args) {
        int[] numeros = {5,20,11,10,8,15};
        int [] cambios =  { 2,1,0,1,3,1};
        imprimirBaraja(numeros);

        if (correcto(numeros)) {
            encriptar(numeros,cambios);

            imprimirBaraja(numeros);

            desencriptar(numeros,cambios);


            imprimirBaraja(numeros);
        }
        else
        {
            System.out.println("no valido");
        }


    }

    public static void desencriptar(int[] numeros,int[] cambios) {
        int posicionCambiar=0;
        int swap = 0;
        for (int i = numeros.length-1; i >=0; i--) {
            swap = numeros[i];
            posicionCambiar = (i+cambios[i]+ numeros.length)%numeros.length;
            numeros[i] = numeros[posicionCambiar];
            numeros[posicionCambiar] = swap;
        }
    }

    public static void encriptar(int[] numeros,int[] cambios) {
        int posicionCambiar=0;
        int swap = 0;
        for (int i = 0; i < numeros.length; i++) {
            swap = numeros[i];
            posicionCambiar = (i+cambios[i])%numeros.length;
            numeros[i] = numeros[posicionCambiar];
            numeros[posicionCambiar] = swap;
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
                if (numeros[i] < 5 || numeros[i] > 29) {
                    ok = false;
                }
            }
            //repetidos
            for (int i = 0; i < numeros.length - 1 && ok; i++) {
                for (int j = i + 1; j < numeros.length && ok; j++) {
                    if (numeros[i] == numeros[j]) {
                        ok = false;
                    }
                }
            }

        }


        return ok;
    }


}
