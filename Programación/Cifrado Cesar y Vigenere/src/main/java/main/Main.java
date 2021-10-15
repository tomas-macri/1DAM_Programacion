package main;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qué método desea utilizar? Ingrese 'c' para Cesar o 'v' para Vigenere");
        StringBuilder metodoCifrado = new StringBuilder();
        metodoCifrado.append(sc.nextLine().toUpperCase().charAt(0));

        while (!metodoCifrado.toString().equals("C") && !metodoCifrado.toString().equals("V")){
            System.out.println("Quiere cifrar o descifrar? Ingrese c o d: ");
            metodoCifrado.append(sc.nextLine().toUpperCase().charAt(0));
            String resultado = "";
            switch (metodoCifrado.toString()){
                case "CD":
                    Cesar cesar = new Cesar();
                    resultado = cesar.Descifrar(sc);
                    break;
                case "CC":
                    cesar = new Cesar();
                    resultado = cesar.Cifrar(sc);
                    break;
                case "VD":
                    Vigenere vigenere = new Vigenere();
                    vigenere.Descifrar();
                    break;
                case "VC":
                    vigenere = new Vigenere();
                    resultado = vigenere.Cifrar(sc);
                    break;
                default:
                    break;
            }
            System.out.println(resultado);




















            metodoCifrado.delete(0,metodoCifrado.length());
            System.out.println("Qué método desea utilizar? Ingrese 'c' para Cesar o 'v' para Vigenere");
            metodoCifrado.append(sc.nextLine().toUpperCase().charAt(0));
        }


    }
}
