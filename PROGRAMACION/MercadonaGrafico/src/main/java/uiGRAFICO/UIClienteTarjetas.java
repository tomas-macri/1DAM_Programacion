package uiGRAFICO;

import jakarta.inject.Inject;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;
import servicios.ServiciosTarjetas;
import common.Constantes;

import java.util.Scanner;

public class UIClienteTarjetas {

    private ServiciosTarjetas serviciosTarjetasImpl;

    @Inject
    public UIClienteTarjetas(ServiciosTarjetas serviciosTarjetasImpl){
        this.serviciosTarjetasImpl = serviciosTarjetasImpl;
    }

    public void inicioUITarjetas(Usuario usuarioLogueado){
        System.out.println(Constantes.BIENVENIDO_AL_MENU_DE_LAS_TARJETAS);
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            do {
                System.out.println(Constantes.MENU_TARJETA);
                opcion = sc.nextInt();
            } while (opcion < 1 || opcion > 5);
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar tarjeta
                    agregarTarjeta(sc, usuarioLogueado);
                    break;
                case 2:
                case 3:
                    //eliminar tarjeta
                    //modificar tarjeta
                    estamosTrabajando();
                    break;
                case 4:
                    // ver tarjetas
                    System.out.println(serviciosTarjetasImpl.devolverLista(usuarioLogueado));
                    System.out.println();
                    break;
                case 5:
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }

    private void estamosTrabajando() {
        System.out.println(Constantes.ESTAMOS_TRABAJANDO_INTENTE_MAS_TARDE);
        System.out.println();
    }


    private void agregarTarjeta(Scanner sc, Usuario userLogueado) {
        System.out.println(Constantes.AGREGAR_TARJETA);
        System.out.println();
        String nombTarjeta;
        int saldoTarjeta;
        int opcion;
        do {
            System.out.println(Constantes.INGRESE_EL_NOMBRE_DE_LA_NUEVA_TARJETA);
            nombTarjeta= sc.nextLine();
            System.out.println();

            System.out.println(Constantes.INGRESE_EL_SALDO_DE_LA_NUEVA_TARJETA);
            saldoTarjeta = sc.nextInt();
            sc.nextLine();
            System.out.println();
            Tarjeta unaTarjeta = new Tarjeta(nombTarjeta, saldoTarjeta);

            if (serviciosTarjetasImpl.agregarTarjeta(unaTarjeta, userLogueado)) {
                System.out.println(Constantes.SE_AGREGO + unaTarjeta + Constantes.A_TU_LISTA_DE_TARJETAS);
            } else {
                System.out.println(Constantes.LA_TARJETA_YA_SE_ENCONTRABA_EN_TU_LISTA_DE_TARJETAS_O_ALGUNOS_DE_LOS_CAMPOS_INGRESADOS_NO_ES_VÁLIDO);
            }
            System.out.println(Constantes.INGRESE_1_SI_QUIERE_AGREGAR_OTRA_TARJETA_O_CUALQUIER_OTRO_NÚMERO_PARA_SALIR);
            opcion = sc.nextInt();
            sc.nextLine();

        } while (opcion == 1);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_AGREGAR_TARJETA);
        System.out.println();
    }
}
