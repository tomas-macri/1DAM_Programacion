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
    public static final String ERROR_MENU = "Por favor, dime una de las opciones del menu." +
            "Vuelvo a mostrartelo.";
    public static final String MODIFICAR_STOCK = "Dime la cantidad a modificar. En caso de ser una retirada de Stock, por favor indicalo en negativo";

    public static void main(String[] args) {
        Faker f = new Faker();
        Scanner sc = new Scanner(System.in);
        ServiciosVideoclub sv = new ServiciosVideoclub();
        //menu
        int opcion = 0;
        boolean seguir = true;
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
                    addSocio(f, sv);
                    Member member;
                    String dni;
                    break;
                case 2:
                    //2. borrarSocio
                    borrarSocio(sc, sv);
                    break;
                case 3:
                    //3. addProducto
                    addProducto(f, sc, sv);
                    break;
                case 4:
                    //4. AddStockProducto
                    addStockProducto(sc, sv);
                    break;
                case 5:
                    //5. Alquilar
                    //encontrar producto,
                    alquilar(sc, sv);
                    break;
                case 6:
                    //6. Devolver
                    devolver(sc, sv);
                    break;
                case 7:
                    //7. pagarMulta
                    pagarMulta(sc, sv);
                    break;
                case 8:
                    seguir = salir();
                    break;
            }
        } while (seguir);
    }

    private static boolean salir() {
        boolean seguir;
        System.out.println("\n GRACIAS POR SU VISITA");
        seguir = false;
        return seguir;
    }

    private static void pagarMulta(Scanner sc, ServiciosVideoclub sv) {
        String dni;
        Member member;
        System.out.println("Digame su DNI");
        dni = sc.nextLine();
        if (sv.getSocio(dni) != null) {
            member = sv.getSocio(dni);
            if (member.isSancionado()) {
                System.out.println("Su multa ha sido pagada");
                member.setSancionado(false);
            } else {
                System.out.println("Este usuario no tiene ninguna multa");
            }
        } else {
            System.out.println("Este usuario no esta registrado");
        }
    }

    private static void devolver(Scanner sc, ServiciosVideoclub sv) {
        String dni;
        System.out.println("Digame su DNI para poder proceder a la devolucion");
        dni = sc.nextLine();
        if (sv.getSocio(dni) != null) {
            System.out.println("Para proceder a la devolucion nos gustaria saber.\n" +
                    "La puntuacion de 0 a 5 que daria al producto");
            int puntuacion;
            do {
                puntuacion = sc.nextInt();
                sc.nextLine();
            } while (puntuacion < 0 || puntuacion > 5);
            System.out.println("¿Volveria a alquilarlo?\n" +
                    "1. Si\n" +
                    "2. No");
            int respuesta;
            do {
                respuesta = sc.nextInt();
                sc.nextLine();
            } while (respuesta < 1 || respuesta > 2);
            boolean realquilar;
            if (respuesta == 1) {
                realquilar = true;
            } else {
                realquilar = false;
            }
            Poll poll = new Poll(puntuacion, realquilar);
            //Servicios -> mirarfecha para multa,sacarAlquilerSocio, acctualizar producto cantidadAlquilada, addEncuestaAProducto
            if (sv.devolverProducto(dni, poll)) {
                System.out.println("Devolucion realizada");
            } else {
                System.out.println("No tiene ningun alquiler con nosotros");
            }
            if (sv.getSocio(dni).isSancionado()) {
                System.out.println("Ha sido sancionado ya que ha devuelto el producto con retraso");
            }
        } else {
            System.out.println("No esta registrado, no puede tener ningun alquiler con nosotros");
        }
    }

    private static void alquilar(Scanner sc, ServiciosVideoclub sv) {
        int opcion;
        String dni;
        int indiceProducto;
        opcion = menuProducto(sc);
        Producto productoAAlquilar = null;
        switch (opcion) {
            case 1:
                if(sv.getTodosVideoJuegos().size() > 0) {
                    indiceProducto = elegirVideojuego(sv, sc);
                    productoAAlquilar = sv.getTodosVideoJuegos().get(indiceProducto);
                }else{
                    System.out.println("No disponemos de articulos de este tipo. \n" +
                            "Disculpe las molestias.");
                }
                break;
            case 2:
                if(sv.getTodosDocumentales().size() > 0) {
                    indiceProducto = elegirDocumental(sv, sc);
                    productoAAlquilar = sv.getTodosDocumentales().get(indiceProducto);
                }else{
                    System.out.println("No disponemos de articulos de este tipo. \n" +
                            "Disculpe las molestias.");
                }
                break;
            case 3:
                if(sv.getTodasPeliculas().size() > 0) {
                    indiceProducto = elegirPelicula(sv, sc);
                    productoAAlquilar = sv.getTodasPeliculas().get(indiceProducto);
                }else{
                    System.out.println("No disponemos de articulos de este tipo. \n" +
                            "Disculpe las molestias.");
                }
                break;
        }
        //nif, comprobar: si member alquilo, si multa, si stock producto; sumar a cantidadAlquilada
        if (productoAAlquilar != null) {
            System.out.println("Para proceder al alquiler del producto necesito su DNI");
            dni = sc.nextLine();
            String alquilado = sv.alquilarProducto(productoAAlquilar, dni);
            System.out.println(alquilado);
            if (alquilado.equals("Producto alquilado correctamente\n" +
                    "MUCHAS GRACIAS")) {
                if (opcion == 1) {
                    System.out.println("Recuerde que tiene " + Configuration.getDiasAlquilerVideojuego()
                            + " segundos para devolverlo sin ser sancionado.");
                } else {
                    System.out.println("Recuerde que tiene " + Configuration.getDiasAlquilerPeliculas()
                            + " segundos para devolverlo sin ser sancionado.");
                }
            }
        }
    }

    private static void addStockProducto(Scanner sc, ServiciosVideoclub sv) {
        int opcion;
        opcion = menuProducto(sc);
        int indiceProducto;
        int cantidadACambiar;
        switch (opcion) {
            case 1:
                if (sv.getTodosVideoJuegos().size() > 0) {
                    indiceProducto = elegirVideojuego(sv, sc);
                    System.out.println(MODIFICAR_STOCK);
                    cantidadACambiar = sc.nextInt();
                    sc.nextLine();
                    Producto producto = sv.getTodosVideoJuegos().get(indiceProducto);
                    actualizarStock(cantidadACambiar, producto, sv);
                } else {
                    System.out.println("No existen productos de este tipo, toooorpe");
                }
                break;
            case 2:
                if (sv.getTodosDocumentales().size() > 0) {
                    indiceProducto = elegirDocumental(sv, sc);
                    System.out.println(MODIFICAR_STOCK);
                    cantidadACambiar = sc.nextInt();
                    sc.nextLine();
                    Producto producto = sv.getTodosDocumentales().get(indiceProducto);
                    actualizarStock(cantidadACambiar, producto, sv);
                } else {
                    System.out.println("No existen productos de este tipo, toooorpe");
                }
                break;
            case 3:
                if (sv.getTodasPeliculas().size() > 0) {
                    indiceProducto = elegirPelicula(sv, sc);
                    System.out.println("Dime la cantidad a modificar. En caso de ser una retirada de Stock, por favor indicalo en negativo");
                    cantidadACambiar = sc.nextInt();
                    sc.nextLine();
                    Producto producto = sv.getTodasPeliculas().get(indiceProducto);
                    actualizarStock(cantidadACambiar, producto, sv);
                } else {
                    System.out.println("No existen productos de este tipo, toooorpe");
                }
                break;
        }
    }

    private static void addProducto(Faker f, Scanner sc, ServiciosVideoclub sv) {
        int opcion;
        opcion = menuProducto(sc);
        switch (opcion) {
            case 1:
                Producto vj = new Videogame(f.harryPotter().book(), f.number().numberBetween(1, 10), f.color().name(), f.animal().name());
                registrarProducto(vj, sv);
                break;
            case 2:
                Producto docu = new Documentary(f.harryPotter().book(), f.number().numberBetween(1, 10), f.color().name(), MovieFormat.DVD, f.gameOfThrones().character(), "120min");
                registrarProducto(docu, sv);
                break;
            case 3:
                Producto peli = new Movie(f.harryPotter().book(), f.number().numberBetween(1, 10), f.color().name(), MovieFormat.DVD, f.gameOfThrones().character(), "120min");
                registrarProducto(peli, sv);
                break;
        }
    }

    private static void borrarSocio(Scanner sc, ServiciosVideoclub sv) {
        String dni;
        System.out.println("Para eliminar un socio digame el DNI de ese socio, por favor");
        dni = sc.nextLine();
        if (sv.borrarSocio(dni)) {
            System.out.println("Se ha encontrado el socio y se ha eliminado de nuestro registro");
        } else {
            System.out.println("Este socio no se encuentra en nuestro sistema");
        }
    }

    private static void addSocio(Faker f, ServiciosVideoclub sv) {
        String dni = f.phoneNumber().extension();
        System.out.println("Dni: " + dni);
        String nombre = f.gameOfThrones().character();
        String direccion = f.gameOfThrones().city();
        String tel = f.phoneNumber().toString();
        int edad = f.number().numberBetween(1, 99);
        Member member = new Member(dni, nombre, direccion, tel, edad);
        if (sv.addSocio(member)) {
            System.out.println("Socio registrado");
        } else {
            System.out.println("Este socio ya se encuentra en nuestra base de datos");
        }
    }

    private static void actualizarStock(int cantidadACambiar, Producto producto, ServiciosVideoclub sv) {
        if (cantidadACambiar < 0) {
            if ((cantidadACambiar * (-1)) > producto.getCantidad()) {
                System.out.println("No disponemos de tantas unidades de este producto, " +
                        "revise los datos y vuelva a realizar la operacion");
            } else {
                sv.actualizarStockProducto(producto, cantidadACambiar);
            }
        } else {
            sv.actualizarStockProducto(producto, cantidadACambiar);
            System.out.println("Stock actualizado");
        }
        System.out.println("La cantidad ahora es: " + producto.getCantidad());
    }

    private static void registrarProducto(Producto p, ServiciosVideoclub sv) {
        if (sv.addProducto(p)) {
            System.out.println("Producto añadido correctamente");
        } else {
            System.out.println("El producto no se ha podido añadir");
        }
    }

    private static int elegirProducto(Scanner sc, int size) {
        System.out.println("Elige el producto deseado de la lista");
        int opcion = 0;
        do {
            opcion = sc.nextInt();
            sc.nextLine();
            if (opcion < 1 || opcion > size) {
                System.out.println("Por favor, elija una de las opciones disponibles");
            }
        } while (opcion < 1 || opcion > size);
        return opcion - 1;
    }

    private static int menuProducto(Scanner sc) {
        int opcion;
        do {
            System.out.println("Seleccione el tipo de producto:\n" +
                    "1. Videogame\n" +
                    "2. Documentary\n" +
                    "3. Movie");
            opcion = sc.nextInt();
            sc.nextLine();
            if (opcion < 1 || opcion > 3) {
                System.out.println("Solo tenemos 3 tipos de productos, dime 1, 2 o 3, por favor.");
            }
        } while (opcion < 1 || opcion > 3);
        return opcion;
    }

    private static int elegirVideojuego(ServiciosVideoclub sv, Scanner sc) {
        for (Videogame vj : sv.getTodosVideoJuegos()) {
            System.out.println((sv.getTodosVideoJuegos().indexOf(vj) + 1) + " " + vj.toString());
        }
        return elegirProducto(sc, sv.getTodosVideoJuegos().size());
    }

    private static int elegirDocumental(ServiciosVideoclub sv, Scanner sc) {
        for (Documentary docu : sv.getTodosDocumentales()) {
            System.out.println((sv.getTodosDocumentales().indexOf(docu) + 1) + " " + docu.toString());
        }
        return elegirProducto(sc, sv.getTodosDocumentales().size());
    }

    private static int elegirPelicula(ServiciosVideoclub sv, Scanner sc) {
        for (Movie peli : sv.getTodasPeliculas()) {
            System.out.println((sv.getTodasPeliculas().indexOf(peli) + 1) + " " + peli.toString());
        }
        return elegirProducto(sc, sv.getTodasPeliculas().size());
    }

}
