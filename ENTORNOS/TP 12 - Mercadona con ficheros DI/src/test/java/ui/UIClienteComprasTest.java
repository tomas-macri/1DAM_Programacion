package ui;

import jakarta.enterprise.inject.Produces;
import modelo.Productos.ProductoCaducable;
import modelo.Productos.ProductoNormal;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import servicios.ServiciosCompras;
import servicios.ServiciosProductos;
import servicios.ServiciosTarjetas;
import servicios.impl.ServiciosComprasImpl;
import servicios.impl.ServiciosProductosImpl;
import servicios.impl.ServiciosTarjetasImpl;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.stream.SystemOut;
import uk.org.webcompere.systemstubs.stream.input.LinesAltStream;

import java.util.ArrayList;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SystemStubsExtension.class)
public class UIClienteComprasTest {

    UIClienteCompras uiClienteCompras;

    @Mock
    private ServiciosTarjetas serviciosTarjetasImpl;
    @Mock
    private ServiciosCompras serviciosComprasImpl;
    @Mock
    private ServiciosProductos serviciosProductosImpl;

    private Scanner sc;

    @SystemStub
    private SystemOut systemOut;

    @Test
    @DisplayName("PAGAR VALIDO")
    void pagarValido(){

        //given
        sc = new Scanner(new LinesAltStream("1","p2", "5", "fin", "0"));


        uiClienteCompras = new UIClienteCompras(serviciosTarjetasImpl, serviciosComprasImpl, serviciosProductosImpl,sc);

        Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());

        //when
        uiClienteCompras.inicioUICompras(userLogueado);


        //then
        verify(serviciosProductosImpl).agregarProducto(any());
    }
}
