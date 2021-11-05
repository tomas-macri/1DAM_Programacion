package main;

import java.util.Scanner;

public class Vigenere {
    public static void main(String[] args) {

    }

    public String Descifrar(Scanner sc) {
        System.out.println("Ingrese el código que quiere descifrar");
        String codigo = sc.nextLine();
        System.out.println("Ingrese el codigo con el cual desea descifrar: ");
        String cifrado = sc.nextLine();
        StringBuilder codigoDescifrado = new StringBuilder();
        char letraActual;
        char letraCifradoActual;
        // Errores con el codigo del  cifrado
        for (int i = 0; i < codigo.length(); i++) {
            letraActual = codigo.charAt(i);
            if (letraActual >= 97 && letraActual <= 122){
                letraCifradoActual = cifrado.toLowerCase().charAt(i%cifrado.length());
                letraActual -= 97;
                codigoDescifrado.append((char) (((letraActual - (letraCifradoActual - 97) + 26) % 26) + 97)); // -1 porque es de 0 a 25. If (letraActual=='x' && letraCifradoActual == 'z') ? codigoCifrado.append('x');
            }
            else if (letraActual >= 65 && letraActual<= 90) {
                letraCifradoActual = cifrado.toUpperCase().charAt(i%cifrado.length());
                letraActual -= 65;
                codigoDescifrado.append((char) (((letraActual - (letraCifradoActual - 65) + 26) % 26) + 65)); // -1 porque es de 0 a 25. If (letraActual=='x' && letraCifradoActual == 'z') ? codigoCifrado.append('x');
            }
            else{
                codigoDescifrado.append(letraActual);
            }
        }
        return codigoDescifrado.toString();
    }

    public String Cifrar(Scanner sc) {
        System.out.println("Ingrese el código que quiere cifrar");
        String codigo = sc.nextLine();
        System.out.println("Ingrese el codigo con el cual desea cifrar: ");
        String cifrado = sc.nextLine();
        StringBuilder codigoCifrado = new StringBuilder();
        char letraActual;
        char letraCifradoActual;
        for (int i = 0; i < codigo.length(); i++) {
            letraActual = codigo.charAt(i);
            if (letraActual >= 97 && letraActual <= 122){
                letraCifradoActual = cifrado.toLowerCase().charAt(i%cifrado.length());
                letraActual -= 97;
                codigoCifrado.append((char) (((letraActual + (letraCifradoActual - 97)) % 26) + 97));
            }
            else if (letraActual >= 65 && letraActual <= 90) {
                letraCifradoActual = cifrado.toUpperCase().charAt(i%cifrado.length());
                letraActual -= 65;
                codigoCifrado.append((char) (((letraActual + (letraCifradoActual - 65)) % 26) + 65));
            }
            else {
                codigoCifrado.append(letraActual);
            }
        }
        return codigoCifrado.toString();
    }
}
