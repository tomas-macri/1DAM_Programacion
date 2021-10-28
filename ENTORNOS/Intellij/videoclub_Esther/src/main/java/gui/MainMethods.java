package gui;

import com.github.javafaker.Faker;
import config.Configuration;
import modelo.*;
import servicios.ServiciosVideoclub;

import java.util.Scanner;

public class MainMethods {
    public static void main(String[] args) {
    }

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
    public static final String DIGAME_SU_DNI = "Digame su DNI";

    public boolean salir() {
        boolean seguir;
        System.out.println(Main.GRACIAS_POR_SU_VISITA);
        seguir = false;
        return seguir;
    }

    public void pagarMulta(Scanner sc, ServiciosVideoclub sv) {
        String dni;
        Member member;
        System.out.println(MainMethods.DIGAME_SU_DNI);
        dni = sc.nextLine();
        if (sv.getSocio(dni) != null) {
            member = sv.getSocio(dni);
            if (member.isSancionado()) {
                System.out.println(SU_MULTA_HA_SIDO_PAGADA);
                member.setSancionado(false);
            } else {
                System.out.println(ESTE_USUARIO_NO_TIENE_NINGUNA_MULTA);
            }
        } else {
            System.out.println(ESTE_USUARIO_NO_ESTA_REGISTRADO);
        }
    }

