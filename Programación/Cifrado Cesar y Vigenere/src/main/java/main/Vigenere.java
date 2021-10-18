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
            letraCifradoActual = cifrado.charAt(i);
            if (letraActual > 90){
                letraActual -= 97;
                letraCifradoActual = cifrado.charAt(i);
                codigoDescifrado.append((char) ((((letraActual - (letraCifradoActual - 97) - 1) + 26) % 26) + 97)); // -1 porque es de 0 a 25. If (letraActual=='x' && letraCifradoActual == 'z') ? codigoCifrado.append('x');
            }
            else {
                letraActual -= 65;
                codigoDescifrado.append((char) ((((letraActual - (letraCifradoActual - 65) - 1) % 26) + 65))); // -1 porque es de 0 a 25. If (letraActual=='x' && letraCifradoActual == 'z') ? codigoCifrado.append('x');
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
        // Errores con el codigo del  cifrado
        for (int i = 0; i < codigo.length(); i++) {
            letraActual = codigo.charAt(i);
            letraCifradoActual = cifrado.charAt(i);
            if (letraActual > 90){
                letraActual -= 97;
                letraCifradoActual = cifrado.charAt(i);
                codigoCifrado.append((char) ((((letraActual + (letraCifradoActual  - 97) + 1) % 26) + 97))); // +1 porque es de 0 a 25. If (letraActual=='z' && letraCifradoActual == 'c') ? codigoCifrado.append('b');
            }
            else {
                letraActual -= 65;
                codigoCifrado.append((char) ((((letraActual + (letraCifradoActual - 65) + 1) % 26) + 65))); // +1 porque es de 0 a 25. If (letraActual=='z' && letraCifradoActual == 'c') ? codigoCifrado.append('b');
            }
        }
        return codigoCifrado.toString();
    }
}
