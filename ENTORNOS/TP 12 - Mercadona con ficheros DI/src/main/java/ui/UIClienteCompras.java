package ui;

import dao.DaoTarjetas;
import jakarta.inject.Inject;
import modelo.Productos.Producto;
import modelo.ProductoComprado;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import servicios.ServiciosCompras;
import servicios.ServiciosProductos;
import servicios.ServiciosTarjetas;
import ui.common.Constantes;

import java.util.ArrayList;
import java.util.Scanner;

public class UIClienteCompras {

    private ServiciosTarjetas serviciosTarjetasImpl;
    private ServiciosCompras serviciosComprasImpl;
    private ServiciosProductos serviciosProductosImpl;
    private Scanner sc;


    @Inject
    public UIClienteCompras(ServiciosTarjetas serviciosTarjetasImpl, ServiciosCompras serviciosComprasImpl, ServiciosProductos serviciosProductosImpl, Scanner sc){
        this.serviciosTarjetasImpl = serviciosTarjetasImpl;
        this.serviciosComprasImpl = serviciosComprasImpl;
        this.serviciosProductosImpl = serviciosProductosImpl;
        this.sc = sc;
    }


    public void inicioUICompras(Usuario userLogueado) {
        //Usuario userLogueado = new UsuarioNormal("u1", "user1", new ArrayList<>());
        int opcion;
        System.out.println(Constantes.BIENVENIDO_AL_MENU_DE_COMPRAS);
        System.out.println();
        do {
            opcion = mostrarMenu(sc);
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar productos a la compra
                    userLogueado = agregarProdACompra(userLogueado, sc, serviciosComprasImpl, serviciosProductosImpl);
                    break;
                case 2:
                    eliminarProductoDeLaCompra(userLogueado, sc, serviciosComprasImpl, serviciosProductosImpl);
                    break;

                case 3:
                    // mostrar productos
                    System.out.println(serviciosComprasImpl.getListaCompra(userLogueado));
                    System.out.println();
                    break;
                case 4:
                    //pagar
                    pagar(userLogueado, sc, serviciosComprasImpl);
                    break;
                case 5:
                    System.out.println(serviciosComprasImpl.getProductosSinAlergia(userLogueado, serviciosProductosImpl.getLista()));
                    System.out.println();
                    break;
                case 6:
                    // salir
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 6);
    }

    private void pagar(Usuario userLogueado, Scanner sc, ServiciosCompras serviciosComprasImpl) {
        String nombTarjeta;

        System.out.println(Constantes.INGRESE_EL_NOMBRE_DE_LA_TARJETA_CON_LA_QUE_DESEA_PAGAR);
        nombTarjeta= sc.nextLine();
        Tarjeta tarjetaParaPagar = serviciosTarjetasImpl.getTarjeta(nombTarjeta, userLogueado);
        if (serviciosComprasImpl.pagar(tarjetaParaPagar, userLogueado)){
            System.out.println(Constantes.LA_COMPRA_SE_REALIZO_CON_EXITO);
        }
        else {
            System.out.println(Constantes.HUBO_UN_PROBLEMA_A_LA_HORA_DE_REALIZAR_LA_COMPRA);
        }
    }

    private void eliminarProductoDeLaCompra(Usuario userLogueado, Scanner sc, ServiciosCompras serviciosComprasImpl, ServiciosProductos serviciosProductosImpl) {
        // eliminar prod de la compra
        System.out.println(Constantes.INGRESE_EL_NOMBRE_DEL_PRODUCTO_QUE_DESEA_ELIMINAR_DE_LA_LISTA_DE_COMPRAS);

        String nombProd = sc.nextLine();
        Producto prodAComprar = serviciosProductosImpl.getProducto(nombProd);
        if (serviciosComprasImpl.eliminarDeLaCompra(prodAComprar, userLogueado)){
            System.out.println(Constantes.EL_PRODUCTO_SE_HA_ELIMINADO_CORRECTAMENTE_DE_LA_LISTA_DE_COMPRAS);
        }
        else {
            System.out.println(Constantes.EL_PRODUTO_QUE_DESEA_ELIMINAR_NO_ESTABA_EN_LA_LISTA_DE_COMPRAS);
        }
    }

    private Usuario agregarProdACompra(Usuario userLogueado, Scanner sc, ServiciosCompras serviciosComprasImpl, ServiciosProductos serviciosProductosImpl) {
        Producto prodAComprar;
        String nombProd;
        do {
            System.out.println(Constantes.INGRESE_EL_NOMBRE_DEL_PRODUCTO_QUE_QUIERE_AGREGAR_A_LA_COMPRA_O_FIN_PARA_TERMINAR);
            nombProd = sc.nextLine();
            prodAComprar = serviciosProductosImpl.getProducto(nombProd);

            if (prodAComprar != null && !nombProd.equalsIgnoreCase(Constantes.FIN)) {
                int stockAComprar;
                System.out.println(Constantes.INGRESE_LA_CANTIDAD_A_COMPRAR_DE + prodAComprar);
                stockAComprar = sc.nextInt();
                sc.nextLine();

                if (serviciosComprasImpl.hayStock(stockAComprar, prodAComprar)) {
                    System.out.println(Constantes.SE_AGREGARON + stockAComprar + Constantes.DE + prodAComprar + Constantes.A_LA_LISTA_DE_COMPRAS);
                    System.out.println();

                    ProductoComprado productoComprado = new ProductoComprado(prodAComprar, stockAComprar);
                    userLogueado = serviciosComprasImpl.agregarALaCompra(productoComprado, userLogueado);
                } else {
                    System.out.println(Constantes.EL_STOCK_QUE_INGRESÓ_ES_MENOR_QUE_1_O_ES_MAYOR_AL_STOCK_QUE_NOS_QUEDA_DEL_PRODUCTO + prodAComprar);
                    System.out.println();
                }
            }
        } while (!nombProd.equalsIgnoreCase(Constantes.FIN));
        return userLogueado;
    }


    private int mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println(Constantes.MENU_COMPRAS);

            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 6);
        return opcion;
    }
}
