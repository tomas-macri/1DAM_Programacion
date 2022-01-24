package ui;

import dao.DaoProducto;
import modelo.Producto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DaoProducto daoProducto = new DaoProducto();
        Main main = new Main();
        int opcion;
        System.out.println("Bienvenido administrador");
        System.out.println();

        do {
            opcion = main.mostrarMenu(sc);
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar productos
                    main.agregarProducto(sc, daoProducto);
                    break;
                case 2:
                    // modificar prod
                    do{
                        System.out.println("MODIFICAR PRODUCTOS");
                        System.out.println();
                        System.out.println("Seleccione una opcion:");
                        System.out.println();
                        System.out.println("1 - Modificar un producto entero (nombre, stock y precio)");
                        System.out.println("2 - Modificar el nombre de un producto");
                        System.out.println("3 - Modificar el precio de un producto");
                        System.out.println("4 - Modificar el stock de un producto");
                        System.out.println("5 - Volver al inicio");

                        int opcionModi = sc.nextInt();
                        sc.nextLine();
                        System.out.println();

                        String nomProdMod;
                        do{
                            System.out.println("Ingrese el nombre del producto a modificar. Recuerde que tiene que estar previamente cargado en la lista de productos");
                            nomProdMod = sc.nextLine();
                            //las variables precio y stock no son necesarias a la hora de buscar un producto, por lo que no se le piden al usuario
                        }while (!daoProducto.elProductoExiste(new Producto(nomProdMod)));

                        String nuevoNombreProd;
                        double nuevoPrecioProd;
                        int nuevoStockProd;

                        switch (opcionModi){
                            case 1:

                                // pedir nuevo nombre
                                do{
                                    System.out.println("Ingrese el nuevo nombre que tendra el/la " + nomProdMod +": ");
                                    nuevoNombreProd = sc.nextLine();
                                }while (nuevoNombreProd.equals(""));

                                // pedir precio
                                do{
                                    System.out.println("Ingrese el nuevo precio que tendra el/la " + nomProdMod +": ");
                                    nuevoPrecioProd = sc.nextDouble();
                                }while (nuevoPrecioProd < 0);

                                // pedir stock
                                do {
                                    System.out.println("Ingrese el nuevo stock que tendra el/la " + nomProdMod +": ");
                                    nuevoStockProd = sc.nextInt();
                                }while (nuevoStockProd < 0);

                                // cambiar
                                Producto prodNuevo = new Producto(nuevoNombreProd, nuevoPrecioProd, nuevoStockProd);
                                daoProducto.modificarProducto(prodNuevo, nomProdMod);
                                break;
                            case 2:
                                do{
                                    System.out.println("Ingrese el nuevo nombre que tendra el/la " + nomProdMod +": ");
                                    nuevoNombreProd = sc.nextLine();
                                }while (nuevoNombreProd.equals(""));
                                daoProducto.modificarProductoNombre(nomProdMod, nuevoNombreProd);


                                // pedir nuevo nombre
                                // cambiar solo nombre
                                break;
                            case 3:
                                // pedir nuevo precio
                                do{
                                    System.out.println("Ingrese el nuevo precio que tendra el/la " + nomProdMod +": ");
                                    nuevoPrecioProd = sc.nextDouble();
                                }while (nuevoPrecioProd < 0);
                                // cambiar solo el precio
                                daoProducto.modificarProductoPrecio(nomProdMod, nuevoPrecioProd);
                                break;
                            case 4:
                                // pedir stock
                                do {
                                    System.out.println("Ingrese el nuevo stock que tendra el/la " + nomProdMod +": ");
                                    nuevoStockProd = sc.nextInt();
                                }while (nuevoStockProd < 0);
                                // cambiar solo el stock
                                daoProducto.modificarProductoStock(nomProdMod, nuevoStockProd);
                                break;
                            case 5:
                                break;
                            default:
                                break;
                        }
                    } while (opcion != 5);
                    System.out.println();
                    System.out.println("Saliendo de modificar producto...");
                    System.out.println();
                    break;
                case 3:
                    // eliminar prod
                    main.eliminarProducto(sc, daoProducto);
                    break;
                case 4:
                    // mostrar productos
                    System.out.println(daoProducto);
                    System.out.println();
                    break;
                case 5:
                    // salir
                    System.out.println("Chau!");
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }

    private int mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("Elija una opcion:");
            System.out.println("1 - Agregar Productos");
            System.out.println("2 - Modificar Productos");
            System.out.println("3 - Eliminar Productos");
            System.out.println("4 - Ver todos los productos");
            System.out.println("5 - Salir");
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }

    private void agregarProducto(Scanner sc, DaoProducto dao) {
        System.out.println("AGREGAR PRODUCTOS");
        System.out.println();
        String nomProd;
        int stockProd;
        double precioProd;
        int opcion;
        do {
            do {
                System.out.println("Ingrese nombre del producto:");
                nomProd = sc.nextLine();
            } while (nomProd.equals(""));
            System.out.println();

            do {
                System.out.println("Ingrese precio del producto:");
                precioProd = sc.nextDouble();
                System.out.println();
            } while (precioProd < 0);

            do {
                System.out.println("Ingrese stock del producto:");
                stockProd = sc.nextInt();
                sc.nextLine();
                System.out.println();
            } while (stockProd < 0);


            Producto unProd = new Producto(nomProd, precioProd, stockProd);

            if (dao.agregarProducto(unProd)) {
                System.out.println("Se agrego " + unProd + " a la lista de productos disponibles");
            } else {
                System.out.println("El producto ya se encontraba en la lista de productos, por lo que no se agrego");
            }

            System.out.println();
            System.out.println("Ingrese 1 si quiere agregar otro producto o cualquier otro numero si quiere salir");
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion == 1);
        System.out.println();
        System.out.println("Saliendo de agregar producto...");
        System.out.println();
    }

    private void eliminarProducto(Scanner sc, DaoProducto dao) {
        String nomProducto;
        System.out.println("ELIMINAR PRODUCTOS");
        System.out.println();
        int opcion;
        do {
            System.out.println("Ingrese el nombre del producto que desea eliminar: ");
            nomProducto = sc.nextLine();

            if (dao.eliminarProducto(nomProducto)) {
                System.out.println("Se elimino " + nomProducto + " de la lista de productos");
            } else {
                System.out.println("El producto " + nomProducto + "no se encontraba en la lista de productos, por lo que no se elimino");
            }
            System.out.println();
            System.out.println("Ingrese 1 si quiere continuar o cualquier otro numero si quiere salir");
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion == 1);
        System.out.println();
        System.out.println("Saliendo de eliminar producto...");
        System.out.println();
    }


}
