package main;

public class Ejercicio15 {
    public static void main(String[] args) {

    }
    public void ejercicio15() {
//        Ejercicio 15
//        Una persona adquirió un producto para pagar en 20 meses. El primer mes pagó 10 €, el
//        segundo 20 €, el tercero 40 € y así sucesivamente. Realizar un algoritmo para determinar
//        cuánto debe pagar mensualmente y el total de
//        lo que pagó después de los 20 meses.
        int acumPagado = 0;
        int formulaPago;
        for (int i = 0; i <= 19; i++){
            formulaPago = 10 * ((int)Math.pow(2, i));
            System.out.println("Este mes ("+(i+1)+") debe pagar un total de " + formulaPago);
            acumPagado += formulaPago;
        }
        System.out.println("Al cabo de los 20 meses usted ha pagado " + acumPagado + "€");


    }
}
