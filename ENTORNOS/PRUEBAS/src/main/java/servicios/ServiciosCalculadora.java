package servicios;

public class ServiciosCalculadora {

    public boolean numeroValido(int num) {
        return num >= 0;
    }

    public boolean operacionValida(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/';
    }

    public int hacerOperacion(int num1, int num2, char op){
        int resultado = -1;
        switch (op){
            case '+':
                resultado = num1 + num2;
                break;
            case '-':
                resultado = num1 - num2;
                break;
            case '*':
                resultado = num1 * num2;
                break;
            case '/':
                resultado = num1 / num2;
                break;
            default:
                break;
        }
        return resultado;
    }


}
