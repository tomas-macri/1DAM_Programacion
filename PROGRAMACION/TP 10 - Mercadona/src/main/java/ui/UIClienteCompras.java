package ui;

import dao.DaoTarjetas;
import modelo.Producto;
import modelo.ProductoComprado;
import modelo.Tarjeta;
import modelo.Usuario;
import servicios.ServiciosCompras;
import servicios.ServiciosProductos;
import ui.common.Constantes;

import java.util.Scanner;

public class UIClienteCompras {

    public void inicioUICompras(Usuario userLogueado) {
        Scanner sc = new Scanner(System.in);
        UIClienteCompras uiClienteCompras = new UIClienteCompras();
        int opcion;
        System.out.println(Constantes.BIENVENIDO_AL_MENU_DE_COMPRAS);
        System.out.println();
        ServiciosCompras serviciosCompras = new ServiciosCompras(userLogueado);
        ServiciosProductos serviciosProductos = new ServiciosProductos();
        do {
            opcion = uiClienteCompras.mostrarMenu(sc);
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar productos a la compra
                    agregarProdACompra(userLogueado, sc, serviciosCompras, serviciosProductos);
                    break;
                case 2:
                    eliminarProductoDeLaCompra(userLogueado, sc, serviciosCompras, serviciosProductos);
                    break;

                case 3:
                    // mostrar productos
                    System.out.println(serviciosCompras.getListaCompra(userLogueado));
                    System.out.println();
                    break;
                case 4:
                    //pagar
                    pagar(userLogueado, sc, serviciosCompras);
                    break;
                case 5:
                    // salir
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }

    private void pagar(Usuario userLogueado, Scanner sc, ServiciosCompras serviciosCompras) {
        String nombTarjeta;

        System.out.println(Constantes.INGRESE_EL_NOMBRE_DE_LA_TARJETA_CON_LA_QUE_DESEA_PAGAR);
        nombTarjeta= sc.nextLine();
        DaoTarjetas daoTarjetas = new DaoTarjetas();
        Tarjeta tarjetaParaPagar = daoTarjetas.getTarjeta(nombTarjeta, userLogueado);
        if (serviciosCompras.pagar(tarjetaParaPagar, userLogueado)){
            System.out.println(Constantes.LA_COMPRA_SE_REALIZO_CON_EXITO);
        }
        else {
            System.out.println(Constantes.HUBO_UN_PROBLEMA_A_LA_HORA_DE_REALIZAR_LA_COMPRA);
        }
    }

    private void eliminarProductoDeLaCompra(Usuario userLogueado, Scanner sc, ServiciosCompras serviciosCompras, ServiciosProductos serviciosProductos) {
        // eliminar prod de la compra
        System.out.println(Constantes.INGRESE_EL_NOMBRE_DEL_PRODUCTO_QUE_DESEA_ELIMINAR_DE_LA_LISTA_DE_COMPRAS);

        String nombProd = sc.nextLine();
        Producto prodAComprar = serviciosProductos.getProducto(nombProd);
        if (serviciosCompras.eliminarDeLaCompra(prodAComprar, userLogueado)){
            System.out.println(Constantes.EL_PRODUCTO_SE_HA_ELIMINADO_CORRECTAMENTE_DE_LA_LISTA_DE_COMPRAS);
        }
        else {
            System.out.println(Constantes.EL_PRODUTO_QUE_DESEA_ELIMINAR_NO_ESTABA_EN_LA_LISTA_DE_COMPRAS);
        }
    }

    private void agregarProdACompra(Usuario userLogueado, Scanner sc, ServiciosCompras serviciosCompras, ServiciosProductos serviciosProductos) {
        Producto prodAComprar;
        String nombProd;
        do {
            System.out.println(Constantes.INGRESE_EL_NOMBRE_DEL_PRODUCTO_QUE_QUIERE_AGREGAR_A_LA_COMPRA_O_FIN_PARA_TERMINAR);
            nombProd = sc.nextLine();
            prodAComprar = serviciosProductos.getProducto(nombProd);

            if (prodAComprar != null && !nombProd.equalsIgnoreCase("fin")) {
                int stockAComprar;
                System.out.println(Constantes.INGRESE_LA_CANTIDAD_A_COMPRAR_DE + prodAComprar);
                stockAComprar = sc.nextInt();
                sc.nextLine();

                if (serviciosCompras.hayStock(stockAComprar, prodAComprar)) {
                    System.out.println(Constantes.SE_AGREGARON + stockAComprar + Constantes.DE + prodAComprar + Constantes.A_LA_LISTA_DE_COMPRAS);
                    System.out.println();

                    ProductoComprado productoComprado = new ProductoComprado(prodAComprar, stockAComprar);
                    serviciosCompras.agregarALaCompra(productoComprado, userLogueado);
                } else {
                    System.out.println(Constantes.EL_STOCK_QUE_INGRESÓ_ES_MENOR_QUE_1_O_ES_MAYOR_AL_STOCK_QUE_NOS_QUEDA_DEL_PRODUCTO + prodAComprar);
                    System.out.println();
                }
            }
        } while (!nombProd.equalsIgnoreCase("fin"));
    }


    private int mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println(Constantes.MENU_COMPRAS);

            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }
}
