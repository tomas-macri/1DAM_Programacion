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
        System.out.println("BIENVENIDO AL MENU DE COMPRAS");
        System.out.println();
        ServiciosCompras serviciosCompras = new ServiciosCompras();
        ServiciosProductos serviciosProductos = new ServiciosProductos();
        do {
            opcion = uiClienteCompras.mostrarMenu(sc);
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar productos a la compra


                    Producto prodAComprar;
                    String nombProd;
                    do {
                        System.out.println("Ingrese el nombre del producto que quiere agregar a la compra o fin para terminar: ");
                        nombProd = sc.nextLine();
                        prodAComprar = serviciosProductos.getProducto(nombProd);
                    } while (prodAComprar == null || !nombProd.equalsIgnoreCase("fin"));

                    if (!nombProd.equalsIgnoreCase("fin")){
                        int stockAComprar;
                        do {
                            System.out.println("Ingrese la cantidad a comprar de " + prodAComprar);
                            stockAComprar = sc.nextInt();
                            sc.nextLine();
                        }while (serviciosCompras.hayStock(stockAComprar, prodAComprar));

                        ProductoComprado productoComprado = new ProductoComprado(prodAComprar, stockAComprar);
                        serviciosCompras.agregarALaCompra(productoComprado, userLogueado);

                    }


                    break;
                case 2:
                    // eliminar prod de la compra
                    break;
                case 3:
                    // mostrar productos
                    // System.out.println(serviciosProductos.getLista());

                    // System.out.println();
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


    private int mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("Elija una opcion: \n" +
                    "1 - Comprar Productos \n" +
                    "2 - Eliminar Productos de la Compra \n" +
                    "3 - Ver todos los Productos \n" +
                    "4 - Pagar \n" +
                    "5 - Salir ");

            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }
}
