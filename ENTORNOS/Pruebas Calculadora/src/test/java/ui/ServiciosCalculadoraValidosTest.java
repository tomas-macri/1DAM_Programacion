package ui;

import org.junit.jupiter.api.Test;
import servicios.ServiciosCalculadora;
import static org.junit.jupiter.api.Assertions.*;

public class ServiciosCalculadoraTest {

    @Test
    void pruebaSumarValida(){
        int num1 = 2;
        int num2 = 4;
        char op = '+';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);
        assertEquals(6, resultado);
    }

    @Test
    void pruebaRestarValida(){
        int num1 = 3;
        int num2 = 1;
        char op = '-';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);
        assertEquals(2, resultado);
    }

    @Test
    void pruebaMultiplicarValida(){
        int num1 = 8;
        int num2 = 6;
        char op = '*';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);
        assertEquals(48, resultado);
    }

    @Test
    void pruebaDividirValida(){
        int num1 = 9;
        int num2 = 3;
        char op = '/';

        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int resultado = serviciosCalculadora.hacerOperacion(num1, num2, op);
        assertEquals(3, resultado);
    }

}
