package main;

public class Ejercicio10 {


    public static void main(String[] args) {

    }

    public void ejercicio10() {

//        Ejercicio 10
//        Algoritmo que muestre la tabla de multiplicar de los números 1,2,3,4 y 5.

        for (int i = 1; i <= 5; i++){
            System.out.println("LA TABLA DEL " + i);
            for (int j = 1; j<=10; j++){
                System.out.println(i + "x" + j + "= " + i*j);
            }
        }
    }
}
