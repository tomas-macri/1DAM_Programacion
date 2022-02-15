package ui;

import modelo.Producto;
import modelo.ProductoComprado;
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
        ServiciosCompras serviciosCompras = new ServiciosCompras();
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
<<<<<<< HEAD
                    eliminarProductoDeLaCompra(userLogueado, sc, serviciosCompras, serviciosProductos);
                    // eliminar prod de la compra
                    System.out.println("Ingrese el nombre del producto que desea eliminar de la lista de compras");

                    String nombProd = sc.nextLine();
                    Producto prodAComprar = serviciosProductos.getProducto(nombProd);
                    serviciosCompras.eliminarDeLaCompra(prodAComprar, userLogueado);
                    break;

                case 3:
                    // mostrar productos
                    System.out.println(serviciosCompras.getListaCompra(userLogueado));
                    System.out.println();
                    break;
                case 4:
                    //pagar
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

<<<<<<< HEAD
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

=======
>>>>>>> 8344a7053992cd746b92e02f7d34e5c2c816c551
    private void agregarProdACompra(Usuario userLogueado, Scanner sc, ServiciosCompras serviciosCompras, ServiciosProductos serviciosProductos) {
        Producto prodAComprar;
        String nombProd;
        do {
<<<<<<< HEAD
            System.out.println(Constantes.INGRESE_EL_NOMBRE_DEL_PRODUCTO_QUE_QUIERE_AGREGAR_A_LA_COMPRA_O_FIN_PARA_TERMINAR);
=======
            System.out.println("Ingrese el nombre del producto que quiere agregar a la compra o fin para terminar: ");
>>>>>>> 8344a7053992cd746b92e02f7d34e5c2c816c551
            nombProd = sc.nextLine();
            prodAComprar = serviciosProductos.getProducto(nombProd);

            if (prodAComprar != null && !nombProd.equalsIgnoreCase("fin")) {
                int stockAComprar;
<<<<<<< HEAD
                System.out.println(Constantes.INGRESE_LA_CANTIDAD_A_COMPRAR_DE + prodAComprar);
=======
                System.out.println("Ingrese la cantidad a comprar de " + prodAComprar);
>>>>>>> 8344a7053992cd746b92e02f7d34e5c2c816c551
                stockAComprar = sc.nextInt();
                sc.nextLine();

                if (serviciosCompras.hayStock(stockAComprar, prodAComprar)) {
<<<<<<< HEAD
                    System.out.println(Constantes.SE_AGREGARON + stockAComprar + Constantes.DE + prodAComprar + Constantes.A_LA_LISTA_DE_COMPRAS);
=======
                    System.out.println("Se agregaron " + stockAComprar + " de " + prodAComprar + " a la lista de compras");
>>>>>>> 8344a7053992cd746b92e02f7d34e5c2c816c551
                    System.out.println();

                    ProductoComprado productoComprado = new ProductoComprado(prodAComprar, stockAComprar);
                    serviciosCompras.agregarALaCompra(productoComprado, userLogueado);
                } else {
<<<<<<< HEAD
                    System.out.println(Constantes.EL_STOCK_QUE_INGRESÓ_ES_MENOR_QUE_1_O_ES_MAYOR_AL_STOCK_QUE_NOS_QUEDA_DEL_PRODUCTO + prodAComprar);
=======
                    System.out.println("El stock que ingresó es menor que 1 o es mayor al stock que nos queda del producto " + prodAComprar);
>>>>>>> 8344a7053992cd746b92e02f7d34e5c2c816c551
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
