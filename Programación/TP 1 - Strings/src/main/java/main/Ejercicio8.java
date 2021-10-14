package main;

import java.util.Locale;
import java.util.Scanner;

public class Ejercicio8 {
    public static void main(String[] args) {

    }

    public String ejercicio(Scanner sc) {
//        Ejercicio 8
//        Realizar un programa que lea una cadena por teclado y convierta las mayúsculas a
//        minúsculas y viceversa.
        System.out.println("Ingrese una palabra: ");
        String cadena = sc.nextLine();
        StringBuilder resultado = new StringBuilder();
        // REEMPLAZA TODOS LOS CARACTERES DE LA CADENA, REVISAR;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) >= 'A' && cadena.charAt(i) <= 'Z') {
               // cadena = cadena.replace(cadena.charAt(i), (char) (cadena.charAt(i) + 32));
               // No usé la instrucción de arriba ya que me reemplazaba todos los caracteres iguales a (cadena.charAt(i)) en la cadena y luego al leerlos los volvía a reemplazar. Ejemplo
               // si cadena = "hHOo" la respuesta hubiese sido = "hhOO".
              resultado.append((char) (cadena.charAt(i) + 32));
            }
            else if (cadena.charAt(i) >= 'a' && cadena.charAt(i) <= 'z') {
                // cadena = cadena.replace(cadena.charAt(i), (char) (cadena.charAt(i) - 32));
                // No usé la instrucción de arriba ya que me reemplazaba todos los caracteres iguales a (cadena.charAt(i)) en la cadena y luego al leerlos los volvía a reemplazar. Ejemplo
                // si cadena = "hHOo" la respuesta hubiese sido = "hhOO".
                resultado.append((char)(cadena.charAt(i) - 32));
            }
            else if (cadena.charAt(i) == 'ñ') {
                // cadena = cadena.replace(cadena.charAt(i), 'Ñ')
                // No usé = (char) (cadena.charAt(i) + 1)) como segundo argumento ya que no mostraba el caracter 'Ñ'
                // No usé la instrucción de arriba ya que me reemplazaba todos los caracteres iguales a (cadena.charAt(i)) en la cadena y luego al leerlos los volvía a reemplazar. Ejemplo
                // si cadena = "hHOo" la respuesta hubiese sido = "hhOO".
                resultado.append(cadena.toUpperCase().charAt(i));

            }
            else {
                // cadena = cadena.replace(cadena.charAt(i), 'ñ');
                // No usé = (char) (cadena.charAt(i) + 1)) como segundo argumento ya que no mostraba el caracter 'ñ'
                // No usé la instrucción de arriba ya que me reemplazaba todos los caracteres iguales a (cadena.charAt(i)) en la cadena y luego al leerlos los volvía a reemplazar. Ejemplo
                // si cadena = "hHOo" la respuesta hubiese sido = "hhOO".
                resultado.append(cadena.toLowerCase().charAt(i));
            }
        }
        return resultado.toString();
    }
}
