package gui;

import com.github.javafaker.Faker;
import config.Configuration;
import modelo.*;
import servicios.ServiciosVideoclub;

import java.util.Scanner;

public class Main {

    public static final String STRING_MENU = "¿Qué desea hacer? \n" +
            "1. Registrar un socio \n" +
            "2. Borrar un socio \n" +
            "3. Añadir un producto nuevo \n" +
            "4. Actualizar el Stock de un producto existente \n" +
            "5. Realizar un nuevo alquiler \n" +
            "6. Devolver un producto alquilado \n" +
            "7. Pagar una multa\n" +
            "8. Salir";
    public static final String ERROR_MENU = "Por favor, dime una de las opciones del menu. Vuelvo a mostrartelo.";
    public static final String MODIFICAR_STOCK = "Dime la cantidad a modificar. En caso de ser una retirada de Stock, por favor indicalo en negativo";
    public static final String NO_EXISTEN_PRODUCTOS_DE_ESTE_TIPO_TOOOORPE = "No existen productos de este tipo, toooorpe";
    public static final String DIME_LA_CANTIDAD_A_MODIFICAR_EN_CASO_DE_SER_UNA_RETIRADA_DE_STOCK_POR_FAVOR_INDICALO_EN_NEGATIVO = "Dime la cantidad a modificar. En caso de ser una retirada de Stock, por favor indicalo en negativo";
    public static final String PARA_ELIMINAR_UN_SOCIO_DIGAME_EL_DNI_DE_ESE_SOCIO_POR_FAVOR = "Para eliminar un socio digame el DNI de ese socio, por favor";
    public static final String SE_HA_ENCONTRADO_EL_SOCIO_Y_SE_HA_ELIMINADO_DE_NUESTRO_REGISTRO = "Se ha encontrado el socio y se ha eliminado de nuestro registro";
    public static final String ESTE_SOCIO_NO_SE_ENCUENTRA_EN_NUESTRO_SISTEMA = "Este socio no se encuentra en nuestro sistema";
    public static final String SOCIO_REGISTRADO = "Socio registrado";
    public static final String ESTE_SOCIO_YA_SE_ENCUENTRA_EN_NUESTRA_BASE_DE_DATOS = "Este socio ya se encuentra en nuestra base de datos";
    public static final String NO_DISPONEMOS_DE_TANTAS_UNIDADES_DE_ESTE_PRODUCTO_REVISE_LOS_DATOS_Y_VUELVA_A_REALIZAR_LA_OPERACION = "No disponemos de tantas unidades de este producto, revise los datos y vuelva a realizar la operacion";
    public static final String STOCK_ACTUALIZADO = "Stock actualizado";
    public static final String LA_CANTIDAD_AHORA_ES = "La cantidad ahora es:";
    public static final String PRODUCTO_AÑADIDO_CORRECTAMENTE = "Producto añadido correctamente";
    public static final String EL_PRODUCTO_NO_SE_HA_PODIDO_AÑADIR = "El producto no se ha podido añadir";
    public static final String ELIGE_EL_PRODUCTO_DESEADO_DE_LA_LISTA = "Elige el producto deseado de la lista";
    public static final String POR_FAVOR_ELIJA_UNA_DE_LAS_OPCIONES_DISPONIBLES = "Por favor, elija una de las opciones disponibles";
    public static final String SOLO_TENEMOS_3_TIPOS_DE_PRODUCTOS_DIME_1_2_O_3_POR_FAVOR = "Solo tenemos 3 tipos de productos, dime 1, 2 o 3, por favor.";
    public static final String GRACIAS_POR_SU_VISITA = "\n GRACIAS POR SU VISITA";
    public static final String SU_MULTA_HA_SIDO_PAGADA = "Su multa ha sido pagada";
    public static final String ESTE_USUARIO_NO_TIENE_NINGUNA_MULTA = "Este usuario no tiene ninguna multa";
    public static final String ESTE_USUARIO_NO_ESTA_REGISTRADO = "Este usuario no esta registrado";
    public static final String DIGAME_SU_DNI_PARA_PODER_PROCEDER_A_LA_DEVOLUCION = "Digame su DNI para poder proceder a la devolucion";
    public static final String PARA_PROCEDER_A_LA_DEVOLUCION_NOS_GUSTARIA_SABER_LA_PUNTUACION_DE_0_A_5_QUE_DARIA_AL_PRODUCTO = "Para proceder a la devolucion nos gustaria saber.\n La puntuacion de 0 a 5 que daria al producto";
    public static final String VOLVERIA_A_ALQUILARLO_1_SI_2_NO = "¿Volveria a alquilarlo?\n 1. Si\n 2. No";
    public static final String NO_ESTA_REGISTRADO_NO_PUEDE_TENER_NINGUN_ALQUILER_CON_NOSOTROS = "No esta registrado, no puede tener ningun alquiler con nosotros";
    public static final String HA_SIDO_SANCIONADO_YA_QUE_HA_DEVUELTO_EL_PRODUCTO_CON_RETRASO = "Ha sido sancionado ya que ha devuelto el producto con retraso";
    public static final String NO_TIENE_NINGUN_ALQUILER_CON_NOSOTROS = "No tiene ningun alquiler con nosotros";
    public static final String DEVOLUCION_REALIZADA = "Devolucion realizada";
    public static final String NO_DISPONEMOS_DE_ARTICULOS_DE_ESTE_TIPO_DISCULPE_LAS_MOLESTIAS = "No disponemos de articulos de este tipo. \n Disculpe las molestias.";
    public static final String PARA_PROCEDER_AL_ALQUILER_DEL_PRODUCTO_NECESITO_SU_DNI = "Para proceder al alquiler del producto necesito su DNI";
    public static final String PRODUCTO_ALQUILADO_CORRECTAMENTE_MUCHAS_GRACIAS = "Producto alquilado correctamente\n MUCHAS GRACIAS";
    public static final String RECUERDE_QUE_TIENE = "Recuerde que tiene ";
    public static final String SEGUNDOS_PARA_DEVOLVERLO_SIN_SER_SANCIONADO = " segundos para devolverlo sin ser sancionado.";

    public static void main(String[] args) {
        Faker f = new Faker();
        Scanner sc = new Scanner(System.in);
        ServiciosVideoclub sv = new ServiciosVideoclub();
        //menu
        int opcion = 0;
        boolean seguir = true;
        MainMethods mm = new MainMethods();
        do {
            do {
                System.out.println(STRING_MENU);
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion < 1 || opcion > 8) {
                    System.out.println(ERROR_MENU);
                }
            } while (opcion < 1 || opcion > 8);
            switch (opcion) {
                case 1:
                    //1. addSocio
                    mm.addSocio(f, sv);
                    Member member;
                    String dni;
                    break;
                case 2:
                    //2. borrarSocio
                    mm.borrarSocio(sc, sv);
                    break;
                case 3:
                    //3. addProducto
                    mm.addProducto(f, sc, sv);
                    break;
                case 4:
                    //4. AddStockProducto
                    mm.addStockProducto(sc, sv);
                    break;
                case 5:
                    //5. Alquilar
                    //encontrar producto,
                    mm.alquilar(sc, sv);
                    break;
                case 6:
                    //6. Devolver
                    mm.devolver(sc, sv);
                    break;
                case 7:
                    //7. pagarMulta
                    mm.pagarMulta(sc, sv);
                    break;
                case 8:
                    seguir = mm.salir();
                    break;
            }
        } while (seguir);
    }











}
