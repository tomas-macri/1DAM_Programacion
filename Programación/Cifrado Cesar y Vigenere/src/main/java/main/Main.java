package main;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qué método desea utilizar? Ingrese 'c' para Cesar o 'v' para Vigenere");
        StringBuilder metodoCifrado = new StringBuilder();
        metodoCifrado.append(sc.nextLine().charAt(0));

        while (metodoCifrado.toString().equalsIgnoreCase("C") || metodoCifrado.toString().equalsIgnoreCase("V")){
            System.out.println("Quiere cifrar o descifrar? Ingrese c o d: ");
            metodoCifrado.append(sc.nextLine().toUpperCase().charAt(0));
            String resultado = "";
            switch (metodoCifrado.toString().toUpperCase()){
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
                    resultado = vigenere.Descifrar(sc);
                    break;
                case "VC":
                    vigenere = new Vigenere();
                    resultado = vigenere.Cifrar(sc);
                    break;
                default:
                    break;
            }
            System.out.println("Su mensaje final es: " + resultado);

            metodoCifrado.delete(0,metodoCifrado.length());
            System.out.println("Qué método desea utilizar? Ingrese 'c' para Cesar o 'v' para Vigenere");
            metodoCifrado.append(sc.nextLine().toUpperCase().charAt(0));
        }
    }
}