    public void devolver(Scanner sc, ServiciosVideoclub sv) {
        String dni;
        System.out.println(DIGAME_SU_DNI_PARA_PODER_PROCEDER_A_LA_DEVOLUCION);
        dni = sc.nextLine();
        if (sv.getSocio(dni) != null) {
            System.out.println(PARA_PROCEDER_A_LA_DEVOLUCION_NOS_GUSTARIA_SABER_LA_PUNTUACION_DE_0_A_5_QUE_DARIA_AL_PRODUCTO);
            int puntuacion;
            do {
                puntuacion = sc.nextInt();
                sc.nextLine();
            } while (puntuacion < 0 || puntuacion > 5);
            System.out.println(VOLVERIA_A_ALQUILARLO_1_SI_2_NO);
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
                System.out.println(DEVOLUCION_REALIZADA);
            } else {
                System.out.println(NO_TIENE_NINGUN_ALQUILER_CON_NOSOTROS);
            }
            if (sv.getSocio(dni).isSancionado()) {
                System.out.println(HA_SIDO_SANCIONADO_YA_QUE_HA_DEVUELTO_EL_PRODUCTO_CON_RETRASO);
            }
        } else {
            System.out.println(NO_ESTA_REGISTRADO_NO_PUEDE_TENER_NINGUN_ALQUILER_CON_NOSOTROS);
        }
    }

    public int menuProducto(Scanner sc) {
        int opcion;
        do {
            System.out.println(STRING_MENU);
            opcion = sc.nextInt();
            sc.nextLine();
            if (opcion < 1 || opcion > 3) {
                System.out.println(SOLO_TENEMOS_3_TIPOS_DE_PRODUCTOS_DIME_1_2_O_3_POR_FAVOR);
            }
        } while (opcion < 1 || opcion > 3);
        return opcion;
    }


    public void alquilar(Scanner sc, ServiciosVideoclub sv) {
        int opcion;
        String dni;

        int indiceProducto;
        MainMethods mm = new MainMethods();
        opcion = mm.menuProducto(sc);
        Producto productoAAlquilar = null;
        switch (opcion) {
            case 1:
                if(sv.getTodosVideoJuegos().size() > 0) {
                    indiceProducto = mm.elegirVideojuego(sv, sc);
                    productoAAlquilar = sv.getTodosVideoJuegos().get(indiceProducto);
                }else{
                    System.out.println(NO_DISPONEMOS_DE_ARTICULOS_DE_ESTE_TIPO_DISCULPE_LAS_MOLESTIAS);
                }
                break;
            case 2:
                if(sv.getTodosDocumentales().size() > 0) {
                    indiceProducto = mm.elegirDocumental(sv, sc);
                    productoAAlquilar = sv.getTodosDocumentales().get(indiceProducto);
                }else{
                    System.out.println(NO_DISPONEMOS_DE_ARTICULOS_DE_ESTE_TIPO_DISCULPE_LAS_MOLESTIAS);
                }
                break;
            case 3:
                if(sv.getTodasPeliculas().size() > 0) {
                    indiceProducto = mm.elegirPelicula(sv, sc);
                    productoAAlquilar = sv.getTodasPeliculas().get(indiceProducto);
                }else{
                    System.out.println(NO_DISPONEMOS_DE_ARTICULOS_DE_ESTE_TIPO_DISCULPE_LAS_MOLESTIAS);
                }
                break;
        }
        //nif, comprobar: si member alquilo, si multa, si stock producto; sumar a cantidadAlquilada
        if (productoAAlquilar != null) {
            System.out.println(PARA_PROCEDER_AL_ALQUILER_DEL_PRODUCTO_NECESITO_SU_DNI);
            dni = sc.nextLine();
            String alquilado = sv.alquilarProducto(productoAAlquilar, dni);
            System.out.println(alquilado);
            if (alquilado.equals(PRODUCTO_ALQUILADO_CORRECTAMENTE_MUCHAS_GRACIAS)) {
                if (opcion == 1) {
                    System.out.println(RECUERDE_QUE_TIENE + Configuration.getDiasAlquilerVideojuego()
                            + SEGUNDOS_PARA_DEVOLVERLO_SIN_SER_SANCIONADO);
                } else {
                    System.out.println(RECUERDE_QUE_TIENE + Configuration.getDiasAlquilerPeliculas()
                            + SEGUNDOS_PARA_DEVOLVERLO_SIN_SER_SANCIONADO);
                }
            }
        }
    }

    public int elegirVideojuego(ServiciosVideoclub sv, Scanner sc) {
        for (Videogame vj : sv.getTodosVideoJuegos()) {
            System.out.println((sv.getTodosVideoJuegos().indexOf(vj) + 1) + " " + vj.toString());
        }
        MainMethods mm = new MainMethods();
        return mm.elegirProducto(sc, sv.getTodosVideoJuegos().size());
    }

    public int elegirPelicula(ServiciosVideoclub sv, Scanner sc) {
        for (Movie peli : sv.getTodasPeliculas()) {
            System.out.println((sv.getTodasPeliculas().indexOf(peli) + 1) + " " + peli.toString());
        }
        MainMethods mm = new MainMethods()  ;
        return mm.elegirProducto(sc, sv.getTodasPeliculas().size());
    }
    public void addStockProducto(Scanner sc, ServiciosVideoclub sv) {
        int opcion;
        opcion = menuProducto(sc);
        int indiceProducto;
        int cantidadACambiar;
        MainMethods mm = new MainMethods();
        switch (opcion) {
            case 1:
                if (sv.getTodosVideoJuegos().size() > 0) {

                    indiceProducto = mm.elegirVideojuego(sv, sc);
                    System.out.println(MODIFICAR_STOCK);
                    cantidadACambiar = sc.nextInt();
                    sc.nextLine();
                    Producto producto = sv.getTodosVideoJuegos().get(indiceProducto);
                    actualizarStock(cantidadACambiar, producto, sv);
                } else {
                    System.out.println(NO_EXISTEN_PRODUCTOS_DE_ESTE_TIPO_TOOOORPE);
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
                    System.out.println(NO_EXISTEN_PRODUCTOS_DE_ESTE_TIPO_TOOOORPE);
                }
                break;
            case 3:
                if (sv.getTodasPeliculas().size() > 0) {
                    indiceProducto = mm.elegirPelicula(sv, sc);
                    System.out.println(DIME_LA_CANTIDAD_A_MODIFICAR_EN_CASO_DE_SER_UNA_RETIRADA_DE_STOCK_POR_FAVOR_INDICALO_EN_NEGATIVO);
                    cantidadACambiar = sc.nextInt();
                    sc.nextLine();
                    Producto producto = sv.getTodasPeliculas().get(indiceProducto);
                    actualizarStock(cantidadACambiar, producto, sv);
                } else {
                    System.out.println(NO_EXISTEN_PRODUCTOS_DE_ESTE_TIPO_TOOOORPE);
                }
                break;
        }
    }

    public void addProducto(Faker f, Scanner sc, ServiciosVideoclub sv) {
        int opcion;
        MainMethods mm = new MainMethods();
        opcion = menuProducto(sc);
        switch (opcion) {
            case 1:
                Producto vj = new Videogame(f.harryPotter().book(), f.number().numberBetween(1, 10), f.color().name(), f.animal().name());
                mm.registrarProducto(vj, sv);
                break;
            case 2:
                Producto docu = new Documentary(f.harryPotter().book(), f.number().numberBetween(1, 10), f.color().name(), MovieFormat.DVD, f.gameOfThrones().character(), "120min");
                mm.registrarProducto(docu, sv);
                break;
            case 3:
                Producto peli = new Movie(f.harryPotter().book(), f.number().numberBetween(1, 10), f.color().name(), MovieFormat.DVD, f.gameOfThrones().character(), "120min");
                mm.registrarProducto(peli, sv);
                break;
        }
    }

    public void borrarSocio(Scanner sc, ServiciosVideoclub sv) {
        String dni;
        System.out.println(PARA_ELIMINAR_UN_SOCIO_DIGAME_EL_DNI_DE_ESE_SOCIO_POR_FAVOR);
        dni = sc.nextLine();
        if (sv.borrarSocio(dni)) {
            System.out.println(SE_HA_ENCONTRADO_EL_SOCIO_Y_SE_HA_ELIMINADO_DE_NUESTRO_REGISTRO);
        } else {
            System.out.println(ESTE_SOCIO_NO_SE_ENCUENTRA_EN_NUESTRO_SISTEMA);
        }
    }

    public void addSocio(Faker f, ServiciosVideoclub sv) {
        String dni = f.phoneNumber().extension();
        System.out.println("Dni: " + dni);
        String nombre = f.gameOfThrones().character();
        String direccion = f.gameOfThrones().city();
        String tel = f.phoneNumber().toString();
        int edad = f.number().numberBetween(1, 99);
        Member member = new Member(dni, nombre, direccion, tel, edad);
        if (sv.addSocio(member)) {
            System.out.println(SOCIO_REGISTRADO);
        } else {
            System.out.println(ESTE_SOCIO_YA_SE_ENCUENTRA_EN_NUESTRA_BASE_DE_DATOS);
        }
    }

    public void actualizarStock(int cantidadACambiar, Producto producto, ServiciosVideoclub sv) {
        if (cantidadACambiar < 0) {
            if ((cantidadACambiar * (-1)) > producto.getCantidad()) {
                System.out.println(NO_DISPONEMOS_DE_TANTAS_UNIDADES_DE_ESTE_PRODUCTO_REVISE_LOS_DATOS_Y_VUELVA_A_REALIZAR_LA_OPERACION);
            } else {
                sv.actualizarStockProducto(producto, cantidadACambiar);
            }
        } else {
            sv.actualizarStockProducto(producto, cantidadACambiar);
            System.out.println(STOCK_ACTUALIZADO);
        }
        System.out.println(LA_CANTIDAD_AHORA_ES  + producto.getCantidad());
    }

    public void registrarProducto(Producto p, ServiciosVideoclub sv) {
        if (sv.addProducto(p)) {
            System.out.println(PRODUCTO_AÑADIDO_CORRECTAMENTE);
        } else {
            System.out.println(EL_PRODUCTO_NO_SE_HA_PODIDO_AÑADIR);
        }
    }

    public int elegirProducto(Scanner sc, int size) {
        System.out.println(ELIGE_EL_PRODUCTO_DESEADO_DE_LA_LISTA);
        int opcion = 0;
        do {
            opcion = sc.nextInt();
            sc.nextLine();
            if (opcion < 1 || opcion > size) {
                System.out.println(POR_FAVOR_ELIJA_UNA_DE_LAS_OPCIONES_DISPONIBLES);
            }
        } while (opcion < 1 || opcion > size);
        return opcion - 1;
    }

    public int elegirDocumental(ServiciosVideoclub sv, Scanner sc) {
        for (Documentary docu : sv.getTodosDocumentales()) {
            System.out.println((sv.getTodosDocumentales().indexOf(docu) + 1) + " " + docu.toString());
        }
        return elegirProducto(sc, sv.getTodosDocumentales().size());
    }
}
