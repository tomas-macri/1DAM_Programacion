package servicios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class erroresTest {

    @Test
    @DisplayName("OPERADOR NO VALIDO CORRECTO")
    void OperadorNoValido() {
        int num1 = 3;
        int num2 = 5;
        char op = 'x';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);

        assertEquals(-1, resultado);
    }


    @Test
    @DisplayName("NUM 1 NEGATIVO VALIDO")
    void num1NoValido() {
        int num1 = -3;
        int num2 = 5;
        char op = '+';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);

        assertEquals(-1, resultado);
    }

    @Test
    @DisplayName("NUM 2 NEGATIVO VALIDO")
    void num2Negativo() {
        int num1 = 3;
        int num2 = -5;
        char op = '+';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);

        assertEquals(-1, resultado);
    }


    @Test
    @DisplayName("RESTA NO VALIDA CORRECTA")
    void restaNoValida() {
        int num1 = 4;
        int num2 = 8;
        char op = '-';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);

        assertEquals(-1, resultado);
    }

    @Test
    @DisplayName("DIVISION POR 0 CORRECTA")
    void divisionNoValida() {
        int num1 = 8;
        int num2 = 0;
        char op = '/';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);

        assertEquals(-1, resultado);
    }
}
