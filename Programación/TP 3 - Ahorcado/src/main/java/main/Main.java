package main;

import com.github.javafaker.*;
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

        // Palabra generada (nombre de un pais (faker.country().name()) o de un nombre para probar que diferencie tilde)
        String palabraGenerada = faker.name().firstName(); //"diferencia tildes: á é í ó ú"

        char caracterCodificar = '_';
        // codifico la palabra generada (Los caracteres que no son letras los dejo igual)
        StringBuilder palabraCodificada = claseMain.codificarPalabraGenerada(palabraGenerada, caracterCodificar);

        boolean arriesgarFrase = false;
        char letraIngresada;
        String ingresoUsuario;
        StringBuilder letrasArriesgadas = new StringBuilder(); // guarda todas las letras que haya arriesgado el usuario
        StringBuilder letrasArriesgadasIncorrectas = new StringBuilder(); // guarda solo las letras que haya arriesgado el usuario y que no se encuentran en la palabra
        // do while tenga vidas y las palabras no sean iguales
        do {
            // pido y valido letra o palabra (que sea caracter y que no lo haya escrito antes o que sea una palabra con = length que la palabra generada)
            clasePintar.pintarAhorcado(intentosRestantes);
            do {
                System.out.println(palabraCodificada);
                System.out.println("Letras incorrectas: " + letrasArriesgadasIncorrectas);
                System.out.println("Tienes " + intentosRestantes + " intentos. Ingresa una letra o arriesga" +
                        " (para arriesgar deberás ingresar la misma cantidad de caracteres que tiene la palabra)");
                ingresoUsuario = sc.nextLine();
                if (ingresoUsuario.length() == palabraGenerada.length()){
                    arriesgarFrase = true;
                }
                // no inicializo letraIngresada dentro de un else porque esta variable debe estar inicializada en todos los casos para poder ejecutar la linea siguiente
                letraIngresada = ingresoUsuario.charAt(0);
            } while (!claseMain.validacionLetraIngresada(letraIngresada, letrasArriesgadas) && !arriesgarFrase);

            // Una vez que ingresó algo válido, me fijo si se arriesgó o si es un caracter (si ingresó + de 1 caracter pero no los de la misma longitud de la palabra, no contará como arriesgarse
           if (arriesgarFrase){
               System.out.println("Te has arriesgado por: " + ingresoUsuario);
               arriesgarSolucion(ingresoUsuario, palabraCodificada, palabraGenerada); // Sugerencia de intellij: no hace falta igualar esta funcion a palabraCodificada

               // como despues de arriesgar no hay segunda oportunidad, si acierta asigno la igualo la palabra codificada a la generada pero en ambos casos devuelvo 0
               intentosRestantes = 0;
           }
            else {
                // No se arriesgó, asigno la letra ingresad
                letraIngresada = ingresoUsuario.charAt(0);
                //busco si la letra ingresada esta en la palabra
                if (claseMain.letraIngresadaCorrecta(letraIngresada, palabraGenerada)) {
                    // si está, reemplazo los _____ por el caracter
                    System.out.println("Letra correcta");
                    claseMain.reemplazarCaracteresCorrectos(letraIngresada, palabraGenerada, palabraCodificada); // Sugerencia de intellij: no hace falta igualar esta funcion a palabraCodificada
                } else {
                    // si no esta, pierde un intento y agrego
                    intentosRestantes--;

                    if (letrasArriesgadasIncorrectas.length() > 0) { // if para acomodar el string builder de letras arriesgadas
                        letrasArriesgadasIncorrectas.append('-');
                    }
                    letrasArriesgadasIncorrectas.append(letraIngresada);
                }
            }

        } while (intentosRestantes > 0 && !palabraCodificada.toString().equals(palabraGenerada));
        //Comparo si la palabra generada y la codificada son diferentes, si es true, murió
        if (!palabraCodificada.toString().equalsIgnoreCase(palabraGenerada)) {
            clasePintar.pintarAhorcado(intentosRestantes);
            System.out.println("Moriste. La respuesta era: " + palabraGenerada);
        } else {
            System.out.println("La palabra era " + palabraGenerada);
            System.out.println("GANASTE!");
        }
    }

    private StringBuilder codificarPalabraGenerada(String palabraGenerada, char caracterCodificacion) {
        StringBuilder palabraCodificada = new StringBuilder();
        for (int i = 0; i < palabraGenerada.length(); i++) {
            if (Character.isLetter(palabraGenerada.charAt(i))) {
                palabraCodificada.append(caracterCodificacion);
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

    private static StringBuilder arriesgarSolucion(String ingresoUsuario, StringBuilder palabraCodificada, String palabraGenerada) {
        if (ingresoUsuario.equalsIgnoreCase(palabraGenerada)){
            palabraCodificada.delete(0, palabraCodificada.length());
            palabraCodificada.append(ingresoUsuario);
        }
        return palabraCodificada;
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