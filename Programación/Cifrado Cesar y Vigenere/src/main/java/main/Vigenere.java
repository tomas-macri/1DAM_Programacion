package main;

import java.util.Scanner;

public class Vigenere {
    public static void main(String[] args) {

    }

    public void Descifrar() {
    }

    public String Cifrar(Scanner sc) {
        System.out.println("Ingrese el código que quiere descifrar");
        String codigo = sc.nextLine();
        System.out.println("Ingrese el codigo con el cual desea cifrar: ");
        String cifrado = sc.nextLine();
        StringBuilder codigoCifrado = new StringBuilder();
        char letraActual;
        char letraCifradoActual;
        // Errores con el codigo del  cifrado
        for (int i = 0; i < codigo.length(); i++) {
            letraActual = codigo.charAt(i);
            letraCifradoActual = cifrado.charAt(i);
            if (letraActual > 90){
                letraActual -= 97;
                letraCifradoActual = cifrado.charAt(i);
                codigoCifrado.append((char) (((letraActual + letraCifradoActual ) % 26) + 97));
            }
            else {
                letraActual -= 65;
                codigoCifrado.append((char) (((letraActual + letraCifradoActual ) % 26) + 65));
            }
        }
        return codigoCifrado.toString();
    }
}
