package uiGRAFICO;

import jakarta.inject.Inject;
import modelo.Usuarios.Usuario;
import servicios.ServiciosCompras;
import common.Constantes;

import java.util.Scanner;

public class MainClientes {

    private ServiciosCompras serviciosComprasImpl;
    private UIClienteCompras uiClienteCompras;
    private UIClienteTarjetas uiClienteTarjetas;

    @Inject
    public MainClientes(ServiciosCompras serviciosComprasImpl, UIClienteCompras uiClienteCompras, UIClienteTarjetas uiClienteTarjetas) {
        this.serviciosComprasImpl = serviciosComprasImpl;
        this.uiClienteCompras = uiClienteCompras;
        this.uiClienteTarjetas = uiClienteTarjetas;
    }

    public void inicioMenuClientes(Usuario userLogueado){
        System.out.println(Constantes.BIENVENIDO_AL_MENU_DE_LOS_CLIENTES);
        Scanner sc = new Scanner(System.in);
        int opcion;


        System.out.println(Constantes.BIENVENIDO_CLIENTE);

        do {
            do {
                System.out.println(Constantes.ELIGE_UNA_OPCIÓN +
                        Constantes.COMPRAR +
                        Constantes.VER_MIS_COMPRAS_PREVIAS +
                        Constantes.ADMINISTRAR_MIS_TARJETAS +
                        Constantes.VER_MI_GASTO_TOTAL +
                        Constantes.SALIR);
                opcion = sc.nextInt();
            } while (opcion < 1 || opcion > 5);
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1:
                    //ir a la ui de compras
                    uiClienteCompras.inicioUICompras(userLogueado);
                    break;
                case 2:
                    // ver las comprar previas
                    System.out.println(serviciosComprasImpl.getComprasPrevias(userLogueado));
                    break;
                case 3:
                    //ir a la ui de tarjetas
                    uiClienteTarjetas.inicioUITarjetas(userLogueado);
                    break;
                case 4:
                    System.out.println(Constantes.LLEVA_GASTADO_UN_TOTAL_DE_€ + serviciosComprasImpl.dineroGastadoPorCliente(userLogueado));
                    break;
                case 5:
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }
}
