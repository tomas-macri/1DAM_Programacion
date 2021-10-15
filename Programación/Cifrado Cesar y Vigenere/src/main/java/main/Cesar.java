package main;

import java.util.Scanner;

public class Cesar {
    public static void main(String[] args) {
        
    }
    public String Cifrar(Scanner sc) {
        System.out.println("Ingrese el código que quiere cifrar");
        String codigo = sc.nextLine();
        System.out.println("Ingrese la cantidad de caracteres por la que quiere reemplazar el codigo: ");
        int cantCaracteres = sc.nextInt();
        sc.nextLine();
        StringBuilder codigoCifrado = new StringBuilder();
        char letraActual;
        for (int i = 0; i < codigo.length(); i++) {
            letraActual = codigo.charAt(i);
            if (letraActual > 90){
                letraActual -= 97;
                codigoCifrado.append((char) (((letraActual + cantCaracteres) % 26) + 97));
            }
            else {
                letraActual -= 65;
                codigoCifrado.append((char) (((letraActual + cantCaracteres) % 26) + 65));
            }
        }
        return codigoCifrado.toString();
    }

    public String Descifrar(Scanner sc) {
        System.out.println("Ingrese el código que quiere descifrar");
        String codigo = sc.nextLine();
        System.out.println("Ingrese la cantidad de caracteres por la que descifrar codigo: ");
        int cantCaracteres = sc.nextInt();
        sc.nextLine();
        StringBuilder codigoDescifrado = new StringBuilder();
        char letraActual;
        for (int i = 0; i < codigo.length(); i++) {
            letraActual = codigo.charAt(i);
            if (letraActual > 90){
                letraActual -= 97;
                codigoDescifrado.append((char) (((letraActual - cantCaracteres + 26) % 26) + 97));
            }
            else {
                letraActual -= 65;
                codigoDescifrado.append((char) (((letraActual - cantCaracteres + 26) % 26) + 65));
            }
        }

        return codigoDescifrado.toString();
    }
}
