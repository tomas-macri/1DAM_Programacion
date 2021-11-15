package main;

public class EJ2 {
//    los números están entre el -10 y el 10 sin incluir el 0, que vienen en grupos
//    de 10 y están ordenados, y con la peculiaridad de vienen los negativos y luego los
//    positivos, el número de negativos y positivos no se sabe, pero siempre irá al menos
//    un número positivo

    public static void main(String[] args) {
        EJ2 ej = new EJ2();
        int[] arrayNumeros = new int[]{-10, -6, -5, -3, -2, 3, 4, 6, 7, 9};

        if (ej.pedirNumerosValidosCrecientes(arrayNumeros)) {

            int cantidadNumerosNegativos = 0;
            int cantidadNumerosPositvos = 0;
            // me fijo cuantos positivos y negativos hay en el array de numeros
            for (int i = 0; i < arrayNumeros.length; i++) {
                if (arrayNumeros[i] < 0){
                    cantidadNumerosNegativos++;
                }
                else {
                    cantidadNumerosPositvos++;
                }
            }

            // creo un array para los positivos y otro para los negativos y voy guardando los valores del array de numeros
            int[] arrayPositivos = new int[cantidadNumerosPositvos];
            int[] arrayNegativos = new int[cantidadNumerosNegativos];
            // guardo por un lado los numeros negativos y por otro los positivos
            ej.separarNegativosDePositivos(arrayNumeros, arrayPositivos, arrayNegativos);

            // ya tengo los dos arrays. Inicializo los contadores a 0 y voy comparando los valores para ir reemplazando los valores del array de numeros

            ej.encriptar(arrayNumeros, arrayPositivos, arrayNegativos);

            // muestro resultados
            System.out.println("El array encriptado quedaría así:");
            ej.mostrarArrayNumeros(arrayNumeros);

            // desencripto array numeros
            ej.desencriptar(arrayNumeros, arrayPositivos, arrayNegativos);

            // muestro resultados
            System.out.println("El array desencriptado quedaría así:");
            ej.mostrarArrayNumeros(arrayNumeros);

        }
        else {
            System.out.println("Los numeros del array no estan ordenados, hay un 0 o no hay positivos");
        }
    }

    private void desencriptar(int[] arrayNumeros, int[] arrayPositivos, int[] arrayNegativos) {
        int contNumsPosi = 0;
        int contNumsNeg = arrayNegativos.length -1;
        for (int i = 0; i < arrayNumeros.length; i++) {
            if (contNumsNeg >= 0){
                arrayNumeros[i] = arrayNegativos[contNumsNeg];
                contNumsNeg--;
            }
            else {
                arrayNumeros[i] = arrayPositivos[contNumsPosi];
                contNumsPosi++;
            }
        }
    }

    private void encriptar(int[] arrayNumeros, int[] arrayPositivos, int[] arrayNegativos) {
        int contNumsNeg = 0;
        int contNumsPosi = 0;
        for (int i = 0; i < arrayNumeros.length; i++) {
            // valido que ningun array haya llegado al limite
            if (contNumsPosi < arrayPositivos.length && contNumsNeg < arrayNegativos.length){
                //comparo valores
                if (arrayPositivos[contNumsPosi] > (arrayNegativos[contNumsNeg]*-1))
                {
                    arrayNumeros[i] = arrayNegativos[contNumsNeg];
                    contNumsNeg++;
                }
                else
                {
                    arrayNumeros[i] = arrayPositivos[contNumsPosi];
                    contNumsPosi++;
                }
            }
            // Me fijo que contador se paso y guardo los valores del otro solamente.
            // Los dos nunca se van a pasar porque la longitud del array de numeros es la suma de ambos.
            else if (contNumsPosi >= arrayPositivos.length)
            {
                arrayNumeros[i] = arrayNegativos[contNumsNeg];
                contNumsNeg++;
            }
            else{
                arrayNumeros[i] = arrayPositivos[contNumsPosi];
                contNumsPosi++;
            }
        }
    }

    private void separarNegativosDePositivos(int[] arrayNumeros, int[] arrayPositivos, int[] arrayNegativos) {
        int contNumsPosi = 0;
        int contNumsNeg = arrayNegativos.length-1;
        for (int i = 0; i < arrayNumeros.length; i++) {
            if (arrayNumeros[i] > 0) {
                arrayPositivos[contNumsPosi] = arrayNumeros[i];
                contNumsPosi++;
            }
            else {
                arrayNegativos[contNumsNeg] = arrayNumeros[i];
                contNumsNeg--;
                // los negativos se guardan al reves asi el primer numero no es el que tiene mayor valor absoulto (-10)
            }
        }
    }

    private void mostrarArrayNumeros(int[] arrayNumeros) {
        for (int i = 0; i < arrayNumeros.length; i++) {
            if (i != arrayNumeros.length -1){
                System.out.print(arrayNumeros[i] + " , ");
            }
            else{
                System.out.print(arrayNumeros[i]);
                System.out.println();
            }
        }
    }


    private boolean pedirNumerosValidosCrecientes(int[] arrayNumeros) {
        boolean esValido = true;
        if (arrayNumeros[arrayNumeros.length - 1] < 1) {
            esValido = false;
        } else {
            for (int i = 1; i < arrayNumeros.length && esValido; i++) {
                if (arrayNumeros[i - 1] >= arrayNumeros[i] || arrayNumeros[i] == 0) {
                    esValido = false;
                }
            }
        }
        return esValido;
    }

}
