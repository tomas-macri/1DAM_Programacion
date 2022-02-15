package ui;

import ui.common.Constantes;
import servicios.ServiciosProductos;
import modelo.Producto;

import java.util.Scanner;

public class UIAdminProductos {

    public void inicioUIProductos() {
        ServiciosProductos serviciosProductos = new ServiciosProductos();
        Scanner sc = new Scanner(System.in);
        UIAdminProductos uiAdminProductos = new UIAdminProductos();
        int opcion;
        System.out.println(Constantes.BIENVENIDO_ADMINISTRADOR);
        System.out.println();

        do {
            opcion = uiAdminProductos.mostrarMenu(sc);
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar productos
                    uiAdminProductos.agregarProducto(sc);
                    break;
                case 2:
                    // modificar prod
                    uiAdminProductos.modificarProducto(sc);
                    break;
                case 3:
                    // eliminar prod
                    uiAdminProductos.eliminarProducto(sc);
                    break;
                case 4:
                    // mostrar productos
                    System.out.println(serviciosProductos.getLista());

                    System.out.println();
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
            System.out.println(Constantes.MENU_ADMINISTRADOR_PRODUCTOS);

            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }

    private void agregarProducto(Scanner sc) {
        ServiciosProductos dao = new ServiciosProductos();
        System.out.println(Constantes.AGREGAR_PRODUCTOS);
        System.out.println();
        String nomProd;
        int stockProd;
        double precioProd;
        int opcion;
        do {
            System.out.println(Constantes.INGRESE_NOMBRE_DEL_PRODUCTO);
            nomProd = sc.nextLine();
            System.out.println();

            System.out.println(Constantes.INGRESE_PRECIO_DEL_PRODUCTO);
            precioProd = sc.nextDouble();
            System.out.println();

            System.out.println(Constantes.INGRESE_STOCK_DEL_PRODUCTO);
            stockProd = sc.nextInt();
            sc.nextLine();
            System.out.println();


            Producto unProd = new Producto(nomProd, precioProd, stockProd);

            if (dao.agregarProducto(unProd)) {
                System.out.println(Constantes.SE_AGREGO + unProd + Constantes.A_LA_LISTA_DE_PRODUCTOS_DISPONIBLES);
            } else {
                System.out.println(Constantes.EL_PRODUCTO_YA_SE_ENCONTRABA_EN_LA_LISTA_DE_PRODUCTOS_O_ALGUNO_DE_SUS_CAMPOS_NO_ERAN_VALIDOS_INTENTE_NUEVAMENTE);
            }

            System.out.println();
            System.out.println(Constantes.INGRESE_1_SI_QUIERE_AGREGAR_OTRO_PRODUCTO_O_CUALQUIER_OTRO_NUMERO_SI_QUIERE_SALIR);
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion == 1);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_AGREGAR_PRODUCTO);
        System.out.println();
    }

