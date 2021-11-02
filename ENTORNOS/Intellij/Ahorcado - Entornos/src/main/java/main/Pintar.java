package main;

public class Pintar {

    // Tuve que crear estas constantes porque sonarLint me lo sugería para no hacer un sout igual más de una vez.
    // Cuando las creo me aparece otro error que dice que le asigne letras a las variables.
    // Al final dejo 2 con un error y 2 con otro ya que no veo una solución posible.
    public static final String parteSuperiorDibujo = " |--------|";
    public static final String cuerpoVacio = " |        |";

    public static void main(String[] args) {
        //SonarLint me pidio un commentrario aqui
        System.out.println("Este sout está escrito porque el inspect code me sugiere que no quede vacio el metodo");

    }
    public void pintarAhorcado(int intRestantes){
        switch (intRestantes){
            case 0:
                System.out.println(parteSuperiorDibujo);
                System.out.println(" |      (x_x)");
                System.out.println(" |       \\|/ ");
                System.out.println(cuerpoVacio);
                System.out.println(" |       / \\ ");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            case 1:
                System.out.println(parteSuperiorDibujo);
                System.out.println(" |      (*-*)");
                System.out.println(" |       \\|/");
                System.out.println(cuerpoVacio);
                System.out.println(" |       / \\");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            case 2:
                System.out.println(parteSuperiorDibujo);
                System.out.println(" |       ( )");
                System.out.println(" |       \\|/");
                System.out.println(cuerpoVacio);
                System.out.println(" |       / \\");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            case 3:
                System.out.println(parteSuperiorDibujo);
                System.out.println(" |       ( )");
                System.out.println(" |       \\|/");
                System.out.println(cuerpoVacio);
                System.out.println(" |       /");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            case 4:
                System.out.println(parteSuperiorDibujo);
                System.out.println(" |       ( )");
                System.out.println(" |       \\|/");
                System.out.println(cuerpoVacio);
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            case 5:
                System.out.println(parteSuperiorDibujo);
                System.out.println(" |       ( )");
                System.out.println(" |       \\|");
                System.out.println(cuerpoVacio);
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            case 6:
                System.out.println(parteSuperiorDibujo);
                System.out.println(" |       ( )");
                System.out.println(cuerpoVacio);
                System.out.println(cuerpoVacio);
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            case 7:
                System.out.println(parteSuperiorDibujo);
                System.out.println(" |       ( )");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            case 8:
                System.out.println(parteSuperiorDibujo);
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            default:
                break;
        }
    }
}
