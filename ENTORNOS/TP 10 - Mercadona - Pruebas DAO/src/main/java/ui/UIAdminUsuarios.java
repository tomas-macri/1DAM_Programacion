package ui;

import jakarta.inject.Inject;
import modelo.*;
import servicios.ServiciosUsuarios;
import ui.common.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UIAdminUsuarios {
    private ServiciosUsuarios serviciosUsuarios;

    @Inject
    public UIAdminUsuarios(ServiciosUsuarios serviciosUsuarios){
        this.serviciosUsuarios = serviciosUsuarios;
    }



    public void inicioUIUsuarios() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        System.out.println(Constantes.BIENVENIDO_ADMINISTRADOR);
        System.out.println();

        do {
            opcion = mostrarMenu(sc);
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar usuarios
                    agregarUsuario(sc);
                    break;
                case 2:
                    // modificar prod
                    modificarUsuario(sc);
                    break;
                case 3:
                    // eliminar prod
                    eliminarUsuario(sc);
                    break;
                case 4:
                    // mostrar usuarios
                    System.out.println(serviciosUsuarios.getLista());
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
            System.out.println(Constantes.MENU_ADMINISTRADOR_USUARIOS);

            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }

    private void agregarUsuario(Scanner sc) {

        System.out.println(Constantes.AGREGAR_USUARIO);
        System.out.println();
        String nomCliente;
        String dniCliente;
        int opcion;
        do {
            System.out.println(Constantes.INGRESE_EL_DNI_DEL_USUARIO);
            dniCliente = sc.nextLine();
            System.out.println();

            System.out.println(Constantes.INGRESE_EL_NOMBRE_DEL_USUARIO);
            nomCliente = sc.nextLine();
            System.out.println();

            List<Ingrediente> ingredienteList = cargarListIngredientes(sc);

            int porcentaje;
            System.out.println("Ingrese el porcentaje de descuento que tendrá el cliente si quiere que sea especial o 0 para no aplicar nada:");
            porcentaje = sc.nextInt();
            sc.nextLine();

            Usuario unUser;
            if (porcentaje <= 0) {
                unUser = new Usuario(dniCliente, nomCliente, ingredienteList);
            } else {
                unUser = new UsuarioEspecial(dniCliente, nomCliente, ingredienteList, porcentaje);
            }

            if (serviciosUsuarios.agregarUsuario(unUser)) {
                System.out.println(Constantes.SE_AGREGO + unUser + Constantes.A_LA_LISTA_DE_USUARIOS_DISPONIBLES);
            } else {
                System.out.println(Constantes.EL_USUARIO_YA_SE_ENCONTRABA_EN_LA_LISTA_DE_USUARIOS_O_ALGUNO_DE_SUS_CAMPOS_NO_ERAN_VALIDOS_INTENTE_NUEVAMENTE);
            }
            System.out.println(Constantes.INGRESE_1_SI_QUIERE_AGREGAR_OTRO_USUARIO_O_CUALQUIER_OTRO_NUMERO_SI_QUIERE_SALIR);
            opcion = sc.nextInt();
            sc.nextLine();

        } while (opcion == 1);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_AGREGAR_USUARIO);
        System.out.println();
    }


    private List<Ingrediente> cargarListIngredientes(Scanner sc) {
        String ingrediente;
        List<Ingrediente> ingredienteList = new ArrayList<>();
        do {
            System.out.println("Ingrese un ingrediente al que es alergico o fin para finalizar");
            ingrediente = sc.nextLine();
            if (!ingrediente.equalsIgnoreCase(Constantes.FIN)) {
                ingredienteList.add(new Ingrediente(ingrediente));
            }
        } while (!ingrediente.equalsIgnoreCase(Constantes.FIN));
        return ingredienteList;
    }

    private void modificarUsuario(Scanner sc) {
        int opcionModi;
        do {
            do {
                System.out.println();
                System.out.println(Constantes.MODIFICAR_USUARIOS);
                System.out.println();
                System.out.println(Constantes.MENU_ADMINISTRADOR_MODIFICAR_USUARIOS);

                opcionModi = sc.nextInt();
                sc.nextLine();
                System.out.println();
            } while (opcionModi < 1 || opcionModi > 4);
            switchModificar(sc, opcionModi);


        } while (opcionModi != 4);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_MODIFICAR_USUARIO);
        System.out.println();
    }

    private void switchModificar(Scanner sc, int opcionModi) {
        String nomProdMod;
        if (opcionModi != 4) {
            System.out.println(Constantes.INGRESE_EL_DNI_DEL_USUARIO_A_MODIFICAR_RECUERDE_QUE_TIENE_QUE_ESTAR_PREVIAMENTE_CARGADO_EN_LA_LISTA_DE_USUARIOS_PARA_VOLVER_INGRESE_0);
            nomProdMod = sc.nextLine();
            if (!nomProdMod.equals("0")) {
                switch (opcionModi) {
                    case 1:
                        modificarUsuarioEntero(sc, nomProdMod);
                        break;
                    case 2:
                        modificarDNIUsuario(sc, nomProdMod);
                        break;
                    case 3:
                        modificarNombreUsuario(sc, nomProdMod);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void modificarDNIUsuario(Scanner sc, String dniMod) {
        String nuevoDNI;
        System.out.println(Constantes.INGRESE_EL_NUEVO_DNI_QUE_TENDRA_EL_USUARIO + dniMod + Constantes.DOS_PUNTOS);
        nuevoDNI = sc.nextLine();
        // cambiar solo el dni
        if (serviciosUsuarios.modificarUsuarioDNI(dniMod, nuevoDNI)) {
            System.out.println(Constantes.SE_MODIFICO_EL_USUARIO_AHORA_ES + serviciosUsuarios.getUsuarioString(nuevoDNI));
        } else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_USUARIO_EN_NUESTRA_LISTA_DE_USUARIOS_O_SE_INTENTO_CAMBIAR_POR_UN_DNI_DE_UN_USUARIO_QUE_YA_ESTA_EN_LA_LISTA_INTENTE_NUEVAMENTE);
        }
    }

    private void modificarNombreUsuario(Scanner sc, String dniMod) {
        String nuevoNombreProd;
        System.out.println(Constantes.INGRESE_EL_NUEVO_NOMBRE_QUE_TENDRA_EL_USUARIO + dniMod + Constantes.DOS_PUNTOS);
        nuevoNombreProd = sc.nextLine();
        if (serviciosUsuarios.modificarUsuarioNombre(dniMod, nuevoNombreProd, serviciosUsuarios.getUsuario(dniMod).getIngredienteList())) {
            System.out.println(Constantes.SE_MODIFICO_EL_USUARIO_AHORA_ES + serviciosUsuarios.getUsuarioString(dniMod));
        } else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_USUARIO_EN_NUESTRA_LISTA_DE_USUARIOS_O_EL_NOMBRE_NUEVO_NO_TIENE_UN_VALOR_INTENTE_NUEVAMENTE);
        }
    }

    private void modificarUsuarioEntero(Scanner sc, String dniMod) {
        String nuevoDNIUsuario;
        String nuevoNombreUsuario;

        System.out.println(Constantes.INGRESE_EL_NUEVO_DNI_QUE_TENDRA_EL_USUARIO + dniMod + Constantes.DOS_PUNTOS);
        nuevoDNIUsuario = sc.nextLine();

        System.out.println(Constantes.INGRESE_EL_NUEVO_NOMBRE_QUE_TENDRA_EL_USUARIO + dniMod + Constantes.DOS_PUNTOS);
        nuevoNombreUsuario = sc.nextLine();

        List<Ingrediente> ingredienteList = cargarListIngredientes(sc);

        // cambiar
        Usuario userNuevo = new Usuario(nuevoDNIUsuario, nuevoNombreUsuario, ingredienteList);
        if (serviciosUsuarios.modificarUsuario(userNuevo, dniMod)) {
            System.out.println(Constantes.SE_MODIFICO_EL_USUARIO_AHORA_ES + serviciosUsuarios.getUsuarioString(nuevoDNIUsuario));
        } else {
            System.out.println(Constantes.ERROR_BUSQUEDA_Y_MODIFICACION_USUARIOS);
        }
    }

    private void eliminarUsuario(Scanner sc) {
        String dniUsuario;
        System.out.println(Constantes.ELIMINAR_USUARIOS);
        System.out.println();
        int opcion;
        do {
            System.out.println(Constantes.INGRESE_EL_DNI_DEL_USUARIO_QUE_DESEA_ELIMINAR);
            dniUsuario = sc.nextLine();

            Usuario userEliminado = serviciosUsuarios.eliminarUsuario(dniUsuario);

            if (userEliminado != null) {
                System.out.println(Constantes.SE_ELIMINO + userEliminado + Constantes.DE_LA_LISTA_DE_USUARIOS);
            } else {
                System.out.println(Constantes.EL_USUARIO_CON_DNI + dniUsuario + Constantes.NO_SE_ENCONTRABA_EN_LA_LISTA_DE_USUARIOS_POR_LO_QUE_NO_SE_ELIMINO);
            }
            System.out.println();
            System.out.println(Constantes.INGRESE_1_SI_QUIERE_ELIMINAR_OTRO_USUARIO_O_CUALQUIER_OTRO_NUMERO_SI_QUIERE_SALIR);
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion == 1);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_ELIMINAR_USUARIOS);
        System.out.println();
    }
}
