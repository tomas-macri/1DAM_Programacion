package main;

public class Ejercicio6 {
    public int[] ejercicio() {
//        6. Leer los datos correspondiente a dos tablas de 12 elementos numéricos, y mezclarlos en
//        una tercera de la forma: 3 de la tabla A, 3 de la B, otros 3 de A, otros 3 de la B, etc.
        final int cantDatosPorArray = 3;
        final int lengthArray = 12;
        Main claseMain = new Main();
        int[] primerArray = claseMain.llenarArray(lengthArray);
        int[] segundoArray = claseMain.llenarArray(lengthArray);

        int contPrimerArray = 0;
        int contSegundoArray = 0;
        int[] arrayResultado = new int[lengthArray*2];
        int contArrayResultado = 0;
        for (int i = 0; i < lengthArray/cantDatosPorArray; i++) {
            for (int inicioContador = contArrayResultado;contArrayResultado < cantDatosPorArray+inicioContador; contArrayResultado++) {
                //inicioContador guarda el valor del contador al inicar el for,
                // mientras el contador del array no valga la suma de la cantidad de datos por array que quiera el user (3) + lo que valia al incio
                // se sigue incrementando
                arrayResultado[contArrayResultado] = primerArray[contPrimerArray];
                contPrimerArray++;
            }
            for (int inicioContador = contArrayResultado;contArrayResultado < cantDatosPorArray+inicioContador; contArrayResultado++) {
                //inicioContador guarda el valor del contador al inicar el for,
                // mientras el contador del array no valga la suma de la cantidad de datos por array que quiera el user (3) + lo que valia al incio
                // se sigue incrementando
                arrayResultado[contArrayResultado] = (segundoArray[contSegundoArray]);
                contSegundoArray++;
            }
        }
        return arrayResultado;
    }
}