    private void eliminarProducto(Scanner sc) {
        ServiciosProductos dao = new ServiciosProductos();
        String nomProducto;
        System.out.println(Constantes.ELIMINAR_PRODUCTOS);
        System.out.println();
        int opcion;
        do {
            System.out.println(Constantes.INGRESE_NOMBRE_DEL_PRODUCTO);
            nomProducto = sc.nextLine();

            if (dao.eliminarProducto(nomProducto)) {
                System.out.println("Se elimino " + nomProducto + Constantes.DE_LA_LISTA_DE_PRODUCTOS);
            } else {
                System.out.println(Constantes.EL_PRODUCTO + nomProducto + Constantes.NO_SE_ENCONTRABA_EN_LA_LISTA_DE_PRODUCTOS_POR_LO_QUE_NO_SE_ELIMINO);
            }
            System.out.println();
            System.out.println(Constantes.INGRESE_1_SI_QUIERE_ELIMINAR_OTRO_PRODUCTO_O_CUALQUIER_OTRO_NUMERO_SI_QUIERE_SALIR);
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion == 1);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_ELIMINAR_PRODUCTO);
        System.out.println();
    }

    private void modificarProducto(Scanner sc) {
        int opcionModi;
        do {
            System.out.println(Constantes.MENU_ADMINISTRADOR_MODIFICAR_PRODUCTOS);

            opcionModi = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switchModificar(sc, opcionModi);


        } while (opcionModi != 5);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_MODIFICAR_PRODUCTO);
        System.out.println();
    }

    private void switchModificar(Scanner sc, int opcionModi) {
        String nomProdMod;
        if (opcionModi != 5) {
            System.out.println(Constantes.INGRESE_EL_NOMBRE_DEL_PRODUCTO_A_MODIFICAR_RECUERDE_QUE_TIENE_QUE_ESTAR_PREVIAMENTE_CARGADO_EN_LA_LISTA_DE_PRODUCTOS_PARA_VOLVER_INGRESE_0);
            nomProdMod = sc.nextLine();
            //las variables precio y stock no son necesarias a la hora de buscar un producto, por lo que no se le piden al usuario
            if (!nomProdMod.equals("0")) {
                switch (opcionModi) {
                    case 1:
                        // pedir nuevo nombre
                        modificarProductoEntero(sc, nomProdMod);
                        break;
                    case 2:
                        // pedir nuevo nombre
                        modificarNombreProducto(sc, nomProdMod);
                        // cambiar solo nombre
                        break;
                    case 3:
                        // pedir nuevo precio
                        modificarPrecioProducto(sc, nomProdMod);
                        break;
                    case 4:
                        // pedir stock
                        modificarStockProducto(sc, nomProdMod);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void modificarStockProducto(Scanner sc, String nomProdMod) {
        ServiciosProductos serviciosProductos = new ServiciosProductos();
        int nuevoStockProd;
        System.out.println(Constantes.INGRESE_EL_NUEVO_STOCK_QUE_TENDRA_EL_LA + nomProdMod + Constantes.DOS_PUNTOS);
        nuevoStockProd = sc.nextInt();
        // cambiar solo el stock
        if (serviciosProductos.modificarProductoStock(nomProdMod, nuevoStockProd)) {
            System.out.println(Constantes.SE_MODIFICO_EL_PRODUCTO_AHORA_ES + serviciosProductos.getProducto(nomProdMod).toString());
        } else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_PRODUCTO_EN_NUESTRA_LISTA_DE_PRODUCTOS_O_EL_STOCK_INGRESADO_ES_MENOR_QUE_0_INTENTE_NUEVAMENTE);
        }
    }

    private void modificarPrecioProducto(Scanner sc, String nomProdMod) {
        ServiciosProductos serviciosProductos = new ServiciosProductos();
        double nuevoPrecioProd;
        System.out.println(Constantes.INGRESE_EL_NUEVO_PRECIO_QUE_TENDRA_EL_LA + nomProdMod + Constantes.DOS_PUNTOS);
        nuevoPrecioProd = sc.nextDouble();
        // cambiar solo el precio
        if (serviciosProductos.modificarProductoPrecio(nomProdMod, nuevoPrecioProd)) {
            System.out.println(Constantes.SE_MODIFICO_EL_PRODUCTO_AHORA_ES + serviciosProductos.getProducto(nomProdMod).toString());
        } else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_PRODUCTO_EN_NUESTRA_LISTA_DE_PRODUCTOS_O_EL_PRECIO_INGRESADO_ES_MENOR_QUE_0_INTENTE_NUEVAMENTE);
        }
    }

    private void modificarNombreProducto(Scanner sc, String nomProdMod) {
        ServiciosProductos serviciosProductos = new ServiciosProductos();
        String nuevoNombreProd;
        System.out.println(Constantes.INGRESE_EL_NUEVO_NOMBRE_QUE_TENDRA_EL_LA + nomProdMod + ": ");
        nuevoNombreProd = sc.nextLine();
        if (serviciosProductos.modificarProductoNombre(nomProdMod, nuevoNombreProd)) {
            System.out.println(Constantes.SE_MODIFICO_EL_PRODUCTO_AHORA_ES + serviciosProductos.getProducto(nuevoNombreProd).toString());
        } else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_PRODUCTO_EN_NUESTRA_LISTA_DE_PRODUCTOS_O_EL_NOMBRE_NUEVO_NO_TIENE_UN_VALOR_INTENTE_NUEVAMENTE);
        }
    }

    private void modificarProductoEntero(Scanner sc, String nomProdMod) {
        ServiciosProductos serviciosProductos = new ServiciosProductos();
        double nuevoPrecioProd;
        int nuevoStockProd;
        String nuevoNombreProd;
        System.out.println(Constantes.INGRESE_EL_NUEVO_NOMBRE_QUE_TENDRA_EL_LA + nomProdMod + Constantes.DOS_PUNTOS);
        nuevoNombreProd = sc.nextLine();

        // pedir precio
        System.out.println(Constantes.INGRESE_EL_NUEVO_PRECIO_QUE_TENDRA_EL_LA + nomProdMod + Constantes.DOS_PUNTOS);
        nuevoPrecioProd = sc.nextDouble();
        sc.nextLine();

        // pedir stock
        System.out.println(Constantes.INGRESE_EL_NUEVO_STOCK_QUE_TENDRA_EL_LA + nomProdMod + Constantes.DOS_PUNTOS);
        nuevoStockProd = sc.nextInt();
        sc.nextLine();

        // cambiar
        Producto prodNuevo = new Producto(nuevoNombreProd, nuevoPrecioProd, nuevoStockProd);
        if (serviciosProductos.modificarProducto(prodNuevo, nomProdMod)) {
            System.out.println(Constantes.SE_MODIFICO_EL_PRODUCTO_AHORA_ES + serviciosProductos.getProducto(nuevoNombreProd).toString());
        } else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_PRODUCTO_EN_NUESTRA_LISTA_DE_PRODUCTOS_O_EL_ALGUNO_DE_LOS_CAMPOS_INGRESADOS_ES_INVALIDO_INTENTE_NUEVAMENTE);
        }
    }
}
