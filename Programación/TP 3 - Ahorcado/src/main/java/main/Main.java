package main;

import com.github.javafaker.*;

import java.lang.annotation.Native;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        //conectar con libreria()
        Faker faker = new Faker(new Locale("es-ES"));

        // definir valores iniciales
        Main claseMain = new Main();
        Pintar clasePintar = new Pintar();
        Scanner sc = new Scanner(System.in);

        // intentos
        int intentosRestantes = 8;

        // Palabra generada (nombre de un pais)
        String palabraGenerada = faker.country().name();//"hola         é í ó ú"

        // codifico la palabra generada (Los caracteres que no son letras los dejo igual)
        StringBuilder palabraCodificada = claseMain.codificarPalabraGenerada(palabraGenerada);

        // do while tenga vidas y las palabras no sean iguales
        //System.out.println(palabraGenerada);
        char letraIngresada;
        StringBuilder letrasArriesgadas = new StringBuilder(); // guarda todas las letras que haya arriesgado el usuario
        StringBuilder letrasArriesgadasIncorrectas = new StringBuilder(); // guarda solo las letras que haya arriesgado el usuario y que no se encuentran en la palabra
        do {
            clasePintar.pintarAhorcado(intentosRestantes);
            do {
                System.out.println(palabraCodificada);
                System.out.println("Letras incorrectas: " + letrasArriesgadasIncorrectas);
                System.out.println("Tienes " + intentosRestantes + " intentos. Ingresa una letra");
                letraIngresada = sc.nextLine().charAt(0);

            } while (!claseMain.validacionLetraIngresada(letraIngresada, letrasArriesgadas));
            if (claseMain.letraIngresadaCorrecta(letraIngresada, palabraGenerada)) {
                System.out.println("Letra correcta");
                claseMain.reemplazarCaracteresCorrectos(letraIngresada, palabraGenerada, palabraCodificada); // Sugerencia de intellij: no hace falta igualar esta funcion a palabraCodificada
            } else {
                intentosRestantes--; // pierde una vida
                if (letrasArriesgadasIncorrectas.length() > 0) { // if para acomodar el string builder de letras arriesgadas
                    letrasArriesgadasIncorrectas.append('-');
                }
                letrasArriesgadasIncorrectas.append(letraIngresada);
            }

        } while (intentosRestantes > 0 && !palabraCodificada.toString().equals(palabraGenerada));
        if (!palabraCodificada.toString().equals(palabraGenerada)) {
            clasePintar.pintarAhorcado(intentosRestantes);
            System.out.println("Moriste. La respuesta era: " + palabraGenerada);
        } else {
            System.out.println("GANASTEEEEEEEEE");
        }


//            CODIGO PARA BORRAR COSAS QUE NO SEAN NI CARACTERES NI ESPACIOS (MAL)
//            for (int j = 0; j < palabraGenerada.length() - 1; j++) {
//                if (!Character.isLetter(palabraGenerada.charAt(j)) && palabraGenerada.charAt(j) != ' ') {
//                    if (Character.isLetter(palabraGenerada.charAt(j + 1))) {
//                        palabraGenerada.replace("" + palabraGenerada.charAt(j), " ");
//                    } else {
//                        palabraGenerada.replace("" + palabraGenerada.charAt(j), "");
//                    }
//                }
//            }


        //bucle hasta oportunidades o que acierte

        // DIFICIL mostrar palabra ocultada *** ****   *A* *****

        // pedirle letra, comprobar que la letra sea nueva
        // o pedir frase  jugarsela

        // ver si esta  o no

        // si no esta saco ahorcado

        // si esta saco palabra con caracteres descubiertos
    }




    private StringBuilder codificarPalabraGenerada(String palabraGenerada) {
        StringBuilder palabraCodificada = new StringBuilder();
        for (int i = 0; i < palabraGenerada.length(); i++) {
            if (Character.isLetter(palabraGenerada.charAt(i))) {
                palabraCodificada.append('_');
            } else {
                palabraCodificada.append(palabraGenerada.charAt(i));
            }
        }
        return palabraCodificada;
    }

    private boolean validacionLetraIngresada(char letraIng, StringBuilder letrasArriesgadas) {
        boolean valido = true;
        if (!Character.isLetter(letraIng)) {
            valido = false;
        } else {
            for (int i = 0; i < letrasArriesgadas.length(); i++) {
                if (letraIng == letrasArriesgadas.charAt(i)) {
                    valido = false;
                    i = letrasArriesgadas.length(); // Si ya encontro un caracter arriesgado dentro del StringBuilder, sale del for
                }
            }
        }
        return valido;
    }

    private boolean letraIngresadaCorrecta(char letraIngresada, String palabraGenerada) {
        boolean esCorrecta = false;
        for (int i = 0; i < palabraGenerada.length(); i++) {
            if (Character.toLowerCase(letraIngresada) == palabraGenerada.toLowerCase().charAt(i)) {
                esCorrecta = true;
                i = palabraGenerada.length();
            }
        }
        return esCorrecta;
    }

    private StringBuilder reemplazarCaracteresCorrectos(char letraIngresada, String palabraGenerada, StringBuilder palabraCodificada) {
        for (int i = 0; i < palabraGenerada.length() ; i++) {
            if (Character.toLowerCase(letraIngresada) == palabraGenerada.toLowerCase().charAt(i)){
                palabraCodificada.replace(i,i+1, palabraGenerada.charAt(i)+"");
            }
        }
        return palabraCodificada;
    }


}