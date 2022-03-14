package servicios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class serviciosOperacionesValidas {

    @Test
    @DisplayName("SUMA VALIDA CORRECTA")
    void sumaValida() {
        int num1 = 3;
        int num2 = 5;
        char op = '+';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);

        assertEquals(8, resultado);
    }


    @Test
    @DisplayName("RESTA VALIDA CORRECTA")
    void restaValida() {
        int num1 = 8;
        int num2 = 4;
        char op = '-';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);

        assertEquals(4, resultado);
    }

    @Test
    @DisplayName("MULTIPLICACIÓN VALIDA CORRECTA")
    void multiplicacionValida() {
        int num1 = 8;
        int num2 = 6;
        char op = '*';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);

        assertEquals(48, resultado);
    }

    @Test
    @DisplayName("DIVISION VALIDA CORRECTA")
    void DivisionValida() {
        int num1 = 4;
        int num2 = 2;
        char op = '/';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);

        assertEquals(2, resultado);
    }


}
